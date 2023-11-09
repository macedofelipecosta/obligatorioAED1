/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.text.SimpleDateFormat;
import java.util.Date;
import tads.ListaNodos;
import tads.Nodo;

/**
 *
 * @author felip
 */
public class Fecha implements Comparable<Fecha> {

    private Date dato;
    private ListaNodos consultasAgendadas;
    private ListaNodos consultasEnEspera; 
    private int cantConsultasAgendadas;
    private int cantConsultasCerradas;

    public Fecha(Date d) {
        this.dato = d;
        this.consultasAgendadas = new ListaNodos();
        this.consultasEnEspera = new ListaNodos();
        this.cantConsultasAgendadas = 0;
        this.cantConsultasCerradas = 0;
    }

    /**
     * @return the dato
     */
    public Date getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(Date dato) {
        this.dato = dato;
    }

    /**
     * @return the consultasAgendadas
     */
    public ListaNodos getConsultasAgendadas() {
        return consultasAgendadas;
    }

    /**
     * @param consultasAgendadas the consultasAgendadas to set
     */
    public void setConsultasAgendadas(ListaNodos consultasAgendadas) {
        this.consultasAgendadas = consultasAgendadas;
    }

    /**
     * @return the consultasEnEspera
     */
    public ListaNodos getConsultasEnEspera() {
        return consultasEnEspera;
    }

    /**
     * @param consultasEnEspera the consultasEnEspera to set
     */
    public void setConsultasEnEspera(ListaNodos consultasEnEspera) {
        this.consultasEnEspera = consultasEnEspera;
    }

    /**
     * @return the cantConsultasAgendadas
     */
    public int getCantConsultasAgendadas() {
        return cantConsultasAgendadas;
    }

    @Override
    public int compareTo(Fecha o) {
        return this.dato.compareTo(o.getDato());
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // Verificamos primero si el objeto es una instancia de Fecha
        if (!(obj instanceof Date)) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String a = sdf.format(this.dato);
        String b = sdf.format(obj);

        return a.equals(b);

    }

    @Override
    public int hashCode() {
        return getCantConsultasAgendadas(); // ver si este metodo es necesario 
    }

    @Override
    public String toString() {

        return dato.toString();
    }

    //--------------------------------------------------->
    public void agregarAgenda(Consulta obj) {
        if (obj != null) {
            boolean existe = consultasAgendadas.existeElemento(obj);
            if (!existe) {
                consultasAgendadas.agregarOrd(obj);
                this.cantConsultasAgendadas++;
            } else {
                System.out.println("El paciente ya tiene una consulta agendada pendiente!");
            }
        }
    }

    public void agregarEspera(Consulta obj) {
        if (obj != null) {
            boolean existe = consultasEnEspera.existeElemento(obj.getCiPaciente());
            if (!existe) {
                consultasEnEspera.agregarOrd(obj);
            } else {
                System.out.println("El paciente ya tiene una consulta agendada en espera!");
            }
        }
    }

    public int cantidadConsultas() {
        return this.cantConsultasAgendadas;
    }

    public boolean pacienteConsultaPendiente(int ciPaciente) {
        boolean resultado = false;

        if (consultasAgendadas.existeElemento(ciPaciente)) {
            Consulta auxC = (Consulta) consultasAgendadas.obtenerElemento(ciPaciente).getDato();
            if (auxC.getEstado().equals("Pendiente")) {
                resultado = true;
//                System.out.println("Existe elemento consulta ci dentro de agendadas y esta en estado pendiente");
            }
        }
        if (consultasEnEspera.existeElemento(ciPaciente)) {
            resultado = true;
//            System.out.println("Existe elemento consulta ci dentro de espera y esta en estado pendiente");
        }
        return resultado;
    }

    public boolean pacienteConsultaEspera(int ciPaciente) {
        boolean resultado = false;

        if (consultasAgendadas.existeElemento(ciPaciente)) {
            Consulta auxC = (Consulta) consultasAgendadas.obtenerElemento(ciPaciente).getDato();
            if (auxC.getEstado().equals("En espera")) {
                resultado = true;
                System.out.println("Existe elemento consulta ci dentro de agendadas y esta en estado en espera");
            }
        }

        return resultado;
    }

    public boolean pacienteConsultaCerrada(int ciPaciente) {
        boolean resultado = false;

        if (consultasAgendadas.existeElemento(ciPaciente)) {
            Consulta auxC = (Consulta) consultasAgendadas.obtenerElemento(ciPaciente).getDato();

            if (auxC.getEstado().equals("Cerrada")) {
                resultado = true;
                System.out.println("Existe elemento consulta ci dentro de agendadas y esta en estado Cerrada");
            }
        }
        return resultado;
    }

    public boolean cancelarReservaFecha(int ciPaciente) {
        boolean cancelada = false;
        Consulta consultaEnEspera = null;

        if (!this.consultasEnEspera.esVacia()) {
            consultaEnEspera = (Consulta) consultasEnEspera.obtenerInicio().getDato();
        } else {
            System.out.println("No existen consultas en espera para asignar");
        }

        if (!this.consultasAgendadas.esVacia()) {
            Nodo inicio = consultasAgendadas.obtenerInicio();
            while (inicio.getSiguiente() != null) {
                Consulta cAgendada = (Consulta) inicio.getDato();
                if (cAgendada.equals(ciPaciente)) {
                    if (consultaEnEspera != null) {
                        cAgendada.setCiPaciente(consultaEnEspera.getCiPaciente());
                        System.out.println("Se ha cambiado el nro cedula de la consulta por: " + consultaEnEspera.getCiPaciente());
                        cancelada = true;
                    } else {
                        consultasAgendadas.borrarElemento(cAgendada);
                        System.out.println("Unicamente se ha borrado la consulta");
                        cancelada = true;
                    }
                }
                inicio = inicio.getSiguiente();
            }
            if (inicio.getSiguiente() == null) {
                Consulta cAgendada = (Consulta) inicio.getDato();
                if (cAgendada.equals(ciPaciente)) {
                    if (consultaEnEspera != null) {
                        cAgendada.setCiPaciente(consultaEnEspera.getCiPaciente());
                        System.out.println("inicio.getSigueinte==null  - Se ha cambiado el nro cedula de la consulta por: " + consultaEnEspera.getCiPaciente());
                        cancelada = true;
                    } else {
                        consultasAgendadas.borrarElemento(cAgendada);
                        System.out.println("inicio.getSigueinte==null  -Unicamente se ha borrado la consulta");
                        cancelada = true;
                    }
                }
            }

        } else {
            System.out.println("No se han encontrado consultas en este dia");
        }

        return cancelada;
    }

    public Consulta cambiarAgenda() {
        if (!consultasEnEspera.esVacia()) {
            Consulta auxC = (Consulta) consultasEnEspera.obtenerInicio().getDato();
            return auxC;
        } else {
            System.out.println("No existen consultas en espera");
            return null;
        }

    }

    public void terminarConsultasPendientes(ListaNodos listaPacientes) {

        if (!consultasAgendadas.esVacia()) {
            Nodo aux = consultasAgendadas.obtenerInicio();
            while (aux.getSiguiente() != null) {
                Consulta auxC = (Consulta) aux.getDato();
                if (auxC.getEstado().equals("Pendiente")) {
                    System.out.println("pendientes del while");
                    auxC.setFalta();
                    Paciente auxP = (Paciente) listaPacientes.obtenerElemento(auxC.getCiPaciente()).getDato();
                    auxP.noAsistencia(auxC);
                }
                aux = aux.getSiguiente();
            }
            Consulta auxC = (Consulta) aux.getDato();
            if (auxC.getEstado().equals("Pendiente")) {
                System.out.println("ultimo pendiente");
                auxC.setFalta();
                Paciente auxP = (Paciente) listaPacientes.obtenerElemento(auxC.getCiPaciente()).getDato();
                auxP.noAsistencia(auxC);
            }
        }
    }

    public boolean cambiarEstado(int ciPaciente) {
        boolean resultado = false;
        if (consultasAgendadas.existeElemento(ciPaciente)) {
            Consulta auxC = (Consulta) consultasAgendadas.obtenerElemento(ciPaciente).getDato();
            if (auxC.getEstado().equals("Pendiente")) {
                auxC.anunciarLLegada();
                resultado = true;
            }
        }
        return resultado;
    }

    public Consulta obtenerConsulta(int ciPaciente) {
        if (!consultasAgendadas.esVacia()) {
            Consulta auxC = (Consulta) consultasAgendadas.obtenerElemento(ciPaciente).getDato();
            return auxC;
        }
        return null;
    }

    public void listarConsultas() {
        listarConsultas(this.consultasAgendadas.obtenerInicio());
    }

    private void listarConsultas(Nodo obj) {
        if (obj != null) {
            System.out.println(obj.getDato().toString());
            listarConsultas(obj.getSiguiente());
        }
    }

    public void listarConsultasPendientesRec(int ciPaciente) {
        listarConsultasPendientesRec(this.consultasAgendadas.obtenerInicio(), ciPaciente);
        listarConsultasPendientesRec(this.consultasEnEspera.obtenerInicio(), ciPaciente);
    }

    private void listarConsultasPendientesRec(Nodo obj, int ciPaciente) {

        if (obj != null) {
            Consulta c = (Consulta) obj.getDato();
            if (ciPaciente==c.getCiPaciente()) {
                if (c.getEstado().equals("Pendiente")) {
                    System.out.println(obj.getDato().toString());
                    listarConsultasPendientesRec(obj.getSiguiente(), ciPaciente);
                } 
            }
             else {
                listarConsultasPendientesRec(obj.getSiguiente(), ciPaciente);
            }
        }
    }

    public void listarConsultasEnEspera() {
        if (!consultasAgendadas.esVacia()) {
            Nodo aux = consultasAgendadas.obtenerInicio();
            while (aux.getSiguiente() != null) {
                Consulta c = (Consulta) aux.getDato();
                if (c.getEstado().equals("En espera")) {
                    System.out.println(c.getCodMedico() + "-Estado: " + c.getEstado() + "-Fecha: " + c.getFecha() + "-C.I.: " + c.getCiPaciente() + "-Nro Reserva: " + c.getNroReserva());
                }
                aux = aux.getSiguiente();
            }
            Consulta c = (Consulta) aux.getDato();
            if (c.getEstado().equals("En espera")) {
                System.out.println(c.getCodMedico() + "-Estado: " + c.getEstado() + "-Fecha: " + c.getFecha() + "-C.I.: " + c.getCiPaciente() + "-Nro Reserva: " + c.getNroReserva());
            }
        }

    }

    /**
     * @return the cantConsultasCerradas
     */
    public int getCantConsultasCerradas() {
        return cantConsultasCerradas;
    }

    public void setCantConsultasCerradas() {
        this.cantConsultasCerradas = cantConsultasCerradas + 1;
    }
}

//SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); Date hoy=new
// * Date(); String dat= "25/10/2023"; System.out.println(sdf.format(hoy));
