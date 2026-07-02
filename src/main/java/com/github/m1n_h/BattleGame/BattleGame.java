package com.github.m1n_h.BattleGame;

public class BattleGame {
    public static void main(String[] args) {
        Hero hero = new Hero();
        Slime slime = new Slime();

        System.out.println("\uD83C\uDFB5 GAME START! \uD83C\uDFB5");
        System.out.println(hero.name + " vs " + slime.name + "\n");

        while (hero.hp > 0 && slime.hp > 0) {
            hero.attack(slime);
            System.out.println();

            if (slime.hp <= 0) {
                System.out.println("🎉 " + slime.name + "이(가) 쓰러졌습니다!\nYou Win");
                break;
            }

            slime.attack(hero);
            System.out.println();

            if (hero.hp <= 0) {
                System.out.println("💀 " + hero.name + "이(가) 쓰러졌습니다...\nYou Lose!");
                break;
            }
        }

        System.out.println("GAME OVER! \uD83C\uDFB5");
    }
}
