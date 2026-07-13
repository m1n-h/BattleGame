package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;

public class Potion implements Usable {
    private String name;
    private int HPHealAmount;
    private int MPHealAmount;

    public Potion(String name, int HPHealAmount, int MPHealAmount) {
        this.name = name;
        this.HPHealAmount = HPHealAmount;
        this.MPHealAmount = MPHealAmount;
    }

    @Override
    public void use(Hero target) {
        if (target == null) return;

        if (this.HPHealAmount > 0) {
            target.setHp(target.getHp() + this.HPHealAmount);
            System.out.println("🧪 [HP 포션 사용] " + target.getName() + "의 HP " + this.HPHealAmount + " 회복! (현재 HP: " + target.getHp() + ")");
        } else if (this.MPHealAmount > 0) {
            target.setMp(target.getMp() + this.MPHealAmount);
            System.out.println("🧪 [MP 포션 사용] " + target.getName() + "의 MP " + this.MPHealAmount + " 회복! (현재 MP: " + target.getMp() + ")");
        }
    }


}
