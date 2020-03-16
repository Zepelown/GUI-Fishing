package io.github.zepelown.guifish.handlers;

import io.github.zepelown.guifish.gamedata.FirstGameDataManager;
import io.github.zepelown.guifish.inventory.Inventories;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;


public class FishHandler implements Listener {

	public static final FishHandler instance = new FishHandler();
	private static final Map<Player, ItemStack> hookedFishes = new HashMap<>();

	public static ItemStack getHookedFish(Player p) {
		return hookedFishes.get(p);
	}

	public static void removeHookedFish(Player p) {
		hookedFishes.remove(p);
	}

	public static void printHookedFish() {
		System.out.println("------잡힌 물고기-------");
		for (Player mapkey : hookedFishes.keySet())
			System.out.println("플레이어 : " + mapkey + " 값 : " + hookedFishes.get(mapkey));
		System.out.println("----------------------");
	}

	private FishHandler() {}

	@EventHandler
	private void PlayerFishingEvent(PlayerFishEvent e) {
		if (e.getState() == CAUGHT_FISH) {
			Player player = e.getPlayer();
			ItemStack hookedFish = ((Item) e.getCaught()).getItemStack();

			FirstGameDataManager.set_direction(player, "right");
			FirstGameDataManager.set_end_count(player, 0);
			hookedFishes.put(player, hookedFish);
			Inventories.FIRST_GAME_INVENTORY.open(player);
			e.setCancelled(true);
		}
	}

}
