package io.github.zepelown.commands;

import io.github.zepelown.inventory.InventoryList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenInventory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("플레이어로 명령어를 입력해주세요.");
            return false;
        }
        InventoryList il = new InventoryList();
        Player player = (Player) sender;
        il.FirstGameInventory.open(player);

        return true;
    }
}
