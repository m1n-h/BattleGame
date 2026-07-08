package com.github.m1n_h.BattleGame;

public class Slime extends Monster {
    int id;

    public Slime(int id) {
        this.id = id;
        setName("슬라임(" + id + ")");
        setHp(10);
        setAttackPower(5);
    }

    public void attack(Hero target) {
        System.out.println("💧 " + getName() + "(이)가 몸통 박치기로 " + target.getName() + "을(를) 공격합니다!");
        target.takeDamage(getAttackPower());
    }

}
