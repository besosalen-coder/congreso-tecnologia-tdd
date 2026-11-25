package ar.edu.unlam.pb2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unlam.pb2.dominio.Acreditacion;
import ar.edu.unlam.pb2.dominio.Actividad;
import ar.edu.unlam.pb2.dominio.Asistente;
import ar.edu.unlam.pb2.dominio.AsistenteYaInscriptoException;
import ar.edu.unlam.pb2.dominio.Auspiciante;
import ar.edu.unlam.pb2.dominio.Charla;
import ar.edu.unlam.pb2.dominio.Congreso;
import ar.edu.unlam.pb2.dominio.CupoCompletoException;
import ar.edu.unlam.pb2.dominio.Estudiante;
import ar.edu.unlam.pb2.dominio.General;
import ar.edu.unlam.pb2.dominio.Laboratorio;
import ar.edu.unlam.pb2.dominio.OrdenAlfabetico;
import ar.edu.unlam.pb2.dominio.OrdenPorMonto;
import ar.edu.unlam.pb2.dominio.Taller;

public class CongresoDeTecnologiaTest {

	@Test
	public void UnaAcreditacionTienePautasYunEstudianteAbonaLaMitadDelArancel() {
		Acreditacion acreditacionEstudiante = new Estudiante();
		
		Double abono = acreditacionEstudiante.calcularAbono(600.00);
		assertEquals(300.00, abono, 0.001 );
	}

	@Test
	public void  UnaAcreditacionTienePautasYClienteGeneralAbonaElArancelCompleto() {
		
			Acreditacion acreditacionEGeneral = new General();
			
			Double abono = acreditacionEGeneral.calcularAbono(600.00);
			assertEquals(600.00, abono, 0.001 );
	}
	@Test
	public void  UnaAcreditacionTienePautasYUnAuspicianteNoAbonaElArancel() {
		
			Acreditacion acreditacionAuspicinte = new Auspiciante();
			
			Double abono = acreditacionAuspicinte.calcularAbono(0.00);
			assertEquals(0.00, abono, 0.001 );
	}
	//asistentes
	@Test 
	public void dosAsitenteConMismoDniSonLaMismaPersonaLograrQueJavaLosDistinga() {
		Asistente asistente1 = new Asistente("31333333", "Poncio", "Jose", new General());
		Asistente asistente2 = new Asistente("31333333", "Sosa", "Dalma", new Estudiante());
		
		assertEquals(asistente1, asistente2);
	}
	@Test 
	public void dosAsitenteConDistintosDniNoSonLaMismaPersona() {
		Asistente asistente1 = new Asistente("31333333", "Poncio", "Jose", new General());
		Asistente asistente2 = new Asistente("31333323", "Sosa", "Dalma", new Estudiante());
		
		assertNotEquals(asistente1, asistente2);
	}
	@Test
	public void actividades_UnaCharlaPerteneceAUnaActividadGratuita() {
		Actividad charla = new Charla("Programacion en java", 2);
		
		Double arancelCharla = charla.getArancel();
		assertEquals(0.0, arancelCharla, 0.0);
		
	}
	@Test
	public void actividades_DosTalleresConDistintoArancelBaseTienenDistintoArancelPeroSeCalculanIgual() {
		Actividad introductorio = new Taller("Java para Principiantes", 2, 600.00);
		Actividad intensivo = new Taller("Java Intencivo", 2, 900.00);
		
		Double arancelIntroductorio = introductorio.getArancel();
		Double arancelIntesivo = intensivo.getArancel();
		
		assertEquals(600.00, arancelIntroductorio, 0.0);
		assertEquals(900.00, arancelIntesivo, 0.0);
		
	}
	@Test
	public void actividades_UnLaboratorioEsUnTallerYSumaCargosFijosAlArancel() {
		Actividad laboratorio = new Laboratorio("Programacion en java", 2, 8000.00);
		
		Double arancel = laboratorio.getArancel();
		
		assertEquals(10500.0, arancel, 0.01);
		
	}
	public void actividades_tallerTieneArancelIgualAlBase() {
		Actividad taller = new Taller("Java para principiantes", 5, 300.00);
		Double arancel = taller.getArancel();
		
		assertEquals(300.00, arancel, 0.01);
	}
	@Test
	public void incripciones_UnAsistentePuedeIncribirseMientrasHayaCupoNoPuedeRepetirAsistente() throws AsistenteYaInscriptoException, CupoCompletoException {
		Actividad charla = new Charla("Programacion en java", 2);
		Asistente asistente1 = new Asistente("31333333", "Poncio", "Jose", new General());
		
		charla.inscribir(asistente1);
	
		assertTrue(charla.estaInscripto(asistente1));
	assertEquals(1, charla.getCantidadDeInscriptos());
		
	}
	@Test
	public void inscripciones_UnAsistenteNoPuedeInscribirseDosVecesEnLaMismaActividad() throws AsistenteYaInscriptoException, CupoCompletoException {
		Actividad charla = new Charla("", 2);
		Asistente persona = new Asistente("31333333", "Poncio", "Jose", new General());
		Asistente  otraVezLaPersona = new Asistente("31333333", "Poncio", "Jose", new General());
		
		charla.inscribir(persona);
		assertThrows(AsistenteYaInscriptoException.class, () -> charla.inscribir(otraVezLaPersona));
		assertEquals(1, charla.getCantidadDeInscriptos());
	}
	
	@Test
	public void inscripciones_NoPuedeInscribirNadieSiNoHayCupo() throws CupoCompletoException, AsistenteYaInscriptoException {
		Actividad charla = new Charla("Seguridad en la nube", 1);
		Asistente persona1 = new Asistente("1234", "Gutierez", "Javier", new General());
		Asistente persona2= new Asistente("1236", "Sosa", "Sergio", new General());
		
		charla.inscribir(persona1); 
		assertThrows(CupoCompletoException.class, () -> charla.inscribir(persona2));
		assertEquals(1, charla.getCantidadDeInscriptos());
		assertFalse(charla.estaInscripto(persona2));
	}

	@Test 
	public void inscripcion_UnaActividadPuedeInformarCuantoAbonaUnAsistenteYCuantoRecaudaEnTotal() throws AsistenteYaInscriptoException, CupoCompletoException {
		Actividad taller = new Taller("Git desde cero", 3, 120.00);
		Asistente persona1 = new Asistente("1234", "Gutierez", "Javier", new General());
		Asistente persona2= new Asistente("1236", "Sosa", "Sergio", new General());
		
		taller.inscribir(persona1);
		taller.inscribir(persona2);
		
		Double montoTotal = taller.calcularRecaudacionTotal();
		
		assertEquals(240.00, montoTotal, 0.01);
	}
	
	@Test
	public void congreso_LaRecaudacionTotalEsLaSumaDeTodasLasActividades() throws Exception {
	
	    Actividad charla = new Charla("Seguridad en la nube", 3);
	    Actividad taller = new Taller("Git desde cero", 2, 6000.00);
	    Actividad laboratorio = new Laboratorio("Hardening de servidores", 2, 8000.00);

	    Asistente ana = new Asistente("1234", "Gutierrez", "Ana", new General());
	    Asistente beto = new Asistente("1236", "Sosa", "Beto", new Estudiante());
	    Asistente carla = new Asistente("1253", "Cabral", "Carla", new Auspiciante());

	    charla.inscribir(ana);
	    charla.inscribir(beto);
	    charla.inscribir(carla);
	    taller.inscribir(ana);
	    taller.inscribir(beto);
	    laboratorio.inscribir(ana);
	    laboratorio.inscribir(beto);

	    Congreso congreso = new Congreso();
	    congreso.agregarActividad(charla);
	    congreso.agregarActividad(taller);
	    congreso.agregarActividad(laboratorio);

	  
	    Double total = congreso.calcularRecaudacionTotal();

	    
	    assertEquals(24750.0, total, 0.01);
	}
	
	@Test
	public void unAsistenteRepetidoNoSeVuelveAcontar() throws Exception{
	    Actividad charla = new Charla("Seguridad en la nube", 3);
	    Actividad taller = new Taller("Git desde cero", 2, 6000.00);
	    Actividad laboratorio = new Laboratorio("Hardening de servidores", 2, 8000.00);

	    Asistente ana = new Asistente("1234", "Gutierrez", "Ana", new General());
	    Asistente beto = new Asistente("1236", "Sosa", "Beto", new Estudiante());
	    Asistente carla = new Asistente("1253", "Cabral", "Carla", new Auspiciante());

	    charla.inscribir(ana);
	    charla.inscribir(beto);
	    charla.inscribir(carla);
	    taller.inscribir(ana);
	    taller.inscribir(beto);
	    laboratorio.inscribir(ana);
	    laboratorio.inscribir(beto);

	    Congreso congreso = new Congreso();
	    congreso.agregarActividad(charla);
	    congreso.agregarActividad(taller);
	    congreso.agregarActividad(laboratorio);

	  
	  Integer sonDistintos = congreso.getCantidadDeAsistentesDistintos();

	    
	    assertEquals(3, sonDistintos, 0.01);
	}
	@Test
	public void inscripciones_UnInscriptoQueCambiaDeAcreditacionAbonaOtroMontoYSigueInscripto() throws Exception {
		Actividad taller = new Taller("Git desde cero", 10, 6000.00);
		Asistente ana = new Asistente("33123123", "Perez", "Ana", new General());
		taller.inscribir(ana);
		assertEquals(6000.00, taller.calcularAbono(ana), 0.01);
		
		ana.setAcreditacion(new Estudiante());
		
		
		assertEquals(3000.00, ana.acreditarAbono(6000.00),0.01);
		assertTrue(taller.estaInscripto(ana));
		assertEquals(1, taller.getCantidadDeInscriptos());
		}
	
	@Test
	public void UnaListaAsistentesTieneQueEstarOrdenadasAlfabeticamente() throws Exception{
		Actividad taller = new Taller("Git desde cero", 10, 6000.00);
		  	taller.inscribir(new Asistente("1", "Perez", "Juan", new Estudiante()));
		    taller.inscribir(new Asistente("2", "Perez", "Ana", new Estudiante()));
		    taller.inscribir(new Asistente("3", "diaz", "Marta", new General()));
		    taller.inscribir(new Asistente("4", "Acosta", "Luis", new General()));
		    taller.inscribir(new Asistente("5", "Gomez", "Rita", new Auspiciante()));
		
		List<Asistente>lista= taller.getInscriptosOrdenados(new OrdenAlfabetico());
		
		assertEquals(5, lista.size());
		assertEquals("Acosta", lista.get(0).getApellido());
		assertEquals("diaz",   lista.get(1).getApellido());
		assertEquals("Gomez",  lista.get(2).getApellido());
		assertEquals("Ana",    lista.get(3).getNombre());   
		assertEquals("Juan",   lista.get(4).getNombre());
	}
	@Test
	public void listados_LosInscriptosSePuedenListarPorMontoDeMayorAMenorYAIgualMontoAlfabetico() throws Exception {
		Actividad taller = new Taller("Git desde cero", 10, 6000.00);
	  	taller.inscribir(new Asistente("1", "Perez", "Juan", new Estudiante()));
	    taller.inscribir(new Asistente("2", "Perez", "Ana", new Estudiante()));
	    taller.inscribir(new Asistente("3", "diaz", "Marta", new General()));
	    taller.inscribir(new Asistente("4", "Acosta", "Luis", new General()));
	    taller.inscribir(new Asistente("5", "Gomez", "Rita", new Auspiciante()));
	    
	    List<Asistente>listaPorMonto = taller.getInscriptosOrdenados(new OrdenPorMonto(taller.getArancel()));
	    
	    assertEquals(5, listaPorMonto.size());
	    assertEquals("Acosta", listaPorMonto.get(0).getApellido());
	    assertEquals("diaz", listaPorMonto.get(1).getApellido());
	}
	@Test
	public void listados_ModificarElListadoNoAlteraLaActividad() throws Exception {
		Actividad taller = new Taller("Git desde cero", 10, 6000.00);
	  	taller.inscribir(new Asistente("1", "Perez", "Juan", new Estudiante()));
	    taller.inscribir(new Asistente("2", "Perez", "Ana", new Estudiante()));
	    taller.inscribir(new Asistente("3", "diaz", "Marta", new General()));
	    taller.inscribir(new Asistente("4", "Acosta", "Luis", new General()));
	    taller.inscribir(new Asistente("5", "Gomez", "Rita", new Auspiciante()));
	    
	    List<Asistente> lista = taller.getInscriptosOrdenados(new OrdenAlfabetico());
	    
	    lista.remove(0);
	    assertEquals(5, taller.getCantidadDeInscriptos());
	}
	@Test
	public void actividades_UnTallerConArancelNegativoSeRechaza() {
		
		assertThrows(IllegalArgumentException.class, ()-> new Taller("Git desde cero", 10, -6000.00));
	}
	
	@Test
	public void actividades_UnTallerConCupoNegativoSeRechaza() {
		
		assertThrows(IllegalArgumentException.class, ()-> new Taller("Git desde cero", -10, 6000.00));
	}
}