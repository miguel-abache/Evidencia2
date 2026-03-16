public class Main {

    public static void main(String[] args) {

        // Crear la biblioteca
        Biblioteca bib = new Biblioteca(
                "Biblioteca Central", "Calle 10 #5-20, Sitionuevo");

        // Registrar libros
        Libro l1 = new Libro(1, "Cien Años de Soledad",
                "Gabriel García Márquez", "Novela");
        Libro l2 = new Libro(2, "El Amor en los Tiempos del Cólera",
                "Gabriel García Márquez", "Novela");
        Libro l3 = new Libro(3, "La Vorágine",
                "José Eustasio Rivera",  "Novela");
        Libro l4 = new Libro(4, "Clean Code",
                "Robert C. Martin",      "Tecnología");
        bib.agregarLibro(l1);
        bib.agregarLibro(l2);
        bib.agregarLibro(l3);
        bib.agregarLibro(l4);

        // Registrar usuarios
        Usuario u1 = new Usuario(101, "Ana",    "Pérez",  1001);
        Usuario u2 = new Usuario(102, "Carlos", "López",  1002);
        bib.registrarUsuario(u1);
        bib.registrarUsuario(u2);

        // Préstamos
        System.out.println("=== Préstamos ===");
        bib.prestarLibro(u1, l1);   // Ana pide l1 → OK
        bib.prestarLibro(u1, l2);   // Ana pide l2 → OK
        bib.prestarLibro(u1, l3);   // Ana pide l3 → OK (llega a 3)
        bib.prestarLibro(u1, l4);   // Ana pide l4 → rechazado (límite)
        bib.prestarLibro(u2, l1);   // Carlos pide l1 → no disponible

        // Búsqueda
        System.out.println("\n=== Búsqueda por autor ===");
        bib.buscarLibrosPorAutor("García")
                .forEach(Libro::mostrarInformacion);

        // Devolución
        System.out.println("\n=== Devolución ===");
        bib.devolverLibro(u1, l1);   // Ana devuelve l1
        bib.prestarLibro(u2, l1);    // Carlos pide l1 → ahora OK

        // Estado final
        System.out.println("\n=== Libros disponibles ===");
        bib.listarLibrosDisponibles()
                .forEach(Libro::mostrarInformacion);

        System.out.println("\n=== Préstamos activos ===");
        bib.listarLibrosPrestados().forEach(p -> {
            System.out.println(p.getIdPrestamo()
                    + " | " + p.getLibro().getTitulo()
                    + " | " + p.getUsuario().getNombre()
                    + " | Vence: " + p.getFechaLimite()
                    + " | Días restantes: " + p.diasRestantes());
        });
    }
}
 