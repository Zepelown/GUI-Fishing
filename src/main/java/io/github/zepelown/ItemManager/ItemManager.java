package io.github.zepelown.ItemManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ItemManager {

    //참고 : http://blog.naver.com/PostView.nhn?blogId=hino1149&logNo=220942348809
    public static ItemStack set_item(String display, Material material, int stack, List<String> lore) {
        ItemStack item = new ItemStack(material, stack);
        ItemMeta item_meta = item.getItemMeta();
        item_meta.setDisplayName(display);
        item_meta.setLore(lore);
        item.setItemMeta(item_meta);
        return item;
    }

    public static ItemStack GreenWool = new ItemStack(set_item("흭득", Material.GREEN_WOOL, 1, Arrays.asList("")));
    public static ItemStack RedWool = new ItemStack(set_item("취소/버리기", Material.RED_WOOL, 1, Arrays.asList("")));
    public static ItemStack White_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.WHITE_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Blue_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.BLUE_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Black_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.BLACK_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Guide_Sign = new ItemStack(set_item("도움말", Material.OAK_SIGN, 1,
            Arrays.asList("")));

    public static ItemStack set_Timer(int block_count) {
        double time = block_count * 12.5;
        return new ItemStack(set_item(time + "%", Material.RED_STAINED_GLASS_PANE, 1,
                Arrays.asList(ChatColor.RED + "빨간색으로 가득차면 게임이 끝납니다!!")));
    }
}
