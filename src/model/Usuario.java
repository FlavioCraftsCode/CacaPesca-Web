package model;

public class Usuario {
    private String email;
    private String password;
    private String tipoUsuario;

    // Construtores, Getters e Setters
    public Usuario(String email, String password, String tipoUsuario) {
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTipoUsuario() { return tipoUsuario; }
    
    public boolean isEmailValido() {
    return email != null && email.contains("@");
}

public boolean isSenhaForte() {
    return password != null && password.length() >= 6;
}
}