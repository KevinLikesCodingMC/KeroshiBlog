package org.keroshi.keroshiblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {
	private final BlogProperties blogProperties;
	public LocaleConfig(BlogProperties blogProperties) {
		this.blogProperties = blogProperties;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		Locale defaultLocale = getLocaleFromLang(blogProperties.getLang());
		resolver.setDefaultLocale(defaultLocale);
		return resolver;
	}

	private Locale getLocaleFromLang(String lang) {
		if(lang == null || lang.isEmpty()) {
			return Locale.ENGLISH;
		}
		return switch (lang.toLowerCase()) {
			case "en" -> Locale.ENGLISH;
			case "zh", "zh_cn", "zh-cn" -> Locale.CHINA;
			default -> Locale.ENGLISH;
		};
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
