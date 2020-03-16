package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.Items;
import io.github.zepelown.guifish.gamedata.SecondGameDataManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

//출처 : https://github.com/MinusKube/SmartInvs
public class SecondGameInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

	@Override
	public void init(Player player, InventoryContents contents) {
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
		contents.set(5, 8, ClickableItem.empty(Items.FIRST_GAME_GUIDE_SIGN));

		for (int x = 0; x < 8; x++) {
			//7줄 처리
			if (x < 2 || x > 6) {
				if (x == 7) {
					contents.set(4, x + 1, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
				}
				contents.set(4, x, ClickableItem.empty(Items.BLACK_STAINED_GLASS_PANE));
			} else
				contents.set(4, x, ClickableItem.empty(Items.BLUE_STAINED_GLASS_PANE));

			//목표 칸 처리
			if (x + 1 < 8) {
				Random random = new Random();
				switch (random.nextInt(5) + 1) {
					case 1:
						contents.set(1, x + 1, ClickableItem.empty(Items.GLASS));
						SecondGameDataManager.add_ArrayList(player, Items.GLASS.getType().toString());
						break;
					case 2:
						contents.set(1, x + 1, ClickableItem.empty(Items.GRASS));
						SecondGameDataManager.add_ArrayList(player, Items.GRASS.getType().toString());
						break;
					case 3:
						contents.set(1, x + 1, ClickableItem.empty(Items.STONE));
						SecondGameDataManager.add_ArrayList(player, Items.STONE.getType().toString());
						break;
					case 4:
						contents.set(1, x + 1, ClickableItem.empty(Items.BED_ROCK));
						SecondGameDataManager.add_ArrayList(player, Items.BED_ROCK.getType().toString());
						break;
					case 5:
						contents.set(1, x + 1, ClickableItem.empty(Items.OAK_LOG));
						SecondGameDataManager.add_ArrayList(player, Items.OAK_LOG.getType().toString());
						break;
				}
			}

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
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		int state = contents.property("state", 0);
		contents.setProperty("state", state + 1);

		if (state % 20 != 0)
			return;

		SlotIterator timer = contents.iterator("timer").get();

		int time = timer.column();

		if (time >= 7) {
			player.closeInventory();
			return;
		}

		timer.set(ClickableItem.empty(Items.getTimer(time + 1))).next();
	}

	public void control_MainGame_Class(Player player, Material item, InventoryContents contents) {
		if (SecondGameDataManager.check_and_remove_ArrayList(player, item.toString())) {
			player.sendMessage(GUIFish.prefix + "제대로 선택하였습니다!");
			contents.set(1, SecondGameDataManager.get_end_count(player), ClickableItem.empty(new ItemStack(Material.AIR)));
		} else {
			player.sendMessage(GUIFish.prefix + "다시 선택해주세요!");
		}

		if (SecondGameDataManager.check_end(player)) {
			SecondGameDataManager.modify_InGame(player, true);
			player.closeInventory();
			Inventories.RESULT_INVENTORY.open(player);
		}

	}
}
