package CLASES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author termi
 */
public class Usuario {

 private String Cedula;
 private String password;
 private String nombre1;
 private String nombre2;
 private String apellido1;
 private String apellido2;
 private String dia;
 private String mes;
 private String año;
 private String direccion;
 private String telefono;
 private String ruta = "C:\\proyecto\\";
 private String usuarios = "usuario.txt";

 private final String separador = "|";
 
 public Usuario(){} 
 public Usuario(String Cedula,String password, String nombre1, String nombre2, String apellido1, String apellido2, String dia, String mes, String año, String direccion,String telefono){
 this.Cedula = Cedula;
 this.password = password;
 this.nombre1= nombre1;
 this.nombre2= nombre2;
 this.apellido1= apellido1;
 this.apellido2= apellido2;
 this.dia = dia;
 this.mes = mes;
 this.año = año;
 this.direccion = direccion;
 this.telefono  = telefono; 
 }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre1() {
        return nombre1;
    }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
 
    
    //METODO DE INSERTAR/////
     public boolean CrearUsuario(){
    File fileRuta = new File(ruta);
    if(!fileRuta.exists())
        fileRuta.mkdir();
    try{
    FileWriter fw = new FileWriter(ruta+usuarios , true);
    PrintWriter pw = new PrintWriter(fw);
    pw.println(this.Cedula +this.separador+
            this.password +this.separador+
            this.nombre1+this.separador+
            this.nombre2+this.separador+
            this.apellido1+this.separador+
            this.apellido2+this.separador+
            this.dia+"/"+this.mes+"/"+this.año+this.separador+
            this.direccion+this.separador+
            this.telefono );
    pw.close();
    fw.close();
    return true ;
    }
    catch(IOException e){
    return false;
    }
    }
    //FIN DEL METODO INSERTAR///// 
    //METODO BUSCAR//// 
    public boolean Buscar(){
    File fileRuta = new File(ruta);
    if(!fileRuta.exists())
        fileRuta.mkdir();
    File fl = new File(ruta +usuarios);
    
    try{
    Scanner read = new Scanner(fl);
        while (read.hasNextLine()) {
        String [] linea = read.nextLine().split("\\|");
        if(linea[0].equals(this.Cedula) ){
            this.Cedula    = linea[0];
            this.password  = linea[1];
            this.nombre1   = linea[2];
            this.nombre2   = linea[3];
            this.apellido1 = linea[4];
            this.apellido2 = linea[5];
            this.dia       = linea[6];
            this.mes       = linea[6];
            this.año       = linea[6];
            this.direccion = linea[7];
            this.telefono  = linea[8];
            read.close();
            return true;    
        }else
            JOptionPane.showMessageDialog(null, "No se encontró su Usuario.\nLo debe Crear o Digitar el Correcto");
        }
        read.close();
        return false;
    }catch(FileNotFoundException e){
    return false;
    }
    }
// FIN DEL METODO BUSCAR   
    //METODO DE MODIFICAR EL USUARIO
    public boolean ModificarUsuario(){
    File fileRuta = new File(ruta);
    if(!fileRuta.exists())
        fileRuta.mkdir();
    File fl = new File(ruta +  usuarios);
    
    try{
    FileWriter fw = new FileWriter(ruta + usuarios + ".tmp");
    PrintWriter pw = new PrintWriter(fw);
        try{
        Scanner read = new Scanner(fl);
        while(read.hasNextLine()){
        String  linea = read.nextLine()/*.split("\\|")*/;
        String [] arr = linea.split("\\|");
        if(arr[0].equals(this.Cedula))
                pw.println(this.Cedula +this.separador+
            this.password +this.separador+
            this.nombre1+this.separador+
            this.nombre2+this.separador+
            this.apellido1+this.separador+
            this.apellido2+this.separador+
            this.dia+"/"+this.mes+"/"+this.año+this.separador+
            this.direccion+this.separador+
            this.telefono );
        else{ 
            pw.println(linea);}
        }
        pw.close();
        fw.close();
        read.close();
        fl.delete();
        File newFile = new File(ruta + usuarios + ".tmp");
        newFile.renameTo(fl);
        return true;
        }catch(FileNotFoundException e){
        return false;
        }
    }catch(IOException e){
        return false;}
    } 
    
    
    

}    

