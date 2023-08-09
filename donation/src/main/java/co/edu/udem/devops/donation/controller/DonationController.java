package co.edu.udem.devops.donation.controller;

import co.edu.udem.devops.donation.model.Donation;
import co.edu.udem.devops.donation.service.DonationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/post")
    public Donation postCampaign(@RequestBody Donation donation) {
        return this.donationService.saveDonation(donation);
    }

    @GetMapping("/get/{id}")
    public Optional<Donation> getDonationById(@PathVariable Long id) {
        return donationService.findDonationById(id);
    }

    @GetMapping("/get/user/{id}")
    public List<Donation> getAllDonationsByUserId(@PathVariable Long id) {
        return donationService.findAllDonationsByUserId(id);
    }

    @GetMapping("/get/campaign/{id}")
    public List<Donation> getAllDonationsByCampaignId(@PathVariable Long id) {
        return donationService.findAllDonationsByCampaignId(id);
    }
}
