package com.bracket.tetring.global.util;

import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.domain.Relic;
import com.bracket.tetring.domain.relic.repository.RelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RelicSelector {
    private final RelicRepository relicRepository;
    private final StoreRelicRepository storeRelicRepository;

    private List<Relic> allRelics;

    @Autowired
    public RelicSelector(RelicRepository relicRepository, StoreRelicRepository storeRelicRepository) {
        this.relicRepository = relicRepository;
        this.storeRelicRepository = storeRelicRepository;
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
            selectedRarity = "common";
        } else if (rarityPick < 5) {
            selectedRarity = "rare";
        } else {
            selectedRarity = "unique";
        }

        List<Relic> filteredRelics = availableRelics.stream()
                .filter(relic -> relic.getRarity().equals(selectedRarity))
                .toList();

        if (filteredRelics.isEmpty()) {
            // 다른 희귀도의 유물을 선택하거나 예외 처리
            // 예를 들어, 다시 랜덤하게 희귀도를 선택하거나 기본 희귀도로 설정
            filteredRelics = availableRelics; // 모든 희귀도의 유물을 사용
        }

        return filteredRelics.get(random.nextInt(filteredRelics.size()));
    }
}
