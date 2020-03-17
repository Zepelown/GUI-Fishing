package io.github.zepelown.guifish.gamedata;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class SecondGameDataManager {

	private static final Map<Player, Integer> end_count = new HashMap<>();
	private static final List<HashMap<Player, String>> current_item_list = new ArrayList<>();
	private static final Map<Player, Boolean> InGame = new HashMap<>();
	private SecondGameDataManager() {
	}

	public static void add_ArrayList(Player p, String s) {
		HashMap<Player, String> temp = new HashMap<>();
		temp.put(p, s);
		current_item_list.add(temp);
	}

	public static boolean check_and_remove_ArrayList(Player p, String m) {
		ListIterator<HashMap<Player, String>> cil_it = current_item_list.listIterator();
		while (cil_it.hasNext()) {
			HashMap<Player, String> map = cil_it.next();
			if (map.containsKey(p)) {
				if (map.containsValue(m)) {
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

	public static void print_ArrayList() {
		for (HashMap<Player, String> playerStringHashMap : current_item_list) System.out.println(playerStringHashMap);
		System.out.println("랜덤 블럭 리스트 불러오기 완료");
	}

	public static void print_mapdata() {
		System.out.println("------Second_end_count-------");
		for (Player mapkey : end_count.keySet())
			System.out.println("플레이어 : " + mapkey + " 값 : " + end_count.get(mapkey));
		System.out.println("----------------------");
		System.out.println("------Ingame-------");
		for (Player mapkey : InGame.keySet())
			System.out.println("플레이어 : " + mapkey + " 값 : " + InGame.get(mapkey));
		System.out.println("----------------------");
	}

	public static void add_end_count(Player p) {
		int former_end_count = end_count.get(p);
		former_end_count++;
		end_count.put(p, former_end_count);
	}

	public static void set_default_end_count(Player p) {
		end_count.put(p, 0);
	}

	public static int get_end_count(Player p) {
		return end_count.get(p);
	}

	public static boolean check_end(Player p) {
		if (end_count.get(p) == 7) {
			end_count.put(p, 0);
			return true;
		} else
			return false;
	}

	public static void clear_game_data(Player p) {
		end_count.remove(p);

		current_item_list.removeIf(map -> map.containsKey(p));
	}

	public static void modify_InGame(Player p, Boolean boolean_) {
		InGame.put(p, boolean_);
	}

	public static boolean check_InGame(Player p) {
		if (InGame.get(p) == null)
			return false;
		if (InGame.get(p)) {
			InGame.remove(p);
			return true;
		}
		return false;
	}
}
