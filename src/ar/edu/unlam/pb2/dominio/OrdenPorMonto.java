package ar.edu.unlam.pb2.dominio;

import java.util.Comparator;

public class OrdenPorMonto implements Comparator<Asistente> {
	private Double arancel;
	
	public  OrdenPorMonto(Double arancel) {
		this.arancel=arancel;
	}
	@Override
	public int compare(Asistente a1, Asistente b2) {
		Double monto1= a1.acreditarAbono(arancel);
		Double monto2= b2.acreditarAbono(arancel);
		
		int porMonto= Double.compare(monto2, monto1);
		if(porMonto !=0) {
			return porMonto;
		}
		return new OrdenAlfabetico().compare(a1, b2);
	}

}
