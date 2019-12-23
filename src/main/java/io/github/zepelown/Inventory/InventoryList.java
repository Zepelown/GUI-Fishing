package io.github.zepelown.Inventory;

import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import io.github.zepelown.event.InventoryEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.List;

public class InventoryList implements Listener {
    private HashMap<Player, Boolean> in_gamemode = new HashMap<>();
    public static SmartInventory MainInventory = SmartInventory.builder()
            .id("MainInventory")
            .provider(new MainInventoryManager())
            .size(6, 9)
            .title(ChatColor.RED + "메인 게임 페이지")
            .closeable(true)
            .build();

    public void add(Player p) {
        in_gamemode.put(p, true);
    }

    public boolean check_in_gamemode(Player p) {
        if(in_gamemode.containsKey(p))
            return true;
        return false;
    }

    public void remove_in_gamemode_hashmap(Player p) {
        in_gamemode.remove(p);
    }
}
