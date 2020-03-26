package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.gamedata.FirstGameData;
import io.github.zepelown.guifish.gamedata.FirstGameData.Direction;
import io.github.zepelown.guifish.gamedata.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class FirstGameManager implements InventoryProvider {

	public static final FirstGameManager instance = new FirstGameManager();

	private FirstGameManager() {}

	@Override
	public void init(Player player, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			FirstGameData gameData = GameManager.getData(player).getFirstGameData();
			for (int i = 0; i < 3; i++) {
				contents.set(2, i, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
				contents.set(2, i + 6, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
			}
			contents.set(2, 8, ClickableItem.empty(Items.FIRST_GAME_GUIDE_SIGN));

			//획득 버튼 처리
			contents.set(2, 3, ClickableItem.of(Items.FISHING_ROD, e -> {
				if (e.isLeftClick()) {
					checkSelected(player, contents);
				}
			}));
			contents.set(2, 4, ClickableItem.of(Items.FISHING_ROD, e -> {
				if (e.isLeftClick()) {
					checkSelected(player, contents);
				}
			}));

			contents.set(2, 5, ClickableItem.of(Items.FISHING_ROD, e -> {
				if (e.isLeftClick()) {
					checkSelected(player, contents);
				}
			}));

			for (int i = 0; i < 9; i++) {
				if (i >= 9 - gameData.getDifficulty()) contents.set(1, i, ClickableItem.empty(Items.CAUGHT_FISH));
				else contents.set(1, i, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
			}
			//목표칸 처리
			contents.newIterator("RIGHT", SlotIterator.Type.HORIZONTAL, 0, 0);
			contents.newIterator("LEFT", SlotIterator.Type.HORIZONTAL, 0, 8);
		} else {
			player.closeInventory();
		}
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
				GameManager.finishGame(player);
				player.sendMessage(GUIFish.prefix + "물고기가 도망가버렸습니다!!");
				player.closeInventory();
			}
		} else {
			player.closeInventory();
		}
	}

	private void checkSelected(Player player, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			FirstGameData gameData = GameManager.getData(player).getFirstGameData();
			Optional<ClickableItem> optional = contents.get(0, 9 - gameData.getDifficulty());
			if (optional.isPresent() && optional.get().getItem().getType() != Material.AIR) {
				player.sendMessage(GUIFish.prefix + "물고기가 잡히지 않으려고 날뜁니다!");
				player.closeInventory();
				Inventories.SECOND_GAME_INVENTORY.open(player);
			} else {
				GameManager.finishGame(player);
				player.sendMessage(GUIFish.prefix + "물고기가 도망가버렸습니다!!");
				player.closeInventory();
			}
		} else {
			player.closeInventory();
		}

	}

}
