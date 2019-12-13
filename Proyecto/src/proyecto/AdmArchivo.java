package proyecto;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdmArchivo {

    private int contador_de_registros;
    private ArrayList<Campo> campos = new ArrayList();
    private ArrayList<Integer> bytes = new ArrayList();
    private Registro registro;
    private File archivo = null;
    private File archivo_reg = null;
    private Btree arbol;

    public AdmArchivo(String path) {
        archivo = new File(path);
        registro = new Registro();
        archivo_reg = new File(archivo.getParent() + "\\reg_" + archivo.getName());//me crea un archivo de registros en el mismo sitio de la metadata
        arbol = new Btree(5);
    }

    public Btree getArbol() {
        return arbol;
    }

    public void setArbol(Btree arbol) {
        this.arbol = arbol;
    }

    public ArrayList<Integer> getBytes() {
        return bytes;
    }

    public void setBytes(ArrayList<Integer> bytes) {
        this.bytes = bytes;
    }

    public int getContador_de_registros() {
        return contador_de_registros;
    }

    public void setContador_de_registros(int contador_de_registros) {
        this.contador_de_registros = contador_de_registros;
    }

    public void actualizar() {//actualiza el contador de registros en el byte 2000 del archivo metadata
        try {
            //campos + contador de registros
            RandomAccessFile write_contador_registros = new RandomAccessFile(archivo, "rw");
            //byte 2000
            write_contador_registros.seek(2000);
            write_contador_registros.writeInt(contador_de_registros);
            write_contador_registros.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdmArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdmArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
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

    public void setCampo(Campo a) {
        campos.add(a);
    }

    public void read_contadador_registro_from_file() throws FileNotFoundException, IOException {//registro actual - como en un arreglo - 2 significaria que existen 3 registros
        RandomAccessFile get_int = new RandomAccessFile(archivo, "rw");
        get_int.seek(2000);
        //leeo el entero en el archivo de metadata pos 2000
        //if ((contador_de_registros = get_int.readInt()) != ) {
        contador_de_registros = get_int.readInt();
        //}
    }

    //for obj
    public void write_obj_registro() {//escribe un objeto registro al final del archivo de registros
        try {
            File filename = new File(archivo_reg.getPath());
            ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(filename, true));
            escribir.writeObject(registro);
            escribir.close();

            //escribir en reg para prueba de tamaño
            File filetest = new File("reg.bin");
            ObjectOutputStream escribir_test = new ObjectOutputStream(new FileOutputStream(filetest));
            escribir_test.writeObject(registro);

            escribir_test.close();

        } catch (IOException e) {

        }
    }

    public void write_arbol() {//escribe el arbol en un archivo
        try {
            File filename = new File(archivo.getParent() + "\\arbol_" + archivo.getName());
            ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(filename));
            escribir.writeObject(arbol);
            escribir.close();

        } catch (IOException e) {

        }
    }

    public void read_arbol() throws ClassNotFoundException {//lee el arbol de un archivo
        File filename = new File(archivo.getParent() + "\\arbol_" + archivo.getName());
        try {
            ObjectInputStream leer = new ObjectInputStream(new FileInputStream(filename));
            arbol = (Btree) leer.readObject();
            leer.close();

        } catch (IOException e) {

        }
    }

    public void read_obj_registro() throws ClassNotFoundException {//lee un objeto registro
        File filename = new File("reg.bin");
        try {
            ObjectInputStream leer = new ObjectInputStream(new FileInputStream(filename));
            registro = (Registro) leer.readObject();
            leer.close();

        } catch (IOException e) {

        }
    }

    public void write_registro_in_bytes(int pos_in_archivo) {//sobreescribe un registro en el archivo de registros

        try {

            RandomAccessFile escribir = new RandomAccessFile(archivo_reg.getPath(), "rw");

            //nos posicionamos en el archivo
            escribir.seek(pos_in_archivo);

            //escribir el registro
            for (int i = 0; i < bytes.size(); i++) {
                escribir.write(bytes.get(i));
            }
            escribir.close();

        } catch (IOException e) {

        }
    }

    public void write_registro_innewfile() {//escribe un registro temporal  -           nombre archivo:reg.bin                 directorio:raiz
        
        
        
        try {
            File filename=new File("reg.bin");
            FileOutputStream escribir=new FileOutputStream(filename);
            for (int i = 0; i < bytes.size(); i++) {
                escribir.write(bytes.get(i));
            }
            bytes.clear();//limpiamos el arraylist
            escribir.close();
            /*        
            RandomAccessFile escribir = new RandomAccessFile(filename, "rw");
            
            //escribir el registro
            for (int i = 0; i < bytes.size(); i++) {
                escribir.write(bytes.get(i));
            }
            
            escribir.close();
            */        
        } catch (IOException e) {

        }
    }

    public void read_registro_in_bytes(int pos_in_archivo, int tamaño_registro_enbytes) throws ClassNotFoundException {//lee un registro del archivo de registros en bytes

        File filename = new File(archivo_reg.getPath());

        try {

            RandomAccessFile r = new RandomAccessFile(filename, "rw");

            //nos posicionamos en el archivo
            r.seek(pos_in_archivo*tamaño_registro_enbytes);

            //leemos byte a byte
            for (int i = 0; i < tamaño_registro_enbytes; i++) {
                bytes.add(r.read());
            }
            
            r.close();

        } catch (IOException e) {

        }
    }

    //read and write metadata de campos
    public void write() {
        try {
            File filename = new File(archivo.getPath());
            ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(filename));
            escribir.writeObject(campos);
            escribir.close();

        } catch (IOException e) {

        }
    }

    public void read() throws ClassNotFoundException {
        File filename = new File(archivo.getPath());
        try {
            ObjectInputStream leer = new ObjectInputStream(new FileInputStream(filename));
            campos = (ArrayList<Campo>) leer.readObject();
            leer.close();

        } catch (IOException e) {

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

    public void WriteHead(int head) {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            bw.writeObject(head);
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

    /*
    private ArrayList<Campo> campos = new ArrayList();
    private ArrayList<Registro> registros = new ArrayList();
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

    public void setCampo(Campo a) {
        campos.add(a);
    }
    
    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }


    public void write() {
        try {
            File filename = new File(archivo.getPath());
            ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(filename));
            escribir.writeObject(campos);
            escribir.close();

        } catch (IOException e) {

        }
    }

    public void read() throws ClassNotFoundException {
        File filename = new File(archivo.getPath());
        try {
            ObjectInputStream leer = new ObjectInputStream(new FileInputStream(filename));
            campos = (ArrayList<Campo>) leer.readObject();
            leer.close();

        } catch (IOException e) {

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
            //for (Campo t : campos) {
              //  bw.writeObject(t);
            //}
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
     */
}
