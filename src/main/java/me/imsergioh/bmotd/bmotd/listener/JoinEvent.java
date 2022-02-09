package me.imsergioh.bmotd.bmotd.listener;

import me.imsergioh.bmotd.bmotd.BMotd;
import me.imsergioh.bmotd.bmotd.manager.MOTDManager;
import me.imsergioh.bmotd.bmotd.util.ChatUtil;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinEvent implements Listener {

    private BMotd plugin = BMotd.getPlugin();

    @EventHandler
    public void onEvent(ServerConnectedEvent event){
        MOTDManager motdManager = plugin.getMotdManager();

        if(!motdManager.hasWhitelist(event.getPlayer().getName())){
            event.getPlayer().disconnect(ChatUtil.chatColor(motdManager.getConfig().getString("messages.not-whitelist")));
        }


    }
}
