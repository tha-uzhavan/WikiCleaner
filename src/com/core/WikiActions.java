package com.core;

import java.io.IOException;
import java.util.logging.Level;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import com.dto.IConfigurations;

public class WikiActions {

	private IConfigurations config;
	private Wiki wiki = null;
	private boolean login = false;

	public WikiActions(IConfigurations config) {
		this.config = config;
		this.wiki = new Wiki(config.getDomain());
		this.wiki.setMarkBot(config.isBot());
		this.wiki.setThrottle(config.getThrottle());
		this.wiki.setLogLevel(Level.OFF);
		this.wiki.setUserAgent(this.config.getUserAgent());
	}

	public void login() throws FailedLoginException, IOException {
		this.wiki.login(this.config.getUserName(), this.config.getPassword(),
				true);
		this.login = true;
	}

	public void logout() {
		this.wiki.logout();
		this.login = false;
	}

	public String getContent(String title) throws IOException {
		String wikiContent = this.wiki.getPageText(title);
		return wikiContent;
	}

	public void setContent(String title, String content) throws IOException,
			LoginException {
		this.wiki.edit(title, content, this.config.getComments());
	}

	public Wiki getWiki() {
		return this.wiki;
	}

	public boolean isLogin() {
		return this.login;
	}
}
