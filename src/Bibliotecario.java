// ============================================================
// Bibliotecario.java  —  Nueva subclase de Persona (EA3)
// Representa al personal de la biblioteca
// ============================================================
public class Bibliotecario extends Persona {

    private String codigoEmpleado;
    private String turno;   // mañana, tarde, noche

    public Bibliotecario(int idPersona, String nombre, String apellido,
                         String codigoEmpleado, String turno) {
        super(idPersona, nombre, apellido);
        this.codigoEmpleado = codigoEmpleado;
        this.turno          = turno;
    }

    public String getCodigoEmpleado() { return codigoEmpleado; }
    public String getTurno()          { return turno; }

    // @Override — comportamiento propio del Bibliotecario
    @Override
    public String getRol() { return "Bibliotecario"; }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Bibliotecario #" + getIdPersona() + "] "
                + getNombreCompleto()
                + " | Código: " + codigoEmpleado
                + " | Turno: "  + turno);
    }
}