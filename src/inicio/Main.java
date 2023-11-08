package inicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import sistemaAutogestion.*;

public class Main {

    public static void main(String[] args) {

        Sistema s = new Sistema();
        Prueba p = new Prueba();

        p.inicializarResultadosPrueba();
        System.out.println("Creacion sistema----------->");
        p1_1creacionSistema(s, p);

        System.out.println("Registro Medico----------->");
        p1_2registroMedico(s, p);

        System.out.println("eliminar Medico----------->");
        p1_3eliminarMedico(s, p);

        System.out.println("Registro paciente----------->");
        p1_4registroPaciente(s, p);

        System.out.println("Eliminar paciente----------->");
        p1_5eliminarPaciente(s, p);

        System.out.println("Registrar dia de consulta------------->");
        p1_6registrarDiaDeConsulta(s, p);

        System.out.println("Agregar consulta----------->");
        p1_7agregarConsulta(s, p);

        System.out.println("Cancelar consulta----------->");
        p1_8cancelarConsulta(s, p);

        System.out.println("Anuncier llegada paciente------------------>");
        p1_10anunciarLlegadaPaciente(s, p);

        System.out.println("Termina consulta medico paciente ----------------->");
        p1_11terminarConsultaMedicoPaciente(s, p);

        p.imprimirResultadosPrueba();

    }

    private static void p1_1creacionSistema(Sistema sistema, Prueba prueba) {
        prueba.ver(sistema.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK, "1 Pasa: Sistema creado");
        prueba.ver(sistema.crearSistemaDeAutogestion(16).resultado, Retorno.Resultado.ERROR_1, "2 Falla: maxPacientes incorrecto");
        prueba.ver(sistema.crearSistemaDeAutogestion(0).resultado, Retorno.Resultado.ERROR_1, "3 Falla: maxPacientes incorrecto");
        prueba.ver(sistema.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1, "4 Falla: maxPacientes incorrecto");

    }

    private static void p1_2registroMedico(Sistema sistema, Prueba prueba) {
        prueba.ver(sistema.registrarMedico("Pedro", 111, 1, 1).resultado, Retorno.Resultado.OK, "1 Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Sandra", 111, 1, 1).resultado, Retorno.Resultado.ERROR_1, "2 Falla: Código repetido");
        prueba.ver(sistema.registrarMedico("Tatiana", 120, 1, 1).resultado, Retorno.Resultado.OK, "3 Pasa:  Médico registrado");
        prueba.ver(sistema.registrarMedico("Federico", 112, 1, 1).resultado, Retorno.Resultado.OK, "4 Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Marcelo", 113, 1, 2).resultado, Retorno.Resultado.OK, "5 Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Tatiana", 124, 1, 0).resultado, Retorno.Resultado.ERROR_2, "6 Falla:   Especialidad menor a 1");
        prueba.ver(sistema.registrarMedico("Tatiana", 333, 1, 21).resultado, Retorno.Resultado.ERROR_2, "7 Falla:  Especialidad mayor a 20");
        prueba.ver(sistema.registrarMedico("Rodolf", 1, 1, 21).resultado, Retorno.Resultado.ERROR_2, "8 Falla:  Ya existe ese médico");
        prueba.ver(sistema.registrarMedico("Rodolf", 2, 1, 4).resultado, Retorno.Resultado.OK, "9 Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Rodolf", 1, 1, 6).resultado, Retorno.Resultado.OK, "10 Pasa: Médico registrado");
    }

    private static void p1_3eliminarMedico(Sistema sistema, Prueba prueba) {
        prueba.ver(sistema.eliminarMedico(113).resultado, Retorno.Resultado.OK, "1 Pasa: Marcelo borrado");
        prueba.ver(sistema.eliminarMedico(111).resultado, Retorno.Resultado.OK, "2 Pasa: Federico borrado");
        prueba.ver(sistema.eliminarMedico(111).resultado, Retorno.Resultado.ERROR_1, "3 Falla: Ya se eliminó un Médico con ese código");
        prueba.ver(sistema.eliminarMedico(25).resultado, Retorno.Resultado.ERROR_1, "4 Falla: No existe Médico con ese código");
    }

    private static void p1_4registroPaciente(Sistema sistema, Prueba prueba) {
        prueba.ver(sistema.agregarPaciente("Felipe", 1, "Alguna").resultado, Retorno.Resultado.OK, "1 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Fe", 2, "Alguna").resultado, Retorno.Resultado.OK, "2 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Wind", 3, "Alguna").resultado, Retorno.Resultado.OK, "3 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Ho", 4, "Alguna").resultado, Retorno.Resultado.OK, "4 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Wind", 5, "Alguna").resultado, Retorno.Resultado.OK, "5 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Felipe", 6, "Alguna").resultado, Retorno.Resultado.OK, "6 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Ho", 7, "Alguna").resultado, Retorno.Resultado.OK, "7 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Wind", 8, "Alguna").resultado, Retorno.Resultado.OK, "8 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Pedro", 111, "direccion1").resultado, Retorno.Resultado.OK, "9 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Tatiana", 112, "direccion1").resultado, Retorno.Resultado.OK, "10 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Julian", 112, "direccion1").resultado, Retorno.Resultado.ERROR_1, "11 Falla: Paciente ya registrado");
        prueba.ver(sistema.agregarPaciente("Marcelo", 120, "direccion1").resultado, Retorno.Resultado.OK, "12 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Federico", 125, "direccion1").resultado, Retorno.Resultado.OK, "13 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Gianfranco", 116, "direccion1").resultado, Retorno.Resultado.OK, "14 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Facundo", 117, "direccion1").resultado, Retorno.Resultado.OK, "15 Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Santiago", 111, "direccion1").resultado, Retorno.Resultado.ERROR_1, "16 Falla: Paciente ya registrado");
        prueba.ver(sistema.agregarPaciente("Valentina", 119, "direccion1").resultado, Retorno.Resultado.OK, "17 Pasa: Paciente registrado");
    }

    private static void p1_5eliminarPaciente(Sistema sistema, Prueba prueba) {
        prueba.ver(sistema.eliminarPaciente(111).resultado, Retorno.Resultado.OK, "1 Pasa: Paciente borrado");
        prueba.ver(sistema.eliminarPaciente(119).resultado, Retorno.Resultado.OK, "2 Pasa: Paciente borrado");
        prueba.ver(sistema.eliminarPaciente(134).resultado, Retorno.Resultado.ERROR_1, "3 Falla: No existe un paciente con esa CI");
    }

    private static void p1_6registrarDiaDeConsulta(Sistema s, Prueba p) {
        p.ver(s.registrarDiaDeConsulta(1, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "1 Prueba de crear registrarDiaDeConsulta");
        p.ver(s.registrarDiaDeConsulta(2, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "2 Prueba de crear registrarDiaDeConsulta");
        p.ver(s.registrarDiaDeConsulta(120, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "3 Prueba de crear registrarDiaDeConsulta");
        p.ver(s.registrarDiaDeConsulta(112, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "4 Prueba de crear registrarDiaDeConsulta");
    }

    private static void p1_7agregarConsulta(Sistema s, Prueba p) {
        p.ver(s.reservaConsulta(1, 1, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "1 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 2, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "2 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 3, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "3 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 4, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "4 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 5, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "5 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 6, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "6 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 7, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "7 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(1, 8, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "8 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 1, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "9 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 2, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "10 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 3, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "11 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 4, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "12 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 5, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "13 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 6, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "14 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 7, new Date("11/08/2023")).resultado, Retorno.Resultado.OK, "15 Prueba de crear reservaConsulta");
        p.ver(s.reservaConsulta(2, 8, new Date("11/16/2023")).resultado, Retorno.Resultado.ERROR_4, "16 Prueba de crear reservaConsulta error 4");
        p.ver(s.reservaConsulta(1, 8, new Date("11/26/2023")).resultado, Retorno.Resultado.ERROR_4, "16 Prueba de crear reservaConsulta error 4");
        p.ver(s.reservaConsulta(2, 8, new Date("11/29/2023")).resultado, Retorno.Resultado.ERROR_4, "16 Prueba de crear reservaConsulta error 4");
    }

    private static void p1_8cancelarConsulta(Sistema s, Prueba p) {
        p.ver(s.cancelarReserva(222, 1).resultado, Retorno.Resultado.ERROR_1, "1 Medico invalido");

    }

    private static void p1_9registrarDiaDeConsulta(Sistema s, Prueba p) {
        p.ver(s.registrarDiaDeConsulta(1, new Date("11/07/2023")).resultado, Retorno.Resultado.OK, "1 Prueba de cancelarConsulta");

    }

    private static void p1_10anunciarLlegadaPaciente(Sistema s, Prueba p) {
        p.ver(s.anunciaLlegada(1, 1).resultado, Retorno.Resultado.OK, "Se anuncia la llegada del paciente 1, medico 1 correctamente");
        p.ver(s.anunciaLlegada(1, 2).resultado, Retorno.Resultado.OK, "Se anuncia la llegada del paciente 2, medico 1 correctamente");
        p.ver(s.anunciaLlegada(2, 3).resultado, Retorno.Resultado.OK, "Se anuncia la llegada del paciente 3, medico 2 correctamente");
        p.ver(s.anunciaLlegada(2, 4).resultado, Retorno.Resultado.OK, "Se anuncia la llegada del paciente 4, medico 2 correctamente");
        p.ver(s.anunciaLlegada(1, 121).resultado, Retorno.Resultado.ERROR_1, "Error, el paciente no existe!");
        p.ver(s.anunciaLlegada(100, 1).resultado, Retorno.Resultado.ERROR_4, "Error, el medico no existe, error no implementado en letra");
    }

    private static void p1_11terminarConsultaMedicoPaciente(Sistema s, Prueba p) {
        p.ver(s.terminarConsultaMedicoPaciente(1, 1, "Detalle de consulta pac 1").resultado, Retorno.Resultado.OK, "Se termina la consulta del paciente 1, medico 1 correctamente");
        p.ver(s.terminarConsultaMedicoPaciente(2, 1, "Detalle de consulta pac 2").resultado, Retorno.Resultado.OK, "Se termina la consulta del paciente 1, medico 1 correctamente");
        p.ver(s.terminarConsultaMedicoPaciente(3, 2, "Detalle de consulta pac 3").resultado, Retorno.Resultado.OK, "Se termina la consulta del paciente 1, medico 1 correctamente");
        p.ver(s.terminarConsultaMedicoPaciente(4, 2, "Detalle de consulta pac 4").resultado, Retorno.Resultado.OK, "Se termina la consulta del paciente 1, medico 1 correctamente");
        p.ver(s.terminarConsultaMedicoPaciente(36541, 1, "Detalle de consulta").resultado, Retorno.Resultado.ERROR_1, "Error 1, este numero de cedula no existe");
        p.ver(s.terminarConsultaMedicoPaciente(8, 2, "Detalle de consulta").resultado, Retorno.Resultado.ERROR_2, "Error este paciente no tiene consulta este dia");
    }

}
