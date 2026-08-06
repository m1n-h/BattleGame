package com.github.m1n_h.BattleGame.util;

import com.github.m1n_h.BattleGame.character.Hero;

import java.io.*;
import java.util.ArrayList;

public class SaveManager {
    private static final String SAVE_FILE_PATH = "savegame.dat";

    public static void saveGame(ArrayList<Hero> heroes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            oos.writeObject(heroes);
            System.out.println("\uD83D\uDCBE 게임 데이터가 성공적으로 저장되었습니다! (" + SAVE_FILE_PATH + ")");
        } catch (IOException e) {
            System.out.println("❌ [Error] 게임 저장 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Hero> loadGame() {
        File file = new File(SAVE_FILE_PATH);

        if (!file.exists()) {
            System.out.println("⚠️ 저장된 게임 파일이 존재하지 않습니다.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE_PATH))) {
            ArrayList<Hero> loadedHeroes = (ArrayList<Hero>) ois.readObject();
            System.out.println("📂 저장된 게임 데이터를 성공적으로 불러왔습니다!");
            return loadedHeroes;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ [Error] 게임 불러오기 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
