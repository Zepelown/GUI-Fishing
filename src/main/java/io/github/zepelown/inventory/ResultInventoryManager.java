package io.github.zepelown.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import io.github.zepelown.ItemManager;
import io.github.zepelown.event.FishingEvent;
import io.github.zepelown.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//출처 : https://github.com/MinusKube/SmartInvs
public class ResultInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

    FishingEvent fe = new FishingEvent();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(0,0, ClickableItem.empty(ItemManager.Result_Guid_Sign));
        contents.set(0,4, ClickableItem.empty(fe.get_Hooked_fish(player)));

        contents.set(1,5,ClickableItem.of(ItemManager.FISHING_ROD, e->{
            if(e.isLeftClick()) {
                if(e.getInventory().firstEmpty() == -1)
                    player.sendMessage(Main.prefix + "인벤토리가 꽉찼습니다. 인벤토리를 비워주세요.");
                else {
                    player.getInventory().addItem(fe.get_Hooked_fish(player));
                    player.sendMessage(Main.prefix + fe.get_Hooked_fish(player).getType().toString() +"를 잡았습니다!");
                    fe.remove_Hooked_fish(player);
                    player.closeInventory();
                }

            }
        }));
        contents.set(1, 3, ClickableItem.of(ItemManager.BARRIER, e -> {
            if(e.isLeftClick()) {
                fe.remove_Hooked_fish(player);
                player.sendMessage(Main.prefix + "잡은 아이템을 버렸습니다!");
                player.closeInventory();
            }
        }));
        contents.set(0,3,ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
        contents.set(0,5,ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
        contents.set(1,4,ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
