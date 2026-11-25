package ar.edu.unlam.pb2.dominio;

public class Estudiante implements Acreditacion{

	@Override
	public Double calcularAbono(Double arancel) {
		// TODO Auto-generated method stub
		return arancel * 0.5;
	}
 
}
