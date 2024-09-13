package com.bracket.tetring.domain.relic.repository;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.domain.GameRelicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRelicRepository extends JpaRepository<GameRelic, GameRelicId> {

    List<GameRelic> findByGame(Game game);

    @Query("SELECT gr FROM GameRelic gr WHERE gr.game = :game AND gr.relic.relicNumber = :relicNumber")
    Optional<GameRelic> findByGameAndRelicNumber(@Param("game") Game game, @Param("relicNumber") Integer relicNumber);

    @Query("SELECT gr FROM GameRelic gr WHERE gr.game = :game AND gr.slotNumber = :slotNumber")
    Optional<GameRelic> findByGameAndSlotNumber(@Param("game") Game game, @Param("slotNumber") Integer slotNumber);
}
