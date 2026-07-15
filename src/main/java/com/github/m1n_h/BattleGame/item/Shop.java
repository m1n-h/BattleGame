package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.item.Equippable;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    public static int openShop(ArrayList<Hero> hero, int currentGold, Usable[] inventory) {
        Scanner sc = new Scanner(System.in);

        ShopItem[] items = {
                new ShopItem("기본 숫돌 (공격력 +5)", 30, 5),
                new ShopItem("전설의 숫돌 (공격력 +15)", 70, 15),
                new ShopItem("대마법사의 숨결 (공격력 +30)", 120, 30),
                new ShopItem("화염병", 50, 0),
                new ShopItem("녹슨 검", 10, 10)
        };

        while (true) {
            System.out.println("\n\uD83D\uDED2 [비밀 상점] 상품 목록");
            System.out.println("💰 현재 보유 골드: " + currentGold + "G");

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

                if (currentGold >= selectedItem.getItemPrice()) {
                    if (selectedItem.getItemName().contains("화염병")) {
                        FireBomb newBomb = new FireBomb();
                        boolean buySuccess = buyItem(inventory, newBomb);
                        if (buySuccess) {
                            currentGold -= selectedItem.getItemPrice();
                            System.out.println(
                                    "✨ [구매 성공] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료! " +
                                    "남은 골드: " + currentGold + "G"
                            );
                        } else {
                            System.out.println("❌ [구매 실패] 공간 부족!");
                        }

                    } else if (selectedItem.getItemName().contains("검")) {
                        RustSword newSword = new RustSword();
                        boolean buySuccess = buyItem(inventory, newSword);
                        if (buySuccess) {
                            currentGold -= selectedItem.getItemPrice();
                            System.out.println(
                                    "✨ [구매 성공] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료! " +
                                    "남은 골드: " + currentGold + "G"
                            );

                            System.out.print(selectedItem.getItemName() + " 장착 여부 선택 (1. 장착 / 2. 미장착): ");
                            Scanner swordEquip = new Scanner(System.in);
                            int swordEquipChoice = swordEquip.nextInt();
                            if (swordEquipChoice == 1) {
                                Hero targetHero = hero.get(0);
                                newSword.equip(targetHero);
                            }

                        } else {
                            System.out.println("❌ [구매 실패] 공간 부족!");
                        }

                    } else {
                        currentGold -= selectedItem.getItemPrice();
                        System.out.println("✨ [구매 성공] 남은 골드: " + currentGold + "G");

                        for (Hero member : hero) {
                            System.out.println(member.getName() + " 의 무기 강화를 시도합니다.");
                            int beforeDamage = member.getAttackPower() + member.getWeapon().bonusAttack;
                            member.getWeapon().bonusAttack += selectedItem.getUpgradeAmount();
                            int afterDamage = member.getAttackPower() + member.getWeapon().bonusAttack;

                            System.out.println("⚔️ " + member.getName() + " 의 공격력: "
                                    + beforeDamage + " ➡️ " + afterDamage + " (+" + selectedItem.getUpgradeAmount() + ")");
                        }
                    }

                } else {
                    System.out.println("❌ 골드가 부족합니다!");
                }

            } else {
                System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }

        return currentGold;
    }

    public static boolean buyItem(Usable[] inventory, Usable newItem) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = newItem;
                System.out.println("🛒 상점에서 " + newItem + " 을(를) 구매하여 인벤토리 " + (i+1) + "번 칸에 추가 되었습니다.");
                return true;
            }
        }
        System.out.println("❌ 공간이 부족합니다!");
        return false;
    }

    public boolean buyAndDeliver(Usable[] inventory, ShopItem selectedShopItem) {
        String name = selectedShopItem.getItemName();
        boolean result = false;

        if (name.contains("화염병")) {
            FireBomb newBomb = new FireBomb();
            result = buyItem(inventory, newBomb);
        }
        
        return result;
    }
}
