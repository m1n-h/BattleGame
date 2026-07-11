package com.github.m1n_h.BattleGame;

public class ShopItem {
    private String itemName;
    private int itemPrice;
    private int upgradeAmount;

    public ShopItem(String itemName, int itemPrice, int upgradeAmount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.upgradeAmount = upgradeAmount;
    }

    public String getItemName() { return this.itemName; }
    public int getItemPrice() { return this.itemPrice; }
    public int getUpgradeAmount() { return this.upgradeAmount; }

}
