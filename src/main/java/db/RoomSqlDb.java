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

public class RoomSqlDb implements RoomDb{
	private PreparedStatement statement;
	private Statement statement1;
	private Connection connection;
	private Properties properties;
	private String url;
	
	public RoomSqlDb() {
		try {
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public List<Lokaal> getRooms() {
		List<Lokaal> lokalen = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, properties);
			statement1 = connection.createStatement();

			ResultSet result = statement1.executeQuery("Select * from Lokaal");
			while (result.next()) {
				String lokaalid = result.getString("lokaalid");
				String naam = result.getString("naam");
				String lokaalnummer = result.getString("lokaalnummer");
				int stopcontact = result.getInt("stopcontact");
				int stoelen = result.getInt("stoelen");

				Lokaal lokaal = new Lokaal(lokaalid, naam, lokaalnummer, stopcontact,stoelen);
				lokalen.add(lokaal);
			}
		} catch (SQLException exc) {
			throw new DbException(exc.getMessage(), exc);
		} finally {
			try {
				connection.close();
				statement1.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		return lokalen;
	}

	@Override
	public void addRoom(Lokaal lokaal) {
		String sql = "insert into froggygroep6.lokaal (lokaalid, naam, lokaalnummer, stopcontact, stoelen)" + " values(?,?,?,?,?)";

		try {
			connection = DriverManager.getConnection(url, properties);
			statement = connection.prepareStatement(sql);

			statement.setString(1, lokaal.getLokaalID());
			statement.setString(2, lokaal.getNaam());
			statement.setString(3, lokaal.getLokaalnummer());
			statement.setInt(4, lokaal.getStopcontact());
			statement.setInt(5, lokaal.getStoelen());

			statement.execute();
		} catch (SQLException exc) {
			throw new DbException(exc.getMessage(), exc);
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		
	}

}
