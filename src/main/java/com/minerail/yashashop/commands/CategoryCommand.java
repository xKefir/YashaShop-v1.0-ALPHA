package com.minerail.yashashop.commands;

import com.minerail.yashashop.main.YashaShop;
import com.minerail.yashashop.menus.GuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CategoryCommand implements CommandExecutor {
    public CategoryCommand(YashaShop yashaShop) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            GuiManager.showCategoriesGui(player);
        }
        return false;
    }
}
