package com.ssdjr2.chall.sg.inditex_prices.price.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties.LoggerProperties;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper.RespEntityErrorMapper;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.exception.PriceGlobalExceptionHandler;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory.PriceSearchQueryDTOMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory.PriceSearchResponseDTOMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.input.service.PriceApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = PriceController.class)
@Import({
		PriceGlobalExceptionHandler.class,
		HttpEncodingAutoConfiguration.class
})
class PriceControllerTest {

	public static final String URL_PATH_SEARCH = "/prices/search";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private PriceApplicationService priceApplicationService;

	@MockitoBean
	private RespEntityErrorMapper respEntityErrorMapper;

	@MockitoBean
	private LoggerProperties loggerProperties;

	@BeforeEach
	void setUp() {
		when(loggerProperties.getLogMsgBaseWarning()).thenReturn("[WARN] »");
		when(loggerProperties.getLogMsgBaseError()).thenReturn("[ERROR] »");
		when(loggerProperties.getLogMsgLogFormatter()).thenReturn("{} {} : {}");
		when(loggerProperties.getLogMsgDateFormatter()).thenReturn("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	}

	@Test
	@DisplayName("Return HTTP 200 OK with the applicable price when request payload is valid")
	void givenValidRequest_whenSearch_thenReturnOkAndResponseDTO() throws Exception {
		PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();
		PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest1AndTest3();

		when(priceApplicationService.searchPrice(any(PriceSearchQueryDTO.class))).thenReturn(response);

		ResultActions resultActions = launchRequest( request );

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));

		verify(priceApplicationService, times(1)).searchPrice(any(PriceSearchQueryDTO.class));
	}

	@Nested
	@DisplayName("Validation Scenarios (HTTP 400 Bad Request)")
	class ValidationErrorScenarios {

		@Test
		@DisplayName("Return HTTP 400 Bad Request when mandatory fields are missing")
		void givenRequestWithNullFields_whenSearch_thenReturnBadRequest() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsNull();

			ResultActions resultActions = launchRequest( request );

			resultActions
					.andExpect(MockMvcResultMatchers.status().isBadRequest());

			verifyNoInteractions(priceApplicationService);
		}

		@Test
		@DisplayName("Return HTTP 400 Bad Request when identifiers violate minimum value constraints")
		void givenRequestWithNegativeOrZeroIds_whenSearch_thenReturnBadRequest() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsInvalid();

			ResultActions resultActions = launchRequest( request );

			resultActions
					.andExpect(MockMvcResultMatchers.status().isBadRequest());

			verifyNoInteractions(priceApplicationService);
		}
	}

	private ResultActions launchRequest( PriceSearchQueryDTO request ) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.post(URL_PATH_SEARCH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)));
	}
}
