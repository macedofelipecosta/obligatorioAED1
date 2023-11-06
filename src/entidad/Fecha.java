/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.text.SimpleDateFormat;
import java.util.Date;
import tads.Cola;
import tads.ListaNodos;
import tads.Nodo;

/**
 *
 * @author felip
 */
public class Fecha implements Comparable<Fecha> {

    private Date dato;
    private ListaNodos consultasAgendadas;
    private ListaNodos consultasEnEspera; //puede ser una lista cola?
    private int cantConsultasAgendadas;

    public Fecha(Date d) {
        this.dato = d;
        this.consultasAgendadas = new ListaNodos();
        this.consultasEnEspera = new ListaNodos();
        this.cantConsultasAgendadas = 0;
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
                System.out.println("Existe elemento consulta ci dentro de agendadas y esta en estado pendiente");
            }
        }
        if (consultasEnEspera.existeElemento(ciPaciente)) {
                resultado = true;
                System.out.println("Existe elemento consulta ci dentro de espera y esta en estado pendiente");        
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
        boolean existeElemento = false;
        Nodo agendada = consultasAgendadas.obtenerInicio();
        Nodo espera = consultasEnEspera.obtenerInicio();
        Consulta c = null;

        if (!consultasEnEspera.esVacia()) {
            c = (Consulta) consultasEnEspera.obtenerInicio().getDato();
        } else {
            System.out.println("No existen consultas en espera");           
        }
        
        
        if (agendada != null) {
            while (agendada!=null && agendada.getSiguiente() != null && !existeElemento) {
                Consulta auxC = (Consulta) agendada.getDato();
                if (auxC.equals(ciPaciente)) {
                    existeElemento = true;
                    System.out.println("Existe elemento el lista de agendas");
                    if (c != null) {
                        auxC.setCiPaciente(c.getCiPaciente());
                        consultasEnEspera.borrarElemento(c);
                    } else {
                        consultasAgendadas.borrarElemento(auxC);
                    }
                    System.out.println("Elemento eliminado de la lista de agendados");
                }
               
                   agendada = agendada.getSiguiente(); 
            }
        }
        if (espera != null && !existeElemento) {

            while (espera.getSiguiente() != null && !existeElemento) {

                if (espera.getDato().equals(ciPaciente)) {
                    existeElemento = true;
                    System.out.println("existe elemento en lista de espera");
                    Consulta auxC = (Consulta) espera.getDato();
                    consultasEnEspera.borrarElemento(auxC);
                    System.out.println("Elemento eliminado de la lista de espera");
                }
                espera = espera.getSiguiente();
            }

        }
        return existeElemento;
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
        if (!consultasAgendadas.esVacia()) {
            Nodo aux = consultasAgendadas.obtenerInicio();
            while (aux.getSiguiente() != null) {
                Consulta c = (Consulta) aux.getDato();
                System.out.println(c.getCodMedico() + " " + c.getEstado() + " " + c.getFecha() + " " + c.getCiPaciente());
                aux = aux.getSiguiente();
            }
            Consulta c = (Consulta) aux.getDato();
            System.out.println(c.getCodMedico() + " " + c.getEstado() + " " + c.getFecha() + " " + c.getCiPaciente());
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
}

//SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); Date hoy=new
// * Date(); String dat= "25/10/2023"; System.out.println(sdf.format(hoy));
