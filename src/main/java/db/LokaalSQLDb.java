package db;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Lokaal;

public class LokaalSQLDb {
	private PreparedStatement statement1;
	private Statement statement;
	private Connection connection;
	private Properties properties;
	private String url;
	
	public LokaalSQLDb(Properties properties){
		try{
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		} catch (Exception e){
			throw new DbException(e.getMessage(), e);
		}
	}
	
	public Lokaal get(String id){
		
		String sql = "SELECT * FROM groep6.lokaal where lokaalid = " + "?";
		
		try{
		connection = DriverManager.getConnection(url, properties);
		statement1 = connection.prepareStatement(sql);
		
		statement1.setString(1, id);
		
		ResultSet result = statement1.executeQuery();
		if(result.next()){
		String lokaalid = result.getString("lokaalid");
		String naam = result.getString("naam");
		int stopcontact = result.getInt("stopcontact");
		int stoelen = result.getInt("stoelen");
		
		Lokaal lokaal = new Lokaal(lokaalid, naam, stopcontact, stoelen);
		return lokaal;
		}
		}
		catch(SQLException exc){
			throw new DbException(exc.getMessage(), exc);
		}
		finally{
			try {
				connection.close();
				statement1.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}	
		}
		return null;	
	}

	
	public List<Lokaal> getAll() {
		List<Lokaal> lokalen = new ArrayList<Lokaal>();
		
		try {
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM lokaal");
			while(result.next()){
				String lokaalid = result.getString("lokaalid");
				String naam = result.getString("naam");
				int stopcontact = result.getInt("stopcontact");
				int stoelen = result.getInt("stoelen");
				Lokaal lokaal = new Lokaal(lokaalid, naam, stopcontact, stoelen);
				lokalen.add(lokaal);
			
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
			
		}
		finally{
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}	
		}
		return lokalen;
		
	}

	public void add(Lokaal lokaal) {
		String sql = "INSERT INTO groep6.lokaal (lokaalid, naam, stopcontact, stoelen)" + " VALUES(?,?,?,?)";
		try{
			connection = DriverManager.getConnection(url, properties);
			statement1 = connection.prepareStatement(sql);
			
			statement1.setString(1, lokaal.getLokaalID());
			statement1.setString(2, lokaal.getNaam());
			statement1.setInt(3, lokaal.getStopcontact());
			statement1.setInt(4, lokaal.getStoelen());
				
			statement1.execute();
		} catch (SQLException exc){
			throw new DbException(exc.getMessage(), exc);
	}
		finally{
			try{
				connection.close();
				statement1.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
			
			}
		
		
	}
		
	
	public void delete(String id) {
		try{
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM groep6 WHERE lokaalid = '" + id + "'");
		}
		catch(SQLException e){
			throw new DbException(e.getMessage(), e);
		}
		finally{
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}	
		}
		
	}
	
}