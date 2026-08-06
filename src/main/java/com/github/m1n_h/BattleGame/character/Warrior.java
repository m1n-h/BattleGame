package com.github.m1n_h.BattleGame.character;

import com.github.m1n_h.BattleGame.monster.Monster;
import com.github.m1n_h.BattleGame.item.Weapon;

import java.io.Serializable;

public class Warrior extends Hero implements Serializable {

    public Warrior() {
        super("레오", 250, 100, 30);
        setSkill(new Skill[] {
                new Skill("휠윈드 소용돌이 🌪️", 1.5, 25),
                new Skill("파워 스트라이크 💥", 2.2, 15),
                new Skill("대지 가르기 🌋", 3.5, 40)
        });
        equipWeapon(new Weapon("엑스칼리버", 25));
    }

    public void attack(Monster target) {
        int totalAttack = getFinalAttackPower();

        if (Math.random() < 0.5 || getMp() < 15) {
            System.out.println("⚔️ " + getName() + " (이)가 " + target.getName() + " 을(를) 공격합니다!");
            target.takeDamage(totalAttack);
        } else {
            Skill[] skills = getSkill();

            int randomIdx = (int)(Math.random() * skills.length);
            Skill chosenSkill = skills[randomIdx];

            setMp(getMp() - chosenSkill.getMPCost());
            int skillDamage = (int)(totalAttack * chosenSkill.getDamageMultiplier());

            System.out.println("🔥 [CRITICAL] " + getName() + " 이(가) 필살기 [" + chosenSkill.getName() + "] 사용합니다! (남은 MP: " + getMp() + ")");
            System.out.println("   " + target.getName() + " 에게 " + skillDamage + " 의 치명상을 입혔습니다!");

            target.takeDamage(skillDamage);
        }
    }
}
