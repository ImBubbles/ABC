package me.bubbles.broadcast.commands;

import me.bubbles.broadcast.Broadcast;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleBroadcast implements CommandExecutor {

    private Broadcast instance;

    public ToggleBroadcast(Broadcast instance) {
        this.instance=instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("abc.toggle")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cInvalid Permissions. &4abc.toggle"));
                return true;
            }
            instance.getAbcManager().toggle();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aAuto-broadcast is now set to &e"+instance.getAbcManager().isEnabled()+"&a."));
        }
        return true;
    }

}
