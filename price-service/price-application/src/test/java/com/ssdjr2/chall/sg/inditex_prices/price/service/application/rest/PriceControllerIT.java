package com.ssdjr2.chall.sg.inditex_prices.price.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties.LoggerProperties;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper.RespEntityErrorMapper;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIT {

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

	@Nested
	@DisplayName("Successful Search Scenarios (HTTP 200 OK)")
	class ValidationOkScenarios {

		@Test
		@DisplayName("Request 1: Target date 2020-06-14T10:00:00 -> Returns Tariff 1")
		void givenDateAboutTest1_whenSearch_thenReturnTariff1() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();
			PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest1AndTest3();

			executeAndVerifySuccess(request, response);
		}

		@Test
		@DisplayName("Request 2: Target date 2020-06-14T16:00:00 -> Returns Tariff 2")
		void givenDateAboutTest2_whenSearch_thenReturnTariff2() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest2();
			PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest2();

			executeAndVerifySuccess(request, response);
		}

		@Test
		@DisplayName("Request 3: Target date 2020-06-14T21:00:00 -> Returns Tariff 1")
		void givenDateAboutTest3_whenSearch_thenReturnTariff1() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest3();
			PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest1AndTest3();

			executeAndVerifySuccess(request, response);
		}

		@Test
		@DisplayName("Request 4: Target date 2020-06-15T10:00:00 -> Returns Tariff 3")
		void givenDateAboutTest4_whenSearch_thenReturnTariff3() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest4();
			PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest4();

			executeAndVerifySuccess(request, response);
		}

		@Test
		@DisplayName("Request 5: Target date 2020-06-16T21:00:00 -> Returns Tariff 4")
		void givenDateAboutTest5_whenSearch_thenReturnTariff4() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest5();
			PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest5();

			executeAndVerifySuccess(request, response);
		}

		private void executeAndVerifySuccess(PriceSearchQueryDTO request, PriceSearchResponseDTO response) throws Exception {
			when(priceApplicationService.searchPrice(any())).thenReturn(response);

			ResultActions resultActions = launchRequest(request);

			resultActions
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));
		}
	}

	@Nested
	@DisplayName("Validation Error Scenarios (HTTP 400 Bad Request)")
	class ValidationErrorScenarios {

		@Test
		@DisplayName("Return HTTP 400 Bad Request when mandatory fields are missing")
		void givenRequestWithNullFields_whenSearch_thenReturnBadRequest() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsNull();
			executeAndVerifyBadRequest(request);
		}

		@Test
		@DisplayName("Return HTTP 400 Bad Request when identifiers violate minimum value constraints")
		void givenRequestWithNegativeOrZeroIds_whenSearch_thenReturnBadRequest() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsInvalid();
			executeAndVerifyBadRequest(request);
		}

		private void executeAndVerifyBadRequest(PriceSearchQueryDTO request) throws Exception {
			ResultActions resultActions = launchRequest( request );

			resultActions
					.andExpect(MockMvcResultMatchers.status().isBadRequest());
		}
	}

	private ResultActions launchRequest(PriceSearchQueryDTO request ) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.post(URL_PATH_SEARCH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)));
	}
}
