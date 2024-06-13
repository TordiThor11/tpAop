package proyecto.model;

public class Participante {
    private String apellido;
    private String nombre;


    private String telefono;
    private String mail;

    public Participante(String apellido, String nombre, String telefono, String mail) throws Exception {
        validar(apellido, nombre, telefono, mail);
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
    }

    private void validar(String apellido, String nombre, String telefono, String mail) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("Nombre no puede ser vacio");
        }
        if (apellido.trim().isEmpty()) {
            throw new Exception("Apellido no puede ser vacio");
        }
        if (!checkEmail(mail)) {
            throw new Exception("El mail es invalido");
        }
        if (!checkPhone(telefono)) {
            throw new Exception("El telefono es invalido");
        }
    }

    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean checkPhone(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMail() {
        return mail;
    }
}
