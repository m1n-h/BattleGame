package com.github.m1n_h.BattleGame;

public class Slime {
    String name;
    private int hp;
    private int attackPower;
    int id;

    public Slime(int id, int maxHp, int attackPower) {
        this.id = id;
        this.name = "슬라임(" + id + ")";
        this.hp = maxHp;
        this.attackPower = attackPower;
    }

    public void attack(Hero target) {
        System.out.println("💧 " + name + "(이)가 몸통 박치기로 " + target.name + "을(를) 공격합니다!");
        target.takeDamage(getAttackPower());
    }

    public int getHp() {
        return this.hp;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println("💥 " + name + "(이)가 " + damage + "의 피해를 입었습니다. (남은 HP: " + hp + ")");
    }
}
