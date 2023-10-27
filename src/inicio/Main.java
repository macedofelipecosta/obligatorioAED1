package inicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import sistemaAutogestion.*;

public class Main {

    public static void main(String[] args) {

        Prueba p = new Prueba();
        Sistema s = new Sistema();
        p.inicializarResultadosPrueba();

        System.out.println("p1_creacionSistema --->");
        p1_creacionSistema(p, s);

        System.out.println("p2_creacionMedicos--->");
        p2_creacionMedicos(p, s);

//         s.agregarPaciente("dragon", 52200432, "Casa julio 123");
//        s.reservaConsulta(1, 52200432, new Date("01/06/2023"));
        System.out.println("p3_eliminarMedico--->");
        p3_eliminarMedico(p, s);
        System.out.println("p4_registrarPaciente--->");
        p4_registrarPaciente(p, s);

        s.reservaConsulta(1, 87654321, new Date("01/06/2023"));

        System.out.println("p5_eliminarPaciente--->");
        p5_eliminarPaciente(p, s);
        System.out.println("p6_listarMedicos--->");
        p6_listarMedicos(p, s);
        System.out.println("p7_listarPacientes--->");
        p7_listarPacientes(p, s);
        System.out.println("p8_agregarConsultasMedico--->");
        p8_agregarConsultasMedico(p, s);

        p.imprimirResultadosPrueba();

        System.out.println("1");
        //formato fecha para realizar la reserva de consulta mm/DD/aaaa
        s.reservaConsulta(1, 52011298, new Date("10/25/2023"));
        System.out.println("2");
        s.reservaConsulta(1, 12345678, new Date("10/30/2023"));
        System.out.println("3");
        s.reservaConsulta(1, 87654321, new Date("10/26/2023"));
        System.out.println("4");
        s.reservaConsulta(1, 52011297, new Date("10/26/2023"));
        System.out.println("5");
        s.reservaConsulta(1, 52011298, new Date("10/31/2023"));
        System.out.println("6");
        s.reservaConsulta(1, 65387491, new Date("10/25/2023"));
        System.out.println("6");
        s.reservaConsulta(1, 90543287, new Date("10/25/2023"));
        System.out.println("7");
        s.cancelarReserva(1, 52011298);
        System.out.println("8");
        s.cancelarReserva(1, 52011297);
        System.out.println("9");
        s.cancelarReserva(1, 52011298);
        System.out.println("10");
        s.reservaConsulta(1, 52011298, new Date("10/25/2023"));
        System.out.println("11");
        s.reservaConsulta(1, 52011297, new Date("10/25/2023"));
        System.out.println("12");
        s.reservaConsulta(1, 21476520, new Date("10/25/2023"));
        System.out.println("13");
        s.reservaConsulta(1, 52011297, new Date("10/25/2023"));
        System.out.println("14");

        s.anunciaLlegada(1, 52011297);
        System.out.println("15");
        s.terminarConsultaMedicoPaciente(52011297, 1, "Prueba de cierre");
        System.out.println("16");
        s.listarMédicos();
        System.out.println("17");
        s.listarPacientes();
        System.out.println("18");
        s.cerrarConsulta(1, new Date("10/26/2023"));
    }

    public static void p1_creacionSistema(Prueba p, Sistema s) {
        p.ver(s.crearSistemaDeAutogestion(4).resultado, Retorno.Resultado.OK, "Se crea correctamente el sistema con capacidad 15");
        p.ver(s.crearSistemaDeAutogestion(20).resultado, Retorno.Resultado.ERROR_1, "Se crea incorrectamente el sistema con capacidad 20");
        p.ver(s.crearSistemaDeAutogestion(-5).resultado, Retorno.Resultado.ERROR_1, "Se crea incorrectamente el sistema con capacidad -5");
    }

    public static void p2_creacionMedicos(Prueba p, Sistema s) {
        p.ver(s.registrarMedico("Furuno", 1, 12546, 5).resultado, Retorno.Resultado.OK, "Se crea correctamente el medico");
        p.ver(s.registrarMedico("Hyper", 2, 12546, 5).resultado, Retorno.Resultado.OK, "Se crea correctamente el medico");
        p.ver(s.registrarMedico("Varsovia", 3, 12546, 5).resultado, Retorno.Resultado.OK, "Se crea correctamente el medico");
        p.ver(s.registrarMedico("Felipe", 4, 12546, 5).resultado, Retorno.Resultado.OK, "Se crea correctamente el medico");
        p.ver(s.registrarMedico("Junito", 5, 12546, 5).resultado, Retorno.Resultado.OK, "No se debe agregar un medico con codMedico ya existente");
        p.ver(s.registrarMedico("Horus", 6, 12546, 6).resultado, Retorno.Resultado.OK, "Se crea correctamente el medico");
        p.ver(s.registrarMedico("Carlos", 7, 1252346, 21).resultado, Retorno.Resultado.ERROR_2, "No se debe registrar el medico con especialidad >20");
        p.ver(s.registrarMedico("Jose", 7, 1252346, 0).resultado, Retorno.Resultado.ERROR_2, "No se debe registrar el medico con especialidad <1");
    }

    public static void p3_eliminarMedico(Prueba p, Sistema s) {
        p.ver(s.eliminarMedico(6).resultado, Retorno.Resultado.OK, "Se elimina correctamente el medico");
        p.ver(s.eliminarMedico(33).resultado, Retorno.Resultado.ERROR_1, "No se elimina un medico con codMedico inexistente");
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.ERROR_2, "No se elimina un medico con consultas pendientes");
    }

    public static void p4_registrarPaciente(Prueba p, Sistema s) {
        p.ver(s.agregarPaciente("Julio", 12345678, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Ramon", 87654321, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Ramiro", 12457896, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Juan", 32659874, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Franco", 85295178, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Jose", 35796148, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Raul", 20165438, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Luis", 30264821, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Ana", 35026471, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Lucas", 93164820, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Facundo", 20462579, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Andres", 52011298, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Jaime", 52011297, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("mapache", 32016257, "Casa julio 123").resultado, Retorno.Resultado.OK, "Se agrega el paciente correctamente");
        p.ver(s.agregarPaciente("Julio", 12345678, "Casa julio 123").resultado, Retorno.Resultado.ERROR_1, "No se agrega un paciente con CI ya existente");
    }

    public static void p5_eliminarPaciente(Prueba p, Sistema s) {
        p.ver(s.eliminarPaciente(12345678).resultado, Retorno.Resultado.OK, "Se elimina el paciente correctamente");
        p.ver(s.eliminarPaciente(12345678).resultado, Retorno.Resultado.ERROR_1, "No se puede eliminar un paciente inexistente");
        p.ver(s.eliminarPaciente(87654321).resultado, Retorno.Resultado.ERROR_2, "No se puede eliminar un paciente con historial medico");
    }

    public static void p6_listarMedicos(Prueba p, Sistema s) {
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se muestran los medicos ordenados alfabeticamente");
    }

    public static void p7_listarPacientes(Prueba p, Sistema s) {
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se listan todos los pacientes en el orden que fueron registrados");
    }

    public static void p8_agregarConsultasMedico(Prueba p, Sistema s) {

        p.ver(s.reservaConsulta(1, 12345678, new Date("01/06/2023")).resultado, Retorno.Resultado.ERROR_2, "No se puede agregar una consulta a un paciente inexistente");
        p.ver(s.reservaConsulta(51, 87654321, new Date("01/06/2023")).resultado, Retorno.Resultado.ERROR_1, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 12457896, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 32659874, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 85295178, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 35796148, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 20165438, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 30264821, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 35026471, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");
        p.ver(s.reservaConsulta(1, 93164820, new Date("01/06/2023")).resultado, Retorno.Resultado.OK, "Se agrega la consulta con el medico");

    }

}
