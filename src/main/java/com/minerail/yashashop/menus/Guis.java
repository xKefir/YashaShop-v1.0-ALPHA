package com.minerail.yashashop.menus;

import com.minerail.yashashop.utils.ConfigUtils;
import com.minerail.yashashop.utils.SectionFilesUtils;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;

public class Guis {
    private Gui guis;
    private ConfigUtils configUtils = new ConfigUtils(this);
    private GuiItem item;

    public Guis(SectionFilesUtils sectionFilesUtils) {
    }

    public void create(String name, int rows) {
        if (this.configUtils.guiEnabled()) {
            guis = Gui.gui()
                    .title(MiniMessage.miniMessage().deserialize(name))
                    .rows(rows)
                    .create();
        }
    }
    public void setItems(GuiItem item, int slot) {
        guis.setItem(slot, item);
    }
    public void buildItemsForCategoryGui(Boolean en, String name, String mat, String id, int slot) {
        if (en) {
            item = ItemBuilder.from(Material.valueOf(mat)).name(MiniMessage.miniMessage().deserialize(name)).setNbt(id,"1b").asGuiItem();
        }
    }
    public void buildItemsForSection(String mat, double cost, double sell) {
        if (cost == -1 && sell == -1) {
            item = ItemBuilder.from(Material.valueOf(mat)).asGuiItem();
        } else if (cost == -1 && sell != -1) {
            item = ItemBuilder.from(Material.valueOf(mat)).asGuiItem();
        } else if (cost != -1 && sell == -1) {
            item = ItemBuilder.from(Material.valueOf(mat)).asGuiItem();
        } else {
            item = ItemBuilder.from(Material.valueOf(mat)).asGuiItem();
        }
    }
    public void createSectionGuis(String id, String name, int rows, int pageSize) {

    }
}
