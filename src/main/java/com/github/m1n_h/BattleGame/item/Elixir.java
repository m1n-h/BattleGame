package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Elixir implements Usable, Serializable {
    private final String name = "파티 엘릭서";

    @Override
    public String getItemName() { return this.name; }

    @Override
    public void use(Hero hero) { System.out.println("🧪 " + hero.getName() + " 이(가) " + name + " 을(를) 사용합니다!"); }

    public void useAll(Hero user, List<Hero> party) {
        use(user);
        System.out.println("✨ " + name + " 의 신비로운 기운이 파티 전체에 퍼집니다!");

        party.stream()
                .filter(hero -> hero.getHp() > 0)
                .forEach(hero -> {
                    int healHp = (int) (hero.getHp() * 1.3);
                    int healMp = (int) (hero.getMp() * 1.3);

                    hero.setHp(healHp);
                    hero.setMp(healMp);

                    System.out.println("  💚 " + hero.getName() + " 의 HP/MP가 회복 되었습니다!");
                    System.out.println("      HP: " + hero.getHp() + " / MP: " + hero.getMp());
                });
    }

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
    public String toString() { return getItemName(); }
}
