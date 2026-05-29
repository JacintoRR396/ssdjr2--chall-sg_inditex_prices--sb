package com.ssdjr2.chall.sg.inditex_prices.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class GlobalProperties {

	// Generic
	private String notApply;

	// Logs
	private String logMsgBaseOk;
	private String logMsgBaseInfo;
	private String logMsgBaseInfoReq;
	private String logMsgBaseInfoReqBody;
	private String logMsgBaseInfoRespBody;
	private String logMsgBaseWarm;
	private String logMsgBaseError;

	// Constants
	private String formatDateTimeBackend;
	private String formatTimestampBackend;
}
