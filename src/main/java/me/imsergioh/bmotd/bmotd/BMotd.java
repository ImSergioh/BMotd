package me.imsergioh.bmotd.bmotd;

import me.imsergioh.bmotd.bmotd.commands.bmotdCMD;
import me.imsergioh.bmotd.bmotd.listener.JoinEvent;
import me.imsergioh.bmotd.bmotd.listener.PingEvent;
import me.imsergioh.bmotd.bmotd.manager.MOTDManager;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public final class BMotd extends Plugin {

    private static BMotd plugin;

    private MOTDManager motdManager;

    @Override
    public void onEnable() {
        plugin = this;
        motdManager = new MOTDManager();

        this.getProxy().getPluginManager().registerCommand(plugin, new bmotdCMD("bmotd"));

        regListener(new PingEvent());
        regListener(new JoinEvent());
    }

    public MOTDManager getMotdManager() {
        return motdManager;
    }

    public static BMotd getPlugin() {
        return plugin;
    }

    private void regListener(Listener listener){
        this.getProxy().getPluginManager().registerListener(plugin, listener);
    }

}
