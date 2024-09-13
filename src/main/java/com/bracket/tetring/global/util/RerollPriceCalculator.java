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
        boolean couponBlock = gameRelicRepository.findByGameAndRelicNumber(game, 10).isPresent();
        boolean rerollBlock = gameRelicRepository.findByGameAndRelicNumber(game, 4).isPresent();
        if(!couponBlock) {
            int rerollPrice = REROLL_INITIAL_PRICE;
            if(rerollBlock)
                rerollPrice -= 1;
            return Math.max(rerollPrice, 0);
        } else {
            return 0;
        }
    }

    public int getRerollPrice(Game game, Store store) {
        boolean couponBlock = gameRelicRepository.findByGameAndRelicNumber(game, 10).isPresent();
        boolean rerollBlock = gameRelicRepository.findByGameAndRelicNumber(game, 4).isPresent();
        if (!couponBlock || store.getUseCoupon()) {
            int rerollPrice = store.getRerollPrice() + REROLL_UPDATE_PRICE;
            if(rerollBlock) {
                rerollPrice -= 1;
            }
            return Math.max(rerollPrice, 0);
        } else {
            int rerollPrice = REROLL_INITIAL_PRICE;
            if(rerollBlock) {
                rerollPrice -= 1;
            }
            store.setUseCoupon(true);
            return Math.max(rerollPrice, 0);
        }
    }
}
