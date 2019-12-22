package io.github.zepelown.Inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class MainGame {
    private HashMap<Player, Integer> end_count = new HashMap<Player, Integer>();
    public ArrayList<HashMap<Player, ItemStack>> current_item_list= new ArrayList<>();


    public void add_ItemStack(Player player, ItemStack item) {
        HashMap<Player, ItemStack> temp = new HashMap<>();
        temp.put(player, item);
        current_item_list.add(temp);
        end_count.put(player, 0);
    }

    public boolean check_ItemStack(Player p, ItemStack item) {
        for(int i = 0; i < current_item_list.size(); i++) {
            if(current_item_list.get(i).containsKey(p))
            {
                if(current_item_list.get(i).containsValue(item))
                    return true;
            }
        }
        return false;

    }

    public void print_array() {
        for(int i = 0; i < current_item_list.size(); i++) {
            System.out.println(current_item_list.get(i));
        }
    }

    public void remove_ItemStack(Player p) {
        for(int i = 0; i < current_item_list.size(); i++) {
            if(current_item_list.get(i).containsKey(p)) {
                current_item_list.remove(i);
                break;
            }
        }
        int a = end_count.get(p);
        a++;
        end_count.put(p, a);
        System.out.println("end_count : " + end_count);
    }

    public int get_end_count(Player p) {
        return end_count.get(p);
    }

    public boolean check_end(Player p) {
        if(end_count.get(p) == 7) {
            end_count.put(p, 0);
            return true;
        } else
            return false;
    }
}
