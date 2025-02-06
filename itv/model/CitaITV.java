/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv.model;

import java.time.LocalDateTime;

public class CitaITV {

    private int id;
    private LocalDateTime fecha;
    private Vehiculo vehiculo;

    public CitaITV(int id, LocalDateTime fecha, Vehiculo vehiculo) {
        this.id = id;
        this.fecha = fecha;
        this.vehiculo = vehiculo;
    }

    public void obtenerCita() {
        System.out.println("Cita programada para el vehículo: " + vehiculo.getMatricula());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
