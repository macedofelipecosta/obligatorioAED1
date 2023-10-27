package tads;


public interface ILista<T> {

    public boolean esVacia();

    public void agregarInicio(T n);

    public void agregarFinal(T n);

    public void borrarInicio();

    public void borrarFin();

    public void vaciar();

    public void mostrar();

    public void borrarElemento(T n);
    
    public void agregarOrd(T n);
    
    public int cantElementos();
    
    public Nodo obtenerElemento(T n);
    //Pos: Retorna true sii el dato pasado como parámetro pertenece a la lista.
    public boolean existeElemento (T x);
    
}
