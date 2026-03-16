import java.time.LocalDate;

public class Prestamo {

    private String    idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;   // null hasta devolver
    private LocalDate fechaLimite;

    private Libro   libro;
    private Usuario usuario;

    public Prestamo(String idPrestamo, Libro libro,
                    Usuario usuario, int diasPrestamo) {
        this.idPrestamo     = idPrestamo;
        this.libro          = libro;
        this.usuario        = usuario;
        this.fechaPrestamo  = LocalDate.now();
        this.fechaLimite    = fechaPrestamo.plusDays(diasPrestamo);
        this.fechaDevolucion = null;
    }

    public String    getIdPrestamo()      { return idPrestamo; }
    public LocalDate getFechaPrestamo()   { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public LocalDate getFechaLimite()     { return fechaLimite; }
    public Libro     getLibro()           { return libro; }
    public Usuario   getUsuario()         { return usuario; }

    /** Registra la fecha real de devolución. */
    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
    }

    /** Indica si el préstamo está vencido. */
    public boolean estaVencido() {
        if (fechaDevolucion != null) return false;   // ya devuelto
        return LocalDate.now().isAfter(fechaLimite);
    }

    /** Días restantes antes del vencimiento (negativo = vencido). */
    public int diasRestantes() {
        if (fechaDevolucion != null) return 0;
        return (int) java.time.temporal.ChronoUnit.DAYS
                .between(LocalDate.now(), fechaLimite);
    }
}