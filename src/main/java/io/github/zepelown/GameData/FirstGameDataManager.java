package io.github.zepelown.GameData;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class FirstGameDataManager {
    private static HashMap<Player, Integer> end_count_hashmap = new HashMap<>();
    private static HashMap<Player, String> direction = new HashMap<>();
    private static HashMap<Player, Boolean> win_game = new HashMap<>();

    public void add_end_count(Player player) {
        int end_count = end_count_hashmap.get(player);
        end_count++;
        end_count_hashmap.put(player, end_count);
    }

    public void set_end_count(Player player, Integer integer) {
        end_count_hashmap.put(player, integer);
    }

    public int get_end_count(Player player) {
        return end_count_hashmap.get(player);
    }

    public void set_direction(Player player,String dir) {
        direction.put(player, dir);
    }

    public String get_direction(Player player) {
        return direction.get(player);
    }

    public boolean get_win_game(Player player) {
        return win_game.get(player);
    }

    public void set_win_game(Player player, Boolean bool) {
        win_game.put(player, bool);
    }

    public void clean_all_first_data(Player player) {
        end_count_hashmap.remove(player);
        direction.remove(player);
        win_game.remove(player);
    }
}
