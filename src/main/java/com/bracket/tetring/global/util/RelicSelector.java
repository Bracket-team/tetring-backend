package com.bracket.tetring.global.util;

import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.domain.Relic;
import com.bracket.tetring.domain.relic.repository.RelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Component
@Slf4j
public class RelicSelector {
    private final RelicRepository relicRepository;
    private final StoreRelicRepository storeRelicRepository;

    @Autowired
    public RelicSelector(RelicRepository relicRepository, StoreRelicRepository storeRelicRepository) {
        this.relicRepository = relicRepository;
        this.storeRelicRepository = storeRelicRepository;
    }

    @Transactional
    public List<StoreRelic> getRandomRelics(Store store, List<GameRelic> playerRelics) {
        List<Relic> allRelics = relicRepository.findAll();
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
                storeRelicRepository.save(storeRelic);
            }
        }

        return storeRelics;
    }

    private Relic getRelicByRarity(List<Relic> availableRelics) {
        Random random = new Random();
        int rarityPick = random.nextInt(6);
        String selectedRarity;

        if (rarityPick < 3) {
            selectedRarity = "normal";
        } else if (rarityPick < 5) {
            selectedRarity = "rare";
        } else {
            selectedRarity = "unique";
        }

        List<Relic> filteredRelics = availableRelics.stream()
                .filter(relic -> relic.getRarity().equals(selectedRarity))
                .toList();

        if (filteredRelics.isEmpty()) {
            System.out.println("Filtered relics are empty. Selected rarity: " + selectedRarity);
            throw new NoSuchElementException("No relics available after filtering.");
        }

        return filteredRelics.get(random.nextInt(filteredRelics.size()));
    }
}