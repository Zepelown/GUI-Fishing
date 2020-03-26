package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.gamedata.GameManager;
import io.github.zepelown.guifish.gamedata.SecondGameData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//출처 : https://github.com/MinusKube/SmartInvs
public class SecondGameManager implements fr.minuskube.inv.content.InventoryProvider {

	public static final SecondGameManager instance = new SecondGameManager();

	private SecondGameManager() {}

	@Override
	public void init(Player player, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			SecondGameData gameData = GameManager.getData(player).getSecondGameData();

			contents.fillRow(0, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
			contents.fillRow(2, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));

			contents.set(1, 8, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
			contents.set(1, 0, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));

			contents.set(3, 0, ClickableItem.empty(Items.WHITE_STAINED_GLASS_PANE));
			contents.set(3, 1, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
			contents.set(3, 7, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
			contents.set(3, 8, ClickableItem.empty(Items.WHITE_STAINED_GLASS_PANE));

			contents.fillRow(5, ClickableItem.empty(Items.WHITE_STAINED_GLASS_PANE));

			//나가기 버튼 처리
			contents.set(5, 8, ClickableItem.empty(Items.SECOND_GAME_GUIDE_SIGN));

			for (int x = 0; x < 8; x++) {
				//7줄 처리
				if (x < 2 || x > 6) {
					if (x == 7) {
						contents.set(4, x + 1, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
					}
					contents.set(4, x, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
				} else
					contents.set(4, x, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));
			}

			int index = 1;
			for (Material type : gameData.getMaterials()) {
				switch (type) {
					case GLASS:
						contents.set(1, index, ClickableItem.empty(Items.GLASS));
						break;
					case GRASS_BLOCK:
						contents.set(1, index, ClickableItem.empty(Items.GRASS));
						break;
					case STONE:
						contents.set(1, index, ClickableItem.empty(Items.STONE));
						break;
					case BEDROCK:
						contents.set(1, index, ClickableItem.empty(Items.BED_ROCK));
						break;
					case OAK_LOG:
						contents.set(1, index, ClickableItem.empty(Items.OAK_LOG));
						break;
				}
				index++;
			}

			//눌러야하는 칸 처리
			contents.set(3, 2, ClickableItem.of(Items.GLASS, e -> {
				if (e.isLeftClick()) {
					control_MainGame_Class(player, Items.GLASS.getType(), contents);
				}
			}));
			contents.set(3, 3, ClickableItem.of(Items.GRASS, e -> {
				if (e.isLeftClick()) {
					control_MainGame_Class(player, Items.GRASS.getType(), contents);
				}
			}));
			contents.set(3, 4, ClickableItem.of(Items.BED_ROCK, e -> {
				if (e.isLeftClick()) {
					control_MainGame_Class(player, Items.BED_ROCK.getType(), contents);
				}

			}));
			contents.set(3, 5, ClickableItem.of(Items.STONE, e -> {
				if (e.isLeftClick()) {
					control_MainGame_Class(player, Items.STONE.getType(), contents);
				}
			}));
			contents.set(3, 6, ClickableItem.of(Items.OAK_LOG, e -> {
				if (e.isLeftClick()) {
					control_MainGame_Class(player, Items.OAK_LOG.getType(), contents);
				}
			}));

			contents.newIterator("timer", SlotIterator.Type.HORIZONTAL, 5, 0);
		} else {
			player.closeInventory();
		}
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			int state = contents.property("state", 0);
			contents.setProperty("state", state + 1);

			if (state % 20 != 0)
				return;

			SlotIterator timer = contents.iterator("timer").get();

			int time = timer.column();

			if (time >= 7) {
				GameManager.finishGame(player);
				player.sendMessage(GUIFish.prefix + "물고기를 잡는데 실패했습니다!");
				player.closeInventory();
				return;
			}

			timer.set(ClickableItem.empty(Items.getTimer(time + 1))).next();
		} else {
			player.closeInventory();
		}
	}

	public void control_MainGame_Class(Player player, Material type, InventoryContents contents) {
		if (GameManager.isGaming(player)) {
			SecondGameData gameData = GameManager.getData(player).getSecondGameData();
			if (gameData.removeIfCorrect(type)) {
				player.sendMessage(GUIFish.prefix + "제대로 선택하였습니다!");
				contents.set(1, gameData.getRemovedCount(), ClickableItem.empty(new ItemStack(Material.AIR)));
			} else {
				player.sendMessage(GUIFish.prefix + "다시 선택해주세요!");
			}

			if (gameData.hasRemovedAll()) {
				player.sendMessage(GUIFish.prefix + "물고기를 잡았습니다!");
				player.closeInventory();
				Inventories.RESULT_INVENTORY.open(player);
			}
		} else {
			player.closeInventory();
		}
	}
}
