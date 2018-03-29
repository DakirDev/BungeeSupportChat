package de.dakir.bungeesupportchat.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import de.dakir.bungeesupportchat.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public class MySQL {
	
	private static ScheduledTask MySQLSchedulerID;
	
	public static String host;
	public static String port;
	public static String database;
	public static String username;
	public static String password;
	public static Connection con;
	
	public static void connect(){
		if(!isConnected()){
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", username, password);
				System.out.println("[BungeeSupportChat-MySQL] Connection established!");
				
				onReconnectScheduler();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void disconnect(){
		if(isConnected()){
			try {
				con.close();
				System.out.println("[BungeeSupportChat-MySQL] Connection closed!");
				
				BungeeCord.getInstance().getScheduler().cancel(MySQLSchedulerID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void onReconnectScheduler(){
		MySQLSchedulerID = BungeeCord.getInstance().getScheduler().schedule(Main.instance, new Runnable() {
            public void run(){
            	onReconnect();
            }
        }, 10, 10, TimeUnit.MINUTES);
    }
 
    private static void onReconnect(){
        if(con != null){
            try {
            	con.close();
            } catch(SQLException e) {
                System.err.println("[BungeeSupportChat-MySQL] Connection could not be disconnected!");
                e.printStackTrace();
            }
        }
   
        BungeeCord.getInstance().getScheduler().schedule(Main.instance, new Runnable() {
            public void run(){
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", username, password);
                } catch(SQLException e) {
                    System.err.println("[BungeeSupportChat-MySQL] Connection failed!");
                    e.printStackTrace();
                }
            }
        }, 50, TimeUnit.MILLISECONDS);
    }
	
	public static boolean isConnected(){
		return (con == null ? false : true);
	}
	
	public static Connection getConnection(){
		return con;
	}
	
	public static ResultSet query(String qry){
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			return ps.executeQuery();
		} catch(SQLException e) {
			
		}
		return null;
	}
	
	//////////////////////////////////////////
	
	public static void createTable(){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Data (UUID VARCHAR(100), Name VARCHAR(100), Supports INTEGER)");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

