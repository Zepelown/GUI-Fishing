package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.Items;
import io.github.zepelown.guifish.gamedata.FirstGameData;
import io.github.zepelown.guifish.gamedata.FirstGameData.Direction;
import io.github.zepelown.guifish.gamedata.GameManager;
import io.github.zepelown.guifish.gamedata.SecondGameDataManager;
import io.github.zepelown.guifish.handlers.FishHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FirstGameInventoryManager implements InventoryProvider {

	@Override
	public void init(Player player, InventoryContents contents) {

		Random random = new Random();
		int randomInt = random.nextInt(3) + 1;
		for (int i = 0; i < 3; i++) {
			contents.set(2, i, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
			contents.set(2, i + 6, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
		}
		contents.set(2, 8, ClickableItem.empty(Items.FIRST_GAME_GUIDE_SIGN));

		//획득 버튼 처리
		contents.set(2, 3, ClickableItem.of(Items.FISHING_ROD, e -> {
			if (e.isLeftClick()) {
				checkSelected(player, randomInt, contents);
			}
		}));
		contents.set(2, 4, ClickableItem.of(Items.FISHING_ROD, e -> {
			if (e.isLeftClick()) {
				checkSelected(player, randomInt, contents);
			}
		}));

		contents.set(2, 5, ClickableItem.of(Items.FISHING_ROD, e -> {
			if (e.isLeftClick()) {
				checkSelected(player, randomInt, contents);
			}
		}));

		//목표칸 처리
		switch (randomInt) {
			case 1:
				for (int i = 0; i < 9; i++)
					if (i >= 5)
						contents.set(1, i, ClickableItem.empty(Items.CAUGHT_FISH));
					else
						contents.set(1, i, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
				break;
			case 2:
				for (int i = 0; i < 9; i++)
					if (i >= 6)
						contents.set(1, i, ClickableItem.empty(Items.CAUGHT_FISH));
					else
						contents.set(1, i, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
				break;
			case 3:
				for (int i = 0; i < 9; i++)
					if (i >= 7)
						contents.set(1, i, ClickableItem.empty(Items.CAUGHT_FISH));
					else
						contents.set(1, i, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
				break;
		}
		contents.newIterator("RIGHT", SlotIterator.Type.HORIZONTAL, 0, 0);
		contents.newIterator("LEFT", SlotIterator.Type.HORIZONTAL, 0, 8);
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			FirstGameData gameData = GameManager.getData(player).getFirstGameData();
			if (gameData.getEndCount() < 4) {
				int state = contents.property("state", 0);
				contents.setProperty("state", state + 1);

				if (gameData.getDirection() == Direction.RIGHT) {
					SlotIterator iterator = contents.iterator("RIGHT").get();
					if (iterator.column() <= 3) {
						if (state % 10 != 0)
							return;
					} else if (iterator.column() <= 7) {
						if (state % 4 != 0)
							return;
					}

					if (iterator.column() == 8 || iterator.row() == 1) {
						gameData.setDirection(Direction.LEFT);
						gameData.addEndCount();
						iterator.set(ClickableItem.empty(Items.YELLOW_STAINED_GLASS_PANE));
						iterator.row(0).column(0);
					} else {
						iterator.set(ClickableItem.empty(Items.YELLOW_STAINED_GLASS_PANE)).next();
					}
				} else {
					SlotIterator iterator = contents.iterator("LEFT").get();
					if (iterator.column() <= 3) {
						if (state % 10 != 0)
							return;
					} else if (iterator.column() <= 7) {
						if (state % 4 != 0)
							return;
					}
					if (iterator.column() == 1) {
						gameData.setDirection(Direction.RIGHT);
						gameData.addEndCount();
						iterator.set(ClickableItem.empty(new ItemStack(Material.AIR)));
						contents.set(0, 0, ClickableItem.empty(new ItemStack(Material.AIR)));
						iterator.row(0).column(8);
					} else {
						iterator.set(ClickableItem.empty(new ItemStack(Material.AIR))).previous();
					}
				}
			} else {
				player.sendMessage(GUIFish.prefix + "물고기가 도망가버렸습니다!!");
				player.closeInventory();
			}
		} else {
			player.closeInventory();
		}
	}

	private void checkSelected(Player player, int randomInt, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			if (contents.get(0, 4 + randomInt).isPresent()) {
				player.sendMessage(GUIFish.prefix + "물고기가 잡히지 않을려고 날뜁니다!");
				SecondGameDataManager.set_default_end_count(player);
				player.closeInventory();
				Inventories.SECOND_GAME_INVENTORY.open(player);
			} else {
				player.sendMessage(GUIFish.prefix + "물고기가 도망가버렸습니다!!");
				FishHandler.removeHookedFish(player);
				player.closeInventory();
			}
		} else {
			player.closeInventory();
		}

	}

}
