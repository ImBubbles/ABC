package me.bubbles.broadcast.commands;

import me.bubbles.broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    private Broadcast instance;

    public BroadcastCommand(Broadcast instance) {
        this.instance=instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("abc.broadcast")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cInvalid Permissions. &4abc.broadcast"));
                return true;
            }
        }

        StringBuilder message = new StringBuilder();
        for(String str : args) {
            message.append(str).append(" ");
        }

        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("broadcastMessage").replace("%message%",message.toString()))
        );

        return true;

    }

}
