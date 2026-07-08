package com.github.m1n_h.BattleGame;

public class Goblin extends Monster {
    int id;

    public Goblin(int id) {
        this.id = id;
        setName("고블린(" + id + ")");
        setHp(30);
        setAttackPower(10);
    }

    public void attack(Hero target) {
        System.out.println(getName() + "(이)가 돌던지기로 " + target.getName() + "을(를) 공격합니다!");
        target.takeDamage(getAttackPower());
    }
}
