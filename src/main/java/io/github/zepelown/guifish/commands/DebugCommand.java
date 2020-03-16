package io.github.zepelown.guifish.commands;

import io.github.zepelown.guifish.gamedata.FirstGameDataManager;
import io.github.zepelown.guifish.gamedata.SecondGameDataManager;
import io.github.zepelown.guifish.handlers.FishHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {

	public static final DebugCommand instance = new DebugCommand();

	private DebugCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp()) {
				if (args.length != 0) {
					if (args[0].equalsIgnoreCase("first")) {
						FirstGameDataManager.printDebug();
						return true;
					} else if (args[0].equalsIgnoreCase("second")) {
						SecondGameDataManager.print_ArrayList();
						SecondGameDataManager.print_mapdata();
						return true;
					} else if (args[0].equalsIgnoreCase("hooked")) {
						FishHandler.printHookedFish();
						return true;
					} else
						player.sendMessage("올바르지 않은 명령어입니다.");
				} else
					player.sendMessage("올바르지 않은 명령어입니다.");
			} else
				player.sendMessage("오피만 사용가능한 명령어 입니다.");
			return false;
		} else {
			if (args.length != 0) {
				if (args[0].equalsIgnoreCase("first")) {
					FirstGameDataManager.printDebug();
					return true;
				} else if (args[0].equalsIgnoreCase("second")) {
					SecondGameDataManager.print_ArrayList();
					SecondGameDataManager.print_mapdata();
					return true;
				} else if (args[0].equalsIgnoreCase("hooked")) {
					FishHandler.printHookedFish();
					return true;
				} else
					System.out.println("올바르지 않은 명령어입니다.");
			} else
				System.out.println("올바르지 않은 명령어입니다.");
		}
		return false;
	}
}
