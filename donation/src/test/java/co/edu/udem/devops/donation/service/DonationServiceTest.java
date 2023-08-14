package co.edu.udem.devops.donation.service;

import co.edu.udem.devops.donation.model.Donation;
import co.edu.udem.devops.donation.repository.DonationRepository;
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
public class DonationServiceTest {
    @Mock
    private DonationRepository donationRepository;

    @InjectMocks
    private DonationService donationService;

    @Test
    public void DonationService_PostDonation_ReturnsSavedDonation() {
        Donation donation = Donation.builder()
                .donationId(1L)
                .userId(1L)
                .campaignId(1L)
                .amount(10.0)
                .donationDate(LocalDate.now())
                .build();

        when(donationRepository.save(Mockito.any(Donation.class))).thenReturn(donation);

        Donation savedDonation = donationService.saveDonation(donation);

        Assertions.assertNotNull(savedDonation);
        Assertions.assertTrue(savedDonation.getUserId() > 0);
    }

    @Test
    public void DonationService_GetDonation_ReturnsDonationById() {
        Long donationId = 1L;

        Donation donation = Donation.builder()
                .donationId(donationId)
                .userId(1L)
                .campaignId(1L)
                .amount(10.0)
                .donationDate(LocalDate.now())
                .build();

        when(donationRepository.findById(donationId)).thenReturn(Optional.ofNullable(donation));

        Optional<Donation> foundDonation = donationService.findDonationById(donationId);

        Assertions.assertTrue(foundDonation.isPresent());
        assert donation != null;
        Assertions.assertSame(donation.getDonationId(), foundDonation.get().getDonationId());
    }
}
