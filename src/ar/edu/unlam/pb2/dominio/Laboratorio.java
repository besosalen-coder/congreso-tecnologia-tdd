package ar.edu.unlam.pb2.dominio;

public class Laboratorio extends Taller{
private static final Double CARGO_FIJO_EQUIPAMIENTO_DOUBLE = 2500.0;
 
	public Laboratorio(String nombre, Integer cupo, Double arancelBase) {
		super(nombre, cupo, arancelBase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double getArancel() {
		// TODO Auto-generated method stub
		return super.getArancel() + CARGO_FIJO_EQUIPAMIENTO_DOUBLE;
	}

}
