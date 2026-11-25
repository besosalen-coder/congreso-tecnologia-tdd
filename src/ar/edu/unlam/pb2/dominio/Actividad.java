package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Actividad {
	private String nombre;
	private Integer cupo;
	private Set<Asistente>inscriptos;
	
	public Actividad(String nombre, Integer cupo) {
		if( cupo == null || cupo <=0) {
			throw new IllegalArgumentException("El numero de cupo debe ser mayor a cero.");
		}
		this.nombre= nombre;
		this.cupo= cupo;
		this.inscriptos= new HashSet<>();
	}
	public abstract Double getArancel();
	
public void inscribir(Asistente asistente)  throws AsistenteYaInscriptoException,  CupoCompletoException{
	if (inscriptos.contains(asistente)) {
		throw new AsistenteYaInscriptoException("El asistente ya esta inscripto en la actividad");
	}
	if(inscriptos.size() >= cupo) {
		throw new CupoCompletoException("El cupo esta completo");
	}
	inscriptos.add(asistente);
	}
public Boolean estaInscripto(Asistente asistente) {
	return inscriptos.contains(asistente);
	}
	public Integer getCantidadDeInscriptos() {
		return inscriptos.size();
	}
	 public Double calcularAbono(Asistente asistente) {
		 return asistente.acreditarAbono(getArancel());
	 }
	 
	 public String getNombre() {
		 return nombre;
	 }
	 public Integer getCupo() {
		 return cupo;
	 }
	 public Double calcularRecaudacionTotal() {
		 Double montoTotal=0.0;
		 
		for (Asistente asistente : inscriptos) {
			montoTotal += asistente.acreditarAbono(getArancel());
		}
		return montoTotal;
	 }
		public Set<Asistente> getInscriptos(){
			return new HashSet<>(inscriptos);
		}

		public List<Asistente> getInscriptosOrdenados(Comparator<Asistente> criterio){
			List<Asistente>lista = new ArrayList<>(inscriptos);
			lista.sort(criterio);
			return lista;
		}
		
}
