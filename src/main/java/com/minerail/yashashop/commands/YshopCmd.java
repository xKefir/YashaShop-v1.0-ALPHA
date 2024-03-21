package com.minerail.yashashop.commands;

import com.minerail.yashashop.main.YashaShop;
import com.minerail.yashashop.menus.Guis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class YshopCmd implements CommandExecutor {
    private Guis gui = new Guis(this);
    public YshopCmd(YashaShop yashaShop) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            gui.guis.open(player);
            return true;
        }
        return false;
    }
}
