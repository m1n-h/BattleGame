package com.github.m1n_h.BattleGame.character;

import com.github.m1n_h.BattleGame.monster.Monster;
import com.github.m1n_h.BattleGame.item.Weapon;

public class Mage extends Hero {

    public Mage() {
        super("아리엘", 150, 250, 15);
        setSkill(new Skill[] {
                new Skill("체인 라이트닝 ⚡", 1.8, 20),
                new Skill("블리자드 폭풍 ❄️", 2.2, 30),
                new Skill("메테오 스트라이크 ☄️", 4.8, 50)
        });
        equipWeapon(new Weapon("대마법사의 지팡이", 30));
    }

    @Override
    public void attack(Monster target) {
        int totalAttack = getAttackPower();

        // 무기 소지시 보너스 데미지
        if (this.weapon != null) totalAttack += this.weapon.bonusAttack;

        if (Math.random() < 0.5 || getMp() < 20) {
            System.out.println("🔮 " + getName() + "이(가) 지팡이로 평타를 툭 칩니다.");
            target.takeDamage(totalAttack);
        } else {
            Skill[] skills = getSkill();

            int randomIdx = (int) (Math.random() * skills.length);
            Skill chosenSkill = skills[randomIdx];

            setMp(getMp() - chosenSkill.getMPCost());
            int skillDamage = (int) (totalAttack * chosenSkill.getDamageMultiplier());

            System.out.println("✨ [MAGIC] " + getName() + "이(가) 필살기 [" + chosenSkill.getName() + "] 사용합니다! (남은 MP: " + getMp() + ")");
            System.out.println("   " + target.getName() + "에게 " + skillDamage + "의 마법 피해를 입혔습니다!");
            target.takeDamage(skillDamage);
        }
    }
}
