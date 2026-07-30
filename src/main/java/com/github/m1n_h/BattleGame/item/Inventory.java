package com.github.m1n_h.BattleGame.item;

import com.github.m1n_h.BattleGame.character.Usable;
import com.github.m1n_h.BattleGame.exception.ItemNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final Map<Usable, Integer> items = new HashMap<>();

    public void addItem(Usable item, int amount) {
        int currentCount = items.getOrDefault(item, 0);
        items.put(item, currentCount + amount);

        System.out.println("\uD83D\uDCE6 [아이템 획득] " + item.getItemName() + " 을(를) " + amount + "개 획득 하셨습니다!");
    }

    public void removeItem(Usable item, int amount) {
        String msg = "";
        int currentCount = items.getOrDefault(item, 0);

        if (currentCount < amount) {
            throw new ItemNotFoundException(item.getItemName() + " 이(가) 부족 합니다. (현재 보유: " + currentCount + "개 / 필요 개수: " + amount + "개)");
        }

        if ((currentCount - amount) <= 0) {
            items.remove(item);
            msg = "\uD83D\uDCE6 [아이템 차감] " + item.getItemName() + " 을(를) 모두 사용하여 삭제 합니다.";
        } else {
            items.put(item, currentCount - amount);
            msg = "\uD83D\uDCE6 [아이템 차감] " + item.getItemName() + " 을(를) " + amount + "개 차감 합니다.";
        }

        System.out.println(msg);
    }

    public List<Usable> getPotions() {
        return items.keySet().stream()
                .filter(item -> item instanceof Potion)
                .toList();
    }
}
