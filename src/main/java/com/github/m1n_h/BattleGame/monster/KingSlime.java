package com.github.m1n_h.BattleGame.monster;
import com.github.m1n_h.BattleGame.character.Hero;

import java.util.Random;

public class KingSlime extends Monster {
    private int[] weakPoints;
    private int destroyedWeakPoints = 0;

    public KingSlime(int[] wp) {
        super("킹슬라임", 800, 70, 200);
        this.weakPoints = wp;
        setAttackName(new String[] {
                "거대한 몸으로 짓누르기",
                "몸통 박치기로 날려버리기",
                "독 점액 뿌리기"
        });
    }

    public int[] getWeakPoints() { return this.weakPoints; }

    public String takeDamageFromScan(int guessZone) {
        String result = "Miss";

        for (int i = 0; i < weakPoints.length; i++) {
            if (weakPoints[i] == guessZone) {
                result = "Hit";
                destroyedWeakPoints++;
                break;
            }
        }

        return result;
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
