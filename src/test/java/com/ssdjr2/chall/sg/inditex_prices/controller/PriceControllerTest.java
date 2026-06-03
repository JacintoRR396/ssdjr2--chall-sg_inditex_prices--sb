package com.ssdjr2.chall.sg.inditex_prices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.mapper.RespEntityErrorMapperImpl;
import com.ssdjr2.chall.sg.inditex_prices.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.factory.BrandMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceSearchQueryDTOMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceSearchResponseDTOMother;
import com.ssdjr2.chall.sg.inditex_prices.service.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = PriceController.class)
@Import({RespEntityErrorMapperImpl.class})
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private PriceService priceService;

	@Test
	@DisplayName("Return HTTP 200 OK with the applicable price when request payload is valid")
	void givenValidRequest_whenSearch_thenReturnOkAndResponseDTO() throws Exception {
		PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();
		PriceSearchResponseDTO response = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest1AndTest3();

		when(priceService.search(any(PriceSearchQueryDTO.class))).thenReturn(response);

		mockMvc.perform(
						MockMvcRequestBuilders.post("/prices/search")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));

		verify(priceService, times(1)).search(any(PriceSearchQueryDTO.class));
	}

	@Test
	@DisplayName("Return HTTP 404 NOT FOUND when no price tariff matches the query parameters")
	void givenValidRequest_whenSearchFindsNoTariff_thenReturnNotFound() throws Exception {
		PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();

		when(priceService.search(any(PriceSearchQueryDTO.class)))
				.thenThrow(new PriceNotFoundException(BrandMother.INVALID_ID, PriceMother.PRODUCT_ID_INVALID));

		mockMvc.perform(
						MockMvcRequestBuilders.post("/prices/search")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(priceService, times(1)).search(any(PriceSearchQueryDTO.class));
	}

	@Nested
	@DisplayName("Validation Scenarios (HTTP 400 Bad Request)")
	class ValidationErrorScenarios {

		@Test
		@DisplayName("Return HTTP 400 Bad Request when mandatory fields are missing")
		void givenRequestWithNullFields_whenSearch_thenReturnBadRequest() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsNull();

			mockMvc.perform(
							MockMvcRequestBuilders.post("/prices/search")
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(request)))
					.andExpect(MockMvcResultMatchers.status().isBadRequest());

			verifyNoInteractions(priceService);
		}

		@Test
		@DisplayName("Return HTTP 400 Bad Request when identifiers violate minimum value constraints")
		void givenRequestWithNegativeOrZeroIds_whenSearch_thenReturnBadRequest() throws Exception {
			PriceSearchQueryDTO request = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsInvalid();

			mockMvc.perform(
							MockMvcRequestBuilders.post("/prices/search")
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(request)))
					.andExpect(MockMvcResultMatchers.status().isBadRequest());

			verifyNoInteractions(priceService);
		}
	}
}
