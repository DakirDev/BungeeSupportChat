package de.dakir.bungeesupportchat.events;

import de.dakir.bungeesupportchat.utils.Data;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabCompleteEvent implements Listener {

    @EventHandler
    public void onTabComplete(net.md_5.bungee.api.event.TabCompleteEvent e) {
        if (Data.enableTabComplete) {
            String partialPlayerName = e.getCursor().toLowerCase();

            int lastSpaceIndex = partialPlayerName.lastIndexOf(' ');
            if (lastSpaceIndex >= 0) {
                partialPlayerName = partialPlayerName.substring(lastSpaceIndex + 1);
            }
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (p.getName().toLowerCase().startsWith(partialPlayerName)) {
                    e.getSuggestions().add(p.getName());
                }
            }
        } else {
            return;
        }
    }
}
