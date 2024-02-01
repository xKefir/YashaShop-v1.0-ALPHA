package com.minerail.yashashop.main;

import com.minerail.yashashop.commands.CategoryCommand;
import com.minerail.yashashop.commands.ReloadCommand;

import com.minerail.yashashop.files.FileManager;
import com.minerail.yashashop.files.LangManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class YashaShop extends JavaPlugin {


    @Override
    public void onEnable() {
        //Plugin Loading
        System.out.printf("Loading config files..");

        //Setup config
        FileManager.setupConfigFiles(this);
        //Loading lang file
        LangManager.setupLangFile(this);
        //Loading or creating files


        //Loading commands
        getCommand("categories").setExecutor(new CategoryCommand(this));
        getCommand("ysreload").setExecutor(new ReloadCommand(this));
        System.out.println("Commands successfully loaded!");


        //Vault hook
        System.out.println("Connecting to Vault..");
        if (!setupEconomy() ) {
            getLogger().severe(String.format("Disabling plugin. You need a Vault plugin."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            System.out.println("Successfully connected to Vault!");
        }


        //Loaded final message
        System.out.println("Plugin successfully loaded!");
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return true;
        }
        Economy econ = rsp.getProvider();
        return econ != null;
    }
    @Override
    public void onDisable() {
        System.out.println("Disabling plugin..");
        System.out.println("Plugin successfully disabled.");
    }
}
