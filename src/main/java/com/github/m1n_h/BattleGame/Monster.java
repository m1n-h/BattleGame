package com.github.m1n_h.BattleGame;

public class Monster extends Character {
    private String name;
    private int hp;
    private int attackPower;

    public int getHp() { return this.hp; }

    public void setHp(int hp) { this.hp = hp; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) { this.hp = 0; }
    }

    public int getAttackPower() { return this.attackPower; }

    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }

}
