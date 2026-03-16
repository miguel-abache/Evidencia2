import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private String       nombre;
    private String       direccion;
    private List<Libro>    libros;
    private List<Usuario>  usuarios;
    private List<Prestamo> prestamos;
    private int            contadorPrestamos;  // local de clase

    public Biblioteca(String nombre, String direccion) {
        this.nombre    = nombre;
        this.direccion = direccion;
        this.libros    = new ArrayList<>();
        this.usuarios  = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.contadorPrestamos = 0;
    }

    // ── Gestión de libros ─────────────────────────────────────
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        // Variable local: filtro
        String filtro = titulo.toLowerCase();
        return libros.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        String filtro = autor.toLowerCase();
        return libros.stream()
                .filter(l -> l.getAutor().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }

    public List<Libro> listarLibrosDisponibles() {
        return libros.stream()
                .filter(Libro::isDisponible)
                .collect(Collectors.toList());
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String idUsuario) {
        int id = Integer.parseInt(idUsuario);
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == id)
                .findFirst().orElse(null);
    }

    /**
     * Presta un libro a un usuario.
     * Variables locales: prestamo (objeto creado en el método).
     * Retorna true si la operación fue exitosa.
     */
    public boolean prestarLibro(Usuario usuario, Libro libro) {
        if (!libro.isDisponible()) {
            System.out.println("Libro no disponible."); return false;
        }
        if (!usuario.puedePedir()) {
            System.out.println("Límite de préstamos alcanzado."); return false;
        }
        // Variable local: nuevo préstamo
        String idP = "P" + (++contadorPrestamos);
        Prestamo prestamo = new Prestamo(idP, libro, usuario, 15);
        libro.actualizarDisponibilidad(false);
        usuario.tomarPrestado(prestamo);
        prestamos.add(prestamo);
        System.out.println("Préstamo " + idP + " registrado.");
        return true;
    }

    /**
     * Registra la devolución de un libro.
     * Variables locales: prestamo (encontrado en la lista).
     */
    public boolean devolverLibro(Usuario usuario, Libro libro) {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getLibro().equals(libro)
                        && p.getUsuario().equals(usuario)
                        && p.getFechaDevolucion() == null)
                .findFirst().orElse(null);
        if (prestamo == null) {
            System.out.println("Préstamo no encontrado."); return false;
        }
        prestamo.registrarDevolucion();
        libro.actualizarDisponibilidad(true);
        usuario.devolverLibro(prestamo);
        System.out.println("Devolución registrada.");
        return true;
    }

    public List<Prestamo> listarLibrosPrestados() {
        return prestamos.stream()
                .filter(p -> p.getFechaDevolucion() == null)
                .collect(Collectors.toList());
    }

    public List<Libro> librosPrestadosAUsuario(String idUsuario) {
        Usuario u = buscarUsuario(idUsuario);
        if (u == null) return new ArrayList<>();
        return u.getLibrosPrestados().stream()
                .map(Prestamo::getLibro)
                .collect(Collectors.toList());
    }
}

