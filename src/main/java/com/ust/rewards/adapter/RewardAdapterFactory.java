package com.ust.rewards.adapter;

import com.ust.rewards.exception.RewardsServiceException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RewardAdapterFactory {

    private final Map<String, RewardAdapter> adapters;

    public RewardAdapterFactory(Set<RewardAdapter> adapters) {
        // Convert the set into a map where the key is the category
        this.adapters = adapters.stream()
                .collect(Collectors.toMap(RewardAdapter::getCategory, adapter -> adapter));
    }

    public RewardAdapter getAdapter(String category) throws RewardsServiceException {
        if (!adapters.containsKey(category)) {
            throw new RewardsServiceException("Invalid category: " + category);
        }
        return adapters.get(category);
    }

    public List<String> getAllCategories() {
        return new ArrayList<>(adapters.keySet());
    }
}
