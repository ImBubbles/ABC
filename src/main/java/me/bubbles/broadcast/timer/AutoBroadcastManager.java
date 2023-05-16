package me.bubbles.broadcast.timer;

import me.bubbles.broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class AutoBroadcastManager {

    private Broadcast instance;
    private boolean enabled = true;
    private Counter counter;
    private List<String> messages;
    private int index=0;
    private int seconds=0;

    public AutoBroadcastManager(Broadcast instance) {
        this.instance=instance;
        counter=new Counter(this,instance).setEnabled(true);
        reloadMessages();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled=!enabled;
        counter.toggle();
    }

    public void setEnabled(boolean bool) {
        enabled=bool;
        if(!enabled)
            counter.setEnabled(false);
        if(enabled)
            counter.setEnabled(true);
    }

    public void onSecond() {
        seconds = clamp(seconds+1,0,instance.getConfig().getInt("abcDelay"));
        if(seconds==0) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("broadcastMessage").replace("%message%",messages.get(index)))
            );
            index = clamp(index+1,0,messages.size()-1);
        }
    }

    public void reloadMessages() {
        this.messages=instance.getConfig().getStringList("autobroadcast");
    }

    private int clamp(int now, int min, int max) {
        if(now>max) {
            return min;
        }
        if(now<min) {
            return max;
        }
        return now;
    }

}
