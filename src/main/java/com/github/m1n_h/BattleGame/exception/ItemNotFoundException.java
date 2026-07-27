package com.github.m1n_h.BattleGame.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super("⚠️ [아이템 부족] " + message);
    }
}
