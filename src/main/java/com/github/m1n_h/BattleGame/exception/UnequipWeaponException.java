package com.github.m1n_h.BattleGame.exception;

public class UnequipWeaponException extends RuntimeException {

    public UnequipWeaponException(String message) {
        super("⚔️ [무기 미장착] " + message);
    }
}
