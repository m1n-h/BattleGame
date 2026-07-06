package com.github.m1n_h.BattleGame;

import java.util.Scanner;

public class BattleGame {
    public static void main(String[] args) {
        Hero hero = new Hero();

        Weapon legendarySword = new Weapon("엑스칼리버", 25);
        hero.equipWeapon(legendarySword);
        System.out.println();

        Slime[] slime = new Slime[5];
        for (int i = 0; i < slime.length; i++) {
            slime[i] = new Slime(i+1, (i+1)*10, (i+1)*5);
        }

        System.out.println("\uD83C\uDFB5 몬스터 무리가 나타났습니다! 배틀 시작! \uD83C\uDFB5\\n");

        for (int i = 0; i < slime.length; i++) {
            while (hero.hp > 0 && slime[i].getHp() > 0) {
                hero.attack(slime[i]);
                System.out.println();

                if (slime[i].getHp() <= 0) {
                    System.out.println("🎉 " + slime[i].name + "이(가) 쓰러졌습니다!\n");
                    break;
                }

                slime[i].attack(hero);
                System.out.println();

                if (hero.hp <= 0) {
                    System.out.println("💀 " + hero.name + "이(가) 쓰러졌습니다...\nYou Lose!\n");
                    break;
                }
            }
        }

        Scanner  sc = new Scanner(System.in);
        int[] bossWeak = {32, 56, 76, 93, 184, 263, 489, 627, 723, 956};

        KingSlime bossSlime = new KingSlime(bossWeak);

        System.out.println("\uD83C\uDFB5 쿠구구궁... 최종 보스 " + bossSlime.getName() + "이(가) 나타났습니다! (HP: " + bossSlime.getHp() + ") \uD83C\uDFB5\\");
        System.out.println(bossSlime.getName() + "의 약점 구역을 예측해 타격하세요!");
        System.out.println();

        while (bossSlime.getHp() > 0 && hero.hp > 0) {
            System.out.print("공격 구역 입력: ");
            int attackZone =  sc.nextInt();

            String attackResult = bossSlime.takeDamageFromScan(attackZone);

            if (attackResult.equals("Hit")) {
                System.out.println("\uD83D\uDCA5 콰광! 약점 타격 성공! 보스의 남은 HP: " + bossSlime.getHp());
            } else if (attackResult.equals("Miss")) {
                System.out.println("🛡️ 팅! 공격이 단단한 외피에 막혔습니다. 보스의 남은 HP: " + bossSlime.getHp());
            } else if (attackResult.equals("Kill")) {
                System.out.println("🎉 축하합니다! " + bossSlime.getName() + "을(를) 완전히 격파하고 세계를 구했습니다!");
                break;
            }

            System.out.println();
            System.out.println("---------- " + bossSlime.getName() + "의 턴! ----------");
            System.out.println("🤢 " + bossSlime.getName() + "이 거대한 몸집으로 용사를 짓누릅니다!");

            hero.hp -= 20;

            System.out.println("🩸 용사가 20의 데미지를 입었습니다! (용사의 남은 HP: " + hero.hp + ")");
            System.out.println("---------------------------------------");
            System.out.println();

            if (hero.hp <= 0) {
                System.out.println("💀 콰당... 용사 " + hero.name + "(이)가 쓰러졌습니다. 게임 오버...");
            }
        }

        System.out.println("GAME OVER! \uD83C\uDFB5");
    }
}
