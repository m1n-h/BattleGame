package com.github.m1n_h.BattleGame.character;

import com.github.m1n_h.BattleGame.exception.NotEnoughGoldException;
import com.github.m1n_h.BattleGame.monster.Monster;
import com.github.m1n_h.BattleGame.item.Weapon;

public abstract class Hero extends Character {
    private int mp;
    private int level;
    private Skill[] skill;
    private int gold = 100;
    private Usable[] inventory = new Usable[5];

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

    public abstract void attack(Monster target);

    public int getGold() { return this.gold; }
    public void setGold(int gold) { this.gold = gold; }

    public Usable[] getInventory() { return this.inventory; }

    public void payGold(int price) {
        if (this.gold < price) {
            throw new NotEnoughGoldException("❌ [골드 부족] 필요 골드: " + price + "G / 보유 골드: " + this.gold + "G");
        }
        this.gold -= price;
    }

}
