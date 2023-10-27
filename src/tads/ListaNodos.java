package tads;

import java.util.Date;

public class ListaNodos<T extends Comparable<T>> implements ILista<T> {

    private Nodo inicio;
    private Nodo fin;
    private int cantidadElementos;

    public ListaNodos() {
        inicio = null;
        fin = null;
        cantidadElementos = 0;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);

        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }

        cantidadElementos++;
    }

    @Override
    public void agregarFinal(T n) {
        Nodo aux = inicio;
        if (esVacia()) {
            agregarInicio(n);
        } else {
            Nodo nuevo = new Nodo(n);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            cantidadElementos++;
        }
    }

    @Override
    public void borrarInicio() {
        Nodo aux = inicio;
        if (esVacia()) {
            System.out.println("Lista vacia");
        } else {
            aux = aux.getSiguiente();
            Nodo borrar = inicio;
            borrar.setSiguiente(null);
            inicio = aux;
            cantidadElementos--;
        }
    }

    @Override
    public void borrarFin() {
        Nodo aux = inicio;
        if (esVacia()) {
            System.out.println("Lista vacia");
        } else if (inicio.getSiguiente() == null) {
            borrarInicio();
        } else {
            while (aux.getSiguiente().getSiguiente() != null) {

                aux = aux.getSiguiente();

            }
            aux.setSiguiente(null);
            fin = aux;
            cantidadElementos--;
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantidadElementos = 0;
    }

    @Override
    public void mostrar() {
        Nodo aux = inicio;
        if (this.cantidadElementos > 0) {
            while (aux.getSiguiente() != null) {
                System.out.println(aux.getDato().toString());
                aux = aux.getSiguiente();
            }
            System.out.println(aux.getDato().toString());
        } else {
            System.out.println("Lista vacia!");
        }

    }

    @Override
    public void borrarElemento(T n) {
        if (!esVacia()) {
            if (inicio.getDato().equals(n)) {
                this.borrarInicio();
                this.cantidadElementos--;
            } else if (fin.getDato().equals(n)) {
                this.borrarFin();
                this.cantidadElementos--;
            } else {

                Nodo aux = inicio;

                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)) {
                    aux = aux.getSiguiente();
                }

                if (aux.getSiguiente() != null) { //Encontre el elemento

                    Nodo aBorrar = aux.getSiguiente();
                    Nodo aux2 = aBorrar.getSiguiente();

                    aux.setSiguiente(aBorrar.getSiguiente());
                    aux2.setAnterior(aux);
                    aBorrar.setSiguiente(null);
                    aBorrar.setAnterior(null);
                    cantidadElementos--;
                }
            }
        }

    }

    @Override
    /*
    Pre:funciona unicamente si es una lista ya ordenada de mayor a menor
     */
    public void agregarOrd(T n) {
        /*
        
         this.compareTo(T otroObjeto);
        Si this es menor que otroObjeto, el método debe devolver un valor negativo.
        Si this es igual a otroObjeto, el método debe devolver 0.
        Si this es mayor que otroObjeto, el método debe devolver un valor positivo.
        
         */

        if (esVacia() || inicio.getDato().compareTo(n) >= 0) {
            this.agregarInicio(n);
            this.cantidadElementos++;
        } else {

            Nodo aux = inicio;

            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(n) < 0) {
                aux = aux.getSiguiente();
                
            }

            if (aux.getSiguiente() == null) {
                this.agregarFinal(n);
            } else {
                Nodo aux2 = aux.getSiguiente();
                Nodo nuevo = new Nodo(n);
                nuevo.setSiguiente(aux.getSiguiente());
                nuevo.setAnterior(aux);
                aux.setSiguiente(nuevo);
                aux2.setAnterior(nuevo);
            }

        }
        cantidadElementos++;
    }

    @Override
    public int cantElementos() {
        return cantidadElementos;
    }

    @Override
    public Nodo obtenerElemento(T n) {
        Nodo<T> aux = inicio;

        if (esVacia()) {
//            System.out.println("La lista está vacía.");
            return null;
        } else {
            if (aux.getDato().equals(n)) {
//                System.out.println(aux.getDato());
                return aux;
            } else {
                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)) {
                    aux = aux.getSiguiente();
                }

                if (aux.getSiguiente() != null) {
//                    System.out.println(aux.getSiguiente().getDato());
                    return aux.getSiguiente();
                } else {
//                    System.out.println("No se ha encontrado el nodo: " + n.toString());
                    return null;
                }
            }
        }

    }

    @Override
    public boolean existeElemento(T x) {
        boolean existe = false;

        Nodo<T> aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }

        return existe;
    }

    public Nodo obtenerInicio() {
        return inicio;
    }

   
}
