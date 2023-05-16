package me.bubbles.broadcast;

import me.bubbles.broadcast.commands.BroadcastCommand;
import me.bubbles.broadcast.commands.BroadcastReload;
import me.bubbles.broadcast.commands.ToggleBroadcast;
import me.bubbles.broadcast.timer.AutoBroadcastManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Broadcast extends JavaPlugin {

    private AutoBroadcastManager abcManager;

    @Override
    public void onEnable() {

        // ABC Manager
        abcManager=new AutoBroadcastManager(this);

        // CONFIG
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // COMMANDS

        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("abcreload").setExecutor(new BroadcastReload(this));
        getCommand("abctoggle").setExecutor(new ToggleBroadcast(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reload() {
        saveDefaultConfig();
        reloadConfig();
        abcManager.reloadMessages();
    }

    public AutoBroadcastManager getAbcManager() {
        return abcManager;
    }

}
