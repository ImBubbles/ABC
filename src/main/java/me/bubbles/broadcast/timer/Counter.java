package me.bubbles.broadcast.timer;

import me.bubbles.broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class Counter {
    private boolean enabled;
    private AutoBroadcastManager abcManager;
    private Broadcast instance;

    public Counter(AutoBroadcastManager abcManager, Broadcast instance) {
        this.abcManager=abcManager;
        this.instance=instance;
        Count();
    }

    private void Count() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(instance, new Runnable() {
            @Override
            public void run() {
                if(enabled) {
                    abcManager.onSecond();
                    Count();
                }
            }
        }, 20);
    }

    public Counter toggle() {
        enabled=!enabled;
        if(enabled) {
            Count();
        }
        return this;
    }

    public Counter setEnabled(boolean bool) {
        enabled=bool;
        if(enabled) {
            Count();
        }
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
