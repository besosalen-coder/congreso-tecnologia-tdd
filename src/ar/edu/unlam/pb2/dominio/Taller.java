package ar.edu.unlam.pb2.dominio;

public class Taller extends Actividad {
	private Double arancelBase;
	
	public Taller(String nombre, Integer cupo,Double arancelBase) {
		super(nombre, cupo);
		if(arancelBase == null || arancelBase <0) {
			throw new IllegalArgumentException("El arancel base debe ser mayor a cero");
		}
		this.arancelBase=arancelBase;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double getArancel() {
		// TODO Auto-generated method stub
		return arancelBase;
	}

}
