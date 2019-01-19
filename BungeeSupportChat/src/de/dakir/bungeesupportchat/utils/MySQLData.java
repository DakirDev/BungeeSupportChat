package de.dakir.bungeesupportchat.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.BungeeCord;

public class MySQLData {
	
	public static boolean isPlayerExists(String uuid){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT UUID FROM Data WHERE UUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isNameExists(String name){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Name FROM Data WHERE Name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createPlayer(UUID uuid){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Data (UUID,Name,Supports) VALUES (?,?,?)");
			ps.setString(1, uuid.toString());
			ps.setString(2, BungeeCord.getInstance().getPlayer(uuid).getName());
			ps.setInt(3, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePlayer(String name){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM Data WHERE Name = ?");
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////
	//////////////////////   UPDATE   ///////////////////////
	/////////////////////////////////////////////////////////

	public static void updateName(UUID uuid){
		if(isPlayerExists(uuid.toString())){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Data SET Name = ? WHERE UUID = ?");
				ps.setString(1, BungeeCord.getInstance().getPlayer(uuid).getName());
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void resetSupports(){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Data SET Supports = 0 WHERE 1");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////
	////////////////////////   ADD   ////////////////////////
	/////////////////////////////////////////////////////////
	
	public static void addSupport(UUID uuid){
		if(isPlayerExists(uuid.toString())){
			int supports = getSupportsByUUID(uuid);
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Data SET Supports = ? WHERE UUID = ?");
				ps.setInt(1, supports + 1);
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addSupportsByName(String name, int number){
		if(isNameExists(name)){
			int supports = getSupportsByName(name);
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Data SET Supports = ? WHERE Name = ?");
				ps.setInt(1, supports + number);
				ps.setString(2, name);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/////////////////////////////////////////////////////////
	//////////////////////   REMOVE   ///////////////////////
	/////////////////////////////////////////////////////////
	
	public static void removeSupportsByName(String name, int number){
		if(isNameExists(name)){
			int supports = getSupportsByName(name);
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Data SET Supports = ? WHERE Name = ?");
				ps.setInt(1, supports - number);
				ps.setString(2, name);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/////////////////////////////////////////////////////////
	////////////////////////   SET   ////////////////////////
	/////////////////////////////////////////////////////////
	
	public static void setSupportsByName(String name, int number){
		if(isNameExists(name)){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Data SET Supports = ? WHERE Name = ?");
				ps.setInt(1, number);
				ps.setString(2, name);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/////////////////////////////////////////////////////////
	////////////////////////   GET   ////////////////////////
	/////////////////////////////////////////////////////////
	
	public static int getSupportsByUUID(UUID uuid){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Supports FROM Data WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt("Supports");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getSupportsByName(String name){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Supports FROM Data WHERE Name = ?");
			ps.setString(1, name.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt("Supports");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static HashMap<Integer, String> getList(){
		HashMap<Integer, String> top = new HashMap<Integer, String>();
		ResultSet rs = MySQL.query("SELECT Name FROM Data ORDER BY Supports DESC");
		
		int in = 0;
		try {
			while(rs.next()){
				in++;
				top.put(in, rs.getString("Name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return top;
	}
}
