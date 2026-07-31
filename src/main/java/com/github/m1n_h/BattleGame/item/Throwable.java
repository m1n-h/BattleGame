package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.monster.Monster;

public interface Throwable extends Usable {
    void throwAt(Monster target);
}
