package com.github.m1n_h.BattleGame.character;

import com.github.m1n_h.BattleGame.exception.NotEnoughGoldException;
import com.github.m1n_h.BattleGame.monster.Monster;
import com.github.m1n_h.BattleGame.item.Weapon;

public abstract class Hero extends Character {
    private int mp;
    private int level = 1;
    private Skill[] skill;
    private int gold = 100;
    private Usable[] inventory = new Usable[5];
    private int exp;

    Weapon weapon;

    public Hero(String name, int hp, int mp, int attackPower) {
        setName(name);
        setHp(hp);
        setMp(mp);
        setAttackPower(attackPower);
    }

    public Skill[] getSkill() { return this.skill; }
    public void setSkill(Skill[] skill) { this.skill = skill;}

    public int getMp() { return this.mp; }
    public void setMp(int mp) { this.mp = mp; }

    public int getLevel() { return this.level; }

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

    public int getRequiredExp() { return this.level * 100; }
    public int getRequiredHp() { return this.level * 10; }
    public int getRequiredMp() { return this.level * 5; }
    public int getRequiredAttackPower() { return this.level * 20; }

    public void gainRewards(Monster target) {
        int ExistingHoldingsGold = this.gold;
        int ExistingHoldingsExp = this.exp;

        this.exp += target.getExp();
        this.gold += target.getGold();
        System.out.println("🎉 " + target.getName() + " 을(를) 처치해 " + target.getExp() + " EXP / " + target.getGold() + "G 획득 했습니다!");
        System.out.println("[GOLD] " + ExistingHoldingsGold + "G ➡️ " + this.gold + "G");
        System.out.println("[EXP] " + ExistingHoldingsExp + " EXP ➡️ " + this.exp + " EXP");

        if (this.exp >= getRequiredExp()) {
            this.levelUp();
        }
    }

    private void levelUp() {
        int bonusHp = getRequiredHp();
        int bonusMp = getRequiredMp();
        int bonusAttackPower = getRequiredAttackPower();

        this.level++;

        setHp(getHp() + (bonusHp));
        setMp(getMp() + (bonusMp));
        setAttackPower(getAttackPower() + (bonusAttackPower));

        System.out.println("🎉 [LEVEL UP] Level " + this.level + " 이 되었습니다.");
        System.out.print("\uD83D\uDCAA [STATE UP] ");
        System.out.print("HP " + bonusHp);
        System.out.print(" / MP " + bonusMp);
        System.out.print(" / 공격력 " + bonusAttackPower + " 향상");
    }

}
