public abstract class Persona {

    private int    idPersona;
    private String nombre;
    private String apellido;

    public Persona(int idPersona, String nombre, String apellido) {
        this.idPersona = idPersona;
        this.nombre    = nombre;
        this.apellido  = apellido;
    }

    public int    getIdPersona()     { return idPersona; }
    public String getNombre()        { return nombre; }
    public String getApellido()      { return apellido; }
    public String getNombreCompleto(){ return nombre + " " + apellido; }

    // Métodos polimórficos abstractos
    public abstract String getRol();
    public abstract void   mostrarInformacion();
}