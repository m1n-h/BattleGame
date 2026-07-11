package com.github.m1n_h.BattleGame;

public class Skill {
    private String name;
    private double damageMultiplier;
    private int mpCost;

    public Skill(String name, double damageMultiplier, int mpCost) {
        this.name = name;
        this.damageMultiplier = damageMultiplier;
        this.mpCost = mpCost;
    }

    public String getName() { return this.name; }
    public double getDamageMultiplier() { return this.damageMultiplier; }
    public int getMPCost() { return this.mpCost; }
}
