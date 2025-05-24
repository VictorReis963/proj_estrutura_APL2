package apl2;

public class Node {
    private String id;
    private String nome;
    private float nota;
    private Node next;
    private Node previous;

    public Node() {
        this.id = "";
        this.nome = "";
        this.nota = 99.9f;
        this.next = null;
        this.previous = null;
    }

    public Node(String id, String nome, float nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.next = null;
        this.previous = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "(" + id + "; " + nome + "; " + nota + ")";
    }
}