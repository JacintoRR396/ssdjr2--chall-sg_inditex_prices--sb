package com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:/properties/logger.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "config")
public class LoggerProperties {

	private String logMsgBaseOk;
	private String logMsgBaseInfo;
	private String logMsgBaseWarning;
	private String logMsgBaseError;

	private String logMsgLogFormatter;
	private String logMsgDateFormatter;
}
