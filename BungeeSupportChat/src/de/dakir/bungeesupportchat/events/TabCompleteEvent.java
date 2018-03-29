package de.dakir.bungeesupportchat.events;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabCompleteEvent implements Listener{
	
	@EventHandler
	public void onTabComplete(net.md_5.bungee.api.event.TabCompleteEvent e){
		String partialPlayerName = e.getCursor().toLowerCase();
	    
	    int lastSpaceIndex = partialPlayerName.lastIndexOf(' ');
	    if(lastSpaceIndex >= 0){
	    	partialPlayerName = partialPlayerName.substring(lastSpaceIndex + 1);
	    }
	    for(ProxiedPlayer p : BungeeCord.getInstance().getPlayers()){
	    	if(p.getName().toLowerCase().startsWith(partialPlayerName)){
	    		e.getSuggestions().add(p.getName());
	    	}
	    }
	}
}
