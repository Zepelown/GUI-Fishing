package io.github.zepelown.guifish.gamedata;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

	private GameManager() {}

	private static final Map<Player, DataContainer> dataMap = new HashMap<>();

	public static boolean isGaming(Player player) {
		return dataMap.containsKey(player);
	}

	public static DataContainer getData(Player player) {
		return dataMap.get(player);
	}

	public static void startGame(Player player) {
		dataMap.put(player, new DataContainer());
	}

	public static void finishGame(Player player) {
		dataMap.remove(player);
	}

}
