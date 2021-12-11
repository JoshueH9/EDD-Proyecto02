package fciencias.edatos.proyecto02;  

/**
* Implementación de una cola.
* @author Pintor Muñoz Pedro Joshue - 420053796
* @author Ortiz Castañeda José Ramón - 318357115
* @version 1.0 21 de octubre de 2021
* @since Laboratorio de Estructuras de Datos 2022-1.
*/
public class Queue<T> implements TDAQueue<T>{

    // Esta es una lista doble
    private DoubleLinkedList<T> lista = new DoubleLinkedList<T>();

    @Override
    public void clear(){
        lista.clear();
    }

    @Override
    public T dequeue(){
        if(lista.isEmpty()){
            return null;
        }else{
            return lista.remove(0);
        }
    }

    @Override
    public void enqueue(T e){
        lista.add(lista.size()-1, e);
        //size++;        
    }

    @Override
	public T first(){
        return lista.get(0);
    }

    @Override
    public boolean isEmpty(){
        return lista.isEmpty();
    }

    @Override
    public String toString(){
        return lista.toString();    
    }

    /**
     * Método que regresa la longitud de la cola
     * @return La longitud de la lista.
     * */
    /*public int size(){
        return size;
    }*/
}
