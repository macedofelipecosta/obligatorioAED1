package tads;

public class Cola<T extends Comparable<T>> implements ICola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int cantidad;

    public Cola() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    @Override
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo(dato);
        if (isEmpty()) {
            primero = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
        }
        ultimo = nuevoNodo;
        cantidad++;
    }

    @Override
    public T desencolar() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = primero.getDato();
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        cantidad--;
        return dato;
    }

    @Override
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return primero.getDato();
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
    }

    @Override
    public void vaciar() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    @Override
    public int cantidadNodos() {
        return cantidad;
    }

    public boolean existeElemento(int dato) {
       if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }

        Nodo<T> actual = primero;
        for (int i = 0; i < cantidad; i++) {
            // Asumiendo que tu Nodo tiene un método getDato() y devuelve un tipo que incluye 'int'
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente(); // Suponiendo que hay un método para obtener el siguiente nodo
        }

        return false;
    }

}
