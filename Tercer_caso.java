import java.util.*;

public class Tercer_caso {

    static List<String> habitaciones = new ArrayList<>(List.of("101", "102", "103")); // Lista mutable
    static Map<String, String> reservas = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n*** Sistema de Gestión de Hotel ***");
            System.out.println("1. Buscar habitaciones disponibles");
            System.out.println("2. Reservar una habitación");
            System.out.println("3. Cancelar una reserva");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    buscarHabitacionesDisponibles(scanner);
                    break;
                case 2:
                    reservarHabitacion(scanner);
                    break;
                case 3:
                    cancelarReserva(scanner);
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    public static void buscarHabitacionesDisponibles(Scanner scanner) {
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = scanner.next();
        System.out.println("Habitaciones disponibles para la fecha " + fecha + ":");
        for (int i = 0; i < habitaciones.size(); i++) {
            System.out.println((i + 1) + ". " + habitaciones.get(i));
        }
    }

    public static void reservarHabitacion(Scanner scanner) {
        buscarHabitacionesDisponibles(scanner);
        System.out.print("Seleccione el número de la habitación que desea reservar: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccion < 1 || seleccion > habitaciones.size()) {
            System.out.println("Selección inválida. Intente de nuevo.");
            return;
        }

        String habitacionSeleccionada = habitaciones.get(seleccion - 1);

        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine();

        // Generar un ID único corto (6 caracteres alfanuméricos)
        String idReserva = generarIdCorto();

        String detallesReserva = "Usuario: " + usuario + ", Habitación: " + habitacionSeleccionada;

        // Guardar la reserva
        reservas.put(idReserva, detallesReserva);

        // Eliminar la habitación de la lista de disponibles
        habitaciones.remove(habitacionSeleccionada);

        System.out.println("Reserva confirmada! Su número de reserva es: " + idReserva);
        System.out.println("Detalles: " + detallesReserva);
    }

    public static void cancelarReserva(Scanner scanner) {
        System.out.println("Reservas actuales:");
        for (Map.Entry<String, String> entry : reservas.entrySet()) {
            System.out.println("ID Reserva: " + entry.getKey() + ", Detalles: " + entry.getValue());
        }
        System.out.print("Ingrese el ID de la reserva que desea cancelar: ");
        String idReserva = scanner.next();

        if (reservas.containsKey(idReserva)) {
            String detalles = reservas.remove(idReserva); // Eliminar reserva
            System.out.println("Reserva cancelada exitosamente. Detalles: " + detalles);
        } else {
            System.out.println("ID de reserva no encontrado. Intente de nuevo.");
        }
    }

    // Método para generar un ID único corto
    private static String generarIdCorto() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder id = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            id.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return id.toString();
    }
}


