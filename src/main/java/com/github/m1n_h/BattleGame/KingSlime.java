package com.github.m1n_h.BattleGame;

public class KingSlime {
    private int hp;
    private String name;
    private int[] weakPoints;
    private int destroyedWeakPoints = 0;

    public KingSlime(int[] wp) {
        this.name = "킹 슬라임";
        this.hp = 100;
        this.weakPoints = wp;
    }

    public int getHp() {
        return this.hp;
    }

    public String getName() { return this.name; }

    public int[] getWeakPoints() { return this.weakPoints; }

    public String takeDamageFromScan(int guessZone) {
        String result = "Miss";

        for (int i = 0; i < weakPoints.length; i++) {
            if (weakPoints[i] == guessZone) {
                result = "Hit";
                this.hp -= 30;
                destroyedWeakPoints++;
                break;
            }
        }

        if ((destroyedWeakPoints == weakPoints.length) || (this.hp <= 0)) {
            result = "Kill";
        }

        return result;
    }
}
