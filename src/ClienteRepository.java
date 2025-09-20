import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
        private List<Cliente> clientes = new ArrayList<>();

        public void salvar(Cliente cliente) {
            // Futuramente, poderia adicionar lógica para não duplicar clientes
            this.clientes.add(cliente);
        }

        public List<Cliente> buscarTodos() {
            return this.clientes;
        }

        public Cliente buscarPorIndice(int index) {
            if (index >= 0 && index < clientes.size()) {
                return clientes.get(index);
            }
            return null; // Ou lançar uma exceção
        }
    }
}
