package entidad;

import tads.*;

public class Paciente implements Comparable<Paciente> {

    private String Nombre;
    private int CI;
    private String Direccion;
    private int cantHistoriaClinica;
    private ListaNodos historialClinico;

    public Paciente(String nombre, int ci, String direccion) {
        this.setNombre(nombre);
        this.setCI(ci);
        this.setDireccion(direccion);
        this.historialClinico = new ListaNodos();

        cantHistoriaClinica = 0;
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
     * @return the CI
     */
    public int getCI() {
        return CI;
    }

    /**
     * @param CI the CI to set
     */
    public void setCI(int CI) {
        this.CI = CI;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getCI() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.getCI();
    }

    @Override
    public int compareTo(Paciente obj) {
        return this.getNombre().compareTo(obj.getNombre());
    }

    @Override
    public String toString() {
        return this.Nombre + "-" + this.CI + "-" + this.Direccion;
    }

    public boolean tieneHistoriaMedica() {
        return this.cantHistoriaClinica != 0;
    }

    public int cantHistoria() {
        return this.cantHistoriaClinica;
    }

    public void aumentarHistoriaClinica() {
        this.cantHistoriaClinica++;
    }

    public boolean terminarConsultaPaciente(Consulta nueva) {
        boolean resultado = false;
        if (nueva != null) {
            if (nueva.getCiPaciente() == this.CI) {
                this.historialClinico.agregarInicio(nueva);
                this.cantHistoriaClinica++;
                resultado = true;
            } else {
                System.out.println("La cedula de la consulta no coincide con la del paciente");
            }
        } else {
            System.out.println("Hubo un error inesperado!");
        }
        return resultado;
    }

    public void noAsistencia(Consulta obj) {

        if (obj != null) {
            if (obj.getCiPaciente() == this.CI) {
                this.historialClinico.agregarInicio(obj);
                this.cantHistoriaClinica++;
                System.out.println("se agrego la consulta al historial medico del paciente");
            } else {
                System.out.println("La cedula de la consulta no coincide con la del paciente");
            }
        } else {
            System.out.println("Hubo un error inesperado!");
        }

    }
}
