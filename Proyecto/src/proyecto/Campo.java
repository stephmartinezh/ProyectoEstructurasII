package proyecto;

public class Campo {
    String nombre;
    String tipo;
    int size;
    boolean key;

    public Campo(String nombre, String tipo, int size, boolean key) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.size = size;
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }
    
}