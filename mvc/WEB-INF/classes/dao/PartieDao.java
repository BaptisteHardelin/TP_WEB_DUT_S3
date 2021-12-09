package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Partie;

public class PartieDao {

	private DataSource ds;
	private Connection con;

	public PartieDao(DataSource ds, Connection con) {
		this.ds = ds;
		try {
			this.con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Partie find(int id) {
		Partie partie = null;

		try {
			String query = "SELECT * FROM PARTIE where pno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				partie = new Partie(rs.getInt("PNO"), rs.getInt("JNO1"), rs.getInt("JNO2"), rs.getDate("DATEP"),
						rs.getString("STATUT"), rs.getString("TEMP"), rs.getString("GAGANNAT"));
			}

			con.close();

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de rechercher la partie " + id, e);
		}

		return partie;
	}

	public List<Partie> findAll() {
		List<Partie> res = new ArrayList<>();
		Partie partie = null;

		try {
			String query = "SELECT * FROM PARTIE";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				partie = new Partie(rs.getInt("PNO"), rs.getInt("JNO1"), rs.getInt("JNO2"), rs.getDate("DATEP"),
						rs.getString("STATUT"), rs.getString("TEMP"), rs.getString("GAGANNAT"));
				res.add(partie);
			}

			con.close();

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de rechercher la partie ", e);
		}

		return res;
	}

	public boolean create(Partie partie) throws SQLException {
		PreparedStatement ps = null;
		try {
			String query = "insert into partie values (" + partie.getPno() + "),(" + partie.getJno1() + "),("
					+ partie.getJno2() + "),(" + partie.getDate() + "),(" + partie.getStatut() + "),("
					+ partie.getTemp() + "),(" + partie.getGagnant() + ")";
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de rechercher la partie " + partie, e);
		}

		return ps.executeUpdate() == 1;
	}
	
	public boolean delete(int id) throws SQLException {
		Partie partie = null;
		PreparedStatement ps = null;

		try {
			String query = "Delete * FROM PARTIE where pno = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				partie = new Partie(rs.getInt("PNO"), rs.getInt("JNO1"), rs.getInt("JNO2"), rs.getDate("DATEP"),
						rs.getString("STATUT"), rs.getString("TEMP"), rs.getString("GAGANNAT"));
			}

			con.close();

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de rechercher la partie " + id, e);
		}
		
		return ps.executeQuery().next();
	}

}
