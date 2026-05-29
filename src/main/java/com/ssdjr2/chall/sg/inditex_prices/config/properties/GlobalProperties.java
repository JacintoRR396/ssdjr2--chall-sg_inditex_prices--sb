package com.ssdjr2.chall.sg.inditex_prices.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class GlobalProperties {

	// Generic
	private String notApply;

	// Logs
	private String logMsgBaseOk;
	private String logMsgBaseInfo;
	private String logMsgBaseError;
	private String logMsgBaseReq;

	// Constants
	private String formatDateTimeBackend;
	private String formatTimestampBackend;
}
