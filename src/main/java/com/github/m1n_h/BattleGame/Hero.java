package com.github.m1n_h.BattleGame;

public class Hero extends Character {
    private int mp;
    private int level;
    private Skill[] skill;

    Weapon weapon;

    public Skill[] getSkill() { return this.skill; }
    public void setSkill(Skill[] skill) { this.skill = skill;}

    public int getMp() { return this.mp; }
    public void setMp(int mp) { this.mp = mp; }

    public int getLevel() { return this.level; }
    public void setLevel(int level) { this.level = level; }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("⚔️ " + getName() + "이(가) [" + weapon.name + "]을(를) 장착했습니다! (공격력 +" + weapon.bonusAttack + ")");
    }
    public Weapon getWeapon() { return this.weapon; }

    public void attack(Monster target) {}

}
