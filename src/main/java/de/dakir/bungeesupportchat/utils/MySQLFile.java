package de.dakir.bungeesupportchat.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import de.dakir.bungeesupportchat.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MySQLFile {

    public static void load() {
        File file = new File(Main.instance.getDataFolder(), "mysql.yml");

        if (!file.exists()) {
            try (InputStream in = Main.instance.getResourceAsStream("mysql.yml")) {
                Files.copy(in, file.toPath());
                System.out.println(Strings.cprefix + "The file 'mysql.yml' has been created.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.instance.getDataFolder(), "mysql.yml"));

            MySQL.host = config.getString("host");
            MySQL.port = config.getString("port");
            MySQL.database = config.getString("database");
            MySQL.username = config.getString("username");
            MySQL.password = config.getString("password");
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }
}
