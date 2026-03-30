public abstract class Material {

    // Atributos comunes heredados por Libro (y futuros tipos)
    private int     id;
    private String  titulo;
    private String  autor;
    private boolean disponibilidad;

    public Material(int id, String titulo, String autor) {
        this.id            = id;
        this.titulo        = titulo;
        this.autor         = autor;
        this.disponibilidad = true;
    }

    public int     getId()          { return id; }
    public String  getTitulo()      { return titulo; }
    public String  getAutor()       { return autor; }
    public boolean isDisponible()   { return disponibilidad; }

    public void actualizarDisponibilidad(boolean estado) {
        this.disponibilidad = estado;
    }

    // MÉTODO ABSTRACTO — obliga a cada subclase a implementarlo (override)
    public abstract void mostrarInformacion();

    // Método base con implementación — puede ser sobreescrito
    public String getTipoMaterial() { return "Material"; }
}
