package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;

import java.io.Serializable;

public interface Equippable extends Serializable {
    void equip(Hero target);
    void unequip(Hero target);

    int getAttackBonus();
}
