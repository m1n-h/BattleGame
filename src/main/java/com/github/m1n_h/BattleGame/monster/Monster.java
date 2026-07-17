package com.github.m1n_h.BattleGame.monster;

import com.github.m1n_h.BattleGame.character.Character;
import com.github.m1n_h.BattleGame.character.Hero;

public abstract class Monster extends Character {
    private int exp;
    private int dropItem;
    private String[] attackName;
    private int burnTurns = 0;

    public Monster(String name, int hp, int attackPower, int exp) {
        setName(name);
        setHp(hp);
        setAttackPower(attackPower);
        this.exp = exp;
    }

    public String[] getAttackName() { return this.attackName; }
    public void setAttackName(String[] attackName) { this.attackName = attackName;}

    public abstract void attack(Hero target);

    public void applyBurn(int turns) {
        this.burnTurns = turns;
        System.out.println("🔥 " + getName() + " 이(가) " + turns + "턴 동안 화상 상태에 빠졌습니다!");
    }

    public void processTurnEffect() {
        if (this.burnTurns > 0) {
            int burnDamage = 20;
            takeDamage(burnDamage);
            System.out.println("🔥 [화상 효과] " + getName() + " 이(가) 불타며 " + burnDamage + "의 추가 피해를 입었습니다.");
            this.burnTurns--;
        }
    }
}
