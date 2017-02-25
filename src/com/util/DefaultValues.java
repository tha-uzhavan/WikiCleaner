package com.util;

public class DefaultValues {

	public static final String comment = Default.COMMENT.getDefaultValue();
	public static final int THROTTLE = new Integer(
			Default.THROTTLE.getDefaultValue()).intValue();
	public static int COUNT = new Integer(
			Default.COUNTOF_CHANES.getDefaultValue()).intValue();
	public static final String DOMAIN = Default.DOMAIN.getDefaultValue();
	public static final String PREVIEW = Default.PREVIEW.getDefaultValue();
	public static final String BOT = Default.BOT.getDefaultValue();
}

enum Default {

	USERNAME("username", "WikiUserName"), PASSWORD("password", "WikiPassword"), TITLE_FILE(
			"sourcefile", "/path/to/file.properties"),

	COMMENT("comments", " - Garbage cleaning "), THROTTLE("throttle", "60000"), COUNTOF_CHANES(
			"changecount", "0"), DOMAIN("domain", "ta.wikipedia.org"), USER_AGENT(
			"useragent",
			"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:47.0) Gecko/20100101 Firefox/47.0"), PREVIEW(
			"preview", "N"), BOT("bot", "false");

	private String key;
	private String defaultValue;

	private Default(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String getKey() {
		return key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}
