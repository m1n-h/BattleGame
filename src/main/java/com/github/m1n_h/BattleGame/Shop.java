package com.github.m1n_h.BattleGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    public static int openShop(ArrayList<Hero> hero, int currentGold) {
        Scanner sc = new Scanner(System.in);

        ShopItem[] items = {
                new ShopItem("기본 숫돌 (공격력 +5)", 30, 5),
                new ShopItem("전설의 숫돌 (공격력 +15)", 70, 15),
                new ShopItem("대마법사의 숨결 (공격력 +30)", 120, 30)
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
                    currentGold -= selectedItem.getItemPrice();
                    System.out.println("✨ 구매 성공! 남은 골드: " + currentGold + "G");

                    //테스트 출력
                    System.out.println("현재 파티원 수: " + hero.size());
                    for (Hero member : hero) {
                        System.out.println(member.getName() + " 의 무기 강화를 시도합니다.");
                        int beforeDamage = member.getWeapon().getDamage();
                        member.getWeapon().addDamage(selectedItem.getUpgradeAmount());
                        int afterDamage = member.getWeapon().getDamage();

                        System.out.println("⚔️ " + member.getName() + "의 무기 공격력: "
                                + beforeDamage + " ➡️ " + afterDamage + " (+" + selectedItem.getUpgradeAmount() + ")");
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
}
