//PAQUETES
package uaslp.engineering.labs.list;

import uaslp.engineering.labs.model.Student;

public class LinkedList {
    private Node front;
    private Node tail;
    private int size;
    //CONSTANTE DE NUMERACION
    public enum InsertPosition {
        BEFORE,
        AFTER
    }
    //CLASE PARA RECORRER LA LISTA
    //NOTA: ESTA ES UNA INNER CLASS (UNA CLASE DENTRO DE UNA CLASE)
    //SUS METODOS Y ATRIBUTOS PUEDEN SER UTILIZADOS POR LOS OBJETOS DE LA CLASE PADRE (HERENCIA)
    //EN CAMBIO LA PALABRA STATIC ES PARA LA CLASE EN SI
    public class Iterator {
        //ATRIBUTO (NODO ACTUAL)
        public Node currentNode;
        //CONSTRUCTOR
        public Iterator() {
            this.currentNode = front;
        }
        //REGRESA VERDADERO O FALSO
        //PREGUNTA SI HAY UN SIGUIENTE
        public boolean hasNext() {
            return currentNode != null;
        }
        //SI NO HAY NUNGUN NODO REGRESA NULL
        public Student next() {
            if(currentNode == null){
                return null;
            }
            //SI HAY MAS NODOS, EMPEZARA A RECORRER Y A REGRESAR LOS NODOS REORRIDOS
            Student currentStudent = currentNode.getStudent();
            currentNode = currentNode.getNext();
            return currentStudent;
        }
    }

    public int size() {
        return size;
    }

    public void add(Student student) {
        Node nuevo =  new Node(student);
        //SI EL TAMANIO ES 0
        if (size == 0) {
            front = tail = nuevo;
        }else {
            //AGREGA EL NODO NUEVO EN EL FINAL DE LA LISTA
           tail.setNext(nuevo);
           //ENLAZA EL NODO NUEVO
           nuevo.setPrevious(tail);
           tail = nuevo;
        }
        //INCREMENTA EL TAMANIO
        size++;


    }

    public void delete(Student student) {
    }

    public void delete(int index) {
        //INDEX ESTE DENTRO DEL TAMANIO
        if( index < 0 || index > size) {
            return;
        }
        Node aux = front;
        for(int i = 0; i < index && i < size; i++) {
            //AUX RECORRE LA LISTA
            aux = aux.getNext();
        }
        //SI EL NODO ES EL UNICO EN LA LISTA
        if(aux == front && aux == tail) {
            //USAR EL RECOLECCTOR DE BASURA QUE LO BORRA AUTOMATICAMENTE
            front = null;
            tail = null;
        //SI ESTA INDEX EN EL FRENTE
        }else if(aux == front) {
            front = aux.getNext();
            front.setPrevious(null);
        //SI INDEX ES EL ULTIMO
        }else if(aux == tail) {
            tail = aux.getPrevious();
            tail.setNext(null);
        //SI INDEX ESTA EN MEDIO
        }else{
            //OBTIENE EL VALOR ANTERIOR DE AUX PARA ASIGNARLE EL SIGUIENTE DE AUX
            aux.getPrevious().setNext(aux.getNext());
            //OBTIENE EL VALOR DEL SIGUIENTE DEL AUX PARA ASIGNARLE EL ANTERIOR DE AUX
            aux.getNext().setPrevious(aux.getPrevious());
        }
        //DISMINUIR TAMANIO
        size--;
    }

    //COMO ES UNA CLASE SE CREO EL OBJETO ITERADOR
    public Iterator getIterator() {
        return new Iterator();
    }

    //OBTIENE EL TAMANIO
    public int getSize() {
        return size;

    }

    public Student getAt(int index) {
        Node currentNode = front;
        //RECRRE LA LISTA
        for (int counter = 0; counter < index && counter < size; counter++) {
            currentNode = currentNode.getNext();
        }
        //REGRESA EL NODO ACTUL: SI EL NODO ACTUAL ES DIFERENTE DE NULL REGRESA EL ESTUDUANTE ACTUAL SINO REGRESA NULL
        return currentNode != null ? currentNode.getStudent() : null;
    }

    public void insert(Student reference, Student newStudent, InsertPosition insertPosition) {
        //SI LA LISTA ESTA VACIO
        if (size == 0) {
            return;
        }

        Node aux = front;
        //AUX VA A RECORRER LA LISTA MIENTRAS SEA DISERENTE DE REFERENCIA
        while(!aux.getStudent().equals(reference)) {
            aux = aux.getNext();

        }
        if (aux == null) {
            return;
        }
        Node nuevo =  new Node(newStudent);
        //INSERTAR ANTES DE LA REFERENCIA
        if(InsertPosition.BEFORE.equals(insertPosition)) {
            Node previo = aux.getPrevious();
            nuevo.setNext(aux);
            nuevo.setPrevious(previo);
            aux.setPrevious(nuevo);
            //SI EL REFERENCIA ES EL PRIMERO
            if(front == aux) {
                front = nuevo;
            //SI EL REFERENCIA ES EL ULTIMO
            }else{
                previo.setNext(nuevo);
            }
        //INSERTAR DESPUES DEL REFERENCIA
        }else{
            Node siguiente = aux.getNext();
            nuevo.setPrevious(aux);
            nuevo.setNext(siguiente);
            aux.setNext(nuevo);
        //SI EL REFERENCIA ES EL ULTIMO
            if(tail == aux) {
                tail = nuevo;
             //SI EL REFERENCIA ES EL PRIMERO
            }else {
                siguiente.setPrevious(nuevo);
            }
        }
        //AUMENTAR TAMANIO
        size++;
    }
}
