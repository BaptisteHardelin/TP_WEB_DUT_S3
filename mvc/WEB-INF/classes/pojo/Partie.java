package pojo;

import java.util.Date;

public class Partie {

	private int pno;
	private int jno1;
	private int jno2;
	private Date dateP;
	private String statut;
	private String temp;
	private String gagnant;

	public Partie(int pno, int jno1, int jno2, Date dateP, String statut, String temp, String gagnant) {
		this.pno = pno;
		this.jno1 = jno1;
		this.jno2 = jno2;
		this.dateP = dateP;
		this.statut = statut;
		this.temp = temp;
		this.gagnant = gagnant;
	}

	public int getPno() {
		return pno;
	}

	public int getJno1() {
		return jno1;
	}

	public int getJno2() {
		return jno2;
	}

	public Date getDate() {
		return dateP;
	}

	public String getStatut() {
		return statut;
	}

	public String getTemp() {
		return temp;
	}

	public String getGagnant() {
		return gagnant;
	}

}
