package com.ust.rewards.controller;
import com.ust.rewards.dto.RewardPointsResponse;
import com.ust.rewards.exception.RewardsServiceException;
import com.ust.rewards.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping
    public ResponseEntity<RewardPointsResponse> getRewards(@PathVariable String customerId,
                                                           @RequestParam(required = false) List<String> categories) throws RewardsServiceException {

        if (customerId == null || customerId.isBlank()) {
            throw new RewardsServiceException("CustomerId must not be null or blank");
        }
        RewardPointsResponse response = rewardsService.calculateRewards(customerId, categories);
        return ResponseEntity.ok(response);
    }
}