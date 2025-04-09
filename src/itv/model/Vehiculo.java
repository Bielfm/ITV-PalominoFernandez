/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv.model;

public class Vehiculo {

    private String matricula;
    private Tipo_Vehiculos tipo;
    private int año;
    private boolean itv;

    public Vehiculo(String matricula, Tipo_Vehiculos tipo, int año, boolean itv) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.año = año;
        this.itv = itv;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Tipo_Vehiculos getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Vehiculos tipo) {
        this.tipo = tipo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isItv() {
        return itv;
    }

    public void setItv(boolean itv) {
        this.itv = itv;
    }
}
