package io.github.zepelown.GameData;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.security.KeyStore;
import java.util.*;

public class SecondGameDataManager {
    private static HashMap<Player, Integer> end_count = new HashMap<Player, Integer>();
    private static ArrayList<HashMap<Player, String>> current_item_list= new ArrayList<HashMap<Player, String>>();
    private static HashMap<Player, Boolean> InGame = new HashMap<Player, Boolean>();

    public void add_ArrayList(Player p, String s) {
        HashMap<Player, String> temp = new HashMap<Player, String>();
        temp.put(p, s);
        current_item_list.add(temp);
    }

    public boolean check_and_remove_ArrayList(Player p, String m) {
        ListIterator cil_it = current_item_list.listIterator();
        while (cil_it.hasNext()) {
            HashMap<Player, String> temp = (HashMap) cil_it.next();
            if(temp.containsKey(p)) {
                if(temp.containsValue(m)) {
                    cil_it.remove();
                    add_end_count(p);
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public void print_ArrayList() {
        for(int i = 0; i < current_item_list.size(); i++)
            System.out.println(current_item_list.get(i));
        System.out.println("랜덤 블럭 리스트 불러오기 완료");
    }

    public void print_mapdata() {
        System.out.println("------Second_end_count-------");
        for(Player mapkey : end_count.keySet())
            System.out.println("플레이어 : " + mapkey + " 값 : " + end_count.get(mapkey));
        System.out.println("----------------------");
        System.out.println("------Ingame-------");
        for(Player mapkey : InGame.keySet())
            System.out.println("플레이어 : " + mapkey + " 값 : " + InGame.get(mapkey));
        System.out.println("----------------------");
    }

    public void add_end_count(Player p) {
        int former_end_count = end_count.get(p);
        former_end_count++;
        end_count.put(p, former_end_count);
    }
    public void set_default_end_count(Player p) {
        end_count.put(p, 0);
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

    public void clear_game_data(Player p) {
        if(end_count.containsKey(p)){
            end_count.remove(p);
        }

        ListIterator cil_it2 = current_item_list.listIterator();
        while (cil_it2.hasNext()) {
            HashMap<Player, String> temp = (HashMap) cil_it2.next();
            if(temp.containsKey(p))
                cil_it2.remove();
        }
    }

    public void modify_InGame(Player p, Boolean boolean_) {
        InGame.put(p, boolean_);
    }

    public Boolean check_InGame(Player p) {
        if(InGame.get(p) == null)
            return false;
        if(InGame.get(p)) {
            InGame.remove(p);
            return true;
        }
        return false;
    }
}
