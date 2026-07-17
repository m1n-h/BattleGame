package com.github.m1n_h.BattleGame.monster;

import com.github.m1n_h.BattleGame.character.Hero;

public class RangedMonster extends Monster {

    public RangedMonster(String name, int hp, int attackPower, int exp, String[] skills) {
        super(name, hp, attackPower, exp);
        setAttackName(skills);
    }

    @Override
    public void attack(Hero target) {
        String[] skills = getAttackName();

        int randomIdx = (int)(Math.random() * skills.length);
        String chosenSkill = skills[randomIdx];

        System.out.println("🏹 " + getName() + " 이(가) [" + chosenSkill + "] 공격을 날립니다!");
        target.takeDamage(getAttackPower());
        System.out.println("💥 " + target.getName() + " 이(가) " + getAttackPower() + " 의 피해를 입었습니다.");
    }
}
