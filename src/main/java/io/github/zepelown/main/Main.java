package io.github.zepelown.main;

import io.github.zepelown.Inventory.InventoryList;
import io.github.zepelown.event.FishingEvent;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "GUIFishing"
            + ChatColor.GRAY + "] " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("On Enable is called");
        getServer().getPluginManager().registerEvents(new FishingEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryList(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("On Disable is called");
        // Plugin shutdown logic
    }
}
