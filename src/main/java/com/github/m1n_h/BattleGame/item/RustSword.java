package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;

import java.util.Objects;

public class RustSword extends Weapon implements Usable, Equippable {
    private boolean isEquipped = false;

    public RustSword() {
        super("녹슨 검", 10);
    }

    @Override
    public void use(Hero target) {
        System.out.println("🎒 " + getItemName() + " 은(는) 장비 아이템입니다. 장착하여 사용하세요!");
    }

    @Override
    public void equip(Hero target) {
        if (!isEquipped) {
            isEquipped = true;
            System.out.println("⚔️ " + target.getName() + " 이(가) " + getItemName() + " 을(를) 장착했습니다! (공격력: +" + bonusAttack + ")");
        }
    }

    @Override
    public void unequip(Hero target) {
        if (isEquipped) {
            isEquipped = false;
            System.out.println("🛡️ " + target.getName() + " 이(가) " + getItemName() + " 을(를) 장착 해제했습니다.");
        }
    }

    @Override
    public String getItemName() { return this.getItemName(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usable usable = (Usable) o;
        return Objects.equals(getItemName(), usable.getItemName());
    }

    @Override
    public int hashCode() { return Objects.hash(getItemName()); }

    @Override
    public String toString(){ return getItemName(); }

    @Override
    public int getAttackBonus() { return bonusAttack; }
}
