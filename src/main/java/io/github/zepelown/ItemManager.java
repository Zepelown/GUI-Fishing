package io.github.zepelown;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
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

    public static ItemStack FISHING_ROD = new ItemStack(set_item(ChatColor.GOLD + "흭득", Material.FISHING_ROD, 1, Arrays.asList("")));
    public static ItemStack BARRIER = new ItemStack(set_item(ChatColor.RED + "취소/버리기", Material.BARRIER, 1, Arrays.asList("")));
    public static ItemStack White_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.WHITE_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Blue_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.BLUE_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Black_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.BLACK_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Green_Stained_Glass_Pane = new ItemStack(set_item("제작자 : Zepelown",
            Material.GREEN_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Yellow_Stained_Glass_Pane = new ItemStack(set_item(ChatColor.YELLOW + "게이지",
            Material.YELLOW_STAINED_GLASS_PANE, 1, Arrays.asList("")));
    public static ItemStack Caught_Fish = new ItemStack(set_item(ChatColor.AQUA + "목표",
            Material.SALMON, 1, Arrays.asList("")));

    public static ItemStack FirstGame_Guide_Sign = new ItemStack(set_item(ChatColor.GREEN + "도움말", Material.OAK_SIGN, 1,
            Arrays.asList(ChatColor.GRAY + "노란색 게이지가 물고기 선을 넘어갈 때 낚싯대를 클릭하시면 됩니다!!",
                    ChatColor.GRAY + "총 게이지는 두 번 왔다 갔다 합니다.")));
    public static ItemStack SecondGame_Guide_Sign = new ItemStack(set_item(ChatColor.YELLOW + "도움말", Material.OAK_SIGN, 1,
            Arrays.asList(ChatColor.GRAY + "목표 칸에 알맞는 순서대로 블럭을 클릭하셔야합니다!",
                    ChatColor.RED + "밑에 빨간색이 끝까지 차면 끝이 나니 조심하세요!")));
    public static ItemStack Result_Guid_Sign = new ItemStack(set_item(ChatColor.YELLOW + "도움말", Material.OAK_SIGN, 1,
            Arrays.asList(ChatColor.RED + "ESC 등으로 인벤토리를 강제 종료할 시 아이템이 사라집니다!!!!!!")));

    public static ItemStack Grass = new ItemStack(set_item("잔디", Material.GRASS_BLOCK, 1, Arrays.asList("")));
    public static ItemStack Glass = new ItemStack(set_item("유리", Material.GLASS, 1, Arrays.asList("")));
    public static ItemStack BedRock = new ItemStack(set_item("베드락", Material.BEDROCK, 1, Arrays.asList("")));
    public static ItemStack Stone = new ItemStack(set_item("돌", Material.STONE, 1, Arrays.asList("")));
    public static ItemStack Oak_Log = new ItemStack(set_item("나무", Material.OAK_LOG, 1, Arrays.asList("")));

    public static ItemStack set_Timer(int block_count) {
        double time = block_count * 12.5;
        return new ItemStack(set_item(time + "%", Material.RED_STAINED_GLASS_PANE, 1,
                Arrays.asList(ChatColor.RED + "빨간색으로 가득차면 게임이 끝납니다!!")));
    }
}
