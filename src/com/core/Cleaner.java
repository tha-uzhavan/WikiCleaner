package com.core;

import static com.util.ErrorMessage.README;
import static com.util.ErrorMessage.TMP_FILE_FAILED;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dto.IConfigurations;
import com.text.processor.ContentProcessors;
import com.util.CleanerUtility;
import com.util.DefaultValues;
import com.util.Status;

/**
 * 
 * @author sathishrtskumar
 * 
 */
public class Cleaner {

	private IConfigurations config;
	private WikiActions wiki;

	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);

	public Cleaner(IConfigurations config, WikiActions wiki) {
		this.config = config;
		this.wiki = wiki;
		this.textArea.setLineWrap(false);
		this.textArea.setWrapStyleWord(true);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		this.textArea.setFont(font);
		this.scrollPane.setPreferredSize(new Dimension(900, 200));
	}

	public void process() throws Exception {
		if (!CleanerUtility.checkFile(config.getSourceFile())) {
			System.out.println("invalid file " + config.getSourceFile());
			throw new Exception("invalid file " + config.getSourceFile());
		}

		// Read source file which contains titles
		File file = new File(config.getSourceFile());
		// Create temp response file in the same parent of source
		File responseFile = CleanerUtility.createTempFile(file);

		if (!responseFile.exists())
			throw new Exception(TMP_FILE_FAILED + System.lineSeparator()
					+ README);

		List<String> titles = CleanerUtility.readLines(file);

		for (String title : titles) {
			this.textArea.setText(title);
			try {
				System.out.println("PROCESSING... " + title);
				String wikiContent = wiki.getContent(title);

				ContentProcessors processor = new ContentProcessors(
						wikiContent, config);
				String editedWikiContent = processor.process();
				System.out.println("CONTENT PROCESSED... " + title);

				int option = JOptionPane.OK_OPTION;
				boolean change = false;

				if (!wikiContent.equals(editedWikiContent)) {
					// Check Preview flag and act accordingly
					if (!config.getPreview().equals(DefaultValues.PREVIEW)) {
						this.textArea.setText(processor.getChanges());
						option = JOptionPane.showConfirmDialog(null,
								scrollPane, title, JOptionPane.YES_NO_OPTION);
					}

					if (option == JOptionPane.OK_OPTION) {
						wiki.setContent(title, editedWikiContent);
						change = true;
						CleanerUtility.appendFile(responseFile, title + " : "
								+ Status.SUCCESS);
						System.out.println(title + " : " + Status.SUCCESS);
					} else {
						CleanerUtility.appendFile(responseFile, title + " : "
								+ Status.CHANGE_CANCELLED);
						System.out.println(title + " : "
								+ Status.CHANGE_CANCELLED);
					}

				} else {
					System.out.println("NO CHANGE FOUND : " + title);
					CleanerUtility.appendFile(responseFile, title + " : "
							+ Status.NO_CHANGE_FOUND);
				}

				System.out.println("WAITING TIME : " + config.getThrottle());

				if (change)
					Thread.sleep(config.getThrottle());
			} catch (Exception e) {
				System.out.println("failed to edit content " + title);
				CleanerUtility.appendFile(responseFile, title + " : "
						+ Status.ERROR + " : " + e.getMessage());
				System.out.println(title + " : " + Status.ERROR + " : "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
