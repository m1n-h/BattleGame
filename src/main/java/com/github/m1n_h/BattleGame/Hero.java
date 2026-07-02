package com.github.m1n_h.BattleGame;

public class Hero {
    String name = "레오";
    int hp = 100;
    int attackPower = 15;

    public void attack(Slime target) {
        System.out.println("⚔️ " + name + "(이)가 " + target.name + "을(를) 공격합니다!");
        target.takeDamage(attackPower);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println("💥 " + name + "(이)가 " + damage + "의 피해를 입었습니다. (남은 HP: " + hp + ")");
    }
}
