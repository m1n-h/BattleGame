package com.github.m1n_h.BattleGame.character;

import java.io.Serializable;

public class Character implements Serializable {
    private String name;
    private int hp;
    private int attackPower;

    public int getHp() { return this.hp; }
    public void setHp(int hp) { this.hp = hp; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public int getAttackPower() { return this.attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }
}
