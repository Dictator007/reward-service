package com.ust.rewards.adapter;

import com.ust.rewards.dto.CasinoTransaction;
import com.ust.rewards.exception.RewardsServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CasinoAdapter implements RewardAdapter<CasinoTransaction> {

    private static final String CASINO_SERVICE = "casinoService";

    private final RestTemplate restTemplate;

    @Value("${casino.url.casino}")
    private String casinoServiceUrl;

    public CasinoAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getCategory() {
        return "casinos";
    }

    @Override
    @CircuitBreaker(name = CASINO_SERVICE, fallbackMethod = "fallbackFetchTransactions")
    @Retry(name = CASINO_SERVICE)
    public List<CasinoTransaction> fetchTransactions(String customerId) throws RewardsServiceException {

        //commented for Test Data  to run
     /*
        String url = casinoServiceUrl + "?customerId=" + customerId;
       ResponseEntity<List<RestaurantTransaction>> response = restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<List<RestaurantTransaction>>() {});
        List<RestaurantTransaction> transactions = response.getBody();
        if (transactions == null || transactions.isEmpty()) {
            throw new RewardsServiceException("No Records Found for Customer with Customer Id : " + customerId);
        }
        */

        // Return dummy records for testing
        return List.of(
                new CasinoTransaction("C9001", customerId, "Lucky Star Casino", "2025-09-05", 120.00, "slots"),
                new CasinoTransaction("C9002", customerId, "Golden Palace Casino", "2025-08-25", 350.00, "poker"),
                new CasinoTransaction("C9003", customerId, "Royal Flush Casino", "2025-07-18", 200.00, "blackjack")
        );
    }

    private List<CasinoTransaction> fallbackFetchTransactions(String customerId, Throwable ex) throws RewardsServiceException {

        // We can call another Service or DB as alternative ,
        // For This Project Purpose I have thrown Exception Here
        throw new RewardsServiceException("Failed to fetch casino transactions for customerId=" + customerId, ex);
    }
}
