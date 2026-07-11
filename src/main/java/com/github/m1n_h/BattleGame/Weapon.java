package com.github.m1n_h.BattleGame;

public class Weapon {
    String name;
    int bonusAttack;
    private int damage;

    public Weapon(String name, int bonusAttack) {
        this.name = name;
        this.bonusAttack = bonusAttack;
    }

    public String getName() { return this.name; }

    public int getDamage() { return this.damage; }
    public void addDamage(int damage) { this.damage += damage; }
}
