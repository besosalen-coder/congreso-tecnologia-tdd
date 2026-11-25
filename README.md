# Congreso de tecnologia 

Sistema hecho en java para gestionar actividades de un Congreso (charlas, talleres y laboratorio): Inscripción con control de cupo, calculos de aranceles segun la acreditacion, recaudacion y listados. 

Desarrollado con **TDD**
Ejecutar "CongresoDeTecnologiaTest" con JUnit 5 (en Eclipse: clic derecho → Run As → JUnit Test).

## Qué se probó
- Aranceles por tipo de actividad (charla, talleres, laboratorio)
- Identidad de asistentes por DNI
- Monto según acreditación (general, estudiante, auspiciante)
- Inscripciones: cupo completo e inscripción repetida
- Cambio de acreditación sin duplicar inscriptos
- Listados con Comparator (alfabético y por monto)
- Recaudación por actividad y total del congreso
- Datos inválidos al crear objetos

## Decisiones de diseño
- Herencia: `Laboratorio` es un `Taller` con cargo fijo
- Polimorfismo en lugar de `instanceof`
- `Set` para inscriptos (sin repetidos) y `List` para actividades (orden)
- Excepciones propias para situaciones del negocio

## Tecnologías
Java · JUnit 5 · TDD
