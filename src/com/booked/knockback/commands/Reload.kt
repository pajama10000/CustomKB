package com.booked.knockback.commands;

import com.booked.knockback.Knockback;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            // The command can only be executed by a player
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("kb.reload")) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§6_________________________________________________________\n" +
                    "§8>>§6§lCommands§8§r<<\n" +
                    "§b/knockback reload §cor §b/kb reload\n" +
                    "§aMake sure to check the config.yml for more information!\n" +
                    "§6_________________________________________________________");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                player.sendMessage("§cConfig reloading...");
                Knockback.getInstance().reloadConfig();
                player.sendMessage("§aConfig reloaded!");
            } else if (args[0].equalsIgnoreCase("help")) {
                player.sendMessage("§6_________________________________________________________\n" +
                        "§8>>§6§lCommands§8§r<<\n" +
                        "§b/knockback reload §cor §b/kb reload\n" +
                        "§aMake sure to check the config.yml for more information!\n" +
                        "§6_________________________________________________________");
            }
        }

        return true;
    }
}
