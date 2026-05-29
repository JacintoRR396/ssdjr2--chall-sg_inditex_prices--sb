package com.ssdjr2.chall.sg.inditex_prices.controllers.exceptions.custom;

import com.ssdjr2.chall.sg.inditex_prices.controllers.exceptions.AppExceptionCodeEnum;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;
import java.util.Map;
import java.util.UUID;

@Getter
public class CustomException extends ResponseStatusException {

	@Serial
	private static final long serialVersionUID = 7890133225255297908L;

	private final String id;
	private final Throwable originalException;
	private final AppExceptionCodeEnum appExCode;
	private final Map<String, String> validationErrors;

	public CustomException(Throwable originalException, AppExceptionCodeEnum appExCode,
	                       Map<String, String> validationErrors ) {
		super( appExCode.getHttpStatusCode(), appExCode.getMessage() );
		this.id = String.valueOf( UUID.randomUUID() );
		this.appExCode = appExCode;
		this.originalException = originalException;
		this.validationErrors = validationErrors;
	}

	public CustomException(Throwable originalException, AppExceptionCodeEnum appExCode ) {
		this( originalException, appExCode, null );
	}

	public CustomException(AppExceptionCodeEnum appExCode ) {
		this( null, appExCode );
	}
}
