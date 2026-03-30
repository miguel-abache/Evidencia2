import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private static final int MAX_PRESTAMOS = 3;  // variable global estática

    // Atributos propios de Usuario
    private int           numeroIdentificacion;
    private List<Prestamo> librosPrestados;

    public Usuario(int idUsuario, String nombre,
                   String apellido, int numeroIdentificacion) {
        super(idUsuario, nombre, apellido);  // delega a Persona
        this.numeroIdentificacion = numeroIdentificacion;
        this.librosPrestados      = new ArrayList<>();
    }

    // Getter propio
    public int getNumeroIdentificacion() { return numeroIdentificacion; }
    public List<Prestamo> getLibrosPrestados() {
        return new ArrayList<>(librosPrestados);   // copia defensiva
    }

    public boolean tomarPrestado(Prestamo prestamo) {
        if (!puedePedir()) return false;
        return librosPrestados.add(prestamo);
    }
    public boolean devolverLibro(Prestamo prestamo) {
        return librosPrestados.remove(prestamo);
    }
    public boolean puedePedir() {
        return librosPrestados.size() < MAX_PRESTAMOS;
    }
    public int cantidadLibrosPrestados() {
        return librosPrestados.size();
    }

    // @Override — métodos abstractos de Persona
    @Override
    public String getRol() { return "Usuario"; }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Usuario #" + getIdPersona() + "] "
                + getNombreCompleto()
                + " | CC: "       + numeroIdentificacion
                + " | Prestamos: " + cantidadLibrosPrestados());
    }
}