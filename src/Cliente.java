public class Cliente {
    private String nome;
    private String documentoObrigatorio;
    private String email;

    public Cliente(String nome, String documentoObrigatorio, String email) {
        this.nome = nome;
        this.documentoObrigatorio = documentoObrigatorio;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoObrigatorio() {
        return documentoObrigatorio;
    }

    public void setDocumentoObrigatorio(String documentoObrigatorio) {
        this.documentoObrigatorio = documentoObrigatorio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
