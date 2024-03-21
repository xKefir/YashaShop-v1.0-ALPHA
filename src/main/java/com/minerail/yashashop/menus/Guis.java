package com.minerail.yashashop.menus;

import com.minerail.yashashop.commands.YshopCmd;
import com.minerail.yashashop.utils.ConfigUtils;
import com.minerail.yashashop.utils.LangManager;
import com.minerail.yashashop.utils.SectionFilesUtils;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import java.util.LinkedHashMap;
import java.util.Map;


public class Guis {
    public static Gui guis;
    private static PaginatedGui sectionGui;
    private ConfigUtils configUtils = new ConfigUtils(this);
    private static GuiItem item;
    public Map<String, PaginatedGui> sections = new LinkedHashMap<>();
    private LangManager langManager;
    private Map<NamespacedKey, String> keys1 = new LinkedHashMap<>();
    private Map<Integer, GuiItem> itemsToAdd = new LinkedHashMap<>();
    public Guis(SectionFilesUtils sectionFilesUtils) {
    }

    public Guis(YshopCmd yshopCmd) {
    }

    public void create(String name, int rows) {
        if (this.configUtils.guiEnabled()) {
            guis = Gui.gui()
                    .title(MiniMessage.miniMessage().deserialize(name))
                    .rows(rows)
                    .create();
            guis.setDefaultClickAction(e -> { e.setCancelled(true);});
        }
    }
    public void setItems(GuiItem item, int slot) {
        guis.setItem(slot, item);
    }
    public void buildItemsForCategoryGui(Boolean en, String name, String mat, String id, int slot) {
        if (en) {
            configUtils.plugin.getLogger().info("test3 " + id);
            NamespacedKey key = new NamespacedKey(configUtils.plugin, id);
            keys1.put(key, id);
            item = ItemBuilder.from(Material.valueOf(mat)).name(MiniMessage.miniMessage().deserialize(name)).setNbt(id, "1b").asGuiItem(e -> {
                for (Map.Entry<String, PaginatedGui> openGui : sections.entrySet()) {
                    for (Map.Entry<NamespacedKey, String> ke : keys1.entrySet()) {
                        if (e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(ke.getKey(), PersistentDataType.STRING)) {
                            openGui.getValue().open(e.getWhoClicked());
                        }
                    }
                }
            });
            itemsToAdd.put(slot, item);
            item = null;
        }
    }
    public void createSectionGuis(String id, String name, int rows, int pageSize) {
        sectionGui = Gui.paginated().title(MiniMessage.miniMessage().deserialize(name)).rows(rows).pageSize(pageSize).create();
        sections.put(id, sectionGui);
    }
    public void buildItemsForSection(String mat, double cost, double sell) {
        this.langManager = new LangManager(this);
        item = ItemBuilder.from(Material.valueOf(mat)).lore(langManager.buildLore(cost, sell)).asGuiItem();
    }

}
