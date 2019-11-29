package proyecto;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdmArchivo {
    private ArrayList<Campo> campos = new ArrayList();
    private File archivo = null;

    public AdmArchivo(String path) {
        archivo = new File(path);
    }
    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> registros) {
        this.campos = registros;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }
    public void setCampo(Campo a){
        campos.add(a);
    }
    
    public void write(){
        try{
            File filename=new File(archivo.getPath());
            ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(filename));
            escribir.writeObject(campos);
            escribir.close();
            

        }catch(IOException e){

        }
    }
    
    public void read() throws ClassNotFoundException{
        File filename=new File(archivo.getPath());
        try{
            ObjectInputStream leer=new ObjectInputStream(new FileInputStream(filename));
            campos=(ArrayList<Campo>)leer.readObject();
            leer.close();
            
            
        }catch(IOException e){
            
        }
    }
    
    public void WriteA() {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (Campo t : campos) {
                bw.writeObject(t);
            }
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }
        public void WriteB(String cadena) {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            /*for (Campo t : campos) {
                bw.writeObject(t);
            }*/
            bw.writeChars(cadena);
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }
    
    public void cargarArchivo() {
        try {            
            campos = new ArrayList();
            Campo temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Campo) objeto.readObject()) != null) {
                        campos.add(temp);
                    }
                } catch (EOFException e) {
                    //encontro el final del archivo
                }
                objeto.close();
                
                entrada.close();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
