package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.gamedata.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

//출처 : https://github.com/MinusKube/SmartInvs
public class Inventories {

	public static final SmartInventory FIRST_GAME_INVENTORY = SmartInventory.builder()
			.id("FirstGame")
			.provider(FirstGameManager.instance)
			.size(3, 9)
			.title(ChatColor.DARK_AQUA + "물고기가 미끼를 물려고 합니다!!!")
			.closeable(true)
			.manager(GUIFish.getPlugin().inventoryManager)
			.build();
	public static final SmartInventory SECOND_GAME_INVENTORY = SmartInventory.builder()
			.id("SecondGame")
			.provider(SecondGameManager.instance)
			.size(6, 9)
			.title(ChatColor.DARK_AQUA + "물고기가 날뜁니다!")
			.listener(new InventoryListener<>(InventoryCloseEvent.class, (e) -> {
				e.getPlayer().sendMessage(GUIFish.prefix + "게임이 끝났습니다.");
			}))
			.closeable(true)
			.manager(GUIFish.getPlugin().inventoryManager)
			.build();
	public static final SmartInventory RESULT_INVENTORY = SmartInventory.builder()
			.id("ResultInventory")
			.provider(ResultGUIManager.instance)
			.size(2, 9)
			.title(ChatColor.DARK_AQUA + "결과")
			.listener(new InventoryListener<>(InventoryCloseEvent.class, e -> {
				if (e.getPlayer() instanceof Player) {
					GameManager.finishGame((Player) e.getPlayer());
				}
			}))
			.closeable(true)
			.manager(GUIFish.getPlugin().inventoryManager)
			.build();

	private Inventories() {
	}
}
