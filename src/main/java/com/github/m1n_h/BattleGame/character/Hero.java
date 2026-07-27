package com.github.m1n_h.BattleGame.character;

import com.github.m1n_h.BattleGame.exception.DuplicateWeaponException;
import com.github.m1n_h.BattleGame.exception.ItemNotFoundException;
import com.github.m1n_h.BattleGame.exception.NotEnoughGoldException;
import com.github.m1n_h.BattleGame.exception.UnequipWeaponException;
import com.github.m1n_h.BattleGame.monster.Monster;
import com.github.m1n_h.BattleGame.item.Weapon;

import java.util.HashMap;
import java.util.Map;

public abstract class Hero extends Character {
    private int mp;
    private int level = 1;
    private Skill[] skill;
    private int gold = 100;
    private Map<Usable, Integer> inventory = new HashMap<>();
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
        if (this.weapon != null && this.getWeapon().equals(weapon))
            throw new DuplicateWeaponException("해당 무기는 이미 장착 중 입니다.");

        if (this.weapon != null) unequipWeapon();

        removeItem((Usable) weapon, 1);

        this.weapon = weapon;
        System.out.print("⚔️ [무기 장착]" + getName() + " 이(가) [" + weapon.name + "] 을(를) 장착했습니다!");
        System.out.println(" (공격력 +" + weapon.bonusAttack + ")");
    }

    public void unequipWeapon() {
        if (this.getWeapon() == null) {
            throw new UnequipWeaponException("현재 장착된 무기가 없습니다.");
        } else {
            addItem((Usable) this.weapon, 1);
            System.out.println("⚔️ [무기 장착 해제]" + getName() + " 이(가) [" + weapon.name + "] 을(를) 장착 해제 했습니다!");
            this.weapon = null;
        }
    }

    public int getFinalAttackPower() {
        int finalAttackPower = 0;
        if (this.getWeapon() == null) {
            finalAttackPower = getAttackPower();
        }  else {
            finalAttackPower = (getAttackPower() + weapon.bonusAttack);
        }

        return finalAttackPower;
    }

    public Weapon getWeapon() { return this.weapon; }


    public abstract void attack(Monster target);

    public int getGold() { return this.gold; }
    public void setGold(int gold) { this.gold = gold; }

    public Map<Usable, Integer> getInventory() { return this.inventory; }

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
        System.out.println(" / 공격력 " + bonusAttackPower + " 향상");
    }

    public void addItem(Usable item, int amount) {
        int currentCount = inventory.getOrDefault(item, 0);
        inventory.put(item, currentCount + amount);

        System.out.println("\uD83D\uDCE6 [아이템 획득] " + item.getItemName() + " 을(를) " + amount + "개 획득 하셨습니다!");
    }

    public void removeItem(Usable item, int amount) {
        String msg = "";
        int currentCount = inventory.getOrDefault(item, 0);

        if (currentCount < amount) {
            throw new ItemNotFoundException(item.getItemName() + " 이(가) 부족 합니다. (현재 보유: " + currentCount + "개 / 필요 개수: " + amount + "개)");
        }

        if ((currentCount - amount) <= 0) {
            inventory.remove(item);
            msg = "\uD83D\uDCE6 [아이템 차감] " + item.getItemName() + " 을(를) 모두 사용하여 삭제 합니다.";
        } else {
            inventory.put(item, currentCount - amount);
            msg = "\uD83D\uDCE6 [아이템 차감] " + item.getItemName() + " 을(를) " + amount + "개 차감 합니다.";
        }

        System.out.println(msg);
    }

}
