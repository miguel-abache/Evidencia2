public class Libro extends Material {

    // Atributo propio de Libro (no estaba en Material)
    private String genero;

    public Libro(int id, String titulo, String autor, String genero) {
        super(id, titulo, autor);   // delega id, titulo, autor, disponibilidad
        this.genero = genero;
    }

    public String getGenero() { return genero; }

    // @Override — implementa el método abstracto de Material
    // Mismo cuerpo que en EA2, ahora es polimórfico
    @Override
    public void mostrarInformacion() {
        System.out.println("[Libro #" + getId() + "] " + getTitulo()
                + " | Autor: "     + getAutor()
                + " | Género: "    + genero
                + " | Disponible: " + (isDisponible() ? "Sí" : "No"));
    }

    // @Override — identifica el tipo concreto
    @Override
    public String getTipoMaterial() { return "Libro"; }
}