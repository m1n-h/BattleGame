package com.github.m1n_h.BattleGame;

public class Warrior extends Hero {

    public Warrior() {
        setName("레오");
        setHp(250);
        setMp(100);
        setLevel(1);
    }

    public void attack(Slime target) {
        int totalAttack = getAttackPower();

        // 무기 소지시 데미지 추가
        if (weapon != null) {
            totalAttack += weapon.bonusAttack;
        }
        System.out.println("⚔️ " + getName() + "(이)가 " + target.getName() + "을(를) 공격합니다!");
        target.takeDamage(totalAttack);
    }
}
