package com.dto;

/**
 * @author sathishrtskumar
 * 
 */
public interface IConfigurations {

	public String getComments();

	public int getThrottle();

	public int getNumberOfChanges();

	public String getDomain();

	public String getSourceFile();

	public String getUserName();

	public String getPassword();

	public String getPreview();

	public String getUserAgent();

	public boolean isBot();
}
