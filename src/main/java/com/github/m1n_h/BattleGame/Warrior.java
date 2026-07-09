package com.github.m1n_h.BattleGame;

public class Warrior extends Hero {

    public Warrior() {
        setName("레오");
        setHp(250);
        setMp(100);
        setLevel(1);
        setSkill(new Skill[] {
                new Skill("휠윈드 소용돌이 🌪️", 1.2),
                new Skill("파워 스트라이크 💥", 1.8),
                new Skill("대지 가르기 🌋", 2.8)
        });
    }

    public void attack(Monster target) {
        int totalAttack = getAttackPower();

        // 무기 소지시 보너스 데미지
        if (weapon != null) totalAttack += weapon.bonusAttack;

        if (Math.random() < 0.5) {
            System.out.println("⚔️ " + getName() + " (이)가 " + target.getName() + " 을(를) 공격합니다!");
            target.takeDamage(totalAttack);
        } else {
            Skill[] skills = getSkill();

            int randomIdx = (int)(Math.random() * skills.length);
            Skill chosenSkill = skills[randomIdx];

            int skillDamage = (int)(totalAttack * chosenSkill.getDamageMultiplier());

            System.out.println("🔥 [CRITICAL] " + getName() + "이(가) 필살기 [" + chosenSkill.getName() + "] 사용합니다!");
            System.out.println("   " + target.getName() + " 에게 " + skillDamage + " 의 치명상을 입혔습니다!");

            target.takeDamage(skillDamage);
        }
    }
}
