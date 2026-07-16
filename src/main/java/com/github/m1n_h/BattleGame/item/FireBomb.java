package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.monster.Monster;

public class FireBomb implements Usable, Throwable {
    private String name = "화염병";
    private int damage = 150;

    @Override
    public void use(Hero target) {
        System.out.println("🔥 " + name + " 을(를) 잘못 사용해 주변이 후끈 거립니다.");
    }

    @Override
    public void throwAt(Monster target) {
        System.out.println("💥 " + name + " 을(를) " + target.getName() + "에게 힘껏 던졌습니다!");
        target.takeDamage(damage);

        System.out.println("🔥 " + target.getName() + " 이(가) " + damage + "의 피해를 입었습니다. (남은 HP: " + target.getHp() + ")");

        target.applyBurn(3);
    }

    @Override
    public String getItemName() { return this.name; }
}
