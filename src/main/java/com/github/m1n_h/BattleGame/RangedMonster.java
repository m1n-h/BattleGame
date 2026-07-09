package com.github.m1n_h.BattleGame;

public class RangedMonster extends Monster {
    @Override
    public void attack(Hero target) {
        String[] skills = getAttackName();

        int randomIdx = (int)(Math.random() * skills.length);
        String chosenSkill = skills[randomIdx];

        System.out.println("🏹 " + getName() + "(이)가 [" + chosenSkill + "] 공격을 날립니다!");
        target.takeDamage(getAttackPower());
    }
}
