package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.Items;
import io.github.zepelown.guifish.handlers.FishHandler;
import org.bukkit.entity.Player;

//출처 : https://github.com/MinusKube/SmartInvs
public class ResultInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

	@Override
	public void init(Player player, InventoryContents contents) {
		contents.set(0, 0, ClickableItem.empty(Items.RESULT_GUIDE_SIGN));
		contents.set(0, 4, ClickableItem.empty(FishHandler.getHookedFish(player)));

		contents.set(1, 5, ClickableItem.of(Items.FISHING_ROD, e -> {
			if (e.isLeftClick()) {
				if (e.getInventory().firstEmpty() == -1)
					player.sendMessage(GUIFish.prefix + "인벤토리가 꽉찼습니다. 인벤토리를 비워주세요.");
				else {
					player.getInventory().addItem(FishHandler.getHookedFish(player));
					player.sendMessage(GUIFish.prefix + FishHandler.getHookedFish(player).getType().toString() + "를 잡았습니다!");
					FishHandler.removeHookedFish(player);
					player.closeInventory();
				}

			}
		}));
		contents.set(1, 3, ClickableItem.of(Items.BARRIER, e -> {
			if (e.isLeftClick()) {
				FishHandler.removeHookedFish(player);
				player.sendMessage(GUIFish.prefix + "잡은 아이템을 버렸습니다!");
				player.closeInventory();
			}
		}));
		contents.set(0, 3, ClickableItem.empty(Items.WHITE_STAINED_GLASS_PANE));
		contents.set(0, 5, ClickableItem.empty(Items.WHITE_STAINED_GLASS_PANE));
		contents.set(1, 4, ClickableItem.empty(Items.WHITE_STAINED_GLASS_PANE));
	}

	@Override
	public void update(Player player, InventoryContents contents) {
	}
}
