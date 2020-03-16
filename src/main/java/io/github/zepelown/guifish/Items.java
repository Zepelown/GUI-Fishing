package io.github.zepelown.guifish;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Items {

	//참고 : http://blog.naver.com/PostView.nhn?blogId=hino1149&logNo=220942348809

	private static ItemStack buildItem(String displayName, Material type, int amount, String... lore) {
		ItemStack stack = new ItemStack(type, amount);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(Arrays.asList(lore));
		stack.setItemMeta(meta);
		return stack;
	}

	public static final ItemStack FISHING_ROD = buildItem(ChatColor.GOLD + "흭득", Material.FISHING_ROD, 1);
	public static final ItemStack BARRIER = buildItem(ChatColor.RED + "취소/버리기", Material.BARRIER, 1);
	public static final ItemStack WHITE_STAINED_GLASS_PANE = buildItem("제작자 : Zepelown", Material.WHITE_STAINED_GLASS_PANE, 1);
	public static final ItemStack BLUE_STAINED_GLASS_PANE = buildItem("제작자 : Zepelown", Material.BLUE_STAINED_GLASS_PANE, 1);
	public static final ItemStack BLACK_STAINED_GLASS_PANE = buildItem("제작자 : Zepelown", Material.BLACK_STAINED_GLASS_PANE, 1);
	public static final ItemStack GREEN_STAINED_GLASS_PANE = buildItem("제작자 : Zepelown", Material.GREEN_STAINED_GLASS_PANE, 1);
	public static final ItemStack YELLOW_STAINED_GLASS_PANE = buildItem(ChatColor.YELLOW + "게이지", Material.YELLOW_STAINED_GLASS_PANE, 1);
	public static final ItemStack CAUGHT_FISH = buildItem(ChatColor.AQUA + "목표", Material.SALMON, 1);

	public static final ItemStack FIRST_GAME_GUIDE_SIGN = buildItem(ChatColor.GREEN + "도움말", Material.OAK_SIGN, 1,
			ChatColor.GRAY + "노란색 게이지가 물고기 선을 넘어갈 때 낚싯대를 클릭하시면 됩니다!!",
			ChatColor.GRAY + "총 게이지는 두 번 왔다 갔다 합니다.");
	public static final ItemStack SECOND_GAME_GUIDE_SIGN = buildItem(ChatColor.YELLOW + "도움말", Material.OAK_SIGN, 1,
			ChatColor.GRAY + "목표 칸에 알맞는 순서대로 블럭을 클릭하셔야합니다!",
			ChatColor.RED + "밑에 빨간색이 끝까지 차면 끝이 나니 조심하세요!");
	public static final ItemStack RESULT_GUIDE_SIGN = buildItem(ChatColor.YELLOW + "도움말", Material.OAK_SIGN, 1,
			ChatColor.RED + "ESC 등으로 인벤토리를 강제 종료할 시 아이템이 사라집니다!!!!!!");

	public static final ItemStack GRASS = buildItem("잔디", Material.GRASS_BLOCK, 1);
	public static final ItemStack GLASS = buildItem("유리", Material.GLASS, 1);
	public static final ItemStack BED_ROCK = buildItem("베드락", Material.BEDROCK, 1);
	public static final ItemStack STONE = buildItem("돌", Material.STONE, 1);
	public static final ItemStack OAK_LOG = buildItem("나무", Material.OAK_LOG, 1);

	public static ItemStack getTimer(int blockCount) {
		return buildItem((blockCount * 12.5) + "%", Material.RED_STAINED_GLASS_PANE, 1, ChatColor.RED + "빨간색으로 가득차면 게임이 끝납니다!!");
	}
}
