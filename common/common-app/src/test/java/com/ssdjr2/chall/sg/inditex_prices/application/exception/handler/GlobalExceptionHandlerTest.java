package com.ssdjr2.chall.sg.inditex_prices.application.exception.handler;

import com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties.LoggerProperties;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.error.RespEntityErrorDTO;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper.RespEntityErrorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

	@Mock
	private LoggerProperties loggerProperties;

	@Mock
	private RespEntityErrorMapper respEntityErrorMapper;

	@InjectMocks
	private GlobalExceptionHandler handler;

	@Test
	void testHandleMethodArgumentNotValid_GwtCompliant() {
		MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
		BindingResult bindingResult = mock(BindingResult.class);

		when(ex.getBindingResult()).thenReturn(bindingResult);
		when(bindingResult.getFieldErrors()).thenReturn(Collections.emptyList());
		when(respEntityErrorMapper.toDTO(any(), anyString())).thenReturn(new RespEntityErrorDTO());
		when(loggerProperties.getLogMsgDateFormatter()).thenReturn("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

		ResponseEntity<RespEntityErrorDTO> response = handler.handleMethodArgumentNotValid(ex);

		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
