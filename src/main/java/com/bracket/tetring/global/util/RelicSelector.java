package com.bracket.tetring.global.util;

import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.relic.domain.Relic;
import com.bracket.tetring.domain.relic.repository.RelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RelicSelector {
    private final RelicRepository relicRepository;
    private final Random random = new Random();

    private List<Relic> allRelics;

    @Autowired
    public RelicSelector(RelicRepository relicRepository) {
        this.relicRepository = relicRepository;
        loadRelicsFromRepository();
    }

    private void loadRelicsFromRepository() {
        this.allRelics = relicRepository.findAll();
    }

    public List<StoreRelic> getRandomRelics(Store store, List<GameRelic> playerRelics) {
        List<Relic> playerRelicList = playerRelics.stream()
                .map(GameRelic::getRelic)
                .toList();

        List<Relic> availableRelics  = allRelics.stream()
                .filter(relic -> !playerRelicList.contains(relic))
                .toList();

        List<StoreRelic> storeRelics = new ArrayList<>();
        int slotNumber = 1;

        while(storeRelics.size() < 2) {
            Relic relic = getRelicByRarity(availableRelics);
            if (storeRelics.stream().noneMatch(storeRelic -> storeRelic.getRelic().equals(relic))) {
                StoreRelic storeRelic = new StoreRelic(store, relic, slotNumber++, GameSettings.getPriceByRarity(relic.getRarity()));
                storeRelics.add(storeRelic);
            }
        }

        return storeRelics;
    }

    private Relic getRelicByRarity(List<Relic> availableRelics) {
        int rarityPick = random.nextInt(6);
        String selectedRarity;

        if (rarityPick < 3) {
            selectedRarity = "common";
        } else if (rarityPick < 5) {
            selectedRarity = "rare";
        } else {
            selectedRarity = "unique";
        }

        List<Relic> filteredRelics = availableRelics.stream()
                .filter(relic -> relic.getRarity().equals(selectedRarity))
                .toList();

        return filteredRelics.get(random.nextInt(filteredRelics.size()));
    }
}
