package co.edu.udem.devops.campaign.controller;

import co.edu.udem.devops.campaign.model.Campaign;
import co.edu.udem.devops.campaign.service.CampaignService;
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

@WebMvcTest(controllers = CampaignController.class)
public class CampaignControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CampaignService campaignService;

    @Test
    public void CampaignController_PostCampaign_ReturnsSavedCampaign() throws Exception {
        given(campaignService.saveCampaign(any(Campaign.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        Campaign campaign = Campaign.builder()
                .campaignId(1L)
                .title("Campaña #1")
                .description("Propuesta de Campaña #1")
                .goal(100.0)
                .deadline(LocalDate.parse("2023-06-01"))
                .status(false)
                .userId(1L)
                .build();

        this.mockMvc.perform(post("/api/campaigns/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campaign)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignId", is(campaign.getCampaignId().intValue())));
    }

    @Test
    public void CampaignController_GetCampaign_ReturnsCampaignById() throws Exception {
        Long campaignId = 1L;

        Campaign campaign = Campaign.builder()
                .campaignId(campaignId)
                .title("Campaña #1")
                .description("Propuesta de Campaña #1")
                .goal(100.0)
                .deadline(LocalDate.parse("2023-06-01"))
                .userId(1L)
                .build();

        given(campaignService.findCampaignById(campaignId)).willReturn(Optional.of(campaign));

        this.mockMvc.perform(get("/api/campaigns/get/{id}", campaignId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(campaign.getCampaignId().intValue())));
    }
}
