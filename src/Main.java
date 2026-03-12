public class Main {
    public static void main(String[] args) {
        // Paso 1: crear un libro disponible para préstamo
        Libro libro1 = new Libro(
                1,
                "Cien años de soledad",
                "Gabriel Garcia Marquez",
                1967,
                "Novela",
                true );

        // Paso 2: crear un usuario que va a pedir el libro
        Usuario usuario1 = new Usuario(101, "Ana", "Lopez", 123456789);

        // Paso 3: mostrar cómo está el libro antes del préstamo
        System.out.println("Estado inicial del libro:");
        libro1.mostrarInformacion();

        // Paso 4: llamar al método prestarLibro
        // Aquí el usuario intenta prestar el libro.
        // Si el libro está disponible, cambia a no disponible.
        System.out.println("\nProbando el método prestarLibro...");
        usuario1.prestarLibro(libro1);

        // Paso 5: mostrar cómo quedó el libro después del préstamo
        // Aquí también se verá qué usuario tiene el libro prestado.
        System.out.println("\nEstado final del libro y usuario que lo tiene prestado:");
        libro1.mostrarInformacion();
    }
}
