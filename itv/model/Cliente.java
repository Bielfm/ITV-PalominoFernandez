package itv.model;
import java.util.ArrayList;

public class Cliente extends Persona implements esCliente {
    private int id; // ID único del cliente
    private ArrayList<Vehiculo> vehiculos; // Lista de vehículos asociados

    public Cliente(String nombre, String apellidos, String dni, int telefono, String direccion, int id) {
        super(nombre, apellidos, dni, telefono, direccion);
        this.id = id;
        this.vehiculos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
    }



    @Override
    public String calcularZBE() {
        return "Cálculo ZBE para el cliente " + getNombre();
    }
}
