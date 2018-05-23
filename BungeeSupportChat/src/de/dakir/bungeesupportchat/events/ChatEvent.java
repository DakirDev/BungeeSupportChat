package de.dakir.bungeesupportchat.events;

import de.dakir.bungeesupportchat.utils.HexxAPI;
import de.dakir.bungeesupportchat.utils.Strings;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ChatEvent implements Listener{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onChat(net.md_5.bungee.api.event.ChatEvent e){
		if(e.isCancelled()){
			return;
		}

        if(!(e.getSender() instanceof ProxiedPlayer)){
        	return;
        }

        if(e.isCommand()){
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        if(HexxAPI.isInSupportChat(p)){
        	String namecolor = Strings.userColor;
        	if(p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")){
        		namecolor = Strings.supporterColor;
        	}
        	
        	String[] args = e.getMessage().split(" ");
        	String msg = "";
        	for(int i = 0; i < args.length; i++){
        		msg = msg + " " + Strings.chatColor + args[i];
        	}
        	
        	p.sendMessage(new TextComponent(Strings.prefix + namecolor + p.getName() + " §8\u00BB§b" + msg));
        	HexxAPI.getSupportChatPartner(p).sendMessage(new TextComponent(Strings.prefix + namecolor + p.getName() + " §8\u00BB§b" + msg));
        	e.setCancelled(true);
        }
	}

}
