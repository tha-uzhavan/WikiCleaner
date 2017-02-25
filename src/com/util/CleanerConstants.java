package com.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.regex.Pattern;

public class CleanerConstants {

	public static final String PIPE = "\\|";

	public static final String BOTAXOBOX = "{{Taxobox";
	public static final String EOTAXOBOX = "\\|?}}";
	public static final String REF_REGX = "<ref.*>.*?</ref>";
	public static final String NUMBER_REGX = ".*?[0-9]{4}.*";
	public static final String NONWORD_REGX = "(\\W)+";
	public static final String SPECIAL_REGX = "#:-";
	public static final Pattern pattern = Pattern.compile("[\\[\\[](.*?)[]]]");
	public static final CharsetEncoder encode = Charset.forName("US-ASCII")
			.newEncoder();
}
