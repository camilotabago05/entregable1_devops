package co.edu.udem.devops.donation.controller;

import co.edu.udem.devops.donation.model.Donation;
import co.edu.udem.devops.donation.service.DonationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DonationController.class)
public class DonationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DonationService donationService;

    @Test
    public void DonationController_PostDonation_ReturnsSavedDonation() throws Exception {
        given(donationService.saveDonation(any(Donation.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        Donation donation = Donation.builder()
                .donationId(1L)
                .userId(1L)
                .campaignId(1L)
                .amount(10.0)
                .donationDate(LocalDate.now())
                .build();

        this.mockMvc.perform(post("/api/donations/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(donation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donationId", is(donation.getDonationId().intValue())));
    }

    @Test
    public void DonationController_GetDonation_ReturnsDonationById() throws Exception {
        Long donationId = 1L;

        Donation donation = Donation.builder()
                .donationId(1L)
                .userId(1L)
                .campaignId(1L)
                .amount(10.0)
                .donationDate(LocalDate.now())
                .build();

        given(donationService.findDonationById(donationId)).willReturn(Optional.of(donation));

        this.mockMvc.perform(get("/api/donations/get/{id}", donationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donationId", is(donation.getDonationId().intValue())));
    }
}
