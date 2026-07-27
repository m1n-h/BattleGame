package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;

public interface Equippable {
    void equip(Hero target);
    void unequip(Hero target);

    int getAttackBonus();
}
