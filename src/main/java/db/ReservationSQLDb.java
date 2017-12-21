package db;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Afspraak;

public class ReservationSQLDb implements ReservationDb {
	private PreparedStatement statement;
	private Statement statement1;
	private Connection connection;
	private Properties properties;
	private String url;

	public ReservationSQLDb() {
		try {
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public List<Afspraak> getReservations() {
		List<Afspraak> afspraken = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, properties);
			statement1 = connection.createStatement();

			ResultSet result = statement1.executeQuery("Select * from afspraak");
			while (result.next()) {
				int afspraakid = result.getInt("afspraakid");
				String lokaalid = result.getString("lokaalid");
				Date startdate = result.getDate("startdate");
				Date enddate = result.getDate("enddate");
				String subject = result.getString("subject");

				Afspraak afspraak = new Afspraak(afspraakid, lokaalid, startdate, enddate, subject);
				afspraken.add(afspraak);

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
		return afspraken;
	}

	@Override
	public void addReservation(Afspraak afspraak) {
		String sql = "insert into froggygroep6.afspraak (afspraakid, lokaalid, startdate, enddate)" + " values(?,?,?,?)";

		try {
			connection = DriverManager.getConnection(url, properties);
			statement = connection.prepareStatement(sql);

			statement.setInt(1, afspraak.getAfspraakid());
			statement.setString(2, afspraak.getLokaalid());
			Date startdate = new java.sql.Date(afspraak.getStartDate2_0().getTime());
			statement.setDate(3, startdate);
			Date enddate = new java.sql.Date(afspraak.getEndDate2_0().getTime());
			statement.setDate(4, enddate);

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

	@Override
	public Afspraak getreservation(int id) {
		String sql = "select * from froggygroep6.afspraak where afspraakid = " + "?";

		try {
			connection = DriverManager.getConnection(url, properties);
			statement = connection.prepareStatement(sql);

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int afspraakid = result.getInt("afspraakid");
				String lokaalid = result.getString("lokaalid");
				Date startdate = result.getDate("startdate");
				Date enddate = result.getDate("enddate");
				String subject = result.getString("subject");

				Afspraak afspraak = new Afspraak(afspraakid, lokaalid, startdate, enddate, subject);
				return afspraak;

			}
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
		return null;
	}

}
