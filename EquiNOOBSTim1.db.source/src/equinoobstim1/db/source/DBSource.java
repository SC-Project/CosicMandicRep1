package equinoobstim1.db.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Timovi;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.Utakmice;
import equinoobstim1.source.ISource;

public class DBSource implements ISource {

	@Override
	public Prvenstvo load(String dbName) throws SQLException {
		Timovi timovi = new Timovi();
		Utakmice utakmice = new Utakmice();
		Prvenstvo prvenstvo = new Prvenstvo();

		SimpleDateFormat df = new SimpleDateFormat("");

		String timoviQuery, utakmiceQuery;

		timoviQuery = "SELECT * FROM TIM";
		utakmiceQuery = "SELECT * FROM UTAKMICE";

		Statement stmt = DBConnection.getConnection(dbName).createStatement();
		ResultSet rset = stmt.executeQuery(timoviQuery);
		while (rset.next()) {
			Tim tim = new Tim();
			tim.setBrojUcesca(Integer.parseInt(rset.getString("BROJ_UCESCA")));
			tim.setNajveciUspeh(rset.getString("NAJVECI_USPEH"));
			tim.setNazivTima(rset.getString("NAZIV_TIMA"));
			tim.setPrvoPrvenstvo(rset.getDate("PRVI_PUT_IGRAO_NA_SP"));

			timovi.addTIm(tim);
		}
		rset.close();
		stmt.close();

		stmt = DBConnection.getConnection(dbName).createStatement();
		rset = stmt.executeQuery(utakmiceQuery);
		while (rset.next()) {
			Utakmica utakmica = new Utakmica();
			utakmica.setBrojCrvenihKartona(rset.getInt("BROJ_CRVENIH_KARTONA"));
			utakmica.setBrojZutihKartona(rset.getInt("BROJ_ZUTIH_KARTONA"));
			utakmica.setDatum(rset.getDate("DATUM_VREME"));
			utakmica.setGoloviDomaci(rset.getInt("GOL_DOMACINI"));
			utakmica.setGoloviGosti(rset.getInt("GOL_GOSTI"));
			utakmica.setNazivDomaci(rset.getString("NAZIV_TIMA_DOMACI"));
			utakmica.setNazivGosti(rset.getString("NAZIV_TIMA_GOSTI"));
			utakmica.setStadion(rset.getString("STADION"));

			utakmice.addUtakmica(utakmica);
		}

		rset.close();
		stmt.close();

		prvenstvo.setTimovi(timovi);
		prvenstvo.setUtakmice(utakmice);

		return prvenstvo;
	}

	@Override
	public void save(Prvenstvo prvenstvo, String dbName) throws SQLException {
		Statement stmt = null;

		DBConnection.setConnection(null);
		
		stmt = DBConnection.getConnection("").createStatement();

		stmt.executeUpdate("CREATE DATABASE " + dbName);
		stmt.close();
		DBConnection.setConnection(null);
		stmt = DBConnection.getConnection(dbName).createStatement();

		try {
			Bundle bundle = Platform.getBundle("EquiNOOBSTim1.db.source");
			URL fileURL = bundle.getEntry("sql/crebas-prvenstvo.sql");
			File file = null;
			try {
				file = new File(FileLocator.resolve(fileURL).toURI());
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String putanjaSlike = file.getAbsolutePath();
			BufferedReader in = null;
			in = new BufferedReader(new FileReader(putanjaSlike));
			String str = "";
			StringBuffer sb = new StringBuffer();
			while ((str = in.readLine()) != null) {
				sb.append(str + "\n ");
			}
			in.close();
			System.out.println(sb.toString());
			stmt.executeUpdate(sb.toString());
			DBConnection.getConnection(dbName).commit();
			stmt.close();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement stm1 = null;
		
		for (Tim tim:prvenstvo.getTimovi().getTimovi()) {
			stm1 = DBConnection
					.getConnection(dbName)
					.prepareStatement(
							"INSERT INTO TIM (NAZIV_TIMA, PRVI_PUT_IGRAO_NA_SP, NAJVECI_USPEH, BROJ_UCESCA)"
									+ " VALUES (?, ?, ?, ?)");
			
			stm1.setString(1, tim.getNazivTima());
			stm1.setDate(2, tim.getPrvoPrvenstvo());
			stm1.setString(3,tim.getNajveciUspeh());
			stm1.setInt(4, tim.getBrojUcesca());
			
			stm1.executeUpdate();
		}
		
		stm1.close(); // Unos slogova u bazu
		DBConnection.getConnection(dbName).commit();
		
		for (Utakmica utakmica : prvenstvo.getUtakmice().getUtakmice()) {
			stm1 = DBConnection
					.getConnection(dbName)
					.prepareStatement(
							"INSERT INTO UTAKMICE (NAZIV_TIMA_GOSTI, NAZIV_TIMA_DOMACI, GOL_GOSTI, GOL_DOMACINI, BROJ_ZUTIH_KARTONA"
									+ ", BROJ_CRVENIH_KARTONA, STADION, DATUM_VREME)"
									+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			stm1.setString(1, utakmica.getNazivGosti());
			stm1.setString(2, utakmica.getNazivDomaci());
			stm1.setInt(3, utakmica.getGoloviGosti());
			stm1.setInt(4, utakmica.getGoloviDomaci());
			stm1.setInt(5, utakmica.getBrojZutihKartona());
			stm1.setInt(6, utakmica.getBrojCrvenihKartona());
			stm1.setString(7, utakmica.getStadion());
			stm1.setDate(8, utakmica.getDatum());

			stm1.executeUpdate();
		}
		stm1.close(); // Unos slogova u bazu
		DBConnection.getConnection(dbName).commit();
	}

	@Override
	public Prvenstvo load(Display display) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Prvenstvo prvenstvo, Display display) {
		// TODO Auto-generated method stub

	}

}
