package com.github.m1n_h.BattleGame;

public class Slime {
    String name;
    int hp = 40;
    int attackPower = 10;
    int id;

    public Slime(int id) {
        this.id = id;
        this.name = "슬라임(" + id + ")";
    }

    public void attack(Hero target) {
        System.out.println("💧 " + name + "(이)가 몸통 박치기로 " + target.name + "을(를) 공격합니다!");
        target.takeDamage(attackPower);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println("💥 " + name + "(이)가 " + damage + "의 피해를 입었습니다. (남은 HP: " + hp + ")");
    }
}
