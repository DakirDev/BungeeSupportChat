package de.dakir.bungeesupportchat;

import java.util.concurrent.TimeUnit;

import de.dakir.bungeesupportchat.commands.Support;
import de.dakir.bungeesupportchat.events.ChatEvent;
import de.dakir.bungeesupportchat.events.PlayerDisconnectEvent;
import de.dakir.bungeesupportchat.events.TabCompleteEvent;
import de.dakir.bungeesupportchat.utils.Data;
import de.dakir.bungeesupportchat.utils.HexxAPI;
import de.dakir.bungeesupportchat.utils.Strings;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class PluginManager {

    public static void load() {
        registerEvents();
        registerCommands();
        if (Data.enableQueueNotification) {
            registerScheduler();
        }
    }

    public static void unload() {
        net.md_5.bungee.api.plugin.PluginManager pm = ProxyServer.getInstance().getPluginManager();
        pm.unregisterCommands(Main.instance);
        pm.unregisterListeners(Main.instance);
    }

    public static void registerEvents() {
        net.md_5.bungee.api.plugin.PluginManager pm = ProxyServer.getInstance().getPluginManager();
        Plugin pl = Main.instance;
        pm.registerListener(pl, new ChatEvent());
        pm.registerListener(pl, new PlayerDisconnectEvent());
        pm.registerListener(pl, new TabCompleteEvent());
    }

    public static void registerCommands() {
        net.md_5.bungee.api.plugin.PluginManager pm = ProxyServer.getInstance().getPluginManager();
        Plugin pl = Main.instance;
        pm.registerCommand(pl, new Support("support"));
        pm.registerCommand(pl, new Support("sup"));
        pm.registerCommand(pl, new Support("sc"));
    }

    public static void registerScheduler() {
        ProxyServer.getInstance().getScheduler().schedule(Main.instance, new Runnable() {
            @Override
            public void run() {
                if (Data.supports.size() > 0) {
                    if (Data.supports.size() == 1) {
                        HexxAPI.sendSupportMessage(Strings.onePlayerNeedSupport.substring(0, Strings.onePlayerNeedSupport.length() - 1).replace("%number%", Data.supports.size() + ""));
                    } else if (Data.supports.size() > 1) {
                        HexxAPI.sendSupportMessage(Strings.morePlayerNeedSupport.substring(0, Strings.morePlayerNeedSupport.length() - 1).replace("%number%", Data.supports.size() + ""));
                    }
                }
            }
        }, Data.queueNotificationInterval, Data.queueNotificationInterval, TimeUnit.SECONDS);
    }

    public static void reloadScheudler() {
        ProxyServer.getInstance().getScheduler().cancel(Main.instance);
        if (Data.enableQueueNotification) {
            registerScheduler();
        }
    }
}
