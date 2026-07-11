package com.github.m1n_h.BattleGame;

public class Archer extends Hero {

    public Archer() {
        setName("리오르");
        setHp(200);
        setMp(170);
        setLevel(1);
        setAttackPower(20);
        setSkill(new Skill[] {
                new Skill("화살비 \uD83C\uDF27\uFE0F", 1.2, 30),
                new Skill("관통의 일격 ⚡", 2.2, 20),
                new Skill("천공의 화살 ☄\uFE0F", 3.5, 50)
        });
        this.weapon = new Weapon("바람의 활", 20);
    }

    @Override
    public void attack(Monster target) {
        int totalAttack = getAttackPower();

        if (Math.random() < 0.5 || getMp() < 20) {
            System.out.println("\uD83C\uDFF9 " + getName() + "이(가) " + target.getName() + " 을(를) 향해 활을 쏩니다!");
            target.takeDamage(totalAttack);
        } else {
            Skill[] skills = getSkill();

            int randomIdx = (int) (Math.random() * skills.length);
            Skill chosenSkill = skills[randomIdx];

            setMp(getMp() - chosenSkill.getMPCost());
            int skillDamage = (int) (totalAttack * chosenSkill.getDamageMultiplier());

            System.out.println("➶ [BULLSEYE] " + getName() + "이(가) 필살기 [" + chosenSkill.getName() + "] 사용합니다! (남은 MP: " + getMp() + ")");
            System.out.println("   " + target.getName() + "에게 " + skillDamage + "의 관통 피해를 입혔습니다!");
            target.takeDamage(skillDamage);
        }
    }
}
