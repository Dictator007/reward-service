package com.ust.rewards.adapter;

import com.ust.rewards.dto.RestaurantTransaction;
import com.ust.rewards.exception.RewardsServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestaurantAdapter implements RewardAdapter<RestaurantTransaction> {

    private static final String RESTAURANT_SERVICE = "restaurantService";

    private final RestTemplate restTemplate;

    @Value("${restaurant.url.restaurant}")
    private String restaurantServiceUrl;

    public RestaurantAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getCategory() {
        return "restaurants";
    }

    @Override
    @CircuitBreaker(name = RESTAURANT_SERVICE, fallbackMethod = "fallbackFetchTransactions")
    @Retry(name = RESTAURANT_SERVICE)
    public List<RestaurantTransaction> fetchTransactions(String customerId) throws RewardsServiceException {

        //commented for Test Data  to run
       /*
       String url = restaurantServiceUrl + "?customerId=" + customerId;
       ResponseEntity<List<RestaurantTransaction>> response = restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<List<RestaurantTransaction>>() {});
        List<RestaurantTransaction> transactions = response.getBody();
        if (transactions == null || transactions.isEmpty()) {
            throw new RewardsServiceException("No Records Found for Customer with Customer Id : " + customerId);
        }
        */

        // Return dummy records for testing
        return List.of(
                new RestaurantTransaction("R1001", customerId, "Oceanview Diner", "2025-09-03", 2, 120.50, false),
                new RestaurantTransaction("R1002", customerId, "Sunset Bistro", "2025-08-15", 4, 250.00, true),
                new RestaurantTransaction("R1003", customerId, "Downtown Cafe", "2025-07-30", 3, 180.75, false)
        );
    }

    private List<RestaurantTransaction> fallbackFetchTransactions(String customerId, Throwable ex) throws RewardsServiceException {

        // We can call another Service or DB as alternative ,
        // For This Project Purpose I have thrown Exception Here
        throw new RewardsServiceException("Failed to fetch restaurant transactions for customerId=" + customerId, ex);
    }
}
