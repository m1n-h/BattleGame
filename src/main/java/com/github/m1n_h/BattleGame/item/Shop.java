package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.exception.NotEnoughGoldException;
import com.github.m1n_h.BattleGame.item.Equippable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Shop {

    public static void openShop(ArrayList<Hero> hero) {
        Scanner sc = new Scanner(System.in);
        Hero buyerHero = hero.get(0);

        ShopItem[] items = {
                new ShopItem("기본 숫돌 (공격력 +5)", 30, 5),
                new ShopItem("전설의 숫돌 (공격력 +15)", 70, 15),
                new ShopItem("대마법사의 숨결 (공격력 +30)", 120, 30),
                new ShopItem("화염병", 50, 0),
                new ShopItem("녹슨 검", 10, 10)
        };

        while (true) {
            System.out.println("\n\uD83D\uDED2 [비밀 상점] 상품 목록");
            System.out.println("💰 현재 보유 골드: " + buyerHero.getGold() + "G");

            for (int i = 0; i < items.length; i++) {
                System.out.println((i + 1) + ". " + items[i].getItemName() + " (" + items[i].getItemPrice() + "G)");
            }
            System.out.println((items.length + 1) + ". 상점 나가기 (보스전 진입)");

            System.out.print("구매할 상품 번호 입력: ");
            int choice = sc.nextInt();

            if (choice == items.length + 1) {
                System.out.println("🚪 상점을 나갑니다. 보스전으로 이동합니다!");
                break;
            }

            if (choice > 0 && choice <= items.length) {
                ShopItem selectedItem = items[choice - 1];

                try {
                    if (selectedItem.getItemName().contains("화염병")) {
                        System.out.print("구매 개수 입력: ");
                        int buyCount = sc.nextInt();
                        FireBomb newBomb = new FireBomb();

                        buyItem(buyerHero, newBomb, selectedItem.getItemPrice(), buyCount);
                        System.out.println("✨ [구매 완료] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료!");

                    } else if (selectedItem.getItemName().contains("검")) {
                        RustSword newSword = new RustSword();

                        buyItem(buyerHero, newSword, selectedItem.getItemPrice(), 1);

                        System.out.println("✨ [구매 완료] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료!");
                        System.out.print(selectedItem.getItemName() + " 장착 여부 선택 (1. 장착 / 2. 미장착): ");
                        int swordEquipChoice = sc.nextInt();
                        if (swordEquipChoice == 1) newSword.equip(buyerHero);
                    } else {

                        if (buyerHero.getGold() < selectedItem.getItemPrice()) {
                            throw new NotEnoughGoldException("❌ [골드 부족] 필요 골드: " + selectedItem.getItemPrice() + "G / 보유 골드: " + buyerHero.getGold() + "G");
                        }

                        buyerHero.payGold(selectedItem.getItemPrice());
                        System.out.println("✨ [구매 성공] 남은 골드: " +  buyerHero.getGold() + "G");

                        for (Hero member : hero) {
                            System.out.println(member.getName() + " 의 무기 강화를 시도합니다.");
                            int beforeDamage = member.getAttackPower() + member.getWeapon().bonusAttack;
                            member.getWeapon().bonusAttack += selectedItem.getUpgradeAmount();
                            int afterDamage = member.getAttackPower() + member.getWeapon().bonusAttack;

                            System.out.println("⚔️ " + member.getName() + " 의 공격력: "
                                    + beforeDamage + " ➡️ " + afterDamage + " (+" + selectedItem.getUpgradeAmount() + ")");
                        }
                    }
                } catch (NotEnoughGoldException e) {
                    System.out.println("❌ [구매 실패] " + e.getMessage());
                }

            } else {
                System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    public static void buyItem(Hero hero, Usable newItem, int itemPrice, int count) {
        int totalItemPrice = itemPrice * count;

        if (hero.getGold() < totalItemPrice) {
            throw new NotEnoughGoldException("❌ [골드부족] 필요 골드: " + totalItemPrice + "G / 보유 골드: " + hero.getGold() + "G");
        }

        Inventory inventory = hero.getInventory();
        Usable existingItem = null;

        for (Usable item : inventory.getItems().keySet()) {
            if (item.getItemName().equals(newItem.getItemName())) {
                existingItem = item;
                break;
            }
        }


        Usable itemToAdd = (existingItem != null) ? existingItem : newItem;
        inventory.addItem(itemToAdd, 1);

        int currentCount = inventory.getItems().get(itemToAdd);
        System.out.println("🛒 상점에서 " + newItem.getItemName() + " 을(를) 구매했습니다! (보유: " + (currentCount+1) + "개)");

        hero.setGold(hero.getGold() - itemPrice);
        System.out.println("💰 남은 골드: " + hero.getGold() + "G");
    }

}
