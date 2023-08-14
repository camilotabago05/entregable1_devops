package co.edu.udem.devops.campaign.service;

import co.edu.udem.devops.campaign.model.Campaign;
import co.edu.udem.devops.campaign.repository.CampaignRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {
    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @Test
    public void CampaignService_PostCampaign_ReturnsSavedCampaign() {
        Campaign campaign = Campaign.builder()
                .campaignId(1L)
                .title("Campaña #1")
                .description("Propuesta de Campaña #1")
                .goal(100.0)
                .deadline(LocalDate.parse("2023-06-01"))
                .userId(1L)
                .build();

        when(campaignRepository.save(Mockito.any(Campaign.class))).thenReturn(campaign);

        Campaign savedCampaign = campaignService.saveCampaign(campaign);

        Assertions.assertNotNull(savedCampaign);
        Assertions.assertTrue(savedCampaign.getUserId() > 0);
    }

    @Test
    public void CampaignService_GetCampaign_ReturnsCampaignByID() {
        Long campaignId = 1L;

        Campaign campaign = Campaign.builder()
                .campaignId(campaignId)
                .title("Campaña #1")
                .description("Propuesta de Campaña #1")
                .goal(100.0)
                .deadline(LocalDate.parse("2023-06-01"))
                .userId(1L)
                .build();

        when(campaignRepository.findById(campaignId)).thenReturn(Optional.ofNullable(campaign));

        Optional<Campaign> foundCampaign = campaignService.findCampaignById(campaignId);

        Assertions.assertTrue(foundCampaign.isPresent());
        assert campaign != null;
        Assertions.assertSame(campaign.getCampaignId(), foundCampaign.get().getCampaignId());
    }
}
