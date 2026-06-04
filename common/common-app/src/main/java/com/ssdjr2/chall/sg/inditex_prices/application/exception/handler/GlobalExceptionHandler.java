package com.ssdjr2.chall.sg.inditex_prices.application.exception.handler;

import com.ssdjr2.chall.sg.inditex_prices.application.exception.AppExceptionCodeEnum;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties.LoggerProperties;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.custom.CustomException;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.error.RespEntityErrorDTO;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper.RespEntityErrorMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Order(2)
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger( GlobalExceptionHandler.class );

	private final LoggerProperties loggerProperties;

	private final RespEntityErrorMapper respEntityErrorMapper;

	@ExceptionHandler({
			CustomException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleExCustom (CustomException ex ) {
		return this.createRespEntityError( ex, ex.getAppExCode(), ex.getValidationErrors() );
	}

	@ExceptionHandler({
			MissingServletRequestParameterException.class,
			MissingServletRequestPartException.class,
			ServletRequestBindingException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			// MethodArgumentNotValidException.class,
			// HandlerMethodValidationException
			BindException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx400 (Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40000, null );
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RespEntityErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> validationErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error ->
			validationErrors.put( error.getField(), "The field " + error.getField() + " " + error.getDefaultMessage() )
		);

		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40001, validationErrors );
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<RespEntityErrorDTO> handleEx403(Exception ex) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40300,null);
	}

	@ExceptionHandler({
			NoHandlerFoundException.class,
			NoResourceFoundException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx404 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40400, null );
	}

	@ExceptionHandler({
			HttpRequestMethodNotSupportedException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx405 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40500, null );
	}

	@ExceptionHandler({
			HttpMediaTypeNotAcceptableException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx406 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40600, null );
	}

	@ExceptionHandler({
			HttpMediaTypeNotSupportedException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx415 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_41500, null );
	}

	@ExceptionHandler({
			ArithmeticException.class,
			MissingPathVariableException.class,
			ConversionNotSupportedException.class,
			HttpMessageNotWritableException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx500 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_50000, null );
	}

	@ExceptionHandler({
			AsyncRequestTimeoutException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx503 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_50300, null );
	}

	protected ResponseEntity<RespEntityErrorDTO> createRespEntityError ( Exception ex, AppExceptionCodeEnum appExCode,
																																		 Map<String, String> validationErrors ) {
		CustomException customEx = Objects.nonNull( appExCode )
				? new CustomException( ex, appExCode, validationErrors ) : ( CustomException ) ex;
		RespEntityErrorDTO error = this.respEntityErrorMapper.toDTO( customEx, this.getTimestamp() );

		this.createLogger( appExCode );

		return new ResponseEntity<>( error, customEx.getAppExCode().getHttpStatusCode() );
	}

	private String getTimestamp () {
		OffsetDateTime currentTime = OffsetDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.loggerProperties.getLogMsgDateFormatter() );

		return currentTime.format( formatter );
	}

	private void createLogger( AppExceptionCodeEnum appExCode ) {
		if ( Objects.nonNull(appExCode) && appExCode.getHttpStatusCode().is5xxServerError() ) {
			LOGGER.error( this.loggerProperties.getLogMsgLogFormatter(), this.loggerProperties.getLogMsgBaseError(), appExCode.getAppStatusCode(), appExCode.getMessage() );
		} else if ( Objects.nonNull(appExCode) && appExCode.getHttpStatusCode().is4xxClientError() ) {
			LOGGER.warn( this.loggerProperties.getLogMsgLogFormatter(), this.loggerProperties.getLogMsgBaseWarning(), appExCode.getAppStatusCode(), appExCode.getMessage() );
		} else if ( Objects.nonNull(appExCode) ) {
			LOGGER.info( this.loggerProperties.getLogMsgLogFormatter(), this.loggerProperties.getLogMsgBaseInfo(), appExCode.getAppStatusCode(), appExCode.getMessage() );
		}
	}
}
