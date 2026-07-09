package com.github.m1n_h.BattleGame;

public class Skill {
    private String name;
    private double damageMultiplier;

    public Skill(String name, double damageMultiplier) {
        this.name = name;
        this.damageMultiplier = damageMultiplier;
    }

    public String getName() { return this.name; }
    public double getDamageMultiplier() { return this.damageMultiplier; }
}
