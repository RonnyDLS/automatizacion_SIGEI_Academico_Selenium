package Entidades;

public class Usuario {

    public String numeroIdentidad;
    public String nombre;
    public String apellido;
    public String tipoDocumento;
    public CredencialesLoginSigei credencialesLoginSigei = new CredencialesLoginSigei();
    public DatosAdmision datosAdmision = new DatosAdmision();

    public Usuario(){}
}
