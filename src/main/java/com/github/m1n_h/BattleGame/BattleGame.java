package com.github.m1n_h.BattleGame;

public class BattleGame {
    public static void main(String[] args) {
        Hero hero = new Hero();

        Weapon legendarySword = new Weapon("엑스칼리버", 25);
        hero.equipWeapon(legendarySword);
        System.out.println();

        Slime[] slime = new Slime[5];
        for (int i = 0; i < slime.length; i++) {
            slime[i] = new Slime(i+1);
        }

        System.out.println("\uD83C\uDFB5 몬스터 무리가 나타났습니다! 배틀 시작! \uD83C\uDFB5\\n");

        for (int i = 0; i < slime.length; i++) {
            while (hero.hp > 0 && slime[i].hp > 0) {
                hero.attack(slime[i]);
                System.out.println();

                if (slime[i].hp <= 0) {
                    System.out.println("🎉 " + slime[i].name + "이(가) 쓰러졌습니다!\nYou Win");
                    break;
                }

                slime[i].attack(hero);
                System.out.println();

                if (hero.hp <= 0) {
                    System.out.println("💀 " + hero.name + "이(가) 쓰러졌습니다...\nYou Lose!");
                    break;
                }
            }
        }

        System.out.println("GAME OVER! \uD83C\uDFB5");
    }
}
