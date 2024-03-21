package com.minerail.yashashop.main;
import com.minerail.yashashop.commands.YshopCmd;
import com.minerail.yashashop.utils.ConfigUtils;
import com.minerail.yashashop.utils.LangManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public final class YashaShop extends JavaPlugin {
    private ConfigUtils config = new ConfigUtils(this);
    @Override
    public void onEnable() {
        //Cmds
        try {
            this.config.createOrLoadConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        setupEconomy();
        getCommand("yshop").setExecutor(new YshopCmd(this));
        //Hooks
    }
    public boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getServer().getPluginManager().disablePlugin(this);
            return false;
        } else { return true;}
    }
    @Override
    public void onDisable() {
        getLogger().info("Disabling plugin..");
    }
}
