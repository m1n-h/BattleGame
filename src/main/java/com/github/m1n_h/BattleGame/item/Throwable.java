package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.monster.Monster;

import java.io.Serializable;

public interface Throwable extends Usable, Serializable {
    void throwAt(Monster target);
}
