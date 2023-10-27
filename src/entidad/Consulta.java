package entidad;

import java.util.Date;

public class Consulta implements Comparable<Consulta> {

    private int ciPaciente;
    private int codMedico;
    private Date fecha;
    private String estado;
    private String detalleConsulta;
    private int nroReserva;
    private static int nroReg = 1;

    public Consulta(int codMedico, int ciPaciente, Date f) {
        this.setCodMedico(codMedico);
        this.setCiPaciente(ciPaciente);
        this.setFecha(f);
        this.setEstado("Pendiente");
        this.setNroReserva(nroReg);
        nroReg++;
    }

    /**
     * @return the ciPaciente
     */
    public int getCiPaciente() {
        return ciPaciente;
    }

    /**
     * @param ciPaciente the ciPaciente to set
     */
    public void setCiPaciente(int ciPaciente) {
        this.ciPaciente = ciPaciente;
    }

    /**
     * @return the codMedico
     */
    public int getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    private void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the detalleConsulta
     */
    public String getDetalleConsulta() {
        return detalleConsulta;
    }

    /**
     * @param detalleConsulta the detalleConsulta to set
     */
    private void setDetalleConsulta(String detalleConsulta) {
        this.detalleConsulta = detalleConsulta;
    }

    /**
     * @return the nroReserva
     */
    public int getNroReserva() {
        return nroReserva;
    }

    /**
     * @param nroReserva the nroReserva to set
     */
    private void setNroReserva(int nroReserva) {
        this.nroReserva = nroReserva;
    }

    public void anunciarLLegada() {
        this.setEstado("En espera");
        System.out.println("Numero de consulta: "+this.nroReserva);
    }

    public void terminarConsulta(String detalleConsulta) {
        this.setEstado("Terminada");
        this.setDetalleConsulta(detalleConsulta);
    }

    @Override
    public int compareTo(Consulta obj) {
        return this.fecha.compareTo(obj.getFecha());
    }

    @Override
    public boolean equals(Object obj) {
        return this.codMedico + this.ciPaciente == obj.hashCode();
    }

    public void setFalta(){
        this.setEstado("No asistio");
    }
    
    @Override
    public int hashCode() {
        return this.codMedico + this.ciPaciente;
    }

//    public boolean compararFecha(Date fecha) {
//        boolean resultado = false;
//        if (this.fecha.compareTo(fecha) == 0) {
//            resultado = true;
//        }
//
//        return resultado;
//    }

}
