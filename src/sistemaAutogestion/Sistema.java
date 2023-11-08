package sistemaAutogestion;

import entidad.*;
import java.time.YearMonth;
import java.util.Date;
import tads.*;

public class Sistema implements IObligatorio {

    private ListaNodos listaPacientes;
    private ListaNodos listaMedicos;
    private int maxPacientes;

    public Sistema() {

    }

    /*
    pre: el maxPacientesporMedico debe ser mayor que 0 y menor que 16
    post: se crea el sistema de autogestion instanciando las listas de pacientes y medicos
          , en caso de que maxPacientesporMedico esté por fuera de los limites retorna codigo de error 1.
    
     */
    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (maxPacientesporMedico <= 0 || maxPacientesporMedico > 15) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaPacientes = new ListaNodos();
            listaMedicos = new ListaNodos();
            maxPacientes = maxPacientesporMedico;
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    /*
    pre: El nombre no puede ser null, codMedico > 0, tel>0, 1 <= especialidad <=20 
    post: Se debería poder obtener el medico con codMedico retorna resultado OK, en caso de que ya 
            exista ese médico retorna codigo error 1, en caso de que la especialidad sea menor a 1
            o mayor que 20 retorna codigo error 2.
     */
    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico nuevo = new Medico(nombre, codMedico, tel, especialidad);

        if (listaMedicos.existeElemento(nuevo)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (especialidad < 1 || especialidad > 20) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            listaMedicos.agregarOrd(nuevo);
//            listaMedicos.mostrar();
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    /*
    pre: el codMedico debe ser mayor que cero
    post:debe de eliminar al medico, no pudiendo volver a encontrarlo por su codMedico retornando codigo OK,
        en caso de que ese codMedico no exista retorna codigo error 1, en caso de que ese medico
        tenga consultas pendientes retorna codigo de error 2.
     */
    @Override
    public Retorno eliminarMedico(int codMedico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Nodo<Medico> m = listaMedicos.obtenerElemento(codMedico);

        if (m == null) {
//            System.out.println(r.resultado = Retorno.Resultado.ERROR_1);
            r.resultado = Retorno.Resultado.ERROR_1;
        } else if (m.getDato().tieneConsultas()) {
//            System.out.println("El medico :" + codMedico + " ,tiene consultas pendientes!");
            r.resultado = Retorno.Resultado.ERROR_2;
        } else {
            listaMedicos.borrarElemento(codMedico);
//            System.out.println("El medico " + codMedico + " ha sido eliminado!");
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    /*
    pre: el nombre debe ser distinto de null, cedula > 0 , dirección <> null
    post: en caso exitoso de registro de paciente se deberia poder encontrar por numero de CI retorna resultado OK,
        en caso de que ese numero de CI ya exista retorna resultado error 1.
    
     */
    @Override
    public Retorno agregarPaciente(String nombre, int CI, String direccion) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente nuevo = new Paciente(nombre, CI, direccion);

//        if (listaPacientes.esVacia()) {
//            listaPacientes.agregarFinal(nuevo);
//            r.resultado = Retorno.Resultado.OK;
//        }
        if (listaPacientes.existeElemento(nuevo)) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaPacientes.agregarFinal(nuevo);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    /*
    pre: CI debe ser mayor que cero
    post: en caso exitoso ese numero de CI no se podrá volver a encontrar en la lista retornando codigo de resultado OK,
           en caso de que no exista ese numero de CI retornara un codigo de error 1, en caso de que el paciente tenga
           historiaMedica no se borrará y devolvera un codigo de resultado error 2.
     */
    @Override
    public Retorno eliminarPaciente(int CI) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Nodo<Paciente> p = listaPacientes.obtenerElemento(CI);

        if (p == null) {
//            System.out.println(r.resultado = Retorno.Resultado.ERROR_1);
            r.resultado = Retorno.Resultado.ERROR_1;
        } else if (p.getDato().tieneHistoriaMedica()) {
//            System.out.println("El historial medico del paciente " + CI + " no esta vacio!");
            r.resultado = Retorno.Resultado.ERROR_2;
        } else {
            listaPacientes.borrarElemento(CI);
//            System.out.println("Paciente " + CI + " eliminado");
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    /*
    pre: codMedico > 0, ciPaciente > 0, fecha > ahora
    post: se realiza la reserva de consulta en la lista de reservasPendientes dando como retorno resultado OK
     */
    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        boolean p = listaPacientes.existeElemento(ciPaciente);
        boolean m = listaMedicos.existeElemento(codMedico);
        if (!p) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (!m) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        Medico med = (Medico) listaMedicos.obtenerElemento(codMedico).getDato();
        if (!med.existeFecha(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }
        if (med.existeConsultaPendiente(ciPaciente)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        } else {
            med.consultaNueva(ciPaciente, fecha, maxPacientes);
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    /*
    pre:codMedico>0, ciPaciente>0
    post: se elimina la reserva de la lista reservasPendientes, devuelve como resultado OK
     */
    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        boolean medico = this.listaMedicos.existeElemento(codMedico);
        boolean paciente = this.listaPacientes.existeElemento(ciPaciente);

        if (!medico) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (!paciente) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        Nodo<Medico> m = listaMedicos.obtenerElemento(codMedico);
        if (m.getDato().existeConsultaCerrada(ciPaciente)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        if (m.getDato().existeConsulta(ciPaciente)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        if (!m.getDato().existeConsultaPendiente(ciPaciente)) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        } else {
            m.getDato().cancelarReserva(ciPaciente);
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    /*
    pre: codMedico > 0 y ciPaciente >0
    post: cambia el estado de la consulta de ese paciente a "en espera", la copia a la listaPacientesEnEspera y la elimina de la lista de pendientes, además en el medico le incrementa en 1
          el atributo consultasEsperando, retorna resultado OK
    
     */
    @Override
    public Retorno anunciaLlegada(int codMedico, int ciPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        boolean medico = this.listaMedicos.existeElemento(codMedico);
        boolean paciente = this.listaPacientes.existeElemento(ciPaciente);

        if (!paciente) {
//            System.out.println("Este paciente no existe!");
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (!medico) {
//            System.out.println("No existe el medico");
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }
        Nodo<Medico> m = listaMedicos.obtenerElemento(codMedico);
        if (!m.getDato().tieneReserva(ciPaciente)) {
//            System.out.println("El paciente no tiene reserva pendiente");
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        if (m.getDato().anunciarLlegadaPaciente(ciPaciente)) {
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    /*
    pre: ciPaciente>0, codMedico>0 y detalleDeConsulta !=null
    post: decrementa en 1 el atributo del medico consultasEsperando, incrementa en 1 el atributo del paciente historialMedico, modifica el estado de la consulta a "cerrada"
           de la lista de consultas y la copia a la lista listaConsultasCerradas y la elimina de la lista de la lista en espera, retorna resultado OK
     */
    @Override
    public Retorno terminarConsultaMedicoPaciente(int ciPaciente, int codMedico, String detalleDeConsulta) {
        /*
         Crear nueva consulta con los detalles del medico, estado cerrada y almacenarla en el historial del paciente, paralelamente
        modificar la consulta en la lista del medico cambiando el estado y agregando el detalle.
         
         */
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
//        boolean medico = this.listaMedicos.existeElemento(codMedico);
        boolean paciente = this.listaPacientes.existeElemento(ciPaciente);
        Nodo<Medico> m = listaMedicos.obtenerElemento(codMedico);
        Nodo<Paciente> p = listaPacientes.obtenerElemento(ciPaciente);

        if (!paciente) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (!m.getDato().consultaEnEspera(ciPaciente)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            Consulta nueva = m.getDato().terminarConsultaMedico(ciPaciente, detalleDeConsulta);
            p.getDato().terminarConsultaPaciente(nueva);
            r.resultado = Retorno.Resultado.OK;
            System.out.println("El numero de consultas terminadas en el paciente: " + ciPaciente + " es de: " + p.getDato().cantHistoria());

        }
        return r;
    }

    /*
    pre: codMedico>0, fechaConsulta= momento consulta
    post: se cierra la consulta modificando el estado a "no asistio",se decrementa en 1 el atributo del medico consultasEsperando, 
        incrementa en 1 el atributo del paciente historialMedico, la copia a la lista listaConsultasCerradas y la elimina de la lista de la lista en espera, retorna resultado OK
    
     */
    @Override
    public Retorno cerrarConsulta(int codMedico, Date fechaConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        boolean medico = this.listaMedicos.existeElemento(codMedico);
        Nodo<Medico> m = listaMedicos.obtenerElemento(codMedico);

        if (!medico) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (!m.getDato().consultasEnFecha(fechaConsulta)) {
            System.out.println("El medico no tiene consultas para este dia: " + fechaConsulta);
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            m.getDato().cerrarPacientesAusentes(fechaConsulta, listaPacientes);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    /*
    pre: debe de haber una lista de medicos instanciada
    post: se espera que se muestren todos los medicos ordenados alfabeticamente, retorna resultado ok 
     */
    @Override
    public Retorno listarMédicos() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        this.listaMedicos.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    /*
    pre: debe haber una lista de pacientes instanciada
    post: se espera que se muestren todos los pacientes ordenados por orden de registro, retorna resultado ok
     */
    @Override
    public Retorno listarPacientes() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        this.listaPacientes.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    /*
    pre: debe haber una lista de listaConsultasPendientes instanciada, codMedico>0
    post: se espera que se muestren todas las consultas pendientes de ese medico agrupadas por dia, retorna resultado ok
     */
    @Override
    public Retorno listarConsultas(int codMédico) {
        boolean med = listaMedicos.existeElemento(codMédico);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (med) {
            Medico m = (Medico) listaMedicos.obtenerElemento(codMédico).getDato();
            m.listarConsultas();
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    /*
    pre: debe haber una lista de listaPacientesEnEspera instanciada, codMedico>0, fecha=today
    post: se espera que se muestren todas los pacientes en espera de ese medico agrupadas por numero de reserva, retorna resultado ok
     */
    @Override
    public Retorno listarPacientesEnEspera(Integer codMédico, Date fecha) {
        boolean med = listaMedicos.existeElemento(codMédico);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (med) {
            Medico m = (Medico) listaMedicos.obtenerElemento(codMédico).getDato();
            m.consultasEnEspera(fecha);
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    /*
    pre: debe haber una lista de listaConsultasPendientes instanciada, ciPaciente>0
    post: se espera que se muestren todas los pacientes con consultas pendientes, retorna resultado ok
     */
    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        boolean pac = listaPacientes.existeElemento(CIPaciente);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (pac) {
            Nodo aux = listaMedicos.obtenerInicio();
            while (aux.getSiguiente() != null) {
                Medico m = (Medico) aux.getDato();
                m.listarConsultasPendientesPacRec(CIPaciente);
                aux = aux.getSiguiente();
            }
            if (aux.getSiguiente() == null) {
                Medico m = (Medico) aux.getDato();
                m.listarConsultasPendientesPacRec(CIPaciente);
            }
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    /*
    pre: debe haber una lista de listaConsultasCerradas instanciada, ci>0
    post: se espera que se muestren todas las consultas con estado "terminada" o "no asistio" de ese paciente , retorna resultado ok
     */
    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        boolean pac = listaPacientes.existeElemento(ci);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (pac) {
            Paciente p = (Paciente) listaPacientes.obtenerElemento(ci).getDato();
            p.listarHisotriaClinica();
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    /*
    pre:debe haber una lista de listaConsultasCerradas instanciada, 0<mes<13,1990 <año<2025
    post: se espera una matriz donde se agrupen la cantidad de paceintes que fueron atendidos por especialidad y por dia 
    
     */
    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        //matriz determinada por el maximo de las especialidades, y maximo días mes 

        if (mes <= 0 || mes >= 12 || año < 2020 || año > 2023) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {

            YearMonth yearMonth = YearMonth.of(año, mes);
            int daysInMonth = yearMonth.lengthOfMonth();

            int matriz[][] = new int[daysInMonth + 1][20 + 1];

            for (int i = 0; i < daysInMonth + 1; i++) {
                matriz[i][0] = i;
            }
            for (int j = 0; j < 21; j++) {
                matriz[0][j] = j;
            }

            if (!listaMedicos.esVacia()) {
                Nodo med = listaMedicos.obtenerInicio();
                int cantPacientes = 0;
                while (med.getSiguiente() != null) {
                    Medico m = (Medico) med.getDato();

                    for (int j = 0; j < 21; j++) {
                        if (m.getEspecialidad() == matriz[0][j]) {

                            for (int i = 1; i < daysInMonth + 1; i++) {

                                if (m.cantidadConsultasCerradas(i, mes, año) > 0) {
                                    cantPacientes = cantPacientes + m.cantidadConsultasCerradas(i, mes, año);

                                    matriz[i][j] = cantPacientes;
                                }
                            }
                        }
                    }
                    med = med.getSiguiente();
                }
                if (med.getSiguiente() == null) {
                    Medico m = (Medico) med.getDato();
                    for (int j = 0; j < 21; j++) {
                        if (m.getEspecialidad() == matriz[0][j]) {
                            for (int i = 1; i < daysInMonth + 1; i++) {
                                if (m.cantidadConsultasCerradas(i, mes, año) > 0) {
                                    cantPacientes = cantPacientes + m.cantidadConsultasCerradas(i, mes, año);
                                    matriz[i][j] = cantPacientes;
                                }
                            }
                        }
                    }
                }
            }
            mostrarRec(matriz);
        }
        return r;
    }

    public Retorno registrarDiaDeConsulta(int codMedico, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        boolean m = listaMedicos.existeElemento(codMedico);
        if (!m) {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        Medico med = (Medico) listaMedicos.obtenerElemento(codMedico).getDato();
        if (med.existeFecha(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_2;
        } else {
            med.crearFecha(fecha);
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    public static void mostrarRec(int[][] mat) {

        mostrarRec(mat, 0, 0);

    }

    private static void mostrarRec(int[][] mat, int columna, int fila) {

        if (fila < mat.length) { //caso base

            if (columna == mat[fila].length - 1) {
                System.out.println(mat[fila][columna]);
                System.out.println(" ");
                mostrarRec(mat, 0, fila + 1);
            } else {
                System.out.print(mat[fila][columna] + " ");
                mostrarRec(mat, columna + 1, fila);
            }
        }
    }

}
