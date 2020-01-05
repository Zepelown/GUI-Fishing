package io.github.zepelown.Inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.ItemManager.ItemManager;
import io.github.zepelown.main.DataManager;
import io.github.zepelown.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

//출처 : https://github.com/MinusKube/SmartInvs
public class MainInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

    DataManager dm = new DataManager();
    ItemStack Glass = new ItemStack(Material.GLASS);
    ItemStack Grass = new ItemStack(Material.GRASS_BLOCK);
    ItemStack BedRock = new ItemStack(Material.BEDROCK);
    ItemStack Oak_Log = new ItemStack(Material.OAK_LOG);
    ItemStack Stone = new ItemStack(Material.STONE);



    InventoryList il = new InventoryList();


    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillRow(0, ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));
        contents.fillRow(2, ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));

        contents.set(1,8,ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));
        contents.set(1, 0,ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));

        contents.set(3, 0, ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
        contents.set(3, 1, ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));
        contents.set(3, 7, ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));
        contents.set(3, 8, ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));

        contents.fillRow(5, ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));

        //나가기 버튼 처리
        contents.set(5, 8, ClickableItem.of(ItemManager.Guide_Sign, e -> {
            if(e.isLeftClick()) {
                player.closeInventory();
            }
        }));

        contents.set(5,7,ClickableItem.of(new ItemStack(Material.CHEST), e-> {
            if(e.isLeftClick()) {
                dm.print_ArrayList();
            }
        }));

        for(int x = 0; x < 8; x++) {
            //7줄 처리
            if(x < 2 || x >6) {
                if(x == 7) {
                    contents.set(4,  x + 1, ClickableItem.empty(ItemManager.Black_Stained_Glass_Pane));
                }
                contents.set(4, x, ClickableItem.empty(ItemManager.Black_Stained_Glass_Pane));
            }
            else
                contents.set(4, x, ClickableItem.empty(ItemManager.Blue_Stained_Glass_Pane));

            //목표 칸 처리
            if(x + 1 < 8) {
                Random random = new Random();
                switch (random.nextInt(5) + 1) {
                    case 1:
                        contents.set(1, x + 1, ClickableItem.empty(Glass));
                        dm.add_ArrayList(player, Glass.getType().toString());
                        break;
                    case 2:
                        contents.set(1, x + 1, ClickableItem.empty(Grass));
                        dm.add_ArrayList(player, Grass.getType().toString());
                        break;
                    case 3:
                        contents.set(1, x + 1, ClickableItem.empty(Stone));
                        dm.add_ArrayList(player, Stone.getType().toString());
                        break;
                    case 4:
                        contents.set(1, x + 1, ClickableItem.empty(BedRock));
                        dm.add_ArrayList(player, BedRock.getType().toString());
                        break;
                    case 5:
                        contents.set(1, x + 1, ClickableItem.empty(Oak_Log));
                        dm.add_ArrayList(player, Oak_Log.getType().toString());
                        break;
                }
            }

        }

        //눌러야하는 칸 처리
        contents.set(3, 2, ClickableItem.of(new ItemStack(Material.GLASS), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Glass.getType(), contents);
            }
                }));
        contents.set(3, 3, ClickableItem.of(new ItemStack(Material.GRASS_BLOCK), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Grass.getType(), contents);
            }
        }));
        contents.set(3,4,ClickableItem.of(new ItemStack(Material.BEDROCK), e-> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, BedRock.getType(), contents);
            }

        }));
        contents.set(3, 5, ClickableItem.of(new ItemStack(Material.STONE), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Stone.getType(), contents);
            }
        }));
        contents.set(3, 6, ClickableItem.of(new ItemStack(Material.OAK_LOG), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Oak_Log.getType(), contents);
            }
        }));

        contents.newIterator("timer", SlotIterator.Type.HORIZONTAL, 5, 0);
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 20 != 0)
            return;

        SlotIterator timer = contents.iterator("timer").get();

        int time = timer.column();

        if(time >= 7) {
            player.closeInventory();
            return;
        }

        timer.set(ClickableItem.empty(ItemManager.set_Timer(time + 1))).next();
    }

    public void control_MainGame_Class(Player player, Material item, InventoryContents contents) {
        if(dm.check_and_remove_ArrayList(player, item.toString())) {
            player.sendMessage(Main.prefix + "제대로 선택하였습니다!");
            contents.set(1, dm.get_end_count(player), ClickableItem.empty(new ItemStack(Material.AIR)));
        } else {
            player.sendMessage(Main.prefix + "다시 선택해주세요!");
        }

        if(dm.check_end(player)) {
            dm.modify_InGame(player, true);
            player.closeInventory();
            il.ResultInventory.open(player);
        }

    }
}
