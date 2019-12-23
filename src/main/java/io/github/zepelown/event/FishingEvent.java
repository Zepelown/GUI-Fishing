package io.github.zepelown.event;

import io.github.zepelown.Inventory.InventoryList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.HashMap;

import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_ENTITY;
import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;


public class FishingEvent implements Listener {

    private static HashMap<Player, Entity> Hooked_fish_list = new HashMap<>();
    @EventHandler
    public void PlayerFishingEvent(PlayerFishEvent e) {
        Player p = e.getPlayer();

        if(e.getState().equals(CAUGHT_FISH) || e.getState().equals(CAUGHT_ENTITY)) {
            Entity Hooked_fish = e.getCaught();
            p.sendMessage("hou");
            InventoryList il = new InventoryList();
            il.add(p);
            il.MainInventory.open(p);
            add_Hooked_fish(p, Hooked_fish);
            e.setCancelled(true);
        }
    }

    public void add_Hooked_fish(Player p, Entity e) {
        Hooked_fish_list.put(p, e);
    }

    public Entity get_Hooked_fish(Player p) {
        return Hooked_fish_list.get(p);
    }

    public void remove_Hooked_fish(Player p) {
        Hooked_fish_list.remove(p);
    }
}
