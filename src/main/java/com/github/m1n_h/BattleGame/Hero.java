package com.github.m1n_h.BattleGame;

public class Hero {
    String name = "레오";
    int hp = 150;
    int attackPower = 15;

    Weapon weapon;

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("⚔️ " + name + "이(가) [" + weapon.name + "]을(를) 장착했습니다! (공격력 +" + weapon.bonusAttack + ")");
    }

    public void attack(Slime target) {
        int totalAttack = attackPower;

        // 무기 소지시 데미지 추가
        if (weapon != null) {
            totalAttack += weapon.bonusAttack;
        }
        System.out.println("⚔️ " + name + "(이)가 " + target.name + "을(를) 공격합니다!");
        target.takeDamage(totalAttack);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println("💥 " + name + "(이)가 " + damage + "의 피해를 입었습니다. (남은 HP: " + hp + ")");
    }
}
