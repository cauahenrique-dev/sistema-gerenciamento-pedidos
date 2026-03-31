package pedidos;

import dados.Cliente;
import dados.ClienteDAO;

public class PrincipalTabela {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA TABELA CLIENTE ===");

        
        if (ClienteDAO.criarTabelaCliente()) {
            System.out.println("✅ Tabela cliente criada com sucesso!");
        } else {
            System.out.println("❌ Erro ao criar a tabela cliente.");
        }

       
        System.out.println("\n--- INSERIR CLIENTES ---");
        Cliente c1 = new Cliente("Pedro", "pedro@gmail.com", "31977777777");
        Cliente c2 = new Cliente("Eduardo", "eduardo@gmail.com", "3188888888");

        if (ClienteDAO.incluirCliente(c1))
            System.out.println("✅ Cliente inserido: " + c1);
        else
            System.out.println("❌ Erro ao inserir cliente 1");

        if (ClienteDAO.incluirCliente(c2))
            System.out.println("✅ Cliente inserido: " + c2);
        else
            System.out.println("❌ Erro ao inserir cliente 2");

        
        System.out.println("\n--- ALTERAR CLIENTES ---");
        c1.setFone("31999977777");
        if (ClienteDAO.alterarCliente(c1))
            System.out.println("✅ Cliente 1 atualizado: " + c1);
        else
            System.out.println("❌ Erro ao alterar cliente 1");

        c2.setFone("3177777777");
        if (ClienteDAO.alterarCliente(c2))
            System.out.println("✅ Cliente 2 atualizado: " + c2);
        else
            System.out.println("❌ Erro ao alterar cliente 2");

        
        System.out.println("\n--- CONSULTAR CLIENTE 1 ---");
        Cliente clienteConsultado = ClienteDAO.consultarCliente(c1.getCodCliente());
        if (clienteConsultado != null)
            System.out.println("🔍 Cliente encontrado: " + clienteConsultado);
        else
            System.out.println("❌ Cliente não encontrado.");

        
        System.out.println("\n--- LISTAR TODOS OS CLIENTES ---");
        ClienteDAO.consultarClientes().forEach(System.out::println);

        // === EXCLUIR CLIENTE (opcional) ===
        /*
        System.out.println("\n--- EXCLUIR CLIENTE 2 ---");
        if (ClienteDAO.excluirCliente(c2.getCodCliente())) {
            System.out.println("✅ Cliente 2 excluído com sucesso!");
        } else {
            System.out.println("❌ Erro ao excluir cliente 2.");
        }
        */
    }
}
