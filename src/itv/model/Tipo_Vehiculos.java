/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv.model;

public class Tipo_Vehiculos {

    private String tipo;         // Tipo de vehículo (Coche, Moto, Camión, etc.)
    private String marca;        // Marca del vehículo
    private String modelo;       // Modelo del vehículo
    private String combustible;  // Tipo de combustible (Gasolina, Diesel, Eléctrico, Híbrido, etc.)

    // Constructor
    public Tipo_Vehiculos(String tipo, String marca, String modelo, String combustible) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.combustible = combustible;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
}
