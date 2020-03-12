package io.github.zepelown.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.GameData.FirstGameDataManager;
import io.github.zepelown.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Random;

public class FirstGameInventoryManager implements InventoryProvider {

    FirstGameDataManager DataManager = new FirstGameDataManager();

    public void init(Player player, InventoryContents contents) {

        Random random = new Random();
        Integer randomInt = random.nextInt(3) + 1;
        for(int i = 0; i < 9; i++) {
            if (i < 3)
                contents.set(2, i, ClickableItem.empty(ItemManager.Black_Stained_Glass_Pane));
            else if (i >= 3 && i < 6)
                contents.set(2, i, ClickableItem.of(ItemManager.FISHING_ROD, e -> {
                    if(e.isLeftClick()) {
                        if(contents.get(1,4 + randomInt).equals(ItemManager.Yellow_Stained_Glass_Pane))
                        {
                            DataManager.set_win_game(player);
                            player.closeInventory();
                        }
                    }
                }));
            else
                contents.set(2, i, ClickableItem.empty(ItemManager.Black_Stained_Glass_Pane));
        }

        //목표칸 처리
        switch (randomInt) {
            case 1:
                for(int i = 5; i < 9; i++)
                    contents.set(1, i, ClickableItem.empty(ItemManager.Caught_Fish));
                break;
            case 2:
                for(int i = 6; i < 9; i++)
                    contents.set(1, i, ClickableItem.empty(ItemManager.Caught_Fish));
                break;
            case 3:
                for(int i = 7; i < 9; i++)
                    contents.set(1, i, ClickableItem.empty(ItemManager.Caught_Fish));
                break;
        }
        contents.newIterator("Right", SlotIterator.Type.HORIZONTAL, 0,0);
        contents.newIterator("Right2", SlotIterator.Type.HORIZONTAL, 0, 0);
        contents.newIterator("Left", SlotIterator.Type.HORIZONTAL,0,8);
        contents.newIterator("Left2", SlotIterator.Type.HORIZONTAL,0,8);
    }

    public void update(Player player, InventoryContents contents) {
        if(DataManager.get_end_count(player) == 0 && DataManager.get_direction(player) == null)
            DataManager.set_end_count(player, 0);

        if(DataManager.get_end_count(player) < 4)
            one_cycle(player, contents);
        else if(DataManager.get_end_count(player) == 4) {
            player.closeInventory();
            player.sendMessage("시간 초과로 인한 게임 끝남");
        }
    }

    private void one_cycle (Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        SlotIterator iter_right = contents.iterator("Right").get();
        SlotIterator iter_left = contents.iterator("Left").get();
        if(DataManager.get_end_count(player) <= 3 && DataManager.get_end_count(player) > 1) {
            iter_right = contents.iterator("Right2").get();
            iter_left = contents.iterator("Left2").get();
        }
        if(DataManager.get_direction(player) == "right" || DataManager.get_direction(player) == null) {
            if(iter_right.column() <= 3) {
                if(state % 10 != 0)
                    return;
            } else if (iter_right.column() <= 7) {
                if(state % 4 != 0)
                    return;
            }

            if(iter_right.column() == 8 || iter_right.row() == 1) {
                DataManager.set_direction(player);
                iter_right.set(ClickableItem.empty(ItemManager.Yellow_Stained_Glass_Pane));
                DataManager.add_end_count(player);
                return;
            }
            iter_right.set(ClickableItem.empty(ItemManager.Yellow_Stained_Glass_Pane)).next();
        } else {
            if(iter_left.column() <= 3) {
                if(state % 10 != 0)
                    return;
            } else if (iter_left.column() <= 7) {
                if (state % 4 != 0)
                    return;
            }
            if (iter_left.column() == 1) {
                DataManager.set_direction(player);
                iter_left.set(ClickableItem.empty(new ItemStack(Material.AIR)));
                contents.set(0,0,ClickableItem.empty(new ItemStack(Material.AIR)));
                DataManager.add_end_count(player);
                return;
            }
            iter_left.set(ClickableItem.empty(new ItemStack(Material.AIR))).previous();
        }
    }

    private void selectItemfun(Player player, InventoryContents contents) {

    }

}
