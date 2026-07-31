package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;

public class Weapon implements Usable {
    public String name;
    public int bonusAttack;
    private int damage;

    public Weapon(String name, int bonusAttack) {
        this.name = name;
        this.bonusAttack = bonusAttack;
    }

    @Override
    public void use(Hero hero) { hero.equipWeapon(this); }

    @Override
    public String getItemName() { return getName(); }

    public String getName() { return this.name; }

    public int getDamage() { return this.damage; }
    public void addDamage(int damage) { this.damage += damage; }
}
