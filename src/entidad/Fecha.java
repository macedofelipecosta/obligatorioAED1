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

//    /**
//     */
//    public void setCantConsultasAgendadas() {
//        this.cantConsultasAgendadas++;
//    }
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

    public void agregarConsulta(Consulta obj) {
        if (obj != null) {
            boolean existe = consultasAgendadas.existeElemento(obj);
            if (!existe) {
                consultasAgendadas.agregarOrd(obj);
                this.cantConsultasAgendadas++;
            } else {
                System.out.println("El paciente ya tiene una consulta agendada!");
            }
        }
    }

    public int cantidadConsultas() {
        return this.cantConsultasAgendadas;
    }

    public boolean pacienteConsulta(int ciPaciente) {
        boolean resultado = false;

        if (consultasAgendadas.existeElemento(ciPaciente)) {
            resultado = true;
            System.out.println("Existe elemento consulta ci dentro de agendadas");
        }
        if (consultasEnEspera.existeElemento(ciPaciente)) {
            resultado = true;
            System.out.println("Existe elemento consulta ci dentro de espera");
        }

        return resultado;
    }

    public boolean cancelarReservaFecha(int ciPaciente) {
        boolean existeElemento = false;
        Nodo aux = consultasAgendadas.obtenerInicio();
        Nodo espera = consultasEnEspera.obtenerInicio();

        if (aux != null) {
            while (aux.getSiguiente() != null && !existeElemento) {
                Consulta auxC = (Consulta) aux.getDato();
                if (auxC.equals(ciPaciente)) {
                    existeElemento = true;
                    Consulta c = (Consulta) aux.getDato();
                    consultasAgendadas.borrarElemento(c);
                    System.out.println("Elemento eliminado de la lista de agendados");
                }
                aux = aux.getSiguiente();
            }
        }
        if (espera != null && !existeElemento) {

            while (espera.getSiguiente() != null && !existeElemento) {

                if (espera.getDato().equals(ciPaciente)) {
                    existeElemento = true;
                    Consulta c = (Consulta) espera.getDato();
                    consultasEnEspera.borrarElemento(c);
                    System.out.println("Elemento eliminado de la lista de espera");
                }
                espera = espera.getSiguiente();
            }

        }
        return existeElemento;
    }

}

//SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); Date hoy=new
// * Date(); String dat= "25/10/2023"; System.out.println(sdf.format(hoy));
