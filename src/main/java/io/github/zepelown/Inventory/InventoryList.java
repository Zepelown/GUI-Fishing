package io.github.zepelown.Inventory;

import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import io.github.zepelown.event.FishingEvent;
import io.github.zepelown.main.DataManager;
import io.github.zepelown.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;

//출처 : https://github.com/MinusKube/SmartInvs
public class InventoryList implements Listener {

    public static SmartInventory MainInventory = SmartInventory.builder()
            .id("MainInventory")
            .provider(new MainInventoryManager())
            .size(6, 9)
            .title(ChatColor.DARK_AQUA + "메인 게임 페이지")
            .listener(new InventoryListener<InventoryCloseEvent>(InventoryCloseEvent.class, (e) -> {
                InventoryList il = new InventoryList();
                DataManager dm = new DataManager();
                Player player = (Player) e.getPlayer();

                dm.clear_game_data(player);
                player.sendMessage(Main.prefix + "게임이 끝났습니다.");
                FishingEvent fe = new FishingEvent();
                //보상
                if (dm.check_InGame(player)) {
                    player.sendMessage(Main.prefix + "물고기를 잡았습니다!");
                } else {
                    player.sendMessage(Main.prefix + "물고기를 잡는데 실패했습니다!");
                    fe.remove_Hooked_fish(player);
                }
            }))
            .closeable(true)
            .build();

    public static SmartInventory ResultInventory = SmartInventory.builder()
            .id("ResultInventory")
            .provider(new ResultInventoryManager())
            .size(2, 9)
            .title(ChatColor.DARK_AQUA + "결과")
            .closeable(true)
            .build();
}
