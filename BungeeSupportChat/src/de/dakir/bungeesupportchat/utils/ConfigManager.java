package de.dakir.bungeesupportchat.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import de.dakir.bungeesupportchat.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {
	
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
                System.out.println(Strings.cprefix + "Die Datei 'config.yml' wurde erstellt.");
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
            Data.enableQueueNotification = config.getBoolean("enableQueueNotification");
            Data.queueNotificationInterval = config.getInt("queueNotificationInterval");
            
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
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}
