public class Libro {
    //Atributos de tipo privado como id

    private int id;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String genero;
    private Boolean disponible;
    private String usuarioPrestamo;
    //Constructor para inicializar los atributos
    public Libro(int id, String titulo, String autor, int anioPublicacion, String genero, Boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponible = disponible;
        this.usuarioPrestamo = "Ninguno";
    }
    //Metodos para actualizar el estado de disponibilidad del libro
    public void actualizarDisponibilidad(boolean disponible) {
        this.disponible = disponible;
    }

    public void asignarUsuarioPrestamo(String usuarioPrestamo) {
        this.usuarioPrestamo = usuarioPrestamo;
    }

    public boolean isDisponible() {
        return disponible;
    }
    //Metodo para mostrar la informacion del libro
    public void mostrarInformacion() {
        System.out.println("Id: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año de publicacion: " + anioPublicacion);
        System.out.println("Genero: " + genero);

        if (disponible) {
            System.out.println("Disponibilidad: Disponible");
            System.out.println("Prestado a: Ninguno");
        } else {
            System.out.println("Disponibilidad: No disponible");
            System.out.println("Prestado a: " + usuarioPrestamo);
        }
    }
}
