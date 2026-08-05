package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Hero;
import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.exception.NotEnoughGoldException;
import com.github.m1n_h.BattleGame.exception.UnequipWeaponException;
import com.github.m1n_h.BattleGame.item.Equippable;

import java.util.*;

public class Shop {

    private static final Map<Integer, ShopItem> itemList = new HashMap<>();

    public static void openShop(ArrayList<Hero> hero) {
        Scanner sc = new Scanner(System.in);
        Hero buyerHero = hero.get(0);

        itemList.put(1, new ShopItem("기본 숫돌 (공격력 +5)", 30, 5));
        itemList.put(2, new ShopItem("전설의 숫돌 (공격력 +15)", 70, 15));
        itemList.put(3, new ShopItem("대마법사의 숨결 (공격력 +30)", 120, 30));
        itemList.put(4, new ShopItem("화염병", 50, 0));
        itemList.put(5, new ShopItem("녹슨 검", 10, 10));
        itemList.put(6, new ShopItem("파티 엘릭서", 100, 0));

        while (true) {
            System.out.println("\n\uD83D\uDED2 [비밀 상점] 상품 목록");
            System.out.println("💰 현재 보유 골드: " + buyerHero.getGold() + "G");

            System.out.println("0. 상점 나가기 (보스전 진입)");
            for (Map.Entry<Integer, ShopItem> entry : itemList.entrySet()) {
                ShopItem items = entry.getValue();
                System.out.println(entry.getKey() + ". " + items.getItemName() + " (" + items.getItemPrice() + "G)");
            }

            System.out.print("구매할 상품 번호 입력: ");
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("🚪 상점을 나갑니다. 보스전으로 이동합니다!");
                break;
            }

            if (itemList.containsKey(choice)) {
                ShopItem selectedItem = itemList.get(choice);

                try {
                    if (selectedItem.getItemName().contains("화염병")) {
                        System.out.print("구매 개수 입력: ");
                        int buyCount = sc.nextInt();
                        FireBomb newBomb = new FireBomb();

                        buyItem(buyerHero, newBomb, selectedItem.getItemPrice(), buyCount);
                        System.out.println("✨ [구매 완료] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료!");

                    } else if (selectedItem.getItemName().contains("검")) {
                        RustSword newSword = new RustSword();
                        Weapon newWeapon = new Weapon(newSword.getItemName(), newSword.getAttackBonus());

                        buyItem(buyerHero, newWeapon, selectedItem.getItemPrice(), 1);

                        System.out.println("✨ [구매 완료] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료!");
                        System.out.print(selectedItem.getItemName() + " 장착 여부 선택 (1. 장착 / 2. 미장착): ");
                        int swordEquipChoice = sc.nextInt();
                        if (swordEquipChoice == 1) {
                            try {
                                if (selectedItem instanceof Equippable equippableItem) {
                                    equippableItem.equip(buyerHero);
                                }
                            } catch (DuplicateFormatFlagsException e) {
                                System.out.println(e.getMessage());
                            } catch (UnequipWeaponException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    } else if (selectedItem.getItemName().contains("엘릭서")) {
                        System.out.print("구매 개수 입력: ");
                        int buyCount = sc.nextInt();
                        Elixir newElixir = new Elixir();

                        buyItem(buyerHero, newElixir, selectedItem.getItemPrice(), buyCount);
                        System.out.println("✨ [구매 완료] 인벤토리에 " + selectedItem.getItemName() + " 추가 완료!");

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
                System.out.println("⚠️ 해당 상품 번호가 존재하지 않습니다. 다시 입력해 주세요.");
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
        inventory.addItem(itemToAdd, count);
        hero.setGold(hero.getGold() - totalItemPrice);

        int currentCount = inventory.getItems().get(itemToAdd);
        System.out.println("🛒 상점에서 " + newItem.getItemName() + " 을(를) 구매했습니다! (보유: " + currentCount + "개)");
        System.out.println("💰 남은 골드: " + hero.getGold() + "G");
    }

    public static void addShopItem(int key, ShopItem item) {
        if (itemList.containsKey(key)) {
            System.out.println("이미 " + key + "번 슬롯에 상품이 존재합니다.");
        } else {
            itemList.put(key, item);
        }
    }

    public static Map<Integer, ShopItem> getShopItems() { return itemList; }

}
