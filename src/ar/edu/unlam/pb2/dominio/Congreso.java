package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Congreso {
	private List<Actividad>actividades = new ArrayList<Actividad>();

	public void agregarActividad(Actividad actividad) {
	actividades.add(actividad);
		
	}

	public Double calcularRecaudacionTotal() {
		Double recaudacionTotal = 0.0;
		
		for (Actividad actividad : actividades) {
			recaudacionTotal += actividad.calcularRecaudacionTotal();
		}
		return recaudacionTotal;
	}
	public Integer getCantidadDeAsistentesDistintos() {
		Set<Asistente>distintos= new HashSet<>();
		
		for (Actividad actividad : actividades) {
			distintos.addAll(actividad.getInscriptos());
		}
		return distintos.size();
	}
}
