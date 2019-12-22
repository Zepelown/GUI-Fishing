package io.github.zepelown.Inventory;

import fr.minuskube.inv.SmartInventory;
import org.bukkit.ChatColor;

public class InventoryList {
    public static SmartInventory MainInventory = SmartInventory.builder()
            .id("MainInventory")
            .provider(new MainInventoryManager())
            .size(6, 9)
            .title(ChatColor.RED + "메인 게임 페이지")
            .closeable(true)
            .build();
}
