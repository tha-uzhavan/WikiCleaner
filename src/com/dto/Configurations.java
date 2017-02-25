package com.dto;

/**
 * @author sathishrtskumar
 * 
 *         has all input params taken from Bot user
 * 
 */
public class Configurations implements IConfigurations {

	private String comments;
	private int throttle;
	private int numberOfChanges;
	private String domain;
	private String sourceFile;
	private String userName;
	private String password;
	private String preview;
	private String userAgent;
	private boolean bot;

	public Configurations() {

	}

	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public String getComments() {
		return comments;
	}

	/**
	 * Reason for editing the wiki content
	 * 
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getThrottle() {
		return throttle;
	}

	/**
	 * Time delay between each edit
	 * 
	 * @param throttle
	 */
	public void setThrottle(int throttle) {
		this.throttle = throttle;
	}

	public int getNumberOfChanges() {
		return numberOfChanges;
	}

	/**
	 * Number of changes to make in given title 0 - Do all the changes
	 * 
	 * @param numberOfChanges
	 */
	public void setNumberOfChanges(int numberOfChanges) {
		this.numberOfChanges = numberOfChanges;
	}

	public String getDomain() {
		return domain;
	}

	/**
	 * domain to which bot connects to , example : ta.wikipedia.org
	 * 
	 * @param domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	/**
	 * Source file which will have all titles
	 * 
	 * @param sourceFile
	 */
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return "Configurations [comments=" + comments + ", throttle="
				+ throttle + ", numberOfChanges=" + numberOfChanges
				+ ", domain=" + domain + ", sourceFile=" + sourceFile
				+ ", userName=" + userName + ", password=" + password
				+ ", preview=" + preview + ", userAgent=" + userAgent + "]";
	}
}
