/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.text.SimpleDateFormat;
import java.util.Date;
import tads.*;

/**
 *
 * @author felip
 */
public final class Medico implements Comparable<Medico> {

    private String Nombre;
    private int codMedico;
    private int Tel;
    private int Especialidad;
    private ListaNodos fechasAgendadas; // adentro de estas van haber fechas con lista de consultas
    private ListaNodos consultasEnEspera;// esta se va pa dentro de las fechas
    private int cantConsultasAgendadas;
    private int consultasEsperando;

    public Medico(String nombre, int codigoMedico, int tel, int especialidad) {
        this.setNombre(nombre);
        this.setCodMedico(codigoMedico);
        this.setTel(tel);
        this.setEspecialidad(especialidad);
        this.fechasAgendadas = new ListaNodos();
        this.consultasEnEspera = new ListaNodos();
        this.cantConsultasAgendadas = 0;
        this.consultasEsperando = 0;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the codMedico
     */
    public int getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedicoP
     */
    public void setCodMedico(int codMedicoP) {
        this.codMedico = codMedicoP;
    }

    /**
     * @return the Tel
     */
    public int getTel() {
        return Tel;
    }

    /**
     * @param Tel the Tel to set
     */
    public void setTel(int Tel) {
        this.Tel = Tel;
    }

    /**
     * @return the Especialidad
     */
    public int getEspecialidad() {
        return Especialidad;
    }

    /**
     * @param Especialidad the Especialidad to set
     */
    public void setEspecialidad(int Especialidad) {
        this.Especialidad = Especialidad;
    }

    @Override
    public int compareTo(Medico obj) {
        return this.getNombre().compareTo(obj.getNombre());
    }

    @Override
    public boolean equals(Object obj) {
        return this.getCodMedico() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.getCodMedico();
    }

    @Override
    public String toString() {
        return this.getNombre() + "-" + this.getCodMedico() + "-" + this.getEspecialidad();
    }

    public boolean tieneConsultas() {
        return this.consultasEsperando != 0 || this.cantConsultasAgendadas != 0;
    }

    /**
     * @return the cantConsultasAgendadas
     */
    public int getCantConsultasAgendadas() {
        return cantConsultasAgendadas;
    }

    /**
     * @param cantConsultasAgendadas the cantConsultasAgendadas to set
     */
    public void setCantConsultasAgendadas(int cantConsultasAgendadas) {
        this.cantConsultasAgendadas = cantConsultasAgendadas;
    }

    /**
     * @return the consultasEsperando
     */
    public int getConsultasEsperando() {
        return consultasEsperando;
    }

    /**
     * @param consultasEsperando the consultasEsperando to set
     */
    public void setConsultasEsperando(int consultasEsperando) {
        this.consultasEsperando = consultasEsperando;
    }

    public void crearFecha(Date obj) {
        //
        if (obj != null) {
            Fecha aux = new Fecha(obj);
            this.fechasAgendadas.agregarOrd((Comparable) aux);
        }
    }

    public void consultaNueva(int ciPaciente, Date objFecha, int maxPacientes) {

        if (fechasAgendadas.existeElemento(objFecha)) {
            Fecha auxFecha = (Fecha) fechasAgendadas.obtenerElemento(objFecha).getDato();
            if (auxFecha != null) {
                Consulta auxConsulta = new Consulta(this.codMedico, ciPaciente, objFecha);
                if (auxFecha.cantidadConsultas() < maxPacientes) {
                    auxFecha.agregarAgenda(auxConsulta);
                    System.out.println("Consulta agendada en agenda");
                } else {
                    auxFecha.agregarEspera(auxConsulta);
                    System.out.println("consulta agendada en espera");
                }

            }
        } else {
            this.crearFecha(objFecha);
            Fecha auxFecha = (Fecha) fechasAgendadas.obtenerElemento(objFecha).getDato();
            Consulta auxConsulta = new Consulta(this.codMedico, ciPaciente, objFecha);
            auxFecha.agregarAgenda(auxConsulta);
            System.out.println("Consulta agendada despues de crear la fecha");
        }

    }

    public boolean existeConsultaPendiente(int ciPaciente) {
        boolean respuesta = false;
        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerInicio();
            if (aux.getSiguiente() != null) {
                while (aux.getSiguiente() != null && !respuesta) {
                    Fecha fecha = (Fecha) aux.getDato();
                    if (fecha != null) {
                        respuesta = fecha.pacienteConsultaPendiente(ciPaciente); // ToDo: verificar creo que pacienteConsultaPendiente no funca bien !!
                    }
                    aux = aux.getSiguiente();
                }
            }
            if (aux.getSiguiente() == null) {
                Fecha fecha = (Fecha) aux.getDato();
                if (fecha != null) {
                    respuesta = fecha.pacienteConsultaPendiente(ciPaciente); // ToDo: verificar creo que pacienteConsultaPendiente no funca bien !!
                }
            }
        }
        return respuesta;
    }

    public boolean existeConsultaCerrada(int ciPaciente) {
        boolean respuesta = false;
        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerInicio();
            if (aux.getSiguiente() != null) {
                while (aux.getSiguiente() != null && !respuesta) {
                    Fecha fecha = (Fecha) aux.getDato();
                    if (fecha != null) {
                        respuesta = fecha.pacienteConsultaCerrada(ciPaciente); // ToDo: verificar creo que pacienteConsultaPendiente no funca bien !!
                    }
                    aux = aux.getSiguiente();
                }
            }
            if (aux.getSiguiente() == null) {
                Fecha fecha = (Fecha) aux.getDato();
                if (fecha != null) {
                    respuesta = fecha.pacienteConsultaCerrada(ciPaciente); // ToDo: verificar creo que pacienteConsultaPendiente no funca bien !!
                }
            }
        }
        return respuesta;
    }

    public boolean existeConsulta(int ciPaciente) {
        return existeConsultaPendiente(ciPaciente) && existeConsultaCerrada(ciPaciente);
    }

    public boolean cancelarReserva(int ciPaciente) {
        boolean result = false;
        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerInicio();
            if (aux.getSiguiente() != null) {
                Fecha auxF = (Fecha) aux.getDato();
                if (!auxF.pacienteConsultaPendiente(ciPaciente) || !auxF.pacienteConsultaCerrada(ciPaciente)) {
                    while (aux.getSiguiente() != null && !result) {
                        auxF.cancelarReservaFecha(ciPaciente);
                        result = true;
                        System.out.println("Result= true, consuilta eliminada -->sout de Medico");
                    }
                }
            }
            if (aux.getSiguiente() == null) {
                Fecha auxF = (Fecha) aux.getDato();
                if (!auxF.pacienteConsultaPendiente(ciPaciente) || !auxF.pacienteConsultaCerrada(ciPaciente)) {
                    auxF.cancelarReservaFecha(ciPaciente);
                    result = true;
                    System.out.println("Result= true, consuilta eliminada sin aux.getSigueinte-->sout de Medico");

                }
            }
        }
        return result;
    }

    public void terminarConsulta(int ciPaciente) {

    }

    ///////////////////////////////////////////////////////////////////////////
    public boolean estadoCerrado(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = fechasAgendadas.obtenerInicio();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (auxConsulta.getEstado().equals("Cerrada")) {
                    resultado = true;
                }
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        if (auxConsulta.getEstado().equals("Cerrada")) {
                            resultado = true;
                        }
                    }
                    aux = aux.getSiguiente();
                }
            }

        }

        return resultado;
    }

    public boolean tieneReservaEnDia(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = fechasAgendadas.obtenerInicio();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {

                if (sdf.format(hoy).equals(sdf.format(auxConsulta.getFecha()))) {
                    resultado = true;
                }
            } else {
                while (aux.getSiguiente() != null && !resultado) { //no me llega a la ultima consulta
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        if (sdf.format(hoy).equals(sdf.format(auxConsulta.getFecha()))) {
                            resultado = true;
                        }
                    }
                    aux = aux.getSiguiente();
                }
            }
        }
        Consulta auxConsulta = (Consulta) aux.getDato();
        if (auxConsulta.getCiPaciente() == ciPaciente) {
            if (sdf.format(hoy).equals(sdf.format(auxConsulta.getFecha()))) {
                resultado = true;
            }
        }
        return resultado;
    }

    public boolean anunciarLlegadaPaciente(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = fechasAgendadas.obtenerInicio();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaHoy = new Date();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (sdf.format(auxConsulta.getFecha()).equals(sdf.format(fechaHoy))) {
                    auxConsulta.anunciarLLegada();
                    System.out.println("Nombre medico :" + this.getNombre());
                    resultado = true;
//                    System.out.println("Consulta: " + auxConsulta.getEstado());
                }
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        if (sdf.format(auxConsulta.getFecha()).equals(sdf.format(fechaHoy))) {
                            auxConsulta.anunciarLLegada();
                            System.out.println("Nombre medico :" + this.getNombre());
//                            System.out.println("Consulta: " + auxConsulta.getEstado());
                            resultado = true;
                        }
                    }
                    aux = aux.getSiguiente();
                }
            }

            auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (sdf.format(auxConsulta.getFecha()).equals(sdf.format(fechaHoy))) {
                    auxConsulta.anunciarLLegada();
                    System.out.println("Nombre medico :" + this.getNombre());
//                            System.out.println("Consulta: " + auxConsulta.getEstado());
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    public Consulta terminarConsultaMedico(int ciPaciente, String detalle) {
        Consulta retorno = null;
        boolean resultado = false;

        Nodo aux = fechasAgendadas.obtenerInicio();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (auxConsulta.getEstado().equals("En espera")) {
                    auxConsulta.terminarConsulta(detalle);
                    retorno = auxConsulta;
//                    resultado=true;
                }
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        if (auxConsulta.getEstado().equals("En espera")) {
                            auxConsulta.terminarConsulta(detalle);
                            retorno = auxConsulta;
                            resultado = true;
                        }
                    }
                    aux = aux.getSiguiente();
                }
            }
        }
        Consulta auxConsulta = (Consulta) aux.getDato();
        if (auxConsulta.getCiPaciente() == ciPaciente) {
            if (auxConsulta.getEstado().equals("En espera")) {
                auxConsulta.terminarConsulta(detalle);
                retorno = auxConsulta;
                resultado = true;
            }
        }
        return retorno;
    }

    public boolean consultaEnEspera(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = fechasAgendadas.obtenerInicio();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (auxConsulta.getEstado().equals("En espera")) {
                    resultado = true;
                }
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        if (auxConsulta.getEstado().equals("En espera")) {
                            resultado = true;
                        }
                    }
                    aux = aux.getSiguiente();
                }
            }
            auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (auxConsulta.getEstado().equals("En espera")) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    public boolean consultasEnFecha(Date f) {
        boolean respuesta = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Nodo aux = fechasAgendadas.obtenerInicio();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            while (aux.getSiguiente() != null) {
                if (sdf.format(f).equals(sdf.format(auxConsulta.getFecha()))) {
                    respuesta = true;
                }
                aux = aux.getSiguiente();
            }
            if (sdf.format(f).equals(sdf.format(auxConsulta.getFecha()))) {
                respuesta = true;
            }
        }

        return respuesta;
    }

    public boolean cerrarPacientesAusentes(Date f) {
        boolean resultado = false;
        Nodo aux = fechasAgendadas.obtenerInicio();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            while (aux.getSiguiente() != null) {
                auxConsulta = (Consulta) aux.getDato();
                if (sdf.format(f).equals(sdf.format(auxConsulta.getFecha()))) {
                    if (auxConsulta.getEstado().equals("Pendiente")) {
                        auxConsulta.setFalta();
                        System.out.println(auxConsulta.getEstado());
                        resultado = true;
                    }
                }
                aux = aux.getSiguiente();
            }
            auxConsulta = (Consulta) aux.getDato();
            if (sdf.format(f).equals(sdf.format(auxConsulta.getFecha()))) {
                if (auxConsulta.getEstado().equals("Pendiente")) {
                    auxConsulta.setFalta();
                    System.out.println(auxConsulta.getEstado());
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    public void listarConsultasXDia() {
        Nodo aux = this.fechasAgendadas.obtenerInicio();
        if (aux != null) {
            listarConsultasDiaRec(aux);
        }

    }

    public void listarConsultasDiaRec(Nodo obj) {

        if (obj != null) {

            if (obj.getSiguiente() != null) {
                Consulta auxConsulta = (Consulta) obj.getDato();
                System.out.println(auxConsulta.getFecha());
                listarConsultasDiaRec(obj.getSiguiente());

            } else {
                Consulta auxConsulta = (Consulta) obj.getDato();
                System.out.println(auxConsulta.getFecha());
            }

        }

    }

}

/**
 * SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); Date hoy=new
 * Date(); String dat= "25/10/2023"; System.out.println(sdf.format(hoy));
 * System.out.println(sdf.format(hoy).equals(dat));
 *
 *
 */
