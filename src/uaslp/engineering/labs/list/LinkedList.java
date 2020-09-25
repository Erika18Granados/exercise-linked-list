package uaslp.engineering.labs.list;

import uaslp.engineering.labs.model.Student;

public class LinkedList {
    private Node front;
    private Node tail;
    private int size;

    public enum InsertPosition {
        BEFORE,
        AFTER
    }

    public class Iterator {
        public Node currentNode;
        public Iterator() {
            this.currentNode = front;
        }
        public boolean hasNext() {
            return currentNode != null;
        }
        public Student next() {
            if(currentNode == null){
                return null;
            }
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

        if (size == 0) {
            front = tail = nuevo;
        }else {
           tail.setNext(nuevo);
           nuevo.setPrevious(tail);
           tail = nuevo;
        }
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

    public Iterator getIterator() {
        return new Iterator();
    }

    public int getSize() {
        return size;

    }

    public Student getAt(int index) {
        Node currentNode = front;
        for (int counter = 0; counter < index && counter < size; counter++) {
            currentNode = currentNode.getNext();
        }
        return currentNode != null ? currentNode.getStudent() : null;
    }

    public void insert(Student reference, Student newStudent, InsertPosition insertPosition) {
        //SI LA LISTA ESTA VACIO
        if (size == 0) {
            return;
        }

        Node aux = front;
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
