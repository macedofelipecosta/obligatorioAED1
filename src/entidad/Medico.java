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
    private ListaNodos consultasAgendadas;
    private ListaNodos consultasEnEspera;
    private int cantConsultasAgendadas;
    private int consultasEsperando;

    public Medico(String nombre, int codigoMedico, int tel, int especialidad) {
        this.setNombre(nombre);
        this.setCodMedico(codigoMedico);
        this.setTel(tel);
        this.setEspecialidad(especialidad);
        this.consultasAgendadas = new ListaNodos();
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

    public int consultasFecha(Consulta obj) {
        int cantidad = 0;
        Nodo aux = consultasAgendadas.obtenerInicio();

        if (aux != null) {
            cantidad++;
            if (aux.getSiguiente() != null) {
                while (aux.getSiguiente() != null) {
                    if (aux.getDato().compareTo(obj) == 0) {
                        cantidad++;
//                        System.out.println("consultasFecha cuando al menos hay 2 elementos ,cantidad: " + cantidad);
                    }

                    aux = aux.getSiguiente();
                }
//                System.out.println("consultasFecha cuando al menos hay 2 elementos ,cantidad: " + cantidad);
            } else {
                cantidad++;
//                System.out.println("Cuando hay una consulta sola");
            }
        }

        return cantidad;
    }

    public void agregarEspera(Consulta obj) {
        if (obj != null && !comprobarEspera(obj)) {
            consultasEnEspera.agregarFinal(obj);
            this.consultasEsperando++;
            System.out.println("Se agrego el paceinte: " + obj.getCiPaciente() + " a la lista de espera del medico: " + obj.getCodMedico());
        } else {
            System.out.println("No se ha podido agregar la consulta a la lista de espera, el objeto es null o el paciente ya se encuentra en ella");
        }
    }

    public boolean comprobarEspera(Consulta obj) {
        boolean resultado = false;
        Nodo aux = consultasEnEspera.obtenerInicio();
        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == obj.getCiPaciente()) {
                resultado = true;
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == obj.getCiPaciente()) {
                        resultado = true;
                    }
                    aux = aux.getSiguiente();
                }
            }
        }
        return resultado;
    }

    public int consult() {
        return consultasAgendadas.cantElementos();
    }

    public int esp() {
        return consultasEnEspera.cantElementos();
    }

    public void agregarConsulta(Consulta obj) {
        if (obj != null) {
            consultasAgendadas.agregarFinal(obj);
            this.cantConsultasAgendadas++;
            System.out.println("Se agrego el paciente: " + obj.getCiPaciente() + "  a la lista del medico: " + obj.getCodMedico());
        } else {
            System.out.println("No se ha podido reservar la cita con el medico");
        }

    }

    public Nodo sacarEspera() {
        if (consultasEnEspera.cantElementos() > 0) {
            Nodo aux = consultasEnEspera.obtenerInicio();
            consultasEnEspera.borrarInicio();
            return aux;
        }
        return null;
    }

    public boolean estadoCerrado(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = consultasAgendadas.obtenerInicio();

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

    public boolean tieneAlgunaReserva(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = consultasAgendadas.obtenerInicio();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                resultado = true;
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        resultado = true;
                    }
                    aux = aux.getSiguiente();
                }
            }

        }

        return resultado;

    }

    public boolean tieneReservaEnDia(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = consultasAgendadas.obtenerInicio();

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

    public boolean reservaPendiente(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = consultasAgendadas.obtenerInicio();

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            if (auxConsulta.getCiPaciente() == ciPaciente) {
                if (auxConsulta.getEstado().equals("Pendiente")) {
                    resultado = true;
                }
            } else {
                while (aux.getSiguiente() != null && !resultado) {
                    auxConsulta = (Consulta) aux.getDato();
                    if (auxConsulta.getCiPaciente() == ciPaciente) {
                        if (auxConsulta.getEstado().equals("Pendiente")) {
                            resultado = true;
                        }
                    }
                    aux = aux.getSiguiente();
                }
            }

        }

        return resultado;
    }

    public void cancelarReserva(int ciPaciente) {
        Nodo espera = consultasEnEspera.obtenerInicio();
        Nodo aux = consultasAgendadas.obtenerInicio();

        boolean consultaEncontrada = false;
        boolean nroEsperaAsignado = false;
        Consulta consulta = null;
        Consulta auxEspera = null;

        if (aux != null) {
            while (!consultaEncontrada && aux.getSiguiente() != null) {
                consulta = (Consulta) aux.getDato();
                if (consulta.getCiPaciente() == ciPaciente) {

                    consultaEncontrada = true;
                    System.out.println("Dato parametro: " + ciPaciente + "Dato lista: " + consulta.getCiPaciente());
                }
                aux = aux.getSiguiente();
            }
        }
        while (espera != null && !nroEsperaAsignado) {
            auxEspera = (Consulta) espera.getDato();
//            System.out.println("Fecha en espera: " + auxEspera.getFecha());
//            System.out.println("Fecha consulta: " + consulta.getFecha());
            if (auxEspera.getFecha().equals(consulta.getFecha())) { //consulta puede ser null
                consulta.setCiPaciente(auxEspera.getCiPaciente());
                nroEsperaAsignado = true;
                System.out.println("La cedula asignada a esta consulta es : " + auxEspera.getCiPaciente());
                consultasEnEspera.borrarElemento(auxEspera);
                System.out.println("Se ha borrado la consulta y se le ha asignado a un paciente en espera");
            }
            espera = espera.getSiguiente();
        }
    }

    public boolean anunciarLlegadaPaciente(int ciPaciente) {
        boolean resultado = false;
        Nodo aux = consultasAgendadas.obtenerInicio();

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

        Nodo aux = consultasAgendadas.obtenerInicio();

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
        Nodo aux = consultasAgendadas.obtenerInicio();

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
        Nodo aux = consultasAgendadas.obtenerInicio();

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
        Nodo aux = consultasAgendadas.obtenerInicio();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (aux != null) {
            Consulta auxConsulta = (Consulta) aux.getDato();
            while (aux.getSiguiente() != null) {
                if (sdf.format(f).equals(sdf.format(auxConsulta.getFecha()))) {
                    if (auxConsulta.getEstado().equals("Pendiente")) {
                        auxConsulta.setFalta();
                    }
                }
                aux = aux.getSiguiente();
            }
            if (sdf.format(f).equals(sdf.format(auxConsulta.getFecha()))) {
                if (auxConsulta.getEstado().equals("Pendiente")) {
                    auxConsulta.setFalta();
                }
            }
        }
        return resultado;
    }
}

/**
 * SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); Date hoy=new
 * Date(); String dat= "25/10/2023"; System.out.println(sdf.format(hoy));
 * System.out.println(sdf.format(hoy).equals(dat));
 *
 *
 */
