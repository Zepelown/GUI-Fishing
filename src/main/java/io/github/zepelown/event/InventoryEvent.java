package io.github.zepelown.event;

import fr.minuskube.inv.SmartInventory;
import io.github.zepelown.Inventory.InventoryList;
import io.github.zepelown.Inventory.MainGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static io.github.zepelown.Inventory.InventoryList.*;

public class InventoryEvent implements Listener {

    MainGame mg = new MainGame();
    @EventHandler
    public void InventoryCloseEvent (InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        InventoryList il = new InventoryList();

        try {
            if(il.check_in_gamemode(p)) {
                mg.clear_game_data(p);
                p.sendMessage("게임이 끝났습니다.");
                il.remove_in_gamemode_hashmap(p);
            }
        } catch (NullPointerException n) {

        }
        System.out.println("인벤 닫기 이벤트 작동");
    }
}
