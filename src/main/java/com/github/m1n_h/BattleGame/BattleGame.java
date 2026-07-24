package com.github.m1n_h.BattleGame;

import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.item.*;

import com.github.m1n_h.BattleGame.character.*;
import com.github.m1n_h.BattleGame.item.Throwable;
import com.github.m1n_h.BattleGame.monster.Goblin;
import com.github.m1n_h.BattleGame.monster.KingSlime;
import com.github.m1n_h.BattleGame.monster.Monster;
import com.github.m1n_h.BattleGame.monster.Slime;

import java.util.*;

public class BattleGame {
    public static void main(String[] args) {

        ArrayList<Hero> hero = new ArrayList<>();
        hero.add(new Warrior());
        hero.add(new Mage());
        hero.add(new Archer());

        Map<Usable, Integer> inventory = hero.get(0).getInventory();

        ArrayList<Monster> monster = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            monster.add(new Slime(i));
            monster.add(new Goblin(i));
        }


        System.out.println("GAME START! \uD83C\uDFB5\n");
        System.out.println("⚠\uFE0F 몬스터 연합군 " + monster.size() + "마리 출몰!!\n");

        for (int i = 0; i < monster.size(); i++) {
            Monster target = monster.get(i);

            while (isPartyAlive(hero) && target.getHp() > 0) {
                Hero victim = getRandomAliveHero(hero);
                Hero winner = null;

                for (Hero party : hero) {
                    if (target.getHp() > 0 && party.getHp() > 0) {
                        party.attack(target);
                        System.out.println();

                        if (target.getHp() <= 0) {
                            winner = party;
                            break;
                        }
                    }
                }

                if (target.getHp() <= 0) {
                    System.out.println("🎉 " + target.getName() + " 이(가) 쓰러졌습니다!");

                    if (isPartyAlive(hero)) {
                        if (winner != null) winner.gainRewards(target);
                    }

                    /*if (lowHpVictim != null) {
                        lowHpVictim.setHp(lowHpVictim.getHp() + 50);
                        System.out.println("🧪 포션 꿀꺽! " + lowHpVictim.getName() + " 의 HP가 회복되었습니다. (현재 HP: " + lowHpVictim.getHp() + ")");
                    }

                    if (lowMpVictim != null) {
                        lowMpVictim.setMp(lowMpVictim.getMp() + 100);
                        System.out.println("🧪 포션 꿀꺽! " + lowMpVictim.getName() + " 의 MP가 회복되었습니다. (현재 MP: " + lowMpVictim.getMp() + ")");
                    }*/

                    break;
                }


                if (target.getHp() > 0 && victim != null) {
                    target.attack(victim);
                    System.out.println("💥 " + victim.getName() + " 이(가) " + target.getName() + " 에게 공격당했습니다! (남은 HP: " + victim.getHp() + ")");
                    System.out.println();
                }

                if (!isPartyAlive(hero)) {
                    System.out.println("💀 모든 용사가 쓰러졌습니다...\nYou Lose!\n");
                    break;
                }
            }
        }

        if (isPartyAlive(hero)) {

            Shop.openShop(hero);

            Hero lowHpVictim = getLowestHpHero(hero);
            Hero lowMpVictim = getLowestMpHero(hero);

            Iterator<Map.Entry<Usable, Integer>> iterator = inventory.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Usable, Integer> entry = iterator.next();
                Usable item = entry.getKey();
                int count = entry.getValue();

                if (count <= 0) {
                    System.out.println("❌ 보유한 아이템이 없습니다!");
                    continue;
                }

                if (count > 1) {
                    inventory.put(item, count - 1);
                } else {
                    iterator.remove();
                }

                item.use(lowHpVictim);
                item.use(lowMpVictim);

            }

        }


        Scanner sc = new Scanner(System.in);
        int[] bossWeak = {32, 56, 76, 93, 184, 263, 489, 627, 723, 956};

        KingSlime kingSlime = new KingSlime(bossWeak);

        System.out.println("\uD83C\uDFB5 쿠구구궁... 최종 보스 " + kingSlime.getName() + " 이(가) 나타났습니다! (HP: " + kingSlime.getHp() + ") \uD83C\uDFB5\\");
        System.out.println(kingSlime.getName() + " 의 약점 구역을 예측해 타격하세요!");
        System.out.println();

        boolean isAngry = false;
        int kingSlimeMaxHp = kingSlime.getHp();

        while (kingSlime.getHp() > 0 && isPartyAlive(hero)) {
            Hero winner = null;

            for (Hero activeHero : hero) {
                if (activeHero.getHp() <= 0) continue;

                boolean isTurnUsed = false;

                while (!isTurnUsed) {
                    System.out.println("\n\uD83D\uDEE1\uFE0F [" + activeHero.getName() + " 의 턴]");
                    System.out.print("⚔\uFE0F 1. 공격 | \uD83C\uDF92 2. 인벤토리 :");

                    int choice = sc.nextInt();
                    if (choice == 1) {
                        activeHero.attack(kingSlime);
                        isTurnUsed = true;

                    } else if (choice == 2) {
                        int beforeEmptyCount = 0;
                        for (int count : inventory.values()) {
                            beforeEmptyCount += count;
                        }

                        useItemBattle(activeHero, inventory, kingSlime, sc);

                        int afterEmptyCount = 0;
                        for (int count : inventory.values()) {
                            afterEmptyCount += count;
                        }

                        if (afterEmptyCount < beforeEmptyCount) {
                            System.out.println("✨ 아이템을 성공적으로 사용하여 턴이 소모됩니다.");
                            isTurnUsed = true;
                        } else {
                            System.out.println("↩️ 아이템을 사용하지 않았습니다. 다시 행동을 선택해 주세요.");
                        }
                    }
                }

                if (kingSlime.getHp() <= 0) {
                    winner = activeHero;
                    break;
                }
            }

            if (kingSlime.getHp() > 0) {
                List<Usable> itemList = new ArrayList<>(inventory.keySet());
                for (int i = 0; i < itemList.size(); i++) {
                    Usable item = itemList.get(i);
                    if (item instanceof Throwable) {
                        ((Throwable) item).throwAt(kingSlime);

                        int currentCount = inventory.get(item);
                        if (currentCount > 1) {
                            inventory.put(item, currentCount - 1);
                        } else {
                            inventory.remove(item);
                        }

                        break;
                    }
                }
            }

            if (kingSlime.getHp() > 0) {

                try {
                    System.out.print("공격 구역 입력: ");
                    int attackZone = sc.nextInt();
                    String attackResult = kingSlime.takeDamageFromScan(attackZone);

                    if (attackResult.equals("Hit")) {
                        System.out.println("\uD83D\uDCA5 콰광! 약점 타격 성공! 보스의 방어벽이 무너졌습니다!");
                        System.out.println("⚔️ [PARTY ATTACK] 용사 일행이 일제히 총공격을 감행합니다! ⚔️\n");

                        for (Hero partyMember : hero) {
                            if (kingSlime.getHp() > 0 && partyMember.getHp() > 0) {
                                partyMember.attack(kingSlime);
                                System.out.println();

                                if (kingSlime.getHp() <= 0) {
                                    winner = partyMember;
                                    break;
                                }
                            }
                        }
                        if (kingSlime.getHp() > 0)
                            System.out.println("🦖 " + kingSlime.getName() + " 의 남은 HP: " + kingSlime.getHp());

                    } else if (attackResult.equals("Miss")) {
                        System.out.println("🛡️ 팅! 공격이 단단한 외피에 막혔습니다. " + kingSlime.getName() + " 의 남은 HP: " + kingSlime.getHp());
                    }

                } catch (InputMismatchException e) {
                    System.out.println("❌ 잘못된 입력입니다.");
                    sc.nextLine();
                }
            }

            if (kingSlime.getHp() > 0) {
                Hero targetHero = getRandomAliveHero(hero);

                kingSlime.processTurnEffect();
                if (kingSlime.getHp() <= 0) {
                    winner = targetHero;
                    System.out.println("💀 " + kingSlime.getName() + " 이(가) 화상 피해를 버티지 못하고 쓰러졌습니다!");
                    break;
                }

                if (targetHero != null) {
                    System.out.println();

                    if (!isAngry && (kingSlime.getHp() <= kingSlimeMaxHp * 0.3)) {
                        isAngry = true;

                        System.out.println(kingSlime.getName() + " 이(가) 분노해 공격력이 2배가 되었습니다!");
                        //targetHero.takeDamage(kingSlime.getAttackPower() * 2);
                    }

                    System.out.println("🤢 " + kingSlime.getName() + " 이(가) 거대한 몸집으로 " + targetHero.getName() + " 을(를) 짓누릅니다!");

                    int finalDamage = isAngry ? kingSlime.getAttackPower() * 2 : kingSlime.getAttackPower();
                    targetHero.takeDamage(finalDamage);

                    System.out.println("   (남은 HP: " + targetHero.getHp() + ")");
                    System.out.println();
                }
            }

            if (kingSlime.getHp() <= 0) {
                if (winner != null) winner.gainRewards(kingSlime);
                System.out.println("\n🎉 축하합니다! " + kingSlime.getName() + " 을(를) 완전히 격파하고 세계를 구했습니다! 🏆");
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

    private static Hero getLowestHpHero(ArrayList<Hero> party) {
        ArrayList<Hero> aliveHero = new ArrayList<>();
        for (Hero hero : party) {
            if (hero.getHp() > 0) aliveHero.add(hero);
        }
        if (aliveHero.isEmpty())  return null;

        Hero lowestHpHero = aliveHero.get(0);
        for (int i = 1; i < aliveHero.size(); i++) {
            Hero current = aliveHero.get(i);
            if (current.getHp() < lowestHpHero.getHp()) {
                lowestHpHero = current;
            }
        }

        return lowestHpHero;
    }

    private static Hero getLowestMpHero(ArrayList<Hero> party) {
        ArrayList<Hero> aliveHero = new ArrayList<>();
        for (Hero hero : party) {
            if (hero.getMp() > 0) aliveHero.add(hero);
        }
        if (aliveHero.isEmpty())  return null;

        Hero lowestMpHero = aliveHero.get(0);
        for (int i = 1; i < aliveHero.size(); i++) {
            Hero current = aliveHero.get(i);
            if (current.getMp() < lowestMpHero.getMp()) {
                lowestMpHero = current;
            }
        }

        return lowestMpHero;
    }

    public static void useItemBattle(Hero user, Map<Usable, Integer> inventory, Monster target, Scanner sc) {
        System.out.println("\n\uD83C\uDF92 [ 인벤토리 목록 ]");
        boolean hasItem = false;

        int index = 1;
        for (Map.Entry<Usable, Integer> entry : inventory.entrySet()) {
            Usable item = entry.getKey();
            int count = entry.getValue();
            if (inventory.get(item) != null) {
                System.out.println(index + ". " + item.getItemName() + "(보유: " +  count + "개)");
                hasItem = true;
                index++;
            } else {
                System.out.println(index + ". [ 비어있음 ]");
            }
        }

        if (!hasItem) {
            System.out.println("❌ 인벤토리가 비어 있습니다.");
            return;
        }

        System.out.println("사용할 아이템 번호를 선택하세요 (0 : 취소) : ");
        int itemChoice = sc.nextInt() - 1;

        List<Usable> itemList = new ArrayList<>(inventory.keySet());
        if (itemChoice < 0 || itemChoice >= itemList.size()) {
            System.out.println("취소했거나 올바르지 않은 슬롯 입니다.");
            return;
        }

        Usable selectedItem = itemList.get(itemChoice);
        int currentCount = inventory.get(selectedItem);

        if (selectedItem instanceof Potion) {
            selectedItem.use(user);
        } else if (selectedItem instanceof FireBomb) {
            ((FireBomb) selectedItem).throwAt(target);
        } else if (selectedItem instanceof Equippable) {
            ((Equippable) selectedItem).equip(user);
        }

        if (currentCount > 1) {
            inventory.put(selectedItem, currentCount - 1);
        } else {
            inventory.remove(selectedItem);
        }

    }

}
