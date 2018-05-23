package de.dakir.bungeesupportchat.utils;

import java.util.UUID;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class HexxAPI {
	
	public static void sendSupportMessage(String message){
		for(ProxiedPlayer p : BungeeCord.getInstance().getPlayers()){
			if(p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")){
				p.sendMessage(new TextComponent(Strings.prefix + message));
				p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Strings.prefix + message));
			}
		}
	}
	
	public static boolean isSupporterOnline(){
		boolean online = false;
		for(ProxiedPlayer p : BungeeCord.getInstance().getPlayers()){
			if(p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")){
				online = true;
			}
		}
		return online;
	}
	
	public static ProxiedPlayer getSupportChatPartner(ProxiedPlayer p){
	    if(Data.inSupport.containsKey(p.getUniqueId())){
	    	return BungeeCord.getInstance().getPlayer(Data.inSupport.get(p.getUniqueId()));
	    }
	    for(UUID uuid : Data.inSupport.keySet()){
	    	if(Data.inSupport.get(uuid).equals(p.getUniqueId())){
	    		return BungeeCord.getInstance().getPlayer(uuid);
	    	}
	    }
	    return null;
	}
	
	public static boolean isInSupportChat(ProxiedPlayer p){
	    if((Data.inSupport.containsKey(p.getUniqueId()) || (Data.inSupport.containsValue(p.getUniqueId())))){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	public static void closeSupportChat(ProxiedPlayer p){
	    if(isInSupportChat(p)){
	    	if(Data.inSupport.containsKey(p.getUniqueId())){
	    		BungeeCord.getInstance().getPlayer(Data.inSupport.get(p.getUniqueId())).sendMessage(new TextComponent(Strings.prefix + Strings.closeSupportChat));
        		p.sendMessage(new TextComponent(Strings.prefix + Strings.closeSupportChat));
		        Data.inSupport.remove(p.getUniqueId());
	    	}else{
		        for(UUID uuid : Data.inSupport.keySet()){
		        	if(Data.inSupport.get(uuid).equals(p.getUniqueId())){
		        		Data.inSupport.remove(uuid);
		        		BungeeCord.getInstance().getPlayer(uuid).sendMessage(new TextComponent(Strings.prefix + Strings.closeSupportChat));
		        		p.sendMessage(new TextComponent(Strings.prefix + Strings.closeSupportChat));
		        	}
		        }
	    	}
	    }
	}
	
	public static void openSupportChat(ProxiedPlayer supporter, ProxiedPlayer spieler){
		if(HexxAPI.isInSupportChat(supporter)){
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.inSupportChat));
		}else if(HexxAPI.isInSupportChat(spieler)){
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.playerIsInSupportChat.replace("%player%", spieler.getName())));
		}else{
			if(Data.supports.contains(spieler.getUniqueId())){
				Data.supports.remove(spieler.getUniqueId());
			}
			Data.inSupport.put(supporter.getUniqueId(), spieler.getUniqueId());
			
			if(Data.enableMySQL) {
				if((!(MySQLData.isPlayerExists(supporter.getUniqueId().toString()))) && (supporter.hasPermission("supportchat.*") || supporter.hasPermission("supportchat.use") || supporter.hasPermission("supportchat.open"))){
					MySQLData.createPlayer(supporter.getUniqueId());
					MySQLData.addSupport(supporter.getUniqueId());
				}else{
					MySQLData.addSupport(supporter.getUniqueId());
					MySQLData.updateName(supporter.getUniqueId());
				}
			}
			
			spieler.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat));
			spieler.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_head.replace("%player%", supporter.getName())));
			spieler.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_user.replace("%player%", spieler.getName())));
			spieler.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_space));
			spieler.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_hellomessage.replace("%player%", supporter.getName())));
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat));
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_head.replace("%player%", supporter.getName())));
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_user.replace("%player%", spieler.getName())));
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_space));
			supporter.sendMessage(new TextComponent(Strings.prefix + Strings.openSupportChat_hellomessage.replace("%player%", supporter.getName())));
		}
	}
	
	public static String getStringWithColorCodes(String s) {
    	String[] words = s.split(" ");
    	String colorcode = "f";
    	if(Strings.prefix.contains("&") && (Strings.prefix.length() > 1)) {
    		int index = Strings.prefix.lastIndexOf("&") + 1;
    		colorcode = Strings.prefix.charAt(index) + "";
    	}
    	
    	for(int i = 0; i < words.length; i++) {
    		if(words[i].contains("&") && (words[i].indexOf("&") != 0)) {
    			words[i] = "&" + colorcode + words[i];
    		} else if(words[i].contains("&") && (words[i].length() > 1)){
    			colorcode = words[i].charAt(words[i].indexOf("&")+1) + "";
    		} else {
    			words[i] = "&" + colorcode + words[i];
    		}
    	}
    	
    	String msg = "";
    	for(int i = 0; i < words.length; i++) {
    		msg = msg + " " + words[i];
    	}
    	msg = msg.substring(1);
    	
    	return msg;
	}
}
