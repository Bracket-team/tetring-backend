package com.bracket.tetring.global.util;

public class GameSettings {
    public static final int INITIAL_MONEY = 5;
    public static final int[] ROUND_GOALS = {800, 1400, 2000, 2400, 2800, 3200, 4000, 4600, 5000, 5800, 6200, 6600};
    public static final int[] MONEY_LEVEL_UP_PRICE = {3, 5, 10, 20};
    public static final int[] MONEY_GET = {1, 3, 5, 10, 20};

    public static int getRoundGoal(int roundNumber) {
        return ROUND_GOALS[roundNumber - 1];
    }

    public static int getMoneyLevelUpPrice(int moneyLevel) {
        return MONEY_LEVEL_UP_PRICE[moneyLevel - 1];
    }

    public static int getMoney(int moneyLevel) {
        return MONEY_GET[moneyLevel - 1];
    }
}
