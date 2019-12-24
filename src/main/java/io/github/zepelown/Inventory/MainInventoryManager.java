package io.github.zepelown.Inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.SlotIterator;
import io.github.zepelown.main.MainGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

//출처 : https://github.com/MinusKube/SmartInvs
public class MainInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

    MainGame mg = new MainGame();
    ItemStack Glass = new ItemStack(Material.GLASS);
    ItemStack Grass = new ItemStack(Material.GRASS_BLOCK);
    ItemStack BedRock = new ItemStack(Material.BEDROCK);
    ItemStack Oak_Log = new ItemStack(Material.OAK_LOG);
    ItemStack Stone = new ItemStack(Material.STONE);

    private static HashMap<Player, Boolean> Complete_Game = new HashMap<>();

    Iterator<Player> Complete_game_Ir = Complete_Game.keySet().iterator();

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

        //나가기 버튼 처리
        contents.set(5, 8, ClickableItem.of(new ItemStack(Material.OAK_SIGN), e -> {
            if(e.isLeftClick()) {
                player.closeInventory();
            }
        }));

        contents.set(5, 7, ClickableItem.of(new ItemStack(Material.CHEST), e -> {
            if(e.isLeftClick()) {
                mg.print_array();
            }
        }));

        for(int x = 0; x < 8; x++) {
            //7줄 처리
            if(x < 2 || x >6) {
                if(x == 7) {
                    contents.set(4,  x + 1, ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
                }
                contents.set(4, x, ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
            }
            else
                contents.set(4, x, ClickableItem.empty(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));

            //목표 칸 처리
            if(x + 1 < 8) {
                Random random = new Random();
                switch (random.nextInt(5) + 1) {
                    case 1:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.GLASS)));
                        mg.add_ItemStack(player, Glass);
                        break;
                    case 2:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.GRASS_BLOCK)));
                        mg.add_ItemStack(player, Grass);
                        break;
                    case 3:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.STONE)));
                        mg.add_ItemStack(player, Stone);
                        break;
                    case 4:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.BEDROCK)));
                        mg.add_ItemStack(player, BedRock);
                        break;
                    case 5:
                        contents.set(1, x + 1, ClickableItem.empty(new ItemStack(Material.OAK_LOG)));
                        mg.add_ItemStack(player, Oak_Log);
                        break;
                }
            }

        }

        //눌러야하는 칸 처리
        contents.set(3, 2, ClickableItem.of(new ItemStack(Material.GLASS, 1), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Glass, contents);
            }
                }));
        contents.set(3, 3, ClickableItem.of(new ItemStack(Material.GRASS_BLOCK, 1), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Grass, contents);
            }
        }));
        contents.set(3,4,ClickableItem.of(new ItemStack(Material.BEDROCK, 1), e-> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, BedRock, contents);
            }

        }));
        contents.set(3, 5, ClickableItem.of(new ItemStack(Material.STONE, 1), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Stone, contents);
            }
        }));
        contents.set(3, 6, ClickableItem.of(new ItemStack(Material.OAK_LOG, 1), e -> {
            if(e.isLeftClick()) {
                control_MainGame_Class(player, Oak_Log, contents);
            }
        }));

        contents.newIterator("timer", SlotIterator.Type.HORIZONTAL, 5, 0);
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 25 != 0)
            return;

        SlotIterator timer = contents.iterator("timer").get();

        if(timer.column() > 6) {
            player.closeInventory();
            return;
        }

        timer.set(ClickableItem.empty(new ItemStack(Material.RED_STAINED_GLASS_PANE))).next();
    }

    public void control_MainGame_Class(Player player, ItemStack item, InventoryContents contents) {
        player.sendMessage(item.getType().toString() + " 를 선택하였습니다.");
        if(mg.check_ItemStack(player, item)) {
            mg.remove_ItemStack(player);
            player.sendMessage("제대로 선택하였습니다!");
            contents.set(1, mg.get_end_count(player), ClickableItem.empty(new ItemStack(Material.AIR)));
        } else {
            player.sendMessage("다시 선택해주세요!");
        }

        if(mg.check_end(player)) {
            player.sendMessage("물고기를 잡았습니다!!");
            Complete_Game.put(player, true);
            player.closeInventory();
        }

    }

    public Boolean check_Complete_Game(Player p) {
        while(Complete_game_Ir.hasNext()) {
            if(Complete_Game.containsKey(p)) {
                Complete_Game.remove(p);
                return true;
            }
        }
        return false;
    }
}
