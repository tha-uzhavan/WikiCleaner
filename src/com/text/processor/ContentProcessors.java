package com.text.processor;

import static com.util.CleanerConstants.EOTAXOBOX;
import static com.util.CleanerConstants.BOTAXOBOX;
import static com.util.CleanerConstants.NONWORD_REGX;
import static com.util.CleanerConstants.NUMBER_REGX;
import static com.util.CleanerConstants.PIPE;
import static com.util.CleanerConstants.REF_REGX;
import static com.util.CleanerConstants.encode;
import static com.util.CleanerConstants.pattern;

import java.util.regex.Matcher;

import com.dto.IConfigurations;
import com.util.CleanerConstants;

public class ContentProcessors {

	private String wikiContent;
	private IConfigurations config;
	private static String EMPTY = "";
	private static String OPEN = "\\[";
	private static String CLOSE = "]";

	private String changes = null;;

	public ContentProcessors(String wikiContent, IConfigurations config) {
		this.wikiContent = wikiContent;
		this.config = config;
	}

	public String process() throws Exception {
		String processedContent = replace(this.wikiContent);
		return processedContent;
	}

	private String replace(String originalData) throws Exception {

		String content = originalData;
		String finalContent = replaceRef(getTaxoBox());
		Matcher matcher = pattern.matcher(finalContent);

		StringBuilder builder = new StringBuilder();

		int count = 0;
		while (matcher.find()) {
			String word = matcher.group();
			if (!word.matches(NONWORD_REGX)
					&& !word.matches(NUMBER_REGX)
					&& restricedChars(word)
					&& encode.canEncode(word)
					&& (this.config.getNumberOfChanges() == 0 || count < this.config
							.getNumberOfChanges())) {

				String target = word.replaceAll(OPEN, EMPTY).replaceAll(CLOSE,
						EMPTY);
				String[] piped = target.split(PIPE);

				if (piped.length > 1 && piped[1].matches(NUMBER_REGX)) {
					// Do nothing for Year
				} else
					target = piped[0];

				builder.append(word + " --> " + target + System.lineSeparator());
				content = content.replace(word, target);
				count += 1;
			}
		}

		changes = builder.toString();

		return content;
	}

	/**
	 * 
	 * @param originalContent
	 * @return only {{Taxobox ... }} content
	 */
	private String getTaxoBox() {

		String originalContent = this.wikiContent;
		StringBuilder taxobox = new StringBuilder();

		if (originalContent != null) {
			String[] datas = originalContent.split(System.lineSeparator());
			boolean end = false;
			boolean taxoboxFound = false;
			for (String d : datas) {
				
				if(!taxoboxFound)
					taxoboxFound = d.contains(BOTAXOBOX);
				
				if(!end)
					end = d.matches(EOTAXOBOX);

				if (taxoboxFound && !end)
					taxobox.append(d + System.lineSeparator());
			}
		}
		
		return taxobox.toString();
	}

	/**
	 * @param taxoBoxContent
	 * @return Skip content available inside <ref></ref> tag
	 */
	private String replaceRef(String taxoBoxContent) {

		if (taxoBoxContent != null && !taxoBoxContent.isEmpty()) {
			// Remove ref tag and its content by empty
			taxoBoxContent = taxoBoxContent.replaceAll(REF_REGX, EMPTY);
		}

		return taxoBoxContent;
	}
	
	public boolean restricedChars(String word){
		String chars[] = CleanerConstants.SPECIAL_REGX.split("|");
		
		for(String c : chars){
			if(word.contains(c))
				return false;
		}
		
		return true;
	}
	
	public String getChanges() {
		return this.changes;
	}
}
