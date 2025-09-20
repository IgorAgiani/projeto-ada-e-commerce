public class Cliente {
    private String nome;
    private String rg;
    private String email;


    public Cliente () {
    }

    public Cliente(String nome, String documentoObrigatorio, String email) {
        this.nome = nome;
        this.rg = documentoObrigatorio;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome + " - RG: " + rg + " - E-mail: " + email;
    }
}
