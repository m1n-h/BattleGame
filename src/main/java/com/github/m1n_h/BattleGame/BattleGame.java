package com.github.m1n_h.BattleGame;

import java.util.Scanner;
import java.util.ArrayList;

public class BattleGame {
    public static void main(String[] args) {

        ArrayList<Hero> hero = new ArrayList<>();
        hero.add(new Warrior());
        hero.add(new Mage());

        ArrayList<Monster> monster = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            monster.add(new Slime(i));
            monster.add(new Goblin(i));
        }

        System.out.println("GAME START! \uD83C\uDFB5\n");
        System.out.println("⚠\uFE0F 몬스터 연합군 " + monster.size() + "마리 출몰!!\n");
        System.out.println("☄️ 전사 " + hero.get(0).getName() + "(이)가 대지진 주문을 외웁니다!\n");

        int earthquakeDamage = 30;

        for (int i = 0; i < monster.size(); i++) {
            Monster target = monster.get(i);
            target.takeDamage(earthquakeDamage);

            if (target.getHp() <= 0) {
                System.out.println("\uD83D\uDC80 " + target.getName() + " 은(는) 지진 충격을 이기지 못하고 쓰러졌습니다!\n");
            } else {
                System.out.println("💥 " + target.getName() + " 에게 " + earthquakeDamage + "의 피해! (남은 HP: " + target.getHp() + ")\n");

                while (isPartyAlive(hero) && target.getHp() > 0) {
                    Hero victim = getRandomAliveHero(hero);

                    for (Hero party : hero) {
                        if (target.getHp() > 0 && party.getHp() > 0) {
                            party.attack(target);
                            System.out.println();
                        }
                    }

                    if (target.getHp() <= 0) {
                        System.out.println("🎉 " + target.getName() + "이(가) 쓰러졌습니다!\n");

                        if (victim != null) {
                            victim.setHp(victim.getHp() + 50);
                            System.out.println("🧪 포션 꿀꺽! " + victim.getName() + "의 HP가 회복되었습니다. (현재 HP: " + victim.getHp() + ")\n");
                        }
                        break;
                    }


                    if (target.getHp() > 0 && victim != null) {
                        target.attack(victim);
                        System.out.println("💥 " + victim.getName() + "이(가) " + target.getName() + "에게 공격당했습니다! (남은 HP: " + victim.getHp() + ")");
                        System.out.println();
                    }

                    if (!isPartyAlive(hero)) {
                        System.out.println("💀 모든 용사가 쓰러졌습니다...\nYou Lose!\n");
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

        while (bossSlime.getHp() > 0 && isPartyAlive(hero)) {
            System.out.print("공격 구역 입력: ");
            int attackZone =  sc.nextInt();

            String attackResult = bossSlime.takeDamageFromScan(attackZone);

            if (attackResult.equals("Hit")) {
                System.out.println("\uD83D\uDCA5 콰광! 약점 타격 성공! 보스의 방어벽이 무너졌습니다!");
                System.out.println("⚔️ [PARTY ATTACK] 용사 일행이 일제히 총공격을 감행합니다! ⚔️\n");

                for (Hero partyMember : hero) {
                    if (bossSlime.getHp() > 0 && partyMember.getHp() > 0) {
                        partyMember.attack(bossSlime);
                        System.out.println();
                    }
                }
                if (bossSlime.getHp() > 0) System.out.println("🦖 " + bossSlime.getName() + "의 남은 HP: " + bossSlime.getHp());

            } else if (attackResult.equals("Miss")) {
                System.out.println("🛡️ 팅! 공격이 단단한 외피에 막혔습니다. " + bossSlime.getName() + "의 남은 HP: " + bossSlime.getHp());
            }

            if (bossSlime.getHp() > 0) {
                Hero bossVictim = getRandomAliveHero(hero);

                if (bossVictim != null) {
                    System.out.println();
                    System.out.println("🤢 " + bossSlime.getName() + "이 거대한 몸집으로 " + bossVictim.getName() + "을(를) 짓누릅니다! (남은 HP: " + bossVictim.getHp() + ")");

                    bossVictim.takeDamage(bossSlime.getAttackPower());
                    System.out.println();
                }
            }

            if (bossSlime.getHp() <= 0) {
                System.out.println("\n🎉 축하합니다! " + bossSlime.getName() + "을(를) 완전히 격파하고 세계를 구했습니다! 🏆");
                break;
            }

            if (!isPartyAlive(hero)) {
                System.out.println("💀 콰당... 모든 용사가 쓰러졌습니다.\nYou Lose!\n");
                break;
            }
        }

        System.out.println();
        System.out.println("GAME OVER! \uD83C\uDFB5");
    }

    private static boolean isPartyAlive(ArrayList<Hero> party) {
        for (Hero hero : party) {
            if (hero.getHp() > 0) return true;
        }
        return false;
    }

    private static Hero getRandomAliveHero(ArrayList<Hero> party) {
        ArrayList<Hero> aliveHero = new ArrayList<>();
        for (Hero hero : party) {
            if (hero.getHp() > 0) aliveHero.add(hero);
        }
        if (aliveHero.isEmpty())  return null;

        int randomIdx = (int)  (Math.random() * aliveHero.size());
        return aliveHero.get(randomIdx);
    }
}
