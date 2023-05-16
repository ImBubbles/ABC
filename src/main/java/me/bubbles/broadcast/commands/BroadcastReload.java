package me.bubbles.broadcast.commands;

import me.bubbles.broadcast.Broadcast;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastReload implements CommandExecutor {

    private Broadcast instance;

    public BroadcastReload(Broadcast instance) {
        this.instance=instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("abc.reload")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cInvalid Permissions. &4abc.reload"));
                return true;
            }
            instance.reload();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"" +
                    "&aABC config reloaded"));
        }
        return true;
    }

}
