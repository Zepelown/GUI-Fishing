package io.github.zepelown.commands;

import io.github.zepelown.GameData.FirstGameDataManager;
import io.github.zepelown.GameData.SecondGameDataManager;
import io.github.zepelown.event.FishingEvent;
import io.github.zepelown.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {

    FirstGameDataManager FGdata = new FirstGameDataManager();
    SecondGameDataManager SGdata = new SecondGameDataManager();
    FishingEvent fe = new FishingEvent();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if(player.isOp()) {
                if(args.length != 0) {
                    if (args[0].equalsIgnoreCase("first")) {
                        FGdata.print_first_data();
                        return true;
                    } else if (args[0].equalsIgnoreCase("second")) {
                        SGdata.print_ArrayList();
                        SGdata.print_mapdata();
                        return true;
                    } else if(args[0].equalsIgnoreCase("hooked")) {
                        fe.print_Hooked_fish();
                        return true;
                    } else
                        player.sendMessage(Main.prefix + "올바르지않은 명령어입니다.");
                } else
                    player.sendMessage(Main.prefix + "올바르지않은 명령어입니다.");
            } else
                player.sendMessage(Main.prefix + "오피만 사용가능한 명령어 입니다.");
            return false;
        } else {
            if(args.length != 0) {
                if (args[0].equalsIgnoreCase("first")) {
                    FGdata.print_first_data();
                    return true;
                } else if (args[0].equalsIgnoreCase("second")) {
                    SGdata.print_ArrayList();
                    SGdata.print_mapdata();
                    return true;
                } else if(args[0].equalsIgnoreCase("hooked")) {
                    fe.print_Hooked_fish();
                    return true;
                } else
                    System.out.println(Main.prefix + "올바르지않은 명령어입니다.");
            } else
                System.out.println(Main.prefix + "올바르지않은 명령어입니다.");
        }
        return false;
    }
}
