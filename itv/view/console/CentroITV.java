package itv.view.console;

import itv.model.Cliente;
import itv.model.Tipo_Vehiculos;
import itv.model.Vehiculo;
import java.util.ArrayList;
import java.util.Random;
import itv.model.Inspeccion;
import itv.model.Inspector;
import java.util.Scanner;

public class CentroITV {

    private final ArrayList<Cliente> clientes;
    private final ArrayList<Inspector> inspectores;
    private final ArrayList<Inspeccion> inspecciones;
    private static int[] idsClientes = new int[100]; 
    private static int contadorIds = 0;
    private static final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
    private static final String[] HORARIOS = {"08:00", "10:00", "12:00", "14:00"};
    
    private final String[][] calendarioInspecciones;

    public CentroITV() {
        clientes = new ArrayList<>();
        inspectores = new ArrayList<>();
        inspecciones = new ArrayList<>();
        calendarioInspecciones = new String[6][4];
        inicializarClientes(); // Precargar clientes aleatorios
        inicializarInspectores(); // Precargar inspectores
        inicializarInspecciones(); // Precargar inspecciones
    }

    private void inicializarClientes() {
    String[] nombres = {"Juan", "Ana", "Carlos", "María", "Luis", "Elena", "Pedro", "Sofía", "Alberto", "Carmen"};
    String[] apellidos = {"Pérez", "Gómez", "López", "Fernández", "Martínez", "Díaz", "Sánchez", "Romero", "Torres", "Hernández"};
    String[] tiposVehiculos = {"Coche", "Moto", "Camión", "Furgoneta", "SUV"};
    String[] marcas = {"Toyota", "Honda", "Ford", "BMW", "Yamaha", "Mercedes", "Audi", "Volkswagen", "Peugeot", "Renault"};
    String[] modelos = {"Corolla", "Civic", "Focus", "X5", "MT-07", "A3", "Golf", "208", "Clio", "C-Class"};
    String[] combustibles = {"Gasolina", "Diesel", "Eléctrico", "Híbrido"};

    Random rand = new Random();

    for (int i = 0; i < 10; i++) { 
        int id = generarIdUnico();
        String nombre = nombres[rand.nextInt(nombres.length)];
        String apellido = apellidos[rand.nextInt(apellidos.length)];
        String dni = generarDNI();  
        int telefono = 600000000 + rand.nextInt(999999);
        String direccion = "Calle " + (i + 1);

        Cliente cliente = new Cliente(nombre, apellido, dni, telefono, direccion, id);

        int numVehiculos = rand.nextInt(3) + 1;
        for (int j = 0; j < numVehiculos; j++) {
            Tipo_Vehiculos tipoVehiculo = new Tipo_Vehiculos(
                tiposVehiculos[rand.nextInt(tiposVehiculos.length)],
                marcas[rand.nextInt(marcas.length)],
                modelos[rand.nextInt(modelos.length)],
                combustibles[rand.nextInt(combustibles.length)]
            );

            String matricula = generarMatricula(); 
            Vehiculo vehiculo = new Vehiculo(matricula, tipoVehiculo, 2000 + rand.nextInt(24), false);
            cliente.agregarVehiculo(vehiculo);
        }
        addCliente(cliente);
    }
}


 
private String generarDNI() {
    Random rand = new Random();
    int numero = rand.nextInt(90000000) + 10000000; 
    char letra = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numero % 23); 
    return numero + String.valueOf(letra);
}


private String generarMatricula() {
    Random rand = new Random();
    int numeros = rand.nextInt(9000) + 1000; 
    char letra1 = (char) (rand.nextInt(26) + 'A');
    char letra2 = (char) (rand.nextInt(26) + 'A');
    char letra3 = (char) (rand.nextInt(26) + 'A');
    return numeros + "" + letra1 + letra2 + letra3;
}


    private void inicializarInspectores() {
        String[] nombres = {"Pedro", "Laura", "Miguel", "Sonia", "Fernando"};
        String[] especialidades = {"Frenos", "Emisiones", "Motor", "Luces", "Suspensión"};

        for (int i = 0; i < 3; i++) { // Crear 3 inspectores
            Inspector inspector = new Inspector(nombres[i], "García", "98765432X", 600123456 + i, "Calle Inspector " + (i + 1), 100 + i, especialidades[i]);
            inspectores.add(inspector);
        }
    }

 private void inicializarInspecciones() {
    Random rand = new Random();
    
    for (int i = 0; i < 3; i++) { 
        Cliente cliente = clientes.get(rand.nextInt(clientes.size()));
        Vehiculo vehiculo = cliente.getVehiculos().get(rand.nextInt(cliente.getVehiculos().size()));
        Inspector inspector = inspectores.get(rand.nextInt(inspectores.size()));

        int dia, hora;
        
        // Buscar un horario disponible
        do {
            dia = rand.nextInt(DIAS_SEMANA.length);
            hora = rand.nextInt(HORARIOS.length);
        } while (calendarioInspecciones[dia][hora] != null); 

        // Crear y registrar la inspección
        Inspeccion inspeccion = new Inspeccion(generarIdUnico(), rand.nextBoolean(), "Revisión inicial", vehiculo);
        inspeccion.addInspector(inspector);
        inspecciones.add(inspeccion);
        
        // Registrar en el calendario
        calendarioInspecciones[dia][hora] = inspector.getNombre();
    }
}


    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
        idsClientes[contadorIds++] = cliente.getId();
    }

    private static int generarIdUnico() {
        int nuevoId;
        do {
            nuevoId = (int) (Math.random() * 1000);
        } while (idYaExiste(nuevoId));
        idsClientes[contadorIds++] = nuevoId;
        return nuevoId;
    }

    private static boolean idYaExiste(int id) {
        for (int i = 0; i < contadorIds; i++) {
            if (idsClientes[i] == id) {
                return true;
            }
        }
        return false;
    }

    private Cliente buscarClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    private void mostrarIdsClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("\n--- IDs de Clientes Registrados ---");
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId());
            }
        }
    }

   private void mostrarInspecciones() {
    if (inspecciones.isEmpty()) {
        System.out.println("No hay inspecciones programadas.");
        return;
    }

    System.out.println("\n--- INSPECCIONES PROGRAMADAS ---");
    for (int i = 0; i < inspecciones.size(); i++) {
        Inspeccion inspeccion = inspecciones.get(i);
        Vehiculo vehiculo = inspeccion.getVehiculo();
        Inspector inspector = inspeccion.getInspectores().get(0); 

        String dia = "";
        String hora = "";
        
       
        for (int d = 0; d < DIAS_SEMANA.length; d++) {
            for (int h = 0; h < HORARIOS.length; h++) {
                if (calendarioInspecciones[d][h] != null && calendarioInspecciones[d][h].equals(inspector.getNombre())) {
                    dia = DIAS_SEMANA[d];
                    hora = HORARIOS[h];
                }
            }
        }

        System.out.println(" Día: " + dia + " |  Hora: " + hora);
        System.out.println(" Inspector: " + inspector.getNombre() + " | Especialidad: " + inspector.getEspecializacion());
        System.out.println(" ID Inspección: " + inspeccion.getIdInspeccion());
        System.out.println(" Vehículo: " + vehiculo.getMatricula() + " - " + vehiculo.getTipo().getMarca() + " " + vehiculo.getTipo().getModelo());
        System.out.println(" Resultado: " + (inspeccion.isResultado() ? "Apto" : " No Apto"));
        System.out.println("-----------------------------------");
    }
}
    private void registrarInspeccion(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());
        Cliente cliente = buscarClientePorId(idCliente);

        if (cliente != null && !cliente.getVehiculos().isEmpty()) {
            System.out.println("\nSeleccione el vehículo para inspección:");
            for (int i = 0; i < cliente.getVehiculos().size(); i++) {
                Vehiculo v = cliente.getVehiculos().get(i);
                System.out.println((i + 1) + ". " + v.getMatricula() + " - " + v.getTipo().getMarca() + " " + v.getTipo().getModelo());
            }

            int opcionVehiculo = Integer.parseInt(scanner.nextLine()) - 1;
            Vehiculo vehiculo = cliente.getVehiculos().get(opcionVehiculo);

            System.out.println("\nSeleccione un inspector:");
            for (int i = 0; i < inspectores.size(); i++) {
                System.out.println((i + 1) + ". " + inspectores.get(i).getNombre());
            }

            int opcionInspector = Integer.parseInt(scanner.nextLine()) - 1;
            Inspector inspector = inspectores.get(opcionInspector);

            System.out.println("\nSeleccione el día de la inspección:");
            for (int i = 0; i < DIAS_SEMANA.length; i++) {
                System.out.println((i + 1) + ". " + DIAS_SEMANA[i]);
            }

            int opcionDia = Integer.parseInt(scanner.nextLine()) - 1;

            System.out.println("\nSeleccione la hora:");
            for (int i = 0; i < HORARIOS.length; i++) {
                System.out.println((i + 1) + ". " + HORARIOS[i]);
            }

            int opcionHora = Integer.parseInt(scanner.nextLine()) - 1;

            if (calendarioInspecciones[opcionDia][opcionHora] != null) {
                System.out.println(" El inspector ya tiene una inspección a esa hora. Elija otro horario.");
            } else {
                Inspeccion nuevaInspeccion = new Inspeccion(generarIdUnico(), false, "Pendiente", vehiculo);
                nuevaInspeccion.addInspector(inspector);
                inspecciones.add(nuevaInspeccion);
                calendarioInspecciones[opcionDia][opcionHora] = inspector.getNombre();

                System.out.println(" Inspección programada el " + DIAS_SEMANA[opcionDia] + " a las " + HORARIOS[opcionHora]
                        + " con el inspector " + inspector.getNombre() + " para el vehículo " + vehiculo.getMatricula());
            }
        } else {
            System.out.println(" Cliente no encontrado o sin vehículos registrados.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CentroITV centro = new CentroITV();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al centro ITV Girbau");
        Thread.sleep(3000);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Consultar datos de un cliente");
            System.out.println("2. Registrar un nuevo cliente");
            System.out.println("3. Registrar un vehículo a un cliente");
            System.out.println("4. Mostrar los IDs de los clientes actuales");
            System.out.println("5. Mostrar inspecciones programadas");
            System.out.println("6. Registrar inspeccion");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                System.out.print("Por favor, introduzca su ID de cliente: ");
                int idCliente;
                try {
                    idCliente = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" Entrada no válida. Debe ser un número.");
                    continue;
                }

                Cliente cliente = centro.buscarClientePorId(idCliente);
                if (cliente != null) {
                    System.out.println("\n--- DATOS DEL CLIENTE ---");
                    System.out.println("ID: " + cliente.getId());
                    System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellidos());
                    System.out.println("DNI: " + cliente.getDni());
                    System.out.println("Teléfono: " + cliente.getTelefono());
                    System.out.println("Dirección: " + cliente.getDireccion());

                    System.out.println("\n--- VEHÍCULOS REGISTRADOS ---");
                    if (cliente.getVehiculos().isEmpty()) {
                        System.out.println("No tiene vehículos registrados.");
                    } else {
                        for (Vehiculo vehiculo : cliente.getVehiculos()) {
                            System.out.println("Matrícula: " + vehiculo.getMatricula());
                            System.out.println("Tipo: " + vehiculo.getTipo().getTipo());
                            System.out.println("Marca: " + vehiculo.getTipo().getMarca());
                            System.out.println("Modelo: " + vehiculo.getTipo().getModelo());
                            System.out.println("Combustible: " + vehiculo.getTipo().getCombustible());
                            System.out.println("Año de fabricación: " + vehiculo.getAño());
                            System.out.println("-----------------------------------");
                        }
                    }
                } else {
                    System.out.println(" Cliente no encontrado. Verifique el ID.");
                }

            } else if (opcion.equals("2")) {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                System.out.print("DNI: ");
                String dni = scanner.nextLine();
                System.out.print("Teléfono: ");
                int telefono = Integer.parseInt(scanner.nextLine());
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();

                int id = generarIdUnico();
                Cliente nuevoCliente = new Cliente(nombre, apellido, dni, telefono, direccion, id);
                centro.addCliente(nuevoCliente);

                System.out.println("Cliente registrado con éxito. Su ID es: " + nuevoCliente.getId());

            } else if (opcion.equals("3")) {
                System.out.print("Ingrese el ID del cliente para registrar un vehículo: ");
                int idCliente = Integer.parseInt(scanner.nextLine());

                Cliente cliente = centro.buscarClientePorId(idCliente);
                if (cliente != null) {
                    System.out.print("Ingrese la matrícula del vehículo: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Ingrese el tipo de vehículo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Ingrese la marca del vehículo: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingrese el modelo del vehículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingrese el tipo de combustible: ");
                    String combustible = scanner.nextLine();
                    System.out.print("Ingrese el año de fabricación: ");
                    int anio = Integer.parseInt(scanner.nextLine());

                    Tipo_Vehiculos tipoVehiculo = new Tipo_Vehiculos(tipo, marca, modelo, combustible);
                    Vehiculo nuevoVehiculo = new Vehiculo(matricula, tipoVehiculo, anio, false);
                    cliente.agregarVehiculo(nuevoVehiculo);

                    System.out.println("Vehículo registrado con éxito para el cliente " + cliente.getNombre());
                } else {
                    System.out.println(" Cliente no encontrado.");
                }

            } else if (opcion.equals("4")) {
                centro.mostrarIdsClientes();
            } else if (opcion.equals("7")) {
                System.out.println("Gracias por utilizar nuestros servicios.");
                break;
            } else if (opcion.equals("5")) {
                centro.mostrarInspecciones();
                
            }
            else if (opcion.equals("6")){
                centro.registrarInspeccion(scanner);
            }
            else {
                System.out.println(" Opción no válida. El programa se cerrará.");
                break;
            }

        }
        scanner.close();
    }
}
