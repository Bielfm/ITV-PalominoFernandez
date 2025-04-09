/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv.model;

public class Inspector extends Persona {

    private int idInspector;
    private String especializacion;

    public Inspector(String nombre, String apellidos, String dni, int telefono, String direccion, int idInspector, String especializacion) {
        super(nombre, apellidos, dni, telefono, direccion);
        this.idInspector = idInspector;
        this.especializacion = especializacion;
    }

    public void asignarInspeccion() {
        System.out.println("Inspección asignada al inspector: " + nombre);
    }

    public String verificarInspeccion() {
        return "Inspección verificada por " + nombre;
    }

    public int getIdInspector() {
        return idInspector;
    }

    public void setIdInspector(int idInspector) {
        this.idInspector = idInspector;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
}
