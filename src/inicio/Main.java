package inicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import sistemaAutogestion.*;

public class Main {

    public static void main(String[] args) {
//        Sistema sistema = new Sistema();
//        Prueba prueba = new Prueba();
//
//        //2.1 Crear Sistema de Autogestión
//        p1_creacionSistema(prueba, sistema);
//        //2.2 Registrar Médico
//        p2_registroMedico(prueba, sistema);
//        //2.3 Eliminar Médico
//        p3_eliminarMedico(prueba, sistema);
//        //2.4 Registrar Paciente
//        p4_registroPaciente(prueba, sistema);
//        //2.5 Eliminar Paciente
//        p5_eliminarPaciente(prueba, sistema);
//
//        prueba.imprimirResultadosPrueba();
//
//        sistema.listarMédicos();
//        sistema.listarPacientes();
//        
        Sistema s = new Sistema();
        Prueba p = new Prueba();

        p.inicializarResultadosPrueba();

//        
//        juegodeprueba1(s, p);
//        juegodeprueba2(s, p);
//        juegodeprueba3(s, p);
//        juegodeprueba4(s, p);
//        juegodeprueba5(s, p);
        p.imprimirResultadosPrueba();

        System.out.println("Pruebas individuales abajo: ");
        s.crearSistemaDeAutogestion(2);

        s.agregarPaciente("Felipe", 1, "Alguna");
        s.agregarPaciente("Fe", 2, "Alguna");
        s.agregarPaciente("Wind", 3, "Alguna");
        s.agregarPaciente("Ho", 4, "Alguna");
        
        
        s.registrarMedico("Hor", 1, 123, 3);
        s.reservaConsulta(1, 1, new Date());
        s.reservaConsulta(1, 2, new Date());
        s.reservaConsulta(1, 3, new Date());
        s.reservaConsulta(1, 4, new Date());
        s.cancelarReserva(1, 3);

    }

    private static void p1_creacionSistema(Prueba prueba, Sistema sistema) {
        prueba.ver(sistema.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK, "Pasa: Sistema creado");
        prueba.ver(sistema.crearSistemaDeAutogestion(16).resultado, Retorno.Resultado.ERROR_1, "Falla: maxPacientes incorrecto");
        prueba.ver(sistema.crearSistemaDeAutogestion(0).resultado, Retorno.Resultado.ERROR_1, "Falla: maxPacientes incorrecto");
        prueba.ver(sistema.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1, "Falla: maxPacientes incorrecto");

    }

    private static void p2_registroMedico(Prueba prueba, Sistema sistema) {
        prueba.ver(sistema.registrarMedico("Pedro", 111, 1, 1).resultado, Retorno.Resultado.OK, "Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Sandra", 111, 1, 1).resultado, Retorno.Resultado.ERROR_1, "Falla: Código repetido");
        prueba.ver(sistema.registrarMedico("Tatiana", 120, 1, 1).resultado, Retorno.Resultado.OK, "Pasa:  Médico registrado");
        prueba.ver(sistema.registrarMedico("Federico", 112, 1, 1).resultado, Retorno.Resultado.OK, "Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Marcelo", 113, 1, 2).resultado, Retorno.Resultado.OK, "Pasa: Médico registrado");
        prueba.ver(sistema.registrarMedico("Tatiana", 120, 1, 0).resultado, Retorno.Resultado.ERROR_2, "Falla:  Especialidad menor a 1");
        prueba.ver(sistema.registrarMedico("Tatiana", 120, 1, 21).resultado, Retorno.Resultado.ERROR_2, "Falla:  Especialidad mayor a 20");
        prueba.ver(sistema.registrarMedico("Rodolf", 1, 1, 21).resultado, Retorno.Resultado.ERROR_2, "Falla:  Especialidad mayor a 20");
    }

    private static void p3_eliminarMedico(Prueba prueba, Sistema sistema) {
        prueba.ver(sistema.eliminarMedico(113).resultado, Retorno.Resultado.OK, "Pasa: Marcelo borrado");
        prueba.ver(sistema.eliminarMedico(111).resultado, Retorno.Resultado.OK, "Pasa: Federico borrado");
        prueba.ver(sistema.eliminarMedico(111).resultado, Retorno.Resultado.ERROR_1, "Falla: Ya se eliminó un Médico con ese código");
        prueba.ver(sistema.eliminarMedico(1).resultado, Retorno.Resultado.ERROR_1, "Falla: No existe Médico con ese código");
    }

    private static void p4_registroPaciente(Prueba prueba, Sistema sistema) {
        prueba.ver(sistema.agregarPaciente("Pedro", 111, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Tatiana", 112, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Julian", 112, "direccion1").resultado, Retorno.Resultado.ERROR_1, "Falla: Paciente ya registrado");
        prueba.ver(sistema.agregarPaciente("Marcelo", 120, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Federico", 125, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Gianfranco", 116, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Facundo", 117, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
        prueba.ver(sistema.agregarPaciente("Santiago", 111, "direccion1").resultado, Retorno.Resultado.ERROR_1, "Falla: Paciente ya registrado");
        prueba.ver(sistema.agregarPaciente("Valentina", 119, "direccion1").resultado, Retorno.Resultado.OK, "Pasa: Paciente registrado");
    }

    private static void p5_eliminarPaciente(Prueba prueba, Sistema sistema) {
        prueba.ver(sistema.eliminarPaciente(111).resultado, Retorno.Resultado.OK, "Pasa: Paciente borrado");
        prueba.ver(sistema.eliminarPaciente(119).resultado, Retorno.Resultado.OK, "Pasa: Paciente borrado");
        prueba.ver(sistema.eliminarPaciente(134).resultado, Retorno.Resultado.OK, "Falla: No existe un paciente con esa CI");
    }

    private static void p6_agregarConsulta(Prueba p, Sistema s) {
        p.ver(s.reservaConsulta(120, 117, new Date()).resultado, Retorno.Resultado.OK, "Prueba de crear reservaConsulta");
    }

    public static void juegodeprueba1(Sistema s, Prueba p) {
        System.out.println("juego de prueba 1");
        /* ----------------- CREAR SISTEMA DE AUTOGESTIÓN --------------------------*/
        // Se crea correctamente el sistema
        p.ver(s.crearSistemaDeAutogestion(5).resultado, Retorno.Resultado.OK, "Sistema de autogestión creado correctamente.");
        /* No se crea el sistema porque el maxPacientesporMedico es < 0 */
        p.ver(s.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1, "El máximo de pacientes por médico es menor a 0.");
        /* No se crea el sistema porque el maxPacientesporMedico es > 15 */
        p.ver(s.crearSistemaDeAutogestion(16).resultado, Retorno.Resultado.ERROR_1, "El máximo de pacientes por médico es mayor a 15.");
        /* No se crea el sistema porque el maxPacientesporMedico es > 15 */
        p.ver(s.crearSistemaDeAutogestion(20).resultado, Retorno.Resultado.ERROR_1, "El máximo de pacientes por médico es mayor a 15.");
        /* No se crea el sistema porque el maxPacientesporMedico es > 15 */
        p.ver(s.crearSistemaDeAutogestion(24).resultado, Retorno.Resultado.ERROR_1, "El máximo de pacientes por médico es mayor a 15.");

        /* ----------------- REGISTRAR MÉDICO --------------------------*/
        // Se registra correctamente a un médico
        p.ver(s.registrarMedico("Santiago", 1, 202020, 3).resultado, Retorno.Resultado.OK, "Médico registrado.");
        // Se registra correctamente a un médico
        p.ver(s.registrarMedico("Micaela", 2, 888888, 2).resultado, Retorno.Resultado.OK, "Médico registrado.");
        // Se registra correctamente a un médico
        p.ver(s.registrarMedico("Romina", 3, 888888, 1).resultado, Retorno.Resultado.OK, "Médico registrado.");
        /* No se registra al médico porque su código es igual al de uno existente */

        System.out.println("Debe listar a: Santiago, Micaela y Romina");
        /* ----------------- LISTAR MÉDICOS --------------------------*/
        // Se listan correctamente los médicos
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se listan los médicos correctamente");

    }

    public static void juegodeprueba2(Sistema s, Prueba p) {
        System.out.println("juego de prueba 2");
        /* ----------------- ELIMINAR MÉDICOS --------------------------*/
        p.ver(s.registrarMedico("Santiago", 1, 202020, 3).resultado, Retorno.Resultado.ERROR_1, "Existe un médico con igual código.");
        /* No se registra al médico porque su especialidad es menor que 1 */
        p.ver(s.registrarMedico("Otro", 2, 202020, -1).resultado, Retorno.Resultado.ERROR_2, "La especialidad del médico es menor a 1");
        /* No se registra al médico porque su especialidad es mayor que 20 */
        p.ver(s.registrarMedico("Otro", 2, 202020, 25).resultado, Retorno.Resultado.ERROR_2, "La especialidad del médico es mayor a 20");

        p.ver(s.registrarMedico("Santiago", 3, 202020, 3).resultado, Retorno.Resultado.OK, "Existe un médico con igual código.");

        p.ver(s.registrarMedico("Santiago", 4, 202020, 3).resultado, Retorno.Resultado.OK, "Existe un médico con igual código.");

        System.out.println("Debe listar a: Santiago, Micaela y Romina");
        /* ----------------- LISTAR MÉDICOS --------------------------*/
        // Se listan correctamente los médicos
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se listan los médicos correctamente");

    }

    public static void juegodeprueba3(Sistema s, Prueba p) {
        System.out.println("juego de prueba 3");
        /* No se elimina al médico porque no existe */
        p.ver(s.eliminarMedico(938322).resultado, Retorno.Resultado.ERROR_1, "El médico no existe.");
        // Se elimina correctamente a un médico
        p.ver(s.eliminarMedico(3).resultado, Retorno.Resultado.OK, "Médico eliminado.");
        // Se elimina correctamente a un médico
        p.ver(s.eliminarMedico(2).resultado, Retorno.Resultado.OK, "Médico eliminado.");
        // Se elimina correctamente a un médico
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.OK, "Médico eliminado.");
        // Se elimina correctamente a un médico
        p.ver(s.eliminarMedico(222222).resultado, Retorno.Resultado.ERROR_1, "El médico no existe.");
        System.out.println("Listado vacio...");
        // Se listan correctamente los médicos
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se listan los médicos correctamente");

    }

    public static void juegodeprueba4(Sistema s, Prueba p) {
        System.out.println("juego de prueba 4");
        /* ----------------- REGISTRAR PACIENTE --------------------------*/
        // Se registra correctamente a un paciente
        p.ver(s.agregarPaciente("Anderson", 10, "Luis de la Torre").resultado, Retorno.Resultado.OK, "Paciente registrado correctamente.");
        // Se registra correctamente a un paciente
        p.ver(s.agregarPaciente("Felipe", 11, "Pastoriza").resultado, Retorno.Resultado.OK, "Paciente registrado correctamente.");
        // Se registra correctamente a un paciente
        p.ver(s.agregarPaciente("Yania", 12, "Republica del Salvador").resultado, Retorno.Resultado.OK, "Paciente registrado correctamente.");
        /* No se registra al paciente porque su cédula es igual a la de uno existente */
        p.ver(s.agregarPaciente("Otro", 12, "Algo").resultado, Retorno.Resultado.ERROR_1, "El paciente ya existe.");
        /* ----------------- LISTAR PACIENTES --------------------------*/
        // Se listan correctamente los pacientes
        System.out.println("Debería mostrar a Anderson, Felipe y Yania");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se listan los pacientes correctamente.");

    }

    public static void juegodeprueba5(Sistema s, Prueba p) {
        System.out.println("juego de prueba 5");
        /* ----------------- ELIMINAR PACIENTE --------------------------*/
        // Se elimina correctamente a un paciente
        p.ver(s.eliminarPaciente(10).resultado, Retorno.Resultado.OK, "Paciente eliminado correctamente.");
        /* No se elimina al paciente porque no existe */
        p.ver(s.eliminarPaciente(11111).resultado, Retorno.Resultado.ERROR_1, "El paciente no existe");
        // Se elimina correctamente a un paciente
        p.ver(s.eliminarPaciente(12).resultado, Retorno.Resultado.OK, "Paciente eliminado correctamente.");
        // Se elimina correctamente a un paciente
        p.ver(s.eliminarPaciente(11).resultado, Retorno.Resultado.OK, "Paciente eliminado correctamente.");

        /* ----------------- LISTAR PACIENTES --------------------------*/
        // Se listan correctamente los pacientes
        System.out.println("Debería mostrar la lista vacia");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se listan los pacientes correctamente.");

    }

}
