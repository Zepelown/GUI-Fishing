package io.github.zepelown.event;

import io.github.zepelown.Inventory.InventoryList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_ENTITY;
import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;


public class FishingEvent implements Listener {
    @EventHandler
    public void PlayerFishingEvent(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if(e.getState().equals(CAUGHT_FISH) || e.getState().equals(CAUGHT_ENTITY)) {
            p.sendMessage("hou");
            InventoryList il = new InventoryList();
            il.add(p);
            il.MainInventory.open(p);
            e.setCancelled(true);
        }
    }
}
