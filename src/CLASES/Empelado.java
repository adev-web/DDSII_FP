package CLASES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Empelado extends Usuario {

  private String empleados = "empleados.txt";
  private String ruta = "C:\\proyecto\\";
  private String usuarios = "usuario.txt";
  private final String separador = "|";
  private String fecha = super.getDia() + "/" + super.getMes() + "/" + super.getAño();

  private double salarioHoras, horasTrabajadas;

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public double getSalarioHoras() {
    return salarioHoras;
  }

  public void setSalarioHoras(double salarioHoras) {
    this.salarioHoras = salarioHoras;
  }

  public double getHorasTrabajadas() {
    return horasTrabajadas;
  }

  public void setHorasTrabajadas(double horasTrabajadas) {
    this.horasTrabajadas = horasTrabajadas;
  }

  public Empelado() {
  }

  public Empelado(String Cedula, String password, String nombre1, String nombre2, String apellido1, String apellido2, String dia, String mes, String año, String direccion, String telefono) {
    super(Cedula, password, nombre1, nombre2, apellido1, apellido2, dia, mes, año, direccion, telefono);
  }

  //METODO DE CREAR/////
  public boolean CrearEmpleado() {
    File fileRuta = new File(this.ruta);
    if (!fileRuta.exists()) {
      fileRuta.mkdir();
    }
    try {
      FileWriter fw = new FileWriter(this.ruta + this.empleados, true);
      PrintWriter pw = new PrintWriter(fw);

      pw.println(
              super.getCedula() + this.separador
              + super.getPassword() + this.separador
              + super.getNombre1() + this.separador
              + super.getNombre2() + this.separador
              + super.getApellido1() + this.separador
              + super.getApellido2() + this.separador
              + fecha + this.separador
              + this.salarioHoras + this.separador
              + this.horasTrabajadas + this.separador
              + "");
      pw.close();
      fw.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  //***************************
  //METODO BUSCAR//// 
  public boolean Buscar() {
    File fileRuta = new File(ruta);
    if (!fileRuta.exists()) {
      fileRuta.mkdir();
    }
    File fl = new File(this.ruta + this.usuarios);
    try {
      Scanner read = new Scanner(fl);
      while (read.hasNextLine()) {
        String[] linea = read.nextLine().split("\\|");
        if (linea[0].equals(super.getCedula())) {
          //super.getCedula() = linea[0];
          linea[0] = super.getCedula();
          linea[1] = super.getNombre1();
          linea[2] = super.getNombre2();
          linea[3] = super.getApellido1();
          linea[4] = super.getApellido2();
          linea[5] = this.fecha;
          /*linea[6] = super.getDireccion(); NO TOQUES ESTO VECES, GONZALES, KALURY.  PEDRITO TE QUIERO, 
          linea[7] = super.getTelefono();*/
          read.close();
          return true;
        } else {
          JOptionPane.showMessageDialog(null, "No se encontró su Usuario.\nLo debe Crear o Digitar el Correcto");
        }
      }
      read.close();
      return false;
    } catch (FileNotFoundException e) {
      return false;
    }
  }

  //METODO DE MODIFICAR EL USUARIO
  public boolean ModificarEmpleado() {
    File fileRuta = new File(ruta);
    if (!fileRuta.exists()) {
      fileRuta.mkdir();
    }
    File fl = new File(this.ruta + this.empleados);
    try {
      FileWriter fw = new FileWriter(ruta + empleados + ".tmp");
      PrintWriter pw = new PrintWriter(fw);
      try {
        Scanner read = new Scanner(fl);
        while (read.hasNextLine()) {
          String linea = read.nextLine()/*.split("\\|")*/;
          String[] arr = linea.split("\\|");
          if (arr[0].equals(super.getCedula())) {
            pw.println(""
                    + super.getCedula() + this.separador //DUDAS DE MODIFICACION
                    + super.getNombre1() + this.separador
                    + super.getNombre2() + this.separador
                    + super.getApellido1() + this.separador
                    + super.getApellido2() + this.separador
                    + this.salarioHoras + this.separador
                    + this.horasTrabajadas + this.separador
            );
          } else {
            pw.println(linea);
          }
        } //FIN DEL WHILE

        pw.close();
        fw.close();
        read.close();
        fl.delete();
        File newFile = new File(ruta + empleados + ".tmp");
        newFile.renameTo(fl);
        return true;
      } catch (FileNotFoundException e) {
        return false;
      }
    } catch (IOException e) {
      return false;
    }
  }
  //METODO ELIMINAR//

  public boolean EliminarEmpleado() {

    File fileRuta = new File(ruta);
    if (!fileRuta.exists()) {
      fileRuta.mkdir();
    }
    File fl = new File(ruta + empleados);

    try {
      FileWriter fw = new FileWriter(ruta + empleados + ".tmp");
      PrintWriter pw = new PrintWriter(fw);
      try {
        Scanner read = new Scanner(fl);
        while (read.hasNextLine()) {
          String linea = read.nextLine();
          String[] arr = linea.split("\\|");
          if (!arr[0].equals(super.getCedula())) {
            pw.println(linea);
          }
        }
        pw.close();
        fw.close();
        read.close();
        fl.delete();
        File newFile = new File(ruta + empleados + ".tmp");
        newFile.renameTo(fl);
        return true;
      } catch (FileNotFoundException e) {
        return false;
      }
    } catch (IOException e) {
      return false;
    }
  }
}
