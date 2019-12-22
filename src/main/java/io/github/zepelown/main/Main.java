package io.github.zepelown.main;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("On Enable is called");
    }

    @Override
    public void onDisable() {
        getLogger().info("On Disable is called");
        // Plugin shutdown logic
    }
}
