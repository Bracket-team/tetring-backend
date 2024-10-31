package com.bracket.tetring.global.util;

public class GameSettings {
    public static final int INITIAL_MONEY = 5;
    public static final int BLOCK_COUNT = 7;
    public static final int BLOCK_PRICE = 2;
    public static final int REROLL_INITIAL_PRICE = 2;
    public static final int REROLL_UPDATE_PRICE = 1;
    public static final int[] ROUND_GOALS = {600, 1400, 2800, 3600, 7200, 12000, 22000, 54000, 180000, 340000, 720000, 1508200};
    public static final int[] MONEY_LEVEL_UP_PRICE = {2, 4, 7, 15, 0};
    public static final int[] MONEY_GET = {1, 3, 5, 10, 20};

    public static final Block[] BLOCKS = {
            new Block("0010001000100010", "하늘색"), // I 블록
            new Block("0010001001100000", "파란색"), // J 블록
            new Block("0100010001100000", "귤색"),  // L 블록
            new Block("0110011000000000", "노란색"), // O 블록
            new Block("0011011000000000", "연두색"), // S 블록
            new Block("0010001100100000", "자주색"), // T 블록
            new Block("0110001100000000", "빨간색")  // Z 블록
    };

    public static int getRoundGoal(int roundNumber) {
        return ROUND_GOALS[roundNumber - 1];
    }

    public static int getMoneyLevelUpPrice(int moneyLevel) {
        return MONEY_LEVEL_UP_PRICE[moneyLevel - 1];
    }

    public static int getMoney(int moneyLevel) {
        return MONEY_GET[moneyLevel - 1];
    }

    public static int getPriceByRarity(String rarity) {
        return switch (rarity) {
            case "normal" -> 3;
            case "rare" -> 5;
            case "unique" -> 10;
            default -> 0;
        };
    }

    public record Block(String shape, String color) {
    }
}
