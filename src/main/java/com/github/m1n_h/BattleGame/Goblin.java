package com.github.m1n_h.BattleGame;

public class Goblin extends Monster {
    int id;

    public Goblin(int id) {
        this.id = id;
        setName("고블린(" + id + ")");
        setHp(30);
        setAttackPower(10);
        setAttackName(new String[] {
                "야비한 돌던지기",
                "독 묻은 단검 투척",
                "조롱하며 메롱하기"
        });
    }

    public void attack(Hero target) {
        System.out.println(getName() + "(이)가 돌던지기로 " + target.getName() + "을(를) 공격합니다!");
        target.takeDamage(getAttackPower());
    }
}
