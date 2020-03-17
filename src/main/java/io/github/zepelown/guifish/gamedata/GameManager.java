package io.github.zepelown.guifish.gamedata;

import io.github.zepelown.guifish.inventory.Inventories;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;

public class GameManager implements Listener {

	public static final GameManager instance = new GameManager();

	private static final Map<Player, DataContainer> dataMap = new HashMap<>();

	public static boolean isGaming(Player player) {
		return dataMap.containsKey(player);
	}

	public static DataContainer getData(Player player) {
		return dataMap.get(player);
	}

	public static void startGame(Player player, ItemStack caught) {
		dataMap.put(player, new DataContainer(caught));
	}

	public static void finishGame(Player player) {
		dataMap.remove(player);
	}

	public static void printDebug() {
		for (Entry<Player, DataContainer> entry : dataMap.entrySet()) {
			System.out.println("-----" + entry.getKey().getName() + "-----");
			entry.getValue().printDebug();
		}
	}

	private GameManager() {}

	@EventHandler
	private void onFish(PlayerFishEvent e) {
		if (e.getState() == CAUGHT_FISH) {
			Player player = e.getPlayer();
			startGame(player, ((Item) e.getCaught()).getItemStack());
			Inventories.FIRST_GAME_INVENTORY.open(player);
			e.setCancelled(true);
		}
	}

	@EventHandler
	private void onQuit(PlayerQuitEvent e) {
		finishGame(e.getPlayer());
	}

}
