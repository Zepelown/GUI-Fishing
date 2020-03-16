package io.github.zepelown.guifish.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.guifish.GUIFish;
import io.github.zepelown.guifish.Items;
import io.github.zepelown.guifish.gamedata.FirstGameDataManager;
import io.github.zepelown.guifish.gamedata.SecondGameDataManager;
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
				check_whether_it_is_selected(player, randomInt, contents);
			}
		}));
		contents.set(2, 4, ClickableItem.of(Items.FISHING_ROD, e -> {
			if (e.isLeftClick()) {
				check_whether_it_is_selected(player, randomInt, contents);
			}
		}));

		contents.set(2, 5, ClickableItem.of(Items.FISHING_ROD, e -> {
			if (e.isLeftClick()) {
				check_whether_it_is_selected(player, randomInt, contents);
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
		contents.newIterator("Right", SlotIterator.Type.HORIZONTAL, 0, 0);
		contents.newIterator("Right2", SlotIterator.Type.HORIZONTAL, 0, 0);
		contents.newIterator("Left", SlotIterator.Type.HORIZONTAL, 0, 8);
		contents.newIterator("Left2", SlotIterator.Type.HORIZONTAL, 0, 8);
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		if (FirstGameDataManager.get_end_count(player) < 4)
			one_cycle(player, contents);
		else if (FirstGameDataManager.get_end_count(player) == 4) {
			FirstGameDataManager.set_win_game(player, false);
			player.sendMessage(GUIFish.prefix + "물고기가 도망가버렸습니다!!");
			player.closeInventory();
		}
	}

	private void one_cycle(Player player, InventoryContents contents) {
		int state = contents.property("state", 0);
		contents.setProperty("state", state + 1);

		SlotIterator iter_right = contents.iterator("Right").get();
		SlotIterator iter_left = contents.iterator("Left").get();
		if (FirstGameDataManager.get_end_count(player) <= 3 && FirstGameDataManager.get_end_count(player) > 1) {
			iter_right = contents.iterator("Right2").get();
			iter_left = contents.iterator("Left2").get();
		}
		if (FirstGameDataManager.get_direction(player).equals("right")) {
			if (iter_right.column() <= 3) {
				if (state % 10 != 0)
					return;
			} else if (iter_right.column() <= 7) {
				if (state % 4 != 0)
					return;
			}

			if (iter_right.column() == 8 || iter_right.row() == 1) {
				FirstGameDataManager.set_direction(player, "left");
				iter_right.set(ClickableItem.empty(Items.YELLOW_STAINED_GLASS_PANE));
				FirstGameDataManager.add_end_count(player);
				return;
			}
			iter_right.set(ClickableItem.empty(Items.YELLOW_STAINED_GLASS_PANE)).next();
		} else {
			if (iter_left.column() <= 3) {
				if (state % 10 != 0)
					return;
			} else if (iter_left.column() <= 7) {
				if (state % 4 != 0)
					return;
			}
			if (iter_left.column() == 1) {
				FirstGameDataManager.set_direction(player, "right");
				iter_left.set(ClickableItem.empty(new ItemStack(Material.AIR)));
				contents.set(0, 0, ClickableItem.empty(new ItemStack(Material.AIR)));
				FirstGameDataManager.add_end_count(player);
				return;
			}
			iter_left.set(ClickableItem.empty(new ItemStack(Material.AIR))).previous();
		}
	}

	private void check_whether_it_is_selected(Player player, int randomInt, InventoryContents contents) {
		if (contents.get(0, 4 + randomInt).isPresent()) {
			FirstGameDataManager.set_win_game(player, true);
			SecondGameDataManager.set_default_end_count(player);
			player.closeInventory();
			Inventories.SECOND_GAME_INVENTORY.open(player);
		} else {
			player.sendMessage(GUIFish.prefix + "물고기가 도망가버렸습니다!!");
			FirstGameDataManager.set_win_game(player, false);
			player.closeInventory();
		}

	}

}
