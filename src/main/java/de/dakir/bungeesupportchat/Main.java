package de.dakir.bungeesupportchat;

import de.dakir.bungeesupportchat.metrics.Metrics;
import de.dakir.bungeesupportchat.utils.ConfigManager;
import de.dakir.bungeesupportchat.utils.Data;
import de.dakir.bungeesupportchat.utils.MySQL;
import de.dakir.bungeesupportchat.utils.MySQLFile;
import de.dakir.bungeesupportchat.utils.Strings;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        Main.instance = this;

        ConfigManager.checkFiles();
        ConfigManager.load();

        PluginManager.load();

        MySQLFile.load();

        if (Data.enableMySQL) {
            MySQL.connect();
            MySQL.createTable();
        }

        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(Main.instance);

        System.out.println(Strings.cprefix + "Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        PluginManager.unload();
        MySQL.disconnect();

        System.out.println(Strings.cprefix + "Plugin has been disabled!");
    }

}
