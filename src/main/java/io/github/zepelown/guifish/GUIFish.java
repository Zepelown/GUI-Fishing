package io.github.zepelown.guifish;

import fr.minuskube.inv.InventoryManager;
import io.github.zepelown.guifish.commands.DebugCommand;
import io.github.zepelown.guifish.gamedata.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GUIFish extends JavaPlugin {

	public static final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "GUIFishing" + ChatColor.GRAY + "] " + ChatColor.WHITE;
	private static GUIFish plugin;
	public final InventoryManager inventoryManager = new InventoryManager(this);

	public GUIFish() {
		GUIFish.plugin = this;
	}

	public static GUIFish getPlugin() {
		return plugin;
	}

	@Override
	public void onEnable() {
		inventoryManager.init();
		Bukkit.getPluginManager().registerEvents(GameManager.instance, this);
		Bukkit.getPluginCommand("gfdebug").setExecutor(DebugCommand.instance);
	}

}
