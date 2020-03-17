package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.gamedata.FirstGameDataManager;
import io.github.zepelown.guifish.gamedata.SecondGameDataManager;
import io.github.zepelown.guifish.handlers.FishHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

//출처 : https://github.com/MinusKube/SmartInvs
public class Inventories {

	public static final SmartInventory FIRST_GAME_INVENTORY = SmartInventory.builder()
			.id("FirstGame")
			.provider(new FirstGameInventoryManager())
			.size(3, 9)
			.title(ChatColor.DARK_AQUA + "물고기가 미끼를 물려고 합니다!!!")
			.listener(new InventoryListener<>(InventoryCloseEvent.class, (e) -> {
				Player player = (Player) e.getPlayer();
				if (FirstGameDataManager.get_win_game(player)) {
					player.sendMessage(GUIFish.prefix + "물고기가 잡히지 않을려고 날뜁니다!");
				} else {
					FishHandler.removeHookedFish(player);
				}
				FirstGameDataManager.clean_all_first_data(player);
			}))
			.closeable(true)
			.manager(GUIFish.getPlugin().inventoryManager)
			.build();
	public static final SmartInventory SECOND_GAME_INVENTORY = SmartInventory.builder()
			.id("SecondGame")
			.provider(new SecondGameInventoryManager())
			.size(6, 9)
			.title(ChatColor.DARK_AQUA + "물고기가 날뜁니다!")
			.listener(new InventoryListener<>(InventoryCloseEvent.class, (e) -> {
				Player player = (Player) e.getPlayer();

				SecondGameDataManager.clear_game_data(player);
				player.sendMessage(GUIFish.prefix + "게임이 끝났습니다.");
				//보상
				if (SecondGameDataManager.check_InGame(player)) {
					player.sendMessage(GUIFish.prefix + "물고기를 잡았습니다!");
				} else {
					player.sendMessage(GUIFish.prefix + "물고기를 잡는데 실패했습니다!");
					FishHandler.removeHookedFish(player);
				}
			}))
			.closeable(true)
			.manager(GUIFish.getPlugin().inventoryManager)
			.build();
	public static final SmartInventory RESULT_INVENTORY = SmartInventory.builder()
			.id("ResultInventory")
			.provider(new ResultInventoryManager())
			.size(2, 9)
			.title(ChatColor.DARK_AQUA + "결과")
			.closeable(true)
			.manager(GUIFish.getPlugin().inventoryManager)
			.build();

	private Inventories() {
	}
}
