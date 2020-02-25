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

import java.util.Random;

public class FirstGameInventoryManager implements InventoryProvider {

    FirstGameDataManager DataManager = new FirstGameDataManager();

    public void init(Player player, InventoryContents contents) {

        for(int i = 0; i < 9; i++) {
            if (i < 3)
                contents.set(2, i, ClickableItem.empty(ItemManager.Black_Stained_Glass_Pane));
            else if (i >= 3 && i < 6)
                contents.set(2, i, ClickableItem.of(ItemManager.FISHING_ROD, e -> {
                    if(e.isLeftClick()) {
                    }
                }));
            else
                contents.set(2, i, ClickableItem.empty(ItemManager.Black_Stained_Glass_Pane));
        }

        //목표칸 처리
        Random random = new Random();
        switch (random.nextInt(3) + 1) {
            case 1:
                contents.set(1,5,ClickableItem.empty(ItemManager.Caught_Fish));
                contents.set(1,6,ClickableItem.empty(ItemManager.Caught_Fish));
                break;
            case 2:
                contents.set(1,6,ClickableItem.empty(ItemManager.Caught_Fish));
                contents.set(1,7,ClickableItem.empty(ItemManager.Caught_Fish));
                break;
            case 3:
                contents.set(1,7,ClickableItem.empty(ItemManager.Caught_Fish));
                contents.set(1,8,ClickableItem.empty(ItemManager.Caught_Fish));
                break;
        }
        contents.newIterator("Right", SlotIterator.Type.HORIZONTAL, 0,0);
        contents.newIterator("Left", SlotIterator.Type.HORIZONTAL,0,8);
    }

    public void update(Player player, InventoryContents contents) {
        do {

        } while ()

        if(DataManager.get_end_count(player) >=1){

        }
    }

    private void one_cycle (Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        SlotIterator iter_right = contents.iterator("Right").get();
        SlotIterator iter_left = contents.iterator("Left").get();

        if(!DataManager.get_direction_changed(player)) {
            if(iter_right.column() <= 3) {
                if(state % 10 != 0)
                    return;
            } else if (iter_right.column() <= 7) {
                if(state % 4 != 0)
                    return;
            }

            if(iter_right.column() == 8 || iter_right.row() == 1) {
                DataManager.enable_direction_changed(player);
                iter_right.set(ClickableItem.empty(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE)));
                return;
            }
            iter_right.set(ClickableItem.empty(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE))).next();
        } else {
            if(iter_left.column() <= 3) {
                if(state % 10 != 0)
                    return;
            } else if (iter_left.column() <= 7) {
                if (state % 4 != 0)
                    return;
            }
            if (iter_left.column() == 1) {
                DataManager.enable_direction_changed(player);
                iter_left.set(ClickableItem.empty(new ItemStack(Material.AIR)));
                contents.set(0,0,ClickableItem.empty(new ItemStack(Material.AIR)));
                return;
            }
            iter_left.set(ClickableItem.empty(new ItemStack(Material.AIR))).previous();
        }

    }
}
