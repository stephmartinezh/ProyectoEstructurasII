package proyecto;

import java.util.ArrayList;

public class Registro {
    int RRN;//posicion en el archivo
    ArrayList<String> data=new ArrayList<>();//contenido del registro

    public Registro(int RRN) {
        this.RRN = RRN;
    }

    public Registro() {
    }

    
    
    
    public int getRRN() {
        return RRN;
    }

    public void setRRN(int RRN) {
        this.RRN = RRN;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
    /*
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
    */
}
