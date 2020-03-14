package io.github.zepelown.inventory;

import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import io.github.zepelown.GameData.FirstGameDataManager;
import io.github.zepelown.event.FishingEvent;
import io.github.zepelown.GameData.SecondGameDataManager;
import io.github.zepelown.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

//출처 : https://github.com/MinusKube/SmartInvs
public class InventoryList implements Listener {

    public static SmartInventory FirstGameInventory = SmartInventory.builder()
            .id("FirstGame")
            .provider(new FirstGameInventoryManager())
            .size(3, 9)
            .title(ChatColor.DARK_AQUA + "물고기가 미끼를 물려고 합니다!!!")
            .listener(new InventoryListener<InventoryCloseEvent>(InventoryCloseEvent.class, (e) -> {
                Player player = (Player) e.getPlayer();
                FirstGameDataManager fg = new FirstGameDataManager();
                if(fg.get_win_game(player)) {
                    player.sendMessage(Main.prefix + "물고기가 잡히지 않을려고 날뜁니다!");
                } else {
                    FishingEvent fe = new FishingEvent();
                    fe.remove_Hooked_fish(player);
                }
                fg.clean_all_first_data(player);
            }))
            .closeable(true)
            .build();

    public static SmartInventory SecondGameInventory = SmartInventory.builder()
            .id("SecondGame")
            .provider(new SecondGameInventoryManager())
            .size(6, 9)
            .title(ChatColor.DARK_AQUA + "물고기가 날뜁니다!")
            .listener(new InventoryListener<InventoryCloseEvent>(InventoryCloseEvent.class, (e) -> {
                InventoryList il = new InventoryList();
                SecondGameDataManager dm = new SecondGameDataManager();
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
