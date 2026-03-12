public class Main {
    public static void main(String[] args) {
        // Crear una instancia de Libro
        Libro libro1 = new Libro(
                1,
                "Cien años de soledad",
                "Gabriel Garcia Marquez",
                1967,
                "Novela",
                true );

        System.out.println("Estado inicial:");
        libro1.mostrarInformacion();

        libro1.actualizarDisponibilidad(false);

        System.out.println("\nEstado despues de actualizar disponibilidad:");
        libro1.mostrarInformacion();
    }
}


