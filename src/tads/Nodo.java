package tads;

public final class Nodo<T extends Comparable> {

    private T dato;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    public Nodo(T Dato) {
        this.setDato(Dato);
        this.setSiguiente(null);
        this.setAnterior(null);
    }

    /**
     * @return the dato
     */
    public T getDato() {
        return dato;
    }

    /**
     * @param Dato the dato to set
     */
    public void setDato(T Dato) {
        this.dato = Dato;
    }

    /**
     * @return the siguiente
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public Nodo<T> getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return this.dato.toString();
    }
}
