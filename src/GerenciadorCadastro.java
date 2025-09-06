public interface GerenciadorCadastro <T> {
    void cadastrar(java.util.Scanner scanner);
    void cadastrar(T entidade);
    void salvar(T entidade);
    void listar();
    void atualizar();
}
