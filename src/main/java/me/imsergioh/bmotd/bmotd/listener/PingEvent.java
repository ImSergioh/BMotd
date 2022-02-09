package me.imsergioh.bmotd.bmotd.listener;

import me.imsergioh.bmotd.bmotd.BMotd;
import me.imsergioh.bmotd.bmotd.manager.MOTDManager;
import me.imsergioh.bmotd.bmotd.util.ChatUtil;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingEvent implements Listener {

    private BMotd plugin = BMotd.getPlugin();

    @EventHandler
    public void onEvent(ProxyPingEvent event){
        MOTDManager motdManager = plugin.getMotdManager();

        PendingConnection connection = event.getConnection();
        ServerPing serverPing = event.getResponse();

        serverPing.setPlayers(new ServerPing.Players(motdManager.getMaxSlots(), plugin.getProxy().getOnlineCount(), null));
        serverPing.setDescriptionComponent(new TextComponent(ChatUtil.chatColor(motdManager.getMOTD())));

        serverPing.setFavicon(motdManager.getFavicon());

        event.setResponse(serverPing);
    }
}
