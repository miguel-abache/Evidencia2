public class Revista extends Material {

    private int    numeroEdicion;
    private String periodicidad;   // mensual, semanal, etc.

    public Revista(int id, String titulo, String autor,
                   int numeroEdicion, String periodicidad) {
        super(id, titulo, autor);
        this.numeroEdicion = numeroEdicion;
        this.periodicidad  = periodicidad;
    }

    public int    getNumeroEdicion() { return numeroEdicion; }
    public String getPeriodicidad()  { return periodicidad; }

    // @Override — comportamiento específico de Revista
    @Override
    public void mostrarInformacion() {
        System.out.println("[Revista #" + getId() + "] " + getTitulo()
                + " | Autor: "      + getAutor()
                + " | Edición: "    + numeroEdicion
                + " | Periodicidad: " + periodicidad
                + " | Disponible: "  + (isDisponible() ? "Sí" : "No"));
    }

    @Override
    public String getTipoMaterial() { return "Revista"; }
}