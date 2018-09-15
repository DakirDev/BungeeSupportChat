package de.dakir.bungeesupportchat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;

import de.dakir.bungeesupportchat.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {
	
	public static String inputStr;
	public static String lines[];
	
	public static void checkFiles(){
		if(!Main.instance.getDataFolder().exists()){
			Main.instance.getDataFolder().mkdir();
		}

        File file1 = new File(Main.instance.getDataFolder(), "config.yml");
     
        if(!file1.exists()){
            try(InputStream in = Main.instance.getResourceAsStream("config.yml")) {
                Files.copy(in, file1.toPath());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
        File file2 = new File(Main.instance.getDataFolder(), "mysql.yml");
        
        if(!file2.exists()){
            try(InputStream in = Main.instance.getResourceAsStream("mysql.yml")) {
                Files.copy(in, file2.toPath());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void load(){
		File file = new File(Main.instance.getDataFolder(), "config.yml");
	     
        if(!file.exists()){
            try(InputStream in = Main.instance.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
                System.out.println(Strings.cprefix + "The file 'config.yml' has been created.");
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        }
        
    	try {
			Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.instance.getDataFolder(), "config.yml"));
			
			Strings.prefix = config.getString("prefix");
            Data.enableMySQL = config.getBoolean("enableMySQL");
            Strings.supporterColor = ChatColor.translateAlternateColorCodes('&', config.getString("supporterColor"));
            Strings.userColor = ChatColor.translateAlternateColorCodes('&', config.getString("userColor"));
            Strings.chatColor = ChatColor.translateAlternateColorCodes('&', config.getString("chatColor"));
            Data.enableQueueNotification = config.getBoolean("enableQueueNotification");
            Data.queueNotificationInterval = config.getInt("queueNotificationInterval");
            Data.enableTabComplete = config.getBoolean("enableTabComplete");
            
            Strings.reload = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("reload")));
            Strings.noPermission = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("noPermission")));
            Strings.noNumber = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("noNumber")));
            Strings.commandNotExists = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("commandNotExists")));
            Strings.playerNotFound = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("playerNotFound")));
            Strings.playerIsSupporter = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("playerIsSupporter")));
            Strings.notInSupportChat = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("notInSupportChat")));
            Strings.inSupportChat = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("inSupportChat")));
            Strings.playerIsInSupportChat = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("playerIsInSupportChat")));
            Strings.noSupportNeeded = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("noSupportNeeded")));
            Strings.noSupporterOnline = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("noSupporterOnline")));
            Strings.noSupportRequest = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("noSupportRequest")));
            Strings.joinSupportQueue = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("joinSupportQueue")));
            Strings.leaveSupportQueue = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("leaveSupportQueue")));
            Strings.needSupport = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("needSupport")));
            Strings.needNoSupport = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("needNoSupport")));
            Strings.onePlayerNeedSupport = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("onePlayerNeedSupport")));
            Strings.morePlayerNeedSupport = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("morePlayerNeedSupport")));
            Strings.playerInQueue = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("playerInQueue")));
            Strings.closeSupportChat = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("closeSupportChat")));
            Strings.openSupportChat = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("openSupportChat")));
            Strings.openSupportChat_head = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("openSupportChat_head")));
            Strings.openSupportChat_user = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("openSupportChat_user")));
            Strings.openSupportChat_space = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("openSupportChat_space")));
            Strings.openSupportChat_hellomessage = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("openSupportChat_hellomessage")));
            Strings.mysqlNotEnabled = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("mysqlNotEnabled")));
            Strings.dataPlayerStats = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerStats")));
            Strings.dataPlayerDeleted = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerDeleted")));
            Strings.dataPlayerSupportsAdded = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerSupportsAdded")));
            Strings.dataPlayerSupportsRemoved = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerSupportsRemoved")));
            Strings.dataPlayerSupportsSet = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerSupportsSet")));
            Strings.dataPlayerList_header = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerList_header")));
            Strings.dataPlayerList_entry = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerList_entry")));
            Strings.dataPlayerList_footer = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("dataPlayerList_footer")));
            
            Strings.header = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("header")));
            Strings.sc_help = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_help")));
            Strings.sc = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc")));
            Strings.sc_list = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_list")));
            Strings.sc_close = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_close")));
            Strings.sc_open = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_open")));
            Strings.sc_open_player = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_open_player")));
            Strings.sc_data_list = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_data_list")));
            Strings.sc_data_stats = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_data_stats")));
            Strings.sc_data_delete = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_data_delete")));
            Strings.sc_data_add = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_data_add")));
            Strings.sc_data_remove = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_data_remove")));
            Strings.sc_data_set = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_data_set")));
            Strings.sc_reload = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("sc_reload")));
            Strings.footer = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("footer")));
            
            Strings.prefix = ChatColor.translateAlternateColorCodes('&', HexxAPI.getStringWithColorCodes(config.getString("prefix")));
            
            //Updatechecker
			try {
				String version = Main.instance.getDescription().getVersion();
				if(!(config.getString("configversion").equalsIgnoreCase(version))){
					System.out.println(Strings.cprefix + "Your config file is outdated and will be updated automatically to version " + version);
					loadConfigBackup();
					System.out.println(Strings.cprefix + "Config backup has been taken");
					update();
					System.out.println(Strings.cprefix + "Config has been updated");
				}
			} catch (NullPointerException e) {
				System.out.println(Strings.cprefix + "The version number in the plugin.yml file is incorrect!");
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void loadConfigBackup(){
		try {
			Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.instance.getDataFolder(), "config.yml"));
			ConfigBackup.configversion = Main.instance.getDescription().getVersion();
			
			if(config.contains("prefix")) ConfigBackup.prefix = config.getString("prefix");
			if(config.contains("enableMySQL")) ConfigBackup.enableMySQL = config.getBoolean("enableMySQL");
			if(config.contains("supporterColor")) ConfigBackup.supporterColor = config.getString("supporterColor");
			if(config.contains("userColor")) ConfigBackup.userColor = config.getString("userColor");
			if(config.contains("chatColor")) ConfigBackup.chatColor = config.getString("chatColor");
			if(config.contains("enableQueueNotification")) ConfigBackup.enableQueueNotification = config.getBoolean("enableQueueNotification");
			if(config.contains("queueNotificationInterval")) ConfigBackup.queueNotificationInterval = config.getInt("queueNotificationInterval");
			if(config.contains("enableTabComplete")) ConfigBackup.enableTabComplete = config.getBoolean("enableTabComplete");
            
			if(config.contains("reload")) ConfigBackup.reload = config.getString("reload");
			if(config.contains("noPermission")) ConfigBackup.noPermission = config.getString("noPermission");
			if(config.contains("noNumber")) ConfigBackup.noNumber = config.getString("noNumber");
			if(config.contains("commandNotExists")) ConfigBackup.commandNotExists = config.getString("commandNotExists");
			if(config.contains("playerNotFound")) ConfigBackup.playerNotFound = config.getString("playerNotFound");
			if(config.contains("playerIsSupporter")) ConfigBackup.playerIsSupporter = config.getString("playerIsSupporter");
			if(config.contains("notInSupportChat")) ConfigBackup.notInSupportChat = config.getString("notInSupportChat");
			if(config.contains("inSupportChat")) ConfigBackup.inSupportChat = config.getString("inSupportChat");
			if(config.contains("playerIsInSupportChat")) ConfigBackup.playerIsInSupportChat = config.getString("playerIsInSupportChat");
			if(config.contains("noSupportNeeded")) ConfigBackup.noSupportNeeded = config.getString("noSupportNeeded");
			if(config.contains("noSupporterOnline")) ConfigBackup.noSupporterOnline = config.getString("noSupporterOnline");
			if(config.contains("noSupportRequest")) ConfigBackup.noSupportRequest = config.getString("noSupportRequest");
			if(config.contains("joinSupportQueue")) ConfigBackup.joinSupportQueue = config.getString("joinSupportQueue");
			if(config.contains("leaveSupportQueue")) ConfigBackup.leaveSupportQueue = config.getString("leaveSupportQueue");
			if(config.contains("needSupport")) ConfigBackup.needSupport = config.getString("needSupport");
			if(config.contains("needNoSupport")) ConfigBackup.needNoSupport = config.getString("needNoSupport");
			if(config.contains("onePlayerNeedSupport")) ConfigBackup.onePlayerNeedSupport = config.getString("onePlayerNeedSupport");
			if(config.contains("morePlayerNeedSupport")) ConfigBackup.morePlayerNeedSupport = config.getString("morePlayerNeedSupport");
			if(config.contains("playerInQueue")) ConfigBackup.playerInQueue = config.getString("playerInQueue");
			if(config.contains("closeSupportChat")) ConfigBackup.closeSupportChat = config.getString("closeSupportChat");
			if(config.contains("openSupportChat")) ConfigBackup.openSupportChat = config.getString("openSupportChat");
			if(config.contains("openSupportChat_head")) ConfigBackup.openSupportChat_head = config.getString("openSupportChat_head");
			if(config.contains("openSupportChat_user")) ConfigBackup.openSupportChat_user = config.getString("openSupportChat_user");
			if(config.contains("openSupportChat_space")) ConfigBackup.openSupportChat_space = config.getString("openSupportChat_space");
            if(config.contains("openSupportChat_hellomessage")) ConfigBackup.openSupportChat_hellomessage = config.getString("openSupportChat_hellomessage");
            if(config.contains("mysqlNotEnabled")) ConfigBackup.mysqlNotEnabled = config.getString("mysqlNotEnabled");
            if(config.contains("dataPlayerStats")) ConfigBackup.dataPlayerStats = config.getString("dataPlayerStats");
            if(config.contains("dataPlayerDeleted")) ConfigBackup.dataPlayerDeleted = config.getString("dataPlayerDeleted");
            if(config.contains("dataPlayerSupportsAdded")) ConfigBackup.dataPlayerSupportsAdded = config.getString("dataPlayerSupportsAdded");
            if(config.contains("dataPlayerSupportsRemoved")) ConfigBackup.dataPlayerSupportsRemoved = config.getString("dataPlayerSupportsRemoved");
            if(config.contains("dataPlayerSupportsSet")) ConfigBackup.dataPlayerSupportsSet = config.getString("dataPlayerSupportsSet");
            if(config.contains("dataPlayerList_header")) ConfigBackup.dataPlayerList_header = config.getString("dataPlayerList_header");
            if(config.contains("dataPlayerList_entry")) ConfigBackup.dataPlayerList_entry = config.getString("dataPlayerList_entry");
            if(config.contains("dataPlayerList_footer")) ConfigBackup.dataPlayerList_footer = config.getString("dataPlayerList_footer");
            
            if(config.contains("header")) ConfigBackup.header = config.getString("header");
            if(config.contains("sc_help")) ConfigBackup.sc_help = config.getString("sc_help");
            if(config.contains("sc")) ConfigBackup.sc = config.getString("sc");
            if(config.contains("sc_list")) ConfigBackup.sc_list = config.getString("sc_list");
            if(config.contains("sc_close")) ConfigBackup.sc_close = config.getString("sc_close");
            if(config.contains("sc_open")) ConfigBackup.sc_open = config.getString("sc_open");
            if(config.contains("sc_open_player")) ConfigBackup.sc_open_player = config.getString("sc_open_player");
            if(config.contains("sc_data_list")) ConfigBackup.sc_data_list = config.getString("sc_data_list");
            if(config.contains("sc_data_stats")) ConfigBackup.sc_data_stats = config.getString("sc_data_stats");
            if(config.contains("sc_data_delete")) ConfigBackup.sc_data_delete = config.getString("sc_data_delete");
            if(config.contains("sc_data_add")) ConfigBackup.sc_data_add = config.getString("sc_data_add");
            if(config.contains("sc_data_remove")) ConfigBackup.sc_data_remove = config.getString("sc_data_remove");
            if(config.contains("sc_data_set")) ConfigBackup.sc_data_set = config.getString("sc_data_set");
            if(config.contains("sc_reload")) ConfigBackup.sc_reload = config.getString("sc_reload");
            if(config.contains("footer")) ConfigBackup.footer = config.getString("footer");
		} catch (IOException e2) {
			e2.printStackTrace();
		}	
	}
	
	public static void update(){
		File file = new File(Main.instance.getDataFolder(), "config.yml");
		file.delete();
	     
        try(InputStream in = Main.instance.getResourceAsStream("config.yml")) {
            Files.copy(in, file.toPath());
        } catch(IOException e1) {
            e1.printStackTrace();
        }
        
        loadInputBuffer();
        
        setVersion("configversion", ConfigBackup.configversion);
		
        setString("prefix", ConfigBackup.prefix);
        setBoolean("enableMySQL", ConfigBackup.enableMySQL);
		setString("supporterColor", ConfigBackup.supporterColor);
		setString("userColor", ConfigBackup.userColor);
		setString("chatColor", ConfigBackup.chatColor);
		setBoolean("enableQueueNotification", ConfigBackup.enableQueueNotification);
		setInt("queueNotificationInterval", ConfigBackup.queueNotificationInterval);
		setBoolean("enableTabComplete", ConfigBackup.enableTabComplete);
		
		setString("reload", ConfigBackup.reload);
		setString("noPermission", ConfigBackup.noPermission);
		setString("noNumber", ConfigBackup.noNumber);
		setString("commandNotExists", ConfigBackup.commandNotExists);
		setString("playerNotFound", ConfigBackup.playerNotFound);
		setString("playerIsSupporter", ConfigBackup.playerIsSupporter);
		setString("notInSupportChat", ConfigBackup.notInSupportChat);
		setString("inSupportChat", ConfigBackup.inSupportChat);
		setString("playerIsInSupportChat", ConfigBackup.playerIsInSupportChat);
		setString("noSupportNeeded", ConfigBackup.noSupportNeeded);
		setString("noSupporterOnline", ConfigBackup.noSupporterOnline);
		setString("noSupportRequest", ConfigBackup.noSupportRequest);
		setString("joinSupportQueue", ConfigBackup.joinSupportQueue);
		setString("leaveSupportQueue", ConfigBackup.leaveSupportQueue);
		setString("needSupport", ConfigBackup.needSupport);
		setString("needNoSupport", ConfigBackup.needNoSupport);
		setString("onePlayerNeedSupport", ConfigBackup.onePlayerNeedSupport);
		setString("morePlayerNeedSupport", ConfigBackup.morePlayerNeedSupport);
		setString("playerInQueue", ConfigBackup.playerInQueue);
		setString("closeSupportChat", ConfigBackup.closeSupportChat);
		setString("openSupportChat", ConfigBackup.openSupportChat);
		setString("openSupportChat_head", ConfigBackup.openSupportChat_head);
		setString("openSupportChat_user", ConfigBackup.openSupportChat_user);
		setString("openSupportChat_space", ConfigBackup.openSupportChat_space);
		setString("openSupportChat_hellomessage", ConfigBackup.openSupportChat_hellomessage);
		setString("mysqlNotEnabled", ConfigBackup.mysqlNotEnabled);
		setString("dataPlayerStats", ConfigBackup.dataPlayerStats);
		setString("dataPlayerDeleted", ConfigBackup.dataPlayerDeleted);
		setString("dataPlayerSupportsAdded", ConfigBackup.dataPlayerSupportsAdded);
		setString("dataPlayerSupportsRemoved", ConfigBackup.dataPlayerSupportsRemoved);
		setString("dataPlayerSupportsSet", ConfigBackup.dataPlayerSupportsSet);
		setString("dataPlayerList_header", ConfigBackup.dataPlayerList_header);
		setString("dataPlayerList_entry", ConfigBackup.dataPlayerList_entry);
		setString("dataPlayerList_footer", ConfigBackup.dataPlayerList_footer);
        
		setString("header", ConfigBackup.header);
		setString("sc_help", ConfigBackup.sc_help);
		setString("sc", ConfigBackup.sc);
		setString("sc_list", ConfigBackup.sc_list);
		setString("sc_close", ConfigBackup.sc_close);
		setString("sc_open", ConfigBackup.sc_open);
		setString("sc_open_player", ConfigBackup.sc_open_player);
		setString("sc_data_list", ConfigBackup.sc_data_list);
		setString("sc_data_stats", ConfigBackup.sc_data_stats);
		setString("sc_data_delete", ConfigBackup.sc_data_delete);
		setString("sc_data_add", ConfigBackup.sc_data_add);
		setString("sc_data_remove", ConfigBackup.sc_data_remove);
		setString("sc_data_set", ConfigBackup.sc_data_set);
		setString("sc_reload", ConfigBackup.sc_reload);
		setString("footer", ConfigBackup.footer);
		
		writeBuffer();
		
		load();
	}
	
	public static void loadInputBuffer() {
		try {
			BufferedReader file = new BufferedReader(new FileReader(new File(Main.instance.getDataFolder(), "config.yml")));
	        String line;
	        StringBuffer inputBuffer = new StringBuffer();

	        while ((line = file.readLine()) != null) {
	            inputBuffer.append(line);
	            inputBuffer.append('\n');
	        }
	        inputStr = inputBuffer.toString();
	        
	        lines = inputStr.split("\n");
	        
	        file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	public static void writeBuffer() {
		try {
			StringBuffer newBuffer = new StringBuffer();
        	
        	for(int i = 0; i < lines.length; i++) {
        		newBuffer.append(lines[i]);
        		newBuffer.append('\n');
        	}
        	
        	inputStr = newBuffer.toString();
			
			FileOutputStream fileOut = new FileOutputStream(new File(Main.instance.getDataFolder(), "config.yml"));
			fileOut.write(inputStr.getBytes(Charset.defaultCharset()));
	        fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	public static void setVersion(String string, String value) {
		for(int i = 0; i < lines.length; i++) {
    		if(lines[i].startsWith(string + ":")) {
    			lines[i] = string + ": " + value;
    			break;
    		}
    	}
	}
	
	public static void setString(String string, String value) {
		for(int i = 0; i < lines.length; i++) {
    		if(lines[i].startsWith(string + ":")) {
    			lines[i] = string + ": " + "'" + value + "'";
    			break;
    		}
    	}
	}
	
	public static void setBoolean(String string, boolean value) {
		for(int i = 0; i < lines.length; i++) {
    		if(lines[i].startsWith(string + ":")) {
    			lines[i] = string + ": " + value;
    			break;
    		}
    	}
	}
	
	public static void setInt(String string, int value) {
		for(int i = 0; i < lines.length; i++) {
    		if(lines[i].startsWith(string + ":")) {
    			lines[i] = string + ": " + value;
    			break;
    		}
    	}
	}
	
	public static void setDouble(String string, double value) {
		for(int i = 0; i < lines.length; i++) {
    		if(lines[i].startsWith(string + ":")) {
    			lines[i] = string + ": " + value;
    			break;
    		}
    	}
	}
	
}
