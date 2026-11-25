package ar.edu.unlam.pb2.dominio;

import java.util.Comparator;

public class OrdenAlfabetico implements Comparator<Asistente> {

	@Override
	public int compare(Asistente a, Asistente b) {
		int porApellido= a.getApellido().compareToIgnoreCase(b.getApellido());
		if(porApellido !=0) {
			return porApellido;
		}
		// TODO Auto-generated method stub
		return a.getNombre().compareToIgnoreCase(b.getNombre());
	}

}
