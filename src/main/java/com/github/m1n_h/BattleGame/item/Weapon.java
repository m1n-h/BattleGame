package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;

import java.io.Serializable;

public class Weapon implements Usable, Equippable, Serializable {
    private static int nextWeaponId = 1;
    private final int weaponId;
    private String name;
    public int bonusAttack;
    private int damage;

    public Weapon(String name, int bonusAttack) {
        this.weaponId = nextWeaponId++;
        this.name = name;
        this.bonusAttack = bonusAttack;
    }

    @Override
    public void use(Hero hero) { hero.equipWeapon(this); }

    @Override
    public String getItemName() { return getName(); }

    @Override
    public void equip(Hero hero) { hero.equipWeapon(this); }

    @Override
    public void unequip(Hero hero) { hero.unequipWeapon(); }

    @Override
    public int getAttackBonus() { return this.bonusAttack; }

    public int getWeaponId() { return weaponId; }
    public String getName() { return this.name; }

    public int getDamage() { return this.damage; }
    public void addDamage(int damage) { this.damage += damage; }
}
