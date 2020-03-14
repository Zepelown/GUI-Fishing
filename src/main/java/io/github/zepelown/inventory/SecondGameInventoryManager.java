package io.github.zepelown.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.ItemManager;
import io.github.zepelown.GameData.SecondGameDataManager;
import io.github.zepelown.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

//출처 : https://github.com/MinusKube/SmartInvs
public class SecondGameInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

    SecondGameDataManager dm = new SecondGameDataManager();
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
        contents.set(5, 8, ClickableItem.empty(ItemManager.SecondGame_Guide_Sign));

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
                        contents.set(1, x + 1, ClickableItem.empty(ItemManager.Glass));
                        dm.add_ArrayList(player, ItemManager.Glass.getType().toString());
                        break;
                    case 2:
                        contents.set(1, x + 1, ClickableItem.empty(ItemManager.Grass));
                        dm.add_ArrayList(player, ItemManager.Grass.getType().toString());
                        break;
                    case 3:
                        contents.set(1, x + 1, ClickableItem.empty(ItemManager.Stone));
                        dm.add_ArrayList(player, ItemManager.Stone.getType().toString());
                        break;
                    case 4:
                        contents.set(1, x + 1, ClickableItem.empty(ItemManager.BedRock));
                        dm.add_ArrayList(player, ItemManager.BedRock.getType().toString());
                        break;
                    case 5:
                        contents.set(1, x + 1, ClickableItem.empty(ItemManager.Oak_Log));
                        dm.add_ArrayList(player, ItemManager.Oak_Log.getType().toString());
                        break;
                }
            }

        }

        //눌러야하는 칸 처리
        contents.set(3, 2, ClickableItem.of(ItemManager.Glass, e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, ItemManager.Glass.getType(), contents);
            }
                }));
        contents.set(3, 3, ClickableItem.of(ItemManager.Grass, e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, ItemManager.Grass.getType(), contents);
            }
        }));
        contents.set(3,4,ClickableItem.of(ItemManager.BedRock, e-> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, ItemManager.BedRock.getType(), contents);
            }

        }));
        contents.set(3, 5, ClickableItem.of(ItemManager.Stone, e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, ItemManager.Stone.getType(), contents);
            }
        }));
        contents.set(3, 6, ClickableItem.of(ItemManager.Oak_Log, e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, ItemManager.Oak_Log.getType(), contents);
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
