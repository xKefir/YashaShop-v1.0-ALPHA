package com.minerail.yashashop.commands;


import com.minerail.yashashop.files.FileManager;
import com.minerail.yashashop.files.LangManager;
import com.minerail.yashashop.main.ColorUtils;
import com.minerail.yashashop.main.YashaShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ReloadCommand implements CommandExecutor {

    public ReloadCommand(YashaShop yashaShop) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("YashaShop.reload")) {
                LangManager.reloadLangFile();
                FileManager.reloadConfigFiles();
                commandSender.sendMessage(ColorUtils.translateColorCodes(LangManager.getPluginPrefix()) + " " + ColorUtils.translateColorCodes(LangManager.getReloadSuccessMsg()));
            }
        } else {
            LangManager.reloadLangFile();
            FileManager.reloadConfigFiles();
            System.out.println(LangManager.getReloadSuccessMsg());

        }
        return true;
    }

}
