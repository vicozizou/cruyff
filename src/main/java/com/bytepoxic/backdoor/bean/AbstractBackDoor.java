package com.aureabox.backdoor.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.aureabox.backdoor.throwing.BackDoorException;

public abstract class AbstractBackDoor implements BackDoorBean {
	protected static Log logger = LogFactory.getLog(AbstractBackDoor.class);
	protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private String[] fileNames;
	private List<File> dataFiles = new ArrayList<File>();
	private LineNumberReader dataReader;

	protected BackDoorBean current;
	protected Object state;

	protected abstract void handleComment(String line);

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public String getDelimiter() {
		return DELIMITER_CHAR;
	}

	public void loadData(File inputLocation) {
		if (inputLocation != null && inputLocation.exists()) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Location %s found as input data", inputLocation.getAbsolutePath()));
			}

			if (fileNames == null || fileNames.length == 0) {
				logger.error(String.format("Could not find any files at location %s", inputLocation));
				return;
			}

			File[] contents = inputLocation.listFiles();

			for (String fileName : fileNames) {
				for (File file : contents) {
					if (fileName.equals(file.getName())) {
						if (logger.isDebugEnabled()) {
							logger.debug(String.format("Found data file %s", file.getAbsolutePath()));
						}

						dataFiles.add(file);
						break;
					}
				}
			}
		} else {
			logger.error("Input data file is not defined or it doesn't exist");
		}
	}

	public boolean canRead() {
		try {
			return dataReader != null && dataReader.ready();
		} catch (IOException e) {
			logger.error("Not ready to read", e);
			return false;
		}
	}

	public void runBackDoor(File dataLocation) {
		loadData(dataLocation);

		for (File dataFile : dataFiles) {
			try {
				dataReader = new LineNumberReader(new FileReader(dataFile));

				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Reading file %s", dataFile.getAbsolutePath()));
				}
			} catch (FileNotFoundException e) {
				dataReader = null;
				logger.error(String.format("Cannot read file %s", dataFile.getAbsolutePath()), e);
			}

			if (canRead()) {
				try {
					String line = null;
					state = null;
					current = this;

					while ((line = dataReader.readLine()) != null) {
						if (logger.isDebugEnabled()) {
							logger.debug(String.format("Processing line %s", line));
						}

						if (isComment(line)) {
							if (logger.isDebugEnabled()) {
								logger.debug(String.format("Comment flag detected at line %s: %s", dataReader.getLineNumber(), line));
							}

							handleComment(line);
							continue;
						}

						try {
							processLine(split(line));
						} catch (BackDoorException e) {
							logger.error(String.format("Error persisting object at line %s", dataReader.getLineNumber()), e);
						}
					}
				} catch (IOException e) {
					logger.error(String.format("Error reading line #%s", dataReader.getLineNumber()), e);
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Closing data reader");
			}

			try {
				dataReader.close();
			} catch (IOException e) {
				logger.error("Could not close reader, keep walking", e);
			}

			dataReader = null;
		}
	}

	private String[] split(String line) {
		if (!line.endsWith(BLANK_SPACE_CHAR)) {
			line += BLANK_SPACE_CHAR;
		}

		String[] splitted = line.split(getDelimiter());

		for (int i = 0; i < splitted.length; i++) {
			String value = splitted[i].trim();
			splitted[i] = StringUtils.hasText(value) ? value : null;
		}

		return splitted;
	}

	protected boolean isComment(String line) {
		return line.startsWith(COMMENT_CHAR);
	}

	protected Long convertId(String value) {
		try {
			return Long.valueOf(value);
		} catch (NumberFormatException e) {
			logger.warn(String.format("Invalid id format in value %s", value));
			return null;
		}
	}

	protected String extractCommand(String line) {
		int index = line.indexOf(":");

		if (index < 0) {
			return null;
		}

		return line.substring(1, index);
	}

	protected boolean canProcess(String[] values) {
		if (values == null || values.length != current.getFieldsCount()) {
			logger.error(String.format("Fields in line #%s is empty or doesn't have required number of fields (%s)", dataReader.getLineNumber(), current.getFieldsCount()));
			return false;
		}

		return true;
	}

	protected void reset() {
		state = null;
	}

	protected boolean isReset() {
		return state == null;
	}

	protected boolean isMainBean() {
		return current == this;
	}
}
