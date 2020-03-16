package io.github.zepelown.guifish;

import fr.minuskube.inv.InventoryManager;
import io.github.zepelown.guifish.commands.DebugCommand;
import io.github.zepelown.guifish.handlers.FishHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GUIFish extends JavaPlugin {

	public static final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "GUIFishing" + ChatColor.GRAY + "] " + ChatColor.WHITE;
	private static GUIFish plugin;

	public static GUIFish getPlugin() {
		return plugin;
	}

	public final InventoryManager inventoryManager = new InventoryManager(this);

	public GUIFish() {
		GUIFish.plugin = this;
	}

	@Override
	public void onEnable() {
		inventoryManager.init();
		Bukkit.getPluginManager().registerEvents(FishHandler.instance, this);
		Bukkit.getPluginCommand("gfdebug").setExecutor(DebugCommand.instance);
	}

}
