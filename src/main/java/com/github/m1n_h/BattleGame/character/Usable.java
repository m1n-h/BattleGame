package com.github.m1n_h.BattleGame.character;

import java.io.Serializable;

public interface Usable extends Serializable {
    void use(Hero target);
    String getItemName();
}
