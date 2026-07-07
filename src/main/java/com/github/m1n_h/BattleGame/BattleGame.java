package com.github.m1n_h.BattleGame;

import java.util.Scanner;
import java.util.ArrayList;

public class BattleGame {
    public static void main(String[] args) {
        Hero hero = new Hero();

        Weapon legendarySword = new Weapon("엑스칼리버", 25);
        hero.equipWeapon(legendarySword);
        System.out.println();

        ArrayList<Slime> slimes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            slimes.add(new Slime(i, i*10, i*5));
        }

        System.out.println("GAME START! \uD83C\uDFB5");
        System.out.println();
        System.out.println("⚠\uFE0F 슬라임 군단 " + slimes.size() + "마리 출몰!!");
        System.out.println();
        System.out.println("☄️ 용사 " + hero.name + "(이)가 대지진 주문을 외웁니다! 모든 슬라임이 지진의 충격을 받습니다!");
        System.out.println();

        int earthquakeDamage = 30;

        for (int i = 0; i < slimes.size(); i++) {
            Slime target = slimes.get(i);
            target.takeDamage(earthquakeDamage);

            if (target.getHp() <= 0) {
                System.out.println("\uD83D\uDC80 슬라임(" + target.id + ") 은(는) 지진 충격을 이기지 못하고 쓰러졌습니다!\n\n");
            } else {
                System.out.println("💥 슬라임(" + target.id + ") 에게 " + earthquakeDamage + "의 피해! (남은 HP: " + target.getHp() + ")\n");

                while (hero.hp > 0 && target.getHp() > 0) {
                    hero.attack(target);
                    System.out.println();

                    if (target.getHp() <= 0) {
                        System.out.println("🎉 " + target.name + "이(가) 쓰러졌습니다!\n");

                        hero.hp += 50;
                        System.out.println("🧪 포션 꿀꺽! 용사 " + hero.name + "의 HP가 회복되었습니다. (현재 HP: " + hero.hp + ")\n");
                        break;
                    }

                    target.attack(hero);
                    System.out.println();

                    if (hero.hp <= 0) {
                        System.out.println("💀 " + hero.name + "이(가) 쓰러졌습니다...\nYou Lose!\n");
                        break;
                    }
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
                System.out.println("\uD83D\uDCA5 콰광! 약점 타격 성공! " + bossSlime.getName() + "의 남은 HP: " + bossSlime.getHp());
            } else if (attackResult.equals("Miss")) {
                System.out.println("🛡️ 팅! 공격이 단단한 외피에 막혔습니다. " + bossSlime.getName() + "의 남은 HP: " + bossSlime.getHp());
            } else if (attackResult.equals("Kill")) {
                System.out.println("🎉 축하합니다! " + bossSlime.getName() + "을(를) 완전히 격파하고 세계를 구했습니다!");
                break;
            }

            if (bossSlime.getHp() > 0) {
                System.out.println();
                System.out.println("🤢 " + bossSlime.getName() + "이 거대한 몸집으로 용사를 짓누릅니다!");

                int bossSlimeAttackDamage = 70;
                hero.takeDamage(bossSlimeAttackDamage);

                //System.out.println("🩸 용사 " + hero.name + "(이)가 " + bossSlimeAttackDamage + "의 데미지를 입었습니다! (용사의 남은 HP: " + hero.hp + ")");
                System.out.println();
            }

            if (hero.hp <= 0) {
                System.out.println("💀 콰당... 용사 " + hero.name + "(이)가 쓰러졌습니다.\nYou Lose!\n");
            }
        }

        System.out.println();
        System.out.println("GAME OVER! \uD83C\uDFB5");
    }
}
