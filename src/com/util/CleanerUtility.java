package com.util;

import static com.util.ErrorMessage.INVALID_CMD_ARG;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dto.Configurations;
import com.dto.IConfigurations;

public class CleanerUtility {

	/**
	 * Writes content to a given File in Append mode
	 * 
	 * @param responseFile
	 * @param status
	 * @throws IOException
	 */
	public static void appendFile(File responseFile, String status)
			throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(responseFile,
				true));
		writer.append(status + System.lineSeparator());
		writer.close();
	}

	/**
	 * Create Temp Response file which will have status of title
	 * 
	 * @param titleFile
	 * @return
	 * @throws IOException
	 */
	public static File createTempFile(File parentFolder) throws IOException {

		File result = new File(parentFolder.getParentFile(),
				"WikiTitleEditor.txt");

		if (!result.exists()) {
			boolean status = result.createNewFile();
			System.out.println("TEMP FILE CREATED : " + status);
		}

		return result;
	}

	/**
	 * Checks file exist or not
	 */
	public static boolean checkFile(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return false;
		}

		File file = new File(fileName);
		return file.exists();
	}

	/**
	 * Reads config file and set in Configurations object
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static IConfigurations getConfig(String... args) throws Exception {

		// Expects one argument, which is a props file
		if (args == null || args.length != 1 || args[0].isEmpty()) {
			throw new Exception(INVALID_CMD_ARG + System.lineSeparator()
					+ ErrorMessage.README);
		}

		File file = new File(args[0]);

		if (!file.exists()) {
			throw new Exception(INVALID_CMD_ARG + System.lineSeparator()
					+ ErrorMessage.README);
		}

		Properties props = new Properties();
		props.load(new FileReader(file));

		Configurations config = new Configurations();

		config.setUserName(props.getProperty(Default.USERNAME.getKey(), null));
		config.setPassword(props.getProperty(Default.PASSWORD.getKey(), null));
		config.setSourceFile(props.getProperty(Default.TITLE_FILE.getKey(),
				null));

		config.setComments(props.getProperty(Default.COMMENT.getKey(),
				DefaultValues.comment));
		config.setDomain(props.getProperty(Default.DOMAIN.getKey(),
				DefaultValues.DOMAIN));
		config.setPreview(props.getProperty(Default.PREVIEW.getKey(),
				DefaultValues.PREVIEW));
		config.setUserAgent(props.getProperty(Default.USER_AGENT.getKey(),
				Default.USER_AGENT.getDefaultValue()));

		String throttle = props.getProperty(Default.THROTTLE.getKey(),
				String.valueOf(DefaultValues.THROTTLE));
		String count = props.getProperty(Default.COUNTOF_CHANES.getKey(),
				String.valueOf(DefaultValues.COUNT));

		config.setThrottle(parseInt(throttle));
		config.setNumberOfChanges(parseInt(count));

		config.setBot(new Boolean(props.getProperty(DefaultValues.BOT,
				Default.BOT.getDefaultValue())));

		System.out.println(config);
		return config;
	}

	public static int parseInt(String text) {
		int numeric = 0;

		try {
			numeric = Integer.parseInt(text);
		} catch (Exception e) {
			System.err.println("Failed parsing : " + text + " -> integer");
		}

		return numeric;
	}

	public static List<String> readLines(File file) throws IOException {
		List<String> list = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}

		reader.close();

		return list;
	}
}
