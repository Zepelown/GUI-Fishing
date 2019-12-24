package io.github.zepelown.event;

import io.github.zepelown.Inventory.InventoryList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_ENTITY;
import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;


public class FishingEvent implements Listener {

    private static HashMap<Player, ItemStack> Hooked_fish_list = new HashMap<>();
    @EventHandler
    public void PlayerFishingEvent(PlayerFishEvent e) {
        Player p = e.getPlayer();

        if(e.getState().equals(CAUGHT_FISH) || e.getState().equals(CAUGHT_ENTITY)) {
            Item Hooked_fish_temp = (Item) e.getCaught();
            ItemStack Hooked_fish = Hooked_fish_temp.getItemStack();

            p.sendMessage("hou");
            InventoryList il = new InventoryList();
            il.add(p);
            il.MainInventory.open(p);
            add_Hooked_fish(p, Hooked_fish);
            e.setCancelled(true);
        }
    }

    public void add_Hooked_fish(Player p, ItemStack Hooked_fish) {
        Hooked_fish_list.put(p, Hooked_fish);
        System.out.println("잡은 물고기 처리 : " + Hooked_fish_list.get(p).toString());
    }

    public ItemStack get_Hooked_fish(Player p) {
        return Hooked_fish_list.get(p);
    }

    public void remove_Hooked_fish(Player p) {
        Hooked_fish_list.remove(p);
        System.out.println("잡은 물고기 데이터 삭제 완료");
    }
}
