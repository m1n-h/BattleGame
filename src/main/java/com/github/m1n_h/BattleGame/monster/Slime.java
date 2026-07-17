package com.github.m1n_h.BattleGame.monster;

import com.github.m1n_h.BattleGame.character.Hero;

import java.util.Random;

public class Slime extends Monster {
    int id;

    public Slime(int id) {
        super("슬라임(" + id + ")", 20, 10, 20);
        this.id = id;
        setAttackName(new String[] {
                "물말랑 몸통 박치기",
                "끈적한 점액 뿜기",
                "부르르 떨며 위협하기"
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
