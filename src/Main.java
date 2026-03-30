public class Main {
    public static void main(String[] args) {

        // 1. Biblioteca (igual que EA2)
        Biblioteca bib = new Biblioteca(
                "Biblioteca Central", "Calle 10 #5-20, Sitionuevo");

        // 2. Materiales: Libro (heredado) + Revista (nueva)
        //    Referencia de tipo Material → polimorfismo
        Material l1 = new Libro(1, "Cien Años de Soledad",
                "Gabriel García Márquez", "Novela");
        Material l2 = new Libro(2, "El Amor en los Tiempos del Cólera",
                "Gabriel García Márquez", "Novela");
        Material l3 = new Libro(3, "La Vorágine",
                "José Eustasio Rivera", "Novela");
        Material l4 = new Libro(4, "Clean Code",
                "Robert C. Martin", "Tecnología");
        Material r1 = new Revista(5, "Semana", "Publicaciones Semana",
                430, "Semanal");
        bib.agregarMaterial(l1); bib.agregarMaterial(l2);
        bib.agregarMaterial(l3); bib.agregarMaterial(l4);
        bib.agregarMaterial(r1);

        // 3. Personas: Usuario (heredado) + Bibliotecario (nuevo)
        //    Referencia de tipo Persona → polimorfismo
        Persona u1 = new Usuario(101, "Ana",    "Pérez",  1001);
        Persona u2 = new Usuario(102, "Carlos", "López",  1002);
        Persona b1 = new Bibliotecario(201, "Miguel", "Abache",
                "EMP-001", "Mañana");
        bib.registrarPersona(u1);
        bib.registrarPersona(u2);
        bib.registrarPersona(b1);

        // 4. Catálogo — polimorfismo en acción:
        //    mostrarInformacion() se resuelve en tiempo de ejecución
        bib.listarCatalogo();

        // 5. Personas — getRol() polimórfico
        bib.listarPersonas();

        // 6. Préstamos (misma lógica EA2, ahora con Material)
        System.out.println("\n=== Préstamos ===");
        Usuario usr1 = bib.buscarUsuario("101");
        Usuario usr2 = bib.buscarUsuario("102");
        bib.prestarMaterial(usr1, l1);  // Ana pide l1 → OK
        bib.prestarMaterial(usr1, l2);  // Ana pide l2 → OK
        bib.prestarMaterial(usr1, l3);  // Ana pide l3 → OK
        bib.prestarMaterial(usr1, l4);  // límite → rechazado
        bib.prestarMaterial(usr1, r1);  // Ana pide revista → rechazado
        bib.prestarMaterial(usr2, l1);  // Carlos pide l1 → no disponible

        // 7. Búsqueda con OVERLOADING
        System.out.println("\n=== Búsqueda por autor (overloading 1 param) ===");
        bib.buscarMaterialPorAutor("García")
                .forEach(Material::mostrarInformacion);

        System.out.println("\n=== Búsqueda por título+autor (overloading 2 param) ===");
        bib.buscarMaterialPorTitulo("Cien", "García")
                .forEach(Material::mostrarInformacion);

        // 8. Devolución y nuevo préstamo
        System.out.println("\n=== Devolución ===");
        bib.devolverMaterial(usr1, l1);
        bib.prestarMaterial(usr2, l1);   // Carlos pide l1 → OK

        // 9. Estado final
        System.out.println("\n=== Materiales disponibles ===");
        bib.listarDisponibles().forEach(Material::mostrarInformacion);

        System.out.println("\n=== Préstamos activos ===");
        bib.listarPrestamosActivos().forEach(p -> {
            System.out.println(p.getIdPrestamo()
                    + " | " + p.getMaterial().getTitulo()
                    + " | Tipo: " + p.getMaterial().getTipoMaterial()
                    + " | Usuario: " + p.getUsuario().getNombreCompleto()
                    + " | Vence: "  + p.getFechaLimite()
                    + " | Días rest: " + p.diasRestantes());
        });
    }
}
