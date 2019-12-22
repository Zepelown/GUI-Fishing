package io.github.zepelown.Inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MainInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillRow(0, ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));
        contents.fillRow(2, ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));

        contents.set(1,8,ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));
        contents.set(1, 0,ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));

        contents.set(3, 0, ClickableItem.empty(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)));
        contents.set(3, 1, ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));
        contents.set(3, 7, ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));
        contents.set(3, 8, ClickableItem.empty(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)));

        contents.fillRow(5, ClickableItem.empty(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)));

        for(int x = 0; x < 8; x++) {
            //7줄 처리
            if(x < 2 || x >6) {
                if(x == 7)
                    x++;
                contents.set(4, x, ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
                x--;
            }
            else
                contents.set(4, x, ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));

            //목표 칸 처리
            if(x + 1 < 8) {
                Random random = new Random();
                switch(random.nextInt(5)+ 1) {
                    case 1:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.GLASS)));
                        break;
                    case 2:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.GRASS_BLOCK)));
                        break;
                    case 3:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.STONE)));
                        break;
                    case 4:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.BEDROCK)));
                        break;
                    case 5:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.OAK_LOG)));
                        break;
                }
            }

        }

        //눌러야하는 칸 처리
        contents.set(3, 2, ClickableItem.of(new ItemStack(Material.GLASS), e -> {
            if(e.isLeftClick()) {
                player.sendMessage("유리를 선택했습니다.");
            }
                }));
        contents.set(3, 3, ClickableItem.empty(new ItemStack(Material.GRASS_BLOCK)));
        contents.set(3,4,ClickableItem.empty(new ItemStack(Material.BEDROCK)));
        contents.set(3, 5, ClickableItem.empty(new ItemStack(Material.STONE)));
        contents.set(3, 6, ClickableItem.empty(new ItemStack(Material.OAK_LOG)));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
