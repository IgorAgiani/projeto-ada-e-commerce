public interface GerenciadorCadastro <T> {
    void cadastrar(java.util.Scanner scanner);
    void salvar(T entidade);
    void listar();
    void atualizar();
}
