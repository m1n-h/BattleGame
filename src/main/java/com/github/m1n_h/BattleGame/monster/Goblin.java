package com.github.m1n_h.BattleGame.monster;

import com.github.m1n_h.BattleGame.character.Hero;

import java.util.Random;

public class Goblin extends Monster {
    int id;

    public Goblin(int id) {
        super("고블린(" + id + ")", 40, 20, 25);
        this.id = id;
        setAttackName(new String[] {
                "야비한 돌던지기",
                "독 묻은 단검 투척",
                "조롱하며 메롱하기"
        });
    }

    @Override
    public void attack(Hero target) {
        Random random = new Random();
        int skillIndex = random.nextInt(getAttackName().length);
        String skill = getAttackName()[skillIndex];

        System.out.println("💧 " + getName() + " (이)가 [" + skill + "] 로 공격합니다!");
        target.takeDamage(getAttackPower());
        System.out.println("💥 " + target.getName() + " 이(가) " + getAttackPower() + " 의 피해를 입었습니다.");
    }
}
