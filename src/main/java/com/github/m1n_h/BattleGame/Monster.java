package com.github.m1n_h.BattleGame;

public class Monster extends Character {
    private int exp;
    private int dropItem;
    private String[] attackName;

    public String[] getAttackName() { return this.attackName; }
    public void setAttackName(String[] attackName) { this.attackName = attackName;}

    public void attack(Hero target) {}
}
