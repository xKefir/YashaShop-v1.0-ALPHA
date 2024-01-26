package com.minerail.yashashop.commands;

import com.minerail.yashashop.main.YashaShop;
import com.minerail.yashashop.menus.CategoriesMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;


public class CategoryCommand implements CommandExecutor {
    public CategoryCommand(YashaShop categories) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new CategoriesMenu(player).open();
        }
        return false;
    }
}