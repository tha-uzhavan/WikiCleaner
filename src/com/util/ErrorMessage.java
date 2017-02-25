package com.util;

public class ErrorMessage {

	public static final String README = "Please use the bot like given Below\n"
			+ "Usage : java -jar jarname.jar /path/to/prop/file.properties\n"
			+ "properties file should contain below mentioned properties\n"
			+ "###################################\n" + getPropsFormat()
			+ "###################################\n";

	/**
	 * Return all the required props as text
	 * 
	 * @return
	 */
	private static String getPropsFormat() {
		StringBuilder builder = new StringBuilder();

		Default[] defaults = Default.values();

		for (Default item : defaults) {
			builder.append(item.getKey());
			builder.append("=");
			builder.append(item.getDefaultValue());
			builder.append(System.lineSeparator());
		}

		return builder.toString();
	}

	public static final String INVALID_CMD_ARG = "Invalid command line argument,"
			+ " please input valid properties file name";

	public static final String TMP_FILE_FAILED = "failed to create response file..";
}
