package com.github.m1n_h.BattleGame.monster;

public class KingSlime extends Monster {
    private int[] weakPoints;
    private int destroyedWeakPoints = 0;

    public KingSlime(int[] wp) {
        setName("킹슬라임");
        setHp(800);
        setAttackPower(70);
        this.weakPoints = wp;
    }

    public int[] getWeakPoints() { return this.weakPoints; }

    public String takeDamageFromScan(int guessZone) {
        String result = "Miss";

        for (int i = 0; i < weakPoints.length; i++) {
            if (weakPoints[i] == guessZone) {
                result = "Hit";
                destroyedWeakPoints++;
                break;
            }
        }

        return result;
    }
}
