package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;

public class RustSword implements Usable, Equippable{
    private String name = "녹슨 검";
    private int attackBonus = 10;
    private boolean isEquipped = false;

    @Override
    public void use(Hero target) {
        System.out.println("🎒 " + name + " 은(는) 장비 아이템입니다. 장착하여 사용하세요!");
    }

    @Override
    public void equip(Hero target) {
        if (!isEquipped) {
            isEquipped = true;
            System.out.println("⚔️ " + target.getName() + " 이(가) " + name + " 을(를) 장착했습니다! (공격력: +" + attackBonus + ")");
        }
    }

    @Override
    public void unequip(Hero target) {
        if (isEquipped) {
            isEquipped = false;
            System.out.println("🛡️ " + target.getName() + " 이(가) " + name + " 을(를) 장착 해제했습니다.");
        }
    }

    @Override
    public String getItemName() { return this.name; }
}
