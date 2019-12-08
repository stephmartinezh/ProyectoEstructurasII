package proyecto;

import java.util.ArrayList;

public class Registro {

    private String campoLleno;
    private ArrayList<Object> campos;
    private Object key;

    public ArrayList<Object> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Object> campos) {
        this.campos = campos;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }
    
    public Registro() {
    }

    public String getCampoLleno() {
        return campoLleno;
    }

    public void setCampoLleno(String campoLleno) {
        this.campoLleno = campoLleno;
    }

}
