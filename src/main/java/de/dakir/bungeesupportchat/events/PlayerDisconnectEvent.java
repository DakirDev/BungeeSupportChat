package de.dakir.bungeesupportchat.events;

import de.dakir.bungeesupportchat.utils.Data;
import de.dakir.bungeesupportchat.utils.HexxAPI;
import de.dakir.bungeesupportchat.utils.Strings;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectEvent implements Listener {

    @EventHandler
    public void onQuit(net.md_5.bungee.api.event.PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if (Data.supports.contains(p.getUniqueId())) {
            Data.supports.remove(p.getUniqueId());
            HexxAPI.sendSupportMessage(Strings.needNoSupport.replace("%player%", p.getName()));
        } else if (HexxAPI.isInSupportChat(p)) {
            HexxAPI.closeSupportChat(p);
        }
    }

}
