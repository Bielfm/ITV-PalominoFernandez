package itv.model;

import java.util.ArrayList;

public class Inspeccion {

    private int idInspeccion;
    private boolean resultado;
    private String comentarios;
    private Vehiculo vehiculo;  // Se cambia de CitaITV a Vehiculo
    private ArrayList<Inspector> inspectores;

    public Inspeccion(int idInspeccion, boolean resultado, String comentarios, Vehiculo vehiculo) {
        this.idInspeccion = idInspeccion;
        this.resultado = resultado;
        this.comentarios = comentarios;
        this.vehiculo = vehiculo;
        this.inspectores = new ArrayList<>();
    }

    public boolean checkITV() {
        return resultado;
    }

    public String generarInforme() {
        StringBuilder informe = new StringBuilder();
        informe.append("🔍 Informe de Inspección ID: ").append(idInspeccion).append("\n");
        informe.append("Vehículo: ").append(vehiculo.getMatricula()).append("\n");
        informe.append("Resultado: ").append(resultado ? "✅ Apto" : "❌ No apto").append("\n");
        informe.append("Comentarios: ").append(comentarios).append("\n");
        informe.append("Inspectores asignados: ");
        if (inspectores.isEmpty()) {
            informe.append("Ninguno\n");
        } else {
            for (Inspector inspector : inspectores) {
                informe.append(inspector.getNombre()).append(" ");
            }
            informe.append("\n");
        }
        return informe.toString();
    }

    public int getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(int idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<Inspector> getInspectores() {
        return inspectores;
    }

    public void addInspector(Inspector inspector) {
        inspectores.add(inspector);
    }
}
