package org.keroshi.keroshiblog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "blog")
@PropertySource(value = {
		"file:blog.properties",
		"classpath:blog.properties"
}, encoding = "UTF-8", ignoreResourceNotFound = true)
public class BlogProperties {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private String lang;
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	private String suUsername;
	public String getSuUsername() {
		return suUsername;
	}
	public void setSuUsername(String suUsername) {
		this.suUsername = suUsername;
	}

	private String suPassword;
	public String getSuPassword() {
		return suPassword;
	}
	public void setSuPassword(String suPassword) {
		this.suPassword = suPassword;
	}
}
