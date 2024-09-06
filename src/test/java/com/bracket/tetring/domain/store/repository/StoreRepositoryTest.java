package com.bracket.tetring.domain.store.repository;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.repository.GameRepository;
import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.store.domain.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // JPA 관련 테스트를 위한 어노테이션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // H2 인메모리 DB를 사용
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GameRepository gameRepository;

    private Game testGame;
    private Store testStore;

    @BeforeEach
    public void setup() {
        testGame = new Game();
        testGame.setGameId(1L);
        testGame.setPlayer(null);
        testGame.setIsStore(false);
        testGame.setRoundNumber(1);
        testGame.setIsPlaying(true);
        gameRepository.save(testGame);

        testStore = new Store();
        testStore.setStoreId(1L);
        testStore.setGame(testGame);
        testStore.setRerollPrice(10);
        testStore.setMoney(100);
        testStore.setMoneyLevel(1);
        storeRepository.save(testStore);

    }


    @Test
    public void testFindByGame_Success() {
        // Given: 이미 게임과 상점 데이터가 저장되어 있음

        // When: findByGame을 호출해서 Store 조회
        Optional<Store> foundStore = storeRepository.findByGame(testGame);

        // Then: Store가 정상적으로 조회되는지 확인
        assertTrue(foundStore.isPresent());
        assertEquals(testStore.getStoreId(), foundStore.get().getStoreId());
        assertEquals(testGame.getGameId(), foundStore.get().getGame().getGameId());
    }
}