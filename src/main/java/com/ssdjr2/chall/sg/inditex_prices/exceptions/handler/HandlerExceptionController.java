package com.ssdjr2.chall.sg.inditex_prices.exceptions.handler;

import com.ssdjr2.chall.sg.inditex_prices.config.properties.GlobalProperties;
import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.error.RespEntityErrorDTO;
import com.ssdjr2.chall.sg.inditex_prices.controllers.mappers.RespEntityErrorMapper;
import com.ssdjr2.chall.sg.inditex_prices.exceptions.AppExceptionCodeEnum;
import com.ssdjr2.chall.sg.inditex_prices.exceptions.custom.CustomException;
import com.ssdjr2.chall.sg.inditex_prices.utils.UDateTimeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
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
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestControllerAdvice
public class HandlerExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger( HandlerExceptionController.class );
	private static final String LOGGER_MSG_FORMATTER = "{} {} : {}";

	private final GlobalProperties globalProperties;
	private final UDateTimeService uDateTimeService;
	private final RespEntityErrorMapper respEntityErrorMapper;

	@ExceptionHandler({
			CustomException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleExCustom ( CustomException ex ) {
		return this.createRespEntityError( ex, null, null );
	}

	@ExceptionHandler({
			MissingServletRequestParameterException.class,
			MissingServletRequestPartException.class,
			ServletRequestBindingException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			MethodArgumentNotValidException.class,
			// HandlerMethodValidationException
			BindException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx400 (Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40000, null );
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

	private ResponseEntity<RespEntityErrorDTO> createRespEntityError ( Exception ex, AppExceptionCodeEnum appExCode,
																																		 Map<String, String> validationErrors ) {
		CustomException customEx = Objects.nonNull( appExCode )
				? new CustomException( ex, appExCode, validationErrors ) : ( CustomException ) ex;
		RespEntityErrorDTO error = this.respEntityErrorMapper.toDTO( customEx, this.uDateTimeService.getTimestamp() );

		this.createLogger( appExCode.getHttpStatusCode(), error.getErrorCode(), error.getExMessage() );

		return new ResponseEntity<>( error, customEx.getAppExCode().getHttpStatusCode() );
	}

	private void createLogger( HttpStatus status, int errorCode, String msgEx ) {
		if (status.is5xxServerError()) {
			LOGGER.error( LOGGER_MSG_FORMATTER, this.globalProperties.getLogMsgBaseError(), errorCode, msgEx );
		} else if (status.is4xxClientError()) {
			LOGGER.warn( LOGGER_MSG_FORMATTER, this.globalProperties.getLogMsgBaseWarm(), errorCode, msgEx );
		} else {
			LOGGER.info( LOGGER_MSG_FORMATTER, this.globalProperties.getLogMsgBaseInfo(), errorCode, msgEx );
		}
	}
}
