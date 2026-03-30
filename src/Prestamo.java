import java.time.LocalDate;

public class Prestamo {

    private String    idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;   // null hasta devolver
    private LocalDate fechaLimite;

    private Material material;
    private Usuario  usuario;

    public Prestamo(String idPrestamo, Material material,
                    Usuario usuario, int diasPrestamo) {
        this.idPrestamo      = idPrestamo;
        this.material        = material;
        this.usuario         = usuario;
        this.fechaPrestamo   = LocalDate.now();
        this.fechaLimite     = fechaPrestamo.plusDays(diasPrestamo);
        this.fechaDevolucion = null;
    }

    public String    getIdPrestamo()      { return idPrestamo; }
    public LocalDate getFechaPrestamo()   { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public LocalDate getFechaLimite()     { return fechaLimite; }
    public Material  getMaterial()        { return material; }
    public Usuario   getUsuario()         { return usuario; }

    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
    }
    public boolean estaVencido() {
        if (fechaDevolucion != null) return false;
        return LocalDate.now().isAfter(fechaLimite);
    }
    public int diasRestantes() {
        if (fechaDevolucion != null) return 0;
        return (int) java.time.temporal.ChronoUnit.DAYS
                .between(LocalDate.now(), fechaLimite);
    }
}