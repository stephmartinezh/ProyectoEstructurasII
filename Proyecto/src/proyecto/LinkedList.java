/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author Daniel RC
 */
public class LinkedList {

    Nodo inicio = null;
    int size = 0;

    public void inserta(int dato, int posicion) {
        Nodo tmp = inicio;
        Nodo newNode = new Nodo();
        newNode.setData(dato);

        if (inicio == null) {
            inicio = newNode;
            size = size + 1;
            System.out.println("Se agrego el elemento exitosamente al primer nodo");
        } else if (posicion == 1) {
            tmp.setAnterior(newNode);
            newNode.setSiguiente(tmp);
            size = size + 1;
            System.out.println("Se agrego el elemento exitosamente en la posicion 1");
        } else if (posicion > 1 && posicion < size) {
            for (int i = 0; i < posicion - 1; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getSiguiente().setAnterior(newNode);
            newNode.setSiguiente(tmp.getSiguiente());
            newNode.setAnterior(tmp);
            tmp.setSiguiente(newNode);
            size = size + 1;
            System.out.println("Se agrego el elemento exitosamente en la posicion: " + posicion);
        } else if (posicion == size + 1) {
            Nodo temporal = new Nodo();
            temporal.setData(dato);
            temporal.setSiguiente(temporal);
            for (int i = 0; i < posicion - 1; i++) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(newNode);
            newNode.setAnterior(temporal);
            size = size + 1;
            System.out.println("Se agrego el elemento exitosamente en la posicion " + posicion);
        } else {
            System.out.println("No se agrego el elemento, Ingrese una posicion valida");
        }
    }

    //Elimina el elemento en la posicion ingresada
    public void borrarElemento(int posicion) {
        Nodo tmp = new Nodo();
        if (posicion == 1) {
            if (size > 1) {
                tmp = inicio.getSiguiente();
                tmp.getAnterior().setSiguiente(null);
                tmp.setAnterior(null);
                inicio = tmp;
            } else {
                inicio = null;
            }
            size = size - 1;
        } else if (posicion > 1 && posicion < size) {
            tmp = inicio;
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getSiguiente().setAnterior(tmp.getAnterior());
            tmp.getAnterior().setSiguiente(tmp.getSiguiente());
            tmp.setSiguiente(null);
            tmp.setAnterior(null);
            size = size - 1;
        } else if (posicion == size) {
            for (int i = 0; i < size; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getAnterior().setSiguiente(null);
            tmp.setAnterior(null);
            size = size - 1;
        } else {

        }
    }

    //Devuelve la posicion del dato a buscar
    public int buscar(int dato) {
        Nodo tmp = inicio;
        int pos = 0;
        for (int i = 0; i < size; i++) {
            if (tmp.getData() == dato) {
                pos = i;
            } else {
                tmp = tmp.getSiguiente();

            }
        }
        return pos;
    }

    //Verifica si la lista esta vacia
    public boolean vacia() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    //Devuelve el dato en la posicion mandada
    public Object elementoPosicion(int posicion) {
        if (posicion >= 1 && posicion <= size) {
            Nodo tmp = inicio;
            for (int i = 2; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData();
        } else {
            System.out.println("Ingreso una posicion incorrecta.");
            return null;
        }
    }

    //Devuelve el dato siguiente a la posicion ingresada
    public Object obtenerSiguiente(int posicion) {
        if (posicion >= 1 && posicion < size) {
            Nodo tmp = inicio;
            for (int i = 2; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData();
        } else {
            System.out.println("Ingreso una posicion incorrecta.");
            return null;
        }
    }

    //Devuelve el dato anterior a la posicion ingresada
    public Object obtenerAnterior(int posicion) {
        if (posicion > 1 && posicion <= size) {
            Nodo tmp = inicio;
            for (int i = 2; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData();
        } else {
            System.out.println("Ingreso una posicion incorrecta.");
            return null;
        }
    }

    //Vacia la lista
    public void anula() {
        if (inicio != null) {
            inicio = null;
            System.out.println("La lista se vacio correctamente.");
        } else {
            System.out.println("La lista esta vacia.");
        }
    }
}
