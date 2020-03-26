package io.github.zepelown.guifish.commands;

import io.github.zepelown.guifish.gamedata.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugCommand implements CommandExecutor {

	public static final DebugCommand instance = new DebugCommand();

	private DebugCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp()) {
			GameManager.printDebug();
		} else sender.sendMessage(ChatColor.RED + "권한이 부족합니다.");
		return true;
	}
}
