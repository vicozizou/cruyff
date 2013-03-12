package com.bytepoxic.backdoor;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.bytepoxic.backdoor.bean.BackDoorBean;

public class MainBackDoorSuit {
	private static final String APP_USAGE = "Usage: java com.aureabox.model.backdoor.MainBackDoorSuite <input data location>";
	private static final String INPUT_DATA_LOCATION = "inputDataLocation";
	private static Log logger = LogFactory.getLog(MainBackDoorSuit.class);

	private File inputDataLocation;
	private List<BackDoorBean> backDoors;
	private ClassPathXmlApplicationContext context;

	public MainBackDoorSuit(Hashtable<String, String> argMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing suite...");
		}

		inputDataLocation = new File(argMap.get(INPUT_DATA_LOCATION));
		backDoors = new ArrayList<BackDoorBean>();
		String[] locations = new String[] { "classpath:backdoor/backDoorContext.xml" };
		logger.info(String.format("Loading Spring context at %s", locations[0]));
		context = new ClassPathXmlApplicationContext(locations);
	}

	private void runBackDoors() {
		for (BackDoorBean bean : backDoors) {
			bean.runBackDoor(inputDataLocation);
		}
	}

	private void loadBackDoors() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("backdoor.backDoor");
		String[] backDoorBeans = StringUtils.tokenizeToStringArray(bundle.getString("backDoor.beans"), BackDoorBean.DELIMITER_CHAR);

		if (backDoorBeans != null && backDoorBeans.length > 0) {
			for (String beanName : backDoorBeans) {
				try {
					backDoors.add((BackDoorBean) context.getBean(beanName));

					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Bean %s found", beanName));
					}
				} catch (BeansException e) {
					logger.warn(String.format("Could not found bean %s", beanName));
				}
			}
		} else {
			logger.info("No back door classes found");
		}
	}

	private void unload() {
		inputDataLocation = null;
		backDoors.clear();
		backDoors = null;
		context.destroy();
		context = null;
	}

	public static void main(String[] args) {
		Hashtable<String, String> argMap = parseArgs(args);
		MainBackDoorSuit suit = new MainBackDoorSuit(argMap);
		suit.loadBackDoors();
		suit.runBackDoors();
		suit.unload();
	}

	private static Hashtable<String, String> parseArgs(String[] args) {
		Hashtable<String, String> parsed = new Hashtable<String, String>();

		if (args.length == 0) {
			throw new IllegalArgumentException(String.format("Wrong number of arguments", APP_USAGE));
		}

		parsed.put(INPUT_DATA_LOCATION, args[0]);
		return parsed;
	}

}
