package io.github.zepelown.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class FishingEvent extends JavaPlugin implements Listener {
    @EventHandler
    public void PlayerFishingEvent(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if(e.getState().equals("CAUGHT_FISH") || e.getState().equals("CAUGHT_ENTITY")) {
            p.sendMessage("hou");
        }

    }
}
