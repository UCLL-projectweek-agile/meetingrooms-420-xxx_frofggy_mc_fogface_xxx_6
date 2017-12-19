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
		int stopcontact = result.getInt("password");
		int stoelen = result.getInt("password");
		boolean status = result.getBoolean("status");
		Lokaal lokaal = new Lokaal(lokaalid, naam, stopcontact, stoelen, status);
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

	/*
	public List<Lokaal> getAll() {
		List<Lokaal> personen = new ArrayList<Lokaal>();
		
		try {
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM person");
			while(result.next()){
				String userid = result.getString("userid");
				String email = result.getString("email");
				String password = result.getString("password");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String salt = result.getString("salt");
				Person person = new Person(userid, email,password, firstname, lastname, salt);
				personen.add(person);
			
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
		return personen;
		
	}

	public void add(Lokaal lokaal) {
		String sql = "INSERT INTO r0652815_natest.person (userid, email, password, firstname, lastname, salt)" + " VALUES(?,?,?,?,?,?)";
		try{
			connection = DriverManager.getConnection(url, properties);
			statement1 = connection.prepareStatement(sql);
			
			statement1.setString(1, person.getUserid());
			statement1.setString(2, person.getEmail());
			statement1.setString(3, person.getPassword());
			statement1.setString(4, person.getFirstName());
			statement1.setString(5, person.getLastName());
			statement1.setString(6, person.getSalt());
			
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
		
		

//overbodig maar kan handig zijn
	
	public void update(Person person) {
		if(person == null){
			throw new DbException("No person given");
		}
		try{
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
			statement.executeUpdate("Update person SET userid= '" + person.getUserid() + "', email='" + person.getEmail() + "', password=" + person.getPassword() + "', firstname=" + person.getFirstName() + "', lastname=" + person.getLastName() + " where userid = "+ person.getUserid());
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
	
	public void delete(String id) {
		try{
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM r0652815_natest.person WHERE userid = '" + id + "'");
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
	*/
}