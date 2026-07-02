package com.github.m1n_h.BattleGame;

public class Slime {
    String name = "슬라임";
    int hp = 60;
    int attackPower = 10;

    public void attack(Hero target) {
        System.out.println("💧 " + name + "(이)가 몸통 박치기로 " + target.name + "을(를) 공격합니다!");
        target.takeDamage(attackPower);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println("💥 " + name + "(이)가 " + damage + "의 피해를 입었습니다. (남은 HP: " + hp + ")");
    }
}
