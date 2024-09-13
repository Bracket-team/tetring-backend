package com.bracket.tetring.global.util;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.repository.GameRelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bracket.tetring.global.util.GameSettings.REROLL_INITIAL_PRICE;
import static com.bracket.tetring.global.util.GameSettings.REROLL_UPDATE_PRICE;

@Component
@RequiredArgsConstructor
public class RerollPriceCalculator {
    private final GameRelicRepository gameRelicRepository;

    public int getInitPrice(Game game) {
        boolean couponRelic = gameRelicRepository.findByGameAndRelicNumber(game, 10).isPresent();
        if(!couponRelic) {
            return REROLL_INITIAL_PRICE;
        } else {
            return 0;
        }
    }

    public int getRerollPrice(Game game, Store store) {
        boolean couponRelic = gameRelicRepository.findByGameAndRelicNumber(game, 10).isPresent();
        if (!couponRelic || store.getUseCoupon()) {
            return store.getRerollPrice() + REROLL_UPDATE_PRICE;
        } else {
            store.setUseCoupon(true);
            return REROLL_INITIAL_PRICE;
        }
    }
}
