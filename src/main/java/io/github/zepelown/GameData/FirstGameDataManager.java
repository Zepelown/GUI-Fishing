package io.github.zepelown.GameData;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class FirstGameDataManager {
    private static HashMap<Player, Integer> end_count_hashmap = new HashMap<>();
    private static HashMap<Player, Boolean> direction_changed = new HashMap<>();
    private static HashMap<Player, Boolean> end_cycle = new HashMap<>();

    public void add_end_count(Player player) {
        if(end_count_hashmap.containsKey(player)){
            int end_count = end_count_hashmap.get(player);
            end_count++;
            end_count_hashmap.put(player, end_count);
        } else {
            end_count_hashmap.put(player, 1);
        }
    }

    public boolean check_and_remove_end_count(Player player) {
        if(end_count_hashmap.get(player) == 3) {
            end_count_hashmap.remove(player);
            direction_changed.remove(player);
            return true;
        }
        return false;
    }

    public int get_end_count(Player player) {
        return end_count_hashmap.get(player);
    }

    public void enable_direction_changed(Player player) {
        if(direction_changed.get(player) == null) {
            direction_changed.put(player, true);
            return;
        }

        if(direction_changed.containsKey(player)) {
            direction_changed.put(player, false);
            return;
        } else
            direction_changed.put(player, true);
    }

    public boolean get_direction_changed(Player player) {
        if(direction_changed.containsKey(player))
            return true;
        else
            return false;
    }

    public boolean get_end_cycle(Player player) {
        if(end_cycle.get(player))
            return true;
        return false;
    }

    public void set_end_cycle(Player player) {
        if(end_cycle.containsKey(player)) {
            end_cycle.put(player, true);
        } else {
            end_cycle.put(player, false);
        }

    }
}
