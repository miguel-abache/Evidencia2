public class Libro {

    private int    id;
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponibilidad;

    public Libro(int id, String titulo, String autor, String genero) {
        this.id            = id;
        this.titulo        = titulo;
        this.autor         = autor;
        this.genero        = genero;
        this.disponibilidad = true;   // disponible al crearse
    }

    public int     getId()             { return id; }
    public String  getTitulo()         { return titulo; }
    public String  getAutor()          { return autor; }
    public String  getGenero()         { return genero; }
    public boolean isDisponible()      { return disponibilidad; }

    /** Cambia el estado de disponibilidad del libro. */
    public void actualizarDisponibilidad(boolean estado) {
        this.disponibilidad = estado;
    }

    /** Muestra los datos del libro por consola. */
    public void mostrarInformacion() {
        System.out.println("[Libro #" + id + "] " + titulo
                + " | Autor: " + autor
                + " | Género: " + genero
                + " | Disponible: " + (disponibilidad ? "Sí" : "No"));
    }
}