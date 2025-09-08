package com.ust.rewards.adapter;

import com.ust.rewards.dto.HotelTransaction;
import com.ust.rewards.exception.RewardsServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HotelAdapter implements RewardAdapter<HotelTransaction> {

    private static final String HOTEL_SERVICE = "hotelService";

    public HotelAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getCategory() {
        return "hotels";
    }

    private final RestTemplate restTemplate;

    @Value("${hotel.url.hotel}")
    private String hotelServiceUrl;

    @Override
    @CircuitBreaker(name = HOTEL_SERVICE, fallbackMethod = "fallbackFetchTransactions")
    @Retry(name = HOTEL_SERVICE)
    public List<HotelTransaction> fetchTransactions(String customerId) throws RewardsServiceException {

        //commented for Test Data  to run
        /*
        String url = hotelServiceUrl + "?customerId=" + customerId;
       ResponseEntity<List<RestaurantTransaction>> response = restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<List<RestaurantTransaction>>() {});
        List<RestaurantTransaction> transactions = response.getBody();
        if (transactions == null || transactions.isEmpty()) {
            throw new RewardsServiceException("No Records Found for Customer with Customer Id : " + customerId);
        }
        */

        // Return dummy records for testing
        return List.of(
                new HotelTransaction("H123456", customerId, "Grand Palace Hotel", "2025-09-01", "2025-09-04", 3, 450.00),
                new HotelTransaction("H123457", customerId, "Sea View Resort", "2025-08-20", "2025-08-22", 2, 200.00),
                new HotelTransaction("H125358", customerId, "Shangri-La", "2025-08-20", "2025-08-22", 5, 200.00)
        );
    }


    private List<HotelTransaction> fallbackFetchTransactions(String customerId, Throwable ex) throws RewardsServiceException {

        // We can call another Service or DB as alternative ,
        // For This Project Purpose I have thrown Exception Here
        throw new RewardsServiceException("Failed to fetch hotel transactions for customerId =" + customerId, ex);
    }
}
