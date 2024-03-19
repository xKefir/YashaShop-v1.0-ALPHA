package com.minerail.yashashop.menus;

import com.minerail.yashashop.utils.ConfigUtils;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Guis {
    private Gui guis;
    private ConfigUtils configUtils = new ConfigUtils(this);
    private GuiItem item;
    public void create(String name, int rows) {
        if (this.configUtils.guiEnabled()) {
            guis = Gui.gui()
                    .title(MiniMessage.miniMessage().deserialize(name))
                    .rows(rows)
                    .create();
        }
    }
    public void setItems(GuiItem item, int slot) {

    }
    public void buildItems(Boolean en, String name, String mat, String id) {

    }
}
