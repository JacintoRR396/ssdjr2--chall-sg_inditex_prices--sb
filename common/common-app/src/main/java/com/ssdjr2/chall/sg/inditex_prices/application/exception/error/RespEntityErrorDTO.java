package com.ssdjr2.chall.sg.inditex_prices.application.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespEntityErrorDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -7048689708460974248L;

	private String id;

	private String timestamp;

	@JsonProperty("http_status_code")
	private int httpStatusCode;      // HttpStatus

	@JsonProperty("error_code")
	private int errorCode;          // AppExceptionCode.code

	@JsonProperty("error_message")
	private String errorMessage;    // AppExceptionCode.msg

	@JsonProperty("validation_errors")
	private Map<String, String> validationErrors;

	@JsonProperty("ex_message")
	private String exMessage;

	@JsonProperty("ex_track_trace")
	private String exTrackTrace;
}
