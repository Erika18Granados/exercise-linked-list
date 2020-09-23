package uaslp.engineering.labs.list;

public class Node {
    public Student student;
    //SOBRECARGA
    public Node previous;
    public Node Next;

    public void setStudent(Student student) { //CAMBIAR EL VALOR DE STUDENT DE LA CLASE
        this.student = student;
    }
    public Student getStudent() { //OBTENER STUDENT
        return student;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    public Node getPrevious() {
        return previous;
    }
    public void setNext(Node next) {
        this.Next = next;
    }
    public Node getNext() {
        return Next;
    }
}
