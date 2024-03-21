package com.minerail.yashashop.utils;

import com.minerail.yashashop.main.YashaShop;
import com.minerail.yashashop.menus.Guis;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LangManager {
    private static Plugin plugin;
    private static YamlConfiguration ms;
    private static File m1;
    private static ConfigUtils config;

    //Messages
    public static String prefix;
    public static String missingPermission;
    public static String reloadMessage;
    //Gui Icons
    public static String returnButton;
    public static String cancelButton;
    public static String nextPage;
    public static String previousPage;
    //Lore content
    public static String cost;
    public static String sell;
    public static String space;
    public static String lAction;
    public static String rAction;
    public static String costOFF;
    public static String sellOFF;

    public LangManager(Guis guis) {
    }

    public LangManager(ConfigUtils configUtils) {
    }


    public void createMessages() {
        plugin = Bukkit.getPluginManager().getPlugin("YashaShop");
        m1 = new File(plugin.getDataFolder(), "messages.yml");
        if (!m1.exists()) {
            plugin.saveResource("messages.yml", false);
            ms = YamlConfiguration.loadConfiguration(m1);
            loadMessages();
        } else {
            ms = YamlConfiguration.loadConfiguration(m1);
            loadMessages();
        }
    }
    public void loadMessages() {
        this.config = new ConfigUtils(this);
        if (config.config.getBoolean("Messages.prefix", true)) {
            prefix = ms.getString("prefix");
            missingPermission = prefix + ms.getString("player-no-permission");
            reloadMessage = prefix + ms.getString("reload-message");
        } else {
            missingPermission = ms.getString("player-no-permission");
            reloadMessage = ms.getString("reload-message");
        }
        returnButton = ms.getString("return-button");
        cancelButton = ms.getString("cancel-button");
        nextPage = ms.getString("next-page");
        previousPage = ms.getString("previous-page");

        cost = ms.getString("items-lore.cost-1st-line");
        sell = ms.getString("items-lore.sell-2st-line");

            space = ms.getString("items-lore.space");
            lAction = ms.getString("items-lore.left-click-action");
            rAction = ms.getString("items-lore.right-click-action");
        sellOFF = ms.getString("items-lore.sell-off");
        costOFF = ms.getString("items-lore.buy-off");
    }
    public Component buildMessage(String msg) {
        return null;
    }
    public List<Component> buildLore(double c, double s) {
        List<Component> lore = new ArrayList<>();
        lore.clear();
        if (c != -1 && s != -1) {
            lore.add(MiniMessage.miniMessage().deserialize(costOFF));
            lore.add(MiniMessage.miniMessage().deserialize(sellOFF));
        } else if (c != -1 && s == -1) {
            lore.add(MiniMessage.miniMessage().deserialize(cost, Placeholder.component("cost", Component.text(c))));
            lore.add(MiniMessage.miniMessage().deserialize(sellOFF));
        } else if (c == -1 && s != -1) {
            lore.add(MiniMessage.miniMessage().deserialize(costOFF));
            lore.add(MiniMessage.miniMessage().deserialize(sell, Placeholder.component("sell", Component.text(s))));
        } else {
            lore.add(MiniMessage.miniMessage().deserialize(cost, Placeholder.component("cost", Component.text(c))));
            lore.add(MiniMessage.miniMessage().deserialize(sell, Placeholder.component("sell", Component.text(s))));
        }
        if (config.config.getBoolean("Messages.space-in-lore")) {
        lore.add(Component.text(space));
        }
        lore.add(Component.text(lAction));
        lore.add(Component.text(rAction));
        return lore;
    }
}
