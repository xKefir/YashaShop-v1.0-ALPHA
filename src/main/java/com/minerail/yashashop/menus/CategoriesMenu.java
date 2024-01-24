package com.minerail.yashashop.menus;


import com.minerail.yashashop.files.ConfigManager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class CategoriesMenu extends Gui {
    public CategoriesMenu(Player player) {
        super(player,
                "category",
                ConfigManager.getCategoryTitle(),
                ConfigManager.getConfigInt("guiMenus.categories.rows"));
    }
    public void onOpen(InventoryOpenEvent event) {
        //Blocks

        fillColumn(new Icon(Material.valueOf("t")),3);
    }



}
