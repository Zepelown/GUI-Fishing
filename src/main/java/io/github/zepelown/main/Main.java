package io.github.zepelown.main;

import io.github.zepelown.Inventory.InventoryList;
import io.github.zepelown.event.FishingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

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
