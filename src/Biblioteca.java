import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private String         nombre;
    private String         direccion;
    private List<Material> materiales;        // EA3: antes List<Libro>
    private List<Persona>  personas;           // EA3: antes List<Usuario>
    private List<Prestamo> prestamos;
    private int            contadorPrestamos;

    public Biblioteca(String nombre, String direccion) {
        this.nombre            = nombre;
        this.direccion         = direccion;
        this.materiales        = new ArrayList<>();
        this.personas          = new ArrayList<>();
        this.prestamos         = new ArrayList<>();
        this.contadorPrestamos = 0;
    }

    // Polimorfismo: acepta cualquier subclase de Material
    public void agregarMaterial(Material m) { materiales.add(m); }

    // Polimorfismo: acepta cualquier subclase de Persona
    public void registrarPersona(Persona p) { personas.add(p); }

    // OVERLOADING — buscar solo por título
    public List<Material> buscarMaterialPorTitulo(String titulo) {
        String f = titulo.toLowerCase();
        return materiales.stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }
    // OVERLOADING — buscar por título Y autor
    public List<Material> buscarMaterialPorTitulo(String titulo, String autor) {
        String ft = titulo.toLowerCase();
        String fa = autor.toLowerCase();
        return materiales.stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(ft)
                        && m.getAutor().toLowerCase().contains(fa))
                .collect(Collectors.toList());
    }

    // Idéntico al EA2, pero opera sobre Material
    public List<Material> buscarMaterialPorAutor(String autor) {
        String f = autor.toLowerCase();
        return materiales.stream()
                .filter(m -> m.getAutor().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }

    public List<Material> listarDisponibles() {
        return materiales.stream()
                .filter(Material::isDisponible)
                .collect(Collectors.toList());
    }

    // buscarUsuario sigue igual, filtra por tipo
    public Usuario buscarUsuario(String idUsuario) {
        int id = Integer.parseInt(idUsuario);
        return personas.stream()
                .filter(p -> p instanceof Usuario
                        && p.getIdPersona() == id)
                .map(p -> (Usuario) p)
                .findFirst().orElse(null);
    }

    // prestarMaterial — lógica idéntica al EA2, opera sobre Material
    public boolean prestarMaterial(Usuario usuario, Material material) {
        if (!material.isDisponible()) {
            System.out.println("Material no disponible."); return false;
        }
        if (!usuario.puedePedir()) {
            System.out.println("Límite de préstamos alcanzado."); return false;
        }
        // Variable local: prestamo (como en EA2)
        String   idP     = "P" + (++contadorPrestamos);
        Prestamo prestamo = new Prestamo(idP, material, usuario, 15);
        material.actualizarDisponibilidad(false);
        usuario.tomarPrestado(prestamo);
        prestamos.add(prestamo);
        System.out.println("Préstamo " + idP + " registrado.");
        return true;
    }

    public boolean devolverMaterial(Usuario usuario, Material material) {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getMaterial().equals(material)
                        && p.getUsuario().equals(usuario)
                        && p.getFechaDevolucion() == null)
                .findFirst().orElse(null);
        if (prestamo == null) {
            System.out.println("Préstamo no encontrado."); return false;
        }
        prestamo.registrarDevolucion();
        material.actualizarDisponibilidad(true);
        usuario.devolverLibro(prestamo);
        System.out.println("Devolución registrada.");
        return true;
    }

    public List<Prestamo> listarPrestamosActivos() {
        return prestamos.stream()
                .filter(p -> p.getFechaDevolucion() == null)
                .collect(Collectors.toList());
    }

    // Polimorfismo: mostrarInformacion() según el tipo real
    public void listarCatalogo() {
        System.out.println("=== Catálogo: " + nombre + " ===");
        materiales.forEach(Material::mostrarInformacion);
    }
    public void listarPersonas() {
        System.out.println("=== Personas registradas ===");
        personas.forEach(p -> {
            p.mostrarInformacion();
            System.out.println("  Rol: " + p.getRol());
        });
    }
}