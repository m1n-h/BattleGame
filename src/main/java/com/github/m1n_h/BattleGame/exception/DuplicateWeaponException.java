package com.github.m1n_h.BattleGame.exception;

public class DuplicateWeaponException extends RuntimeException {

    public DuplicateWeaponException(String message) {
        super("⚔️ [무기 중복] " + message);
    }
}
