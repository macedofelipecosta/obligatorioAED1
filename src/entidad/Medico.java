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
    private final ListaNodos fechasAgendadas; // adentro de estas van haber fechas con lista de consultas
    private int cantConsultasAgendadas;
    private int consultasEsperando;

    public Medico(String nombre, int codigoMedico, int tel, int especialidad) {
        this.setNombre(nombre);
        this.setCodMedico(codigoMedico);
        this.setTel(tel);
        this.setEspecialidad(especialidad);
        this.fechasAgendadas = new ListaNodos();
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

    /**
     *
     * @param obj
     * @return
     */
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
                    System.out.println("Result= true, consulta eliminada sin aux.getSigueinte-->sout de Medico");

                }
            }
        }
        return result;
    }

    public boolean anunciarLlegadaPaciente(int ciPaciente) {
        boolean resultado = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaHoy = new Date();
        String fecha = sdf.format(fechaHoy);

        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerInicio();
            while (aux.getSiguiente() != null && !resultado) {
                Fecha auxF = (Fecha) aux.getDato();
                String fechaConsulta = sdf.format(auxF.getDato());
                if (fecha.equals(fechaConsulta)) {
                    if (auxF.pacienteConsultaPendiente(ciPaciente)) {
                        auxF.cambiarEstado(ciPaciente);
                        resultado = true;
                        System.out.println("Nombre medico :" + this.getNombre());
                    }
                }
                aux = aux.getSiguiente();
            }
            if (aux.getSiguiente() == null) {
                Fecha auxF = (Fecha) aux.getDato();
                String fechaConsulta = sdf.format(auxF.getDato());
                if (fecha.equals(fechaConsulta)) {
                    if (auxF.pacienteConsultaPendiente(ciPaciente)) {
                        auxF.cambiarEstado(ciPaciente);
                        resultado = true;
                        System.out.println("Nombre medico :" + this.getNombre());
                    }
                }
            }
        }

        return resultado;
    }

    public boolean tieneReservaEnDia(int ciPaciente) {
        boolean resultado = false;
        Date hoy = new Date();

        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerElemento(hoy);
            if (aux != null) {
                Fecha auxF = (Fecha) aux.getDato();
                if (auxF.pacienteConsultaPendiente(ciPaciente)) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    public boolean consultaEnEspera(int ciPaciente) {
        boolean resultado = false;

        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerInicio();
            
                while (aux.getSiguiente() != null && !resultado) {
                    Fecha auxF = (Fecha) aux.getDato();
                    if (auxF.pacienteConsultaEspera(ciPaciente)) {
                        resultado = true;
                    }
                    aux = aux.getSiguiente();
                }
             if (aux.getSiguiente() == null)  {
                Fecha auxF = (Fecha) aux.getDato();
                if (auxF.pacienteConsultaEspera(ciPaciente)) {
                    resultado = true;
                }
            }
        }

        return resultado;
    }

    public Consulta terminarConsultaMedico(int ciPaciente, String detalle) {
        Consulta retorno = null;
        boolean resultado = false;

        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerInicio();
                while (aux.getSiguiente() != null && !resultado) {
                    Fecha auxF = (Fecha) aux.getDato();
                    if (auxF.pacienteConsultaEspera(ciPaciente)) {
                        retorno = auxF.obtenerConsulta(ciPaciente);
                        retorno.terminarConsulta(detalle);
//                        System.out.println(retorno.getEstado() + retorno.getDetalleConsulta());
                        resultado = true;
                    }
                    aux = aux.getSiguiente();
                }
             if (aux.getSiguiente() == null) {
                Fecha auxF = (Fecha) aux.getDato();
                if (auxF.pacienteConsultaEspera(ciPaciente)) {
                    retorno = auxF.obtenerConsulta(ciPaciente);
                    retorno.terminarConsulta(detalle);
//                    System.out.println(retorno.getEstado() + retorno.getDetalleConsulta());
                }
            }
        }
        return retorno;
    }

    public boolean consultasEnFecha(Date f) {
        boolean respuesta = false;
        if (!fechasAgendadas.esVacia()) {
            Nodo aux = fechasAgendadas.obtenerElemento(f);
            if (aux != null) {
                Fecha auxF = (Fecha) aux.getDato();
                if (auxF.getCantConsultasAgendadas() > 0) {
                    respuesta = true;
                }
            }
        }
        return respuesta;
    }

    public void cerrarPacientesAusentes(Date f, ListaNodos listaPacientes) {
        if (!fechasAgendadas.esVacia()) {
            Fecha auxF = (Fecha) fechasAgendadas.obtenerElemento(f).getDato();
            auxF.terminarConsultasPendientes(listaPacientes);
        }
    }

    public void listarConsultas() {
        listarConsultasDiaRec(this.fechasAgendadas.obtenerInicio());
    }

    public void listarConsultasDiaRec(Nodo obj) {
        if (obj != null) {
            System.out.println(obj.getDato().toString());
            Fecha f = (Fecha) obj.getDato(); //lo unico que no se como manejar es esto
            f.listarConsultas();
            listarConsultasDiaRec(obj.getSiguiente());
        }
    }

    public void consultasEnEspera(Date fecha) {
        if (!fechasAgendadas.esVacia()) {

            Nodo aux = this.fechasAgendadas.obtenerElemento(fecha);
            if (aux != null) {
                Fecha auxF = (Fecha) aux.getDato();
                auxF.listarConsultasEnEspera();
            }

        }
    }

}

//
/**
 * SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); Date hoy=new
 * Date(); String dat= "25/10/2023"; System.out.println(sdf.format(hoy));
 * System.out.println(sdf.format(hoy).equals(dat));
 *
 *
 */
