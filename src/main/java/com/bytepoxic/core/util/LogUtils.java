package com.aureabox.webcore.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.StringUtils;

public class LogUtils {
	private static final String DEFAULT_DELIM = System.getProperty("line.separator", "\n");

	public static String formatArray(Object[] items, String delimiter) {
		StringBuilder sb = new StringBuilder();
		if (items != null && items.length > 0) {
			sb.append(DEFAULT_DELIM).append(StringUtils.arrayToDelimitedString(items, delimiter));
		} else {
			sb.append(" none");
		}
		return sb.toString();
	}

	public static String formatArray(Object[] items) {
		return formatArray(items, DEFAULT_DELIM);
	}

	public static String formatCollection(Collection<?> items, String delimiter) {
		StringBuilder sb = new StringBuilder();
		if (items != null && !items.isEmpty()) {
			sb.append(DEFAULT_DELIM).append(StringUtils.collectionToDelimitedString(items, delimiter));
		} else {
			sb.append(" none");
		}
		return sb.toString();
	}

	public static String formatCollection(Collection<?> items) {
		return formatCollection(items, DEFAULT_DELIM);
	}

	public static String formatMap(Map<?, ?> items, String delimiter) {
		StringBuilder sb = new StringBuilder();
		if (items != null && !items.isEmpty()) {
			for (Object key : items.keySet()) {
				Object value = items.get(key);
				sb.append(DEFAULT_DELIM).append(key).append(" -> ").append(value instanceof Collection ? formatCollection((Collection<?>) value, ",") : value);
			}
		} else {
			sb.append(" none");
		}
		return sb.toString();
	}

	public static String formatMap(Map<?, ?> items) {
		return formatMap(items, DEFAULT_DELIM);
	}
}
