package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Asistente {
	private String dni;
	private String apellido;
	private String nombre;
	private Acreditacion acreditacion;
	
	public Asistente(String dni, String apellido, String nombre, Acreditacion acreditacion) {
		if(dni == null || dni.isBlank() ) {
			throw new IllegalArgumentException("El dni no puede ser un campo vacio");
		}
		this.dni= dni;
		this.apellido=apellido;
		this.nombre=nombre;
		this.acreditacion=acreditacion;
	}
	
	

	public String getDni() {
		return dni;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setAcreditacion(Acreditacion acreditacion) {
		this.acreditacion = acreditacion;
	}
	public Double acreditarAbono(Double arancel) {
		return acreditacion.calcularAbono(arancel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asistente other = (Asistente) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
}
