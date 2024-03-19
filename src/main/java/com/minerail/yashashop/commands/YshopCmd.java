package com.minerail.yashashop.commands;

import com.minerail.yashashop.main.YashaShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class YshopCmd implements CommandExecutor {
    public YshopCmd(YashaShop yashaShop) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}
