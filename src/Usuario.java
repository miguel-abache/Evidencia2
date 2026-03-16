import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private static final int MAX_PRESTAMOS = 3;

    private int    idUsuario;
    private String nombre;
    private String apellido;
    private int    numeroIdentificacion;
    private List<Prestamo> librosPrestados;   // lista interna

    public Usuario(int idUsuario, String nombre,
                   String apellido, int numeroIdentificacion) {
        this.idUsuario            = idUsuario;
        this.nombre               = nombre;
        this.apellido             = apellido;
        this.numeroIdentificacion = numeroIdentificacion;
        this.librosPrestados      = new ArrayList<>();
    }

    public int    getIdUsuario()            { return idUsuario; }
    public String getNombre()               { return nombre; }
    public String getApellido()             { return apellido; }
    public int    getNumeroIdentificacion() { return numeroIdentificacion; }
    public List<Prestamo> getLibrosPrestados() {
        return new ArrayList<>(librosPrestados); // copia defensiva
    }

    /** Registra un préstamo en el historial del usuario. */
    public boolean tomarPrestado(Prestamo prestamo) {
        if (!puedePedir()) return false;
        librosPrestados.add(prestamo);
        return true;
    }

    /** Elimina el préstamo del historial al devolver. */
    public boolean devolverLibro(Prestamo prestamo) {
        return librosPrestados.remove(prestamo);
    }

    /** Verifica si puede pedir más libros. */
    public boolean puedePedir() {
        return librosPrestados.size() < MAX_PRESTAMOS;
    }

    /** Retorna cuántos libros tiene actualmente prestados. */
    public int cantidadLibrosPrestados() {
        return librosPrestados.size();
    }
}