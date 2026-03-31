package pedidos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import conexaoDB.Conexao;


// Caua e Melissa
public class ItemPedidoDAO {

    // Criação da tabela
    public static boolean criarTabelaItemPedido() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = """
                CREATE TABLE IF NOT EXISTS item_pedido (
                    codItem INT PRIMARY KEY AUTO_INCREMENT,
                    codProduto INT NOT NULL,
                    codPedido INT NOT NULL,
                    quantidade INT NOT NULL,
                    precoUnit DOUBLE NOT NULL,
                    FOREIGN KEY (codProduto) REFERENCES produto(codProduto)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    FOREIGN KEY (codPedido) REFERENCES pedido(codPedido)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
                """;

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            System.out.println("✅ Tabela 'item_pedido' criada com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("❌ ERRO: A tabela 'item_pedido' não foi criada. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    // Exclusão da tabela
    public static boolean excluirTabelaItemPedido() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DROP TABLE IF EXISTS item_pedido";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            System.out.println("✅ Tabela 'item_pedido' excluída com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: A tabela 'item_pedido' não foi excluída. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    // Inserção
    public static boolean incluirItemPedido(ItemPedido item) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "INSERT INTO item_pedido (quantidade, codProduto, codPedido, precoUnit) VALUES (?, ?, ?, ?);";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setInt(1, item.getQuantidade());
            stat.setInt(2, item.getProduto().getCodProduto());
            stat.setInt(3, item.getPedido().getCodPedido());
            stat.setDouble(4, item.getPrecoUnit());
            stat.execute();

            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                item.setCodItem(rs.getInt(1));
            }

            System.out.println("✅ Item do pedido inserido com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: Item do pedido não inserido. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    // Atualização
    public static boolean alterarItemPedido(ItemPedido item) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = """
                UPDATE item_pedido
                SET quantidade = ?, codProduto = ?, codPedido = ?, precoUnit = ?
                WHERE codItem = ?;
                """;

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, item.getQuantidade());
            stat.setInt(2, item.getProduto().getCodProduto());
            stat.setInt(3, item.getPedido().getCodPedido());
            stat.setDouble(4, item.getPrecoUnit());
            stat.setInt(5, item.getCodItem());
            stat.execute();

            System.out.println("✅ Item do pedido alterado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: Item do pedido não alterado. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    // Exclusão
    public static boolean excluirItemPedido(int cod) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DELETE FROM item_pedido WHERE codItem = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            stat.execute();

            System.out.println("✅ Item do pedido excluído com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: Item do pedido não excluído. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    // Consulta individual
    public static ItemPedido consultarItemPedido(int cod) {
        ItemPedido item = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM item_pedido WHERE codItem = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            rs = stat.executeQuery();

            if (rs.next()) {
                item = new ItemPedido();
                item.setCodItem(rs.getInt("codItem"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnit(rs.getDouble("precoUnit"));

                Produto produto = new Produto();
                produto.setCodProduto(rs.getInt("codProduto"));
                item.setProduto(produto);

                Pedido pedido = new Pedido();
                pedido.setCodPedido(rs.getInt("codPedido"));
                item.setPedido(pedido);
            }

        } catch (Exception e) {
            System.out.println("❌ ERRO na consulta do item_pedido. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }
        return item;
    }

    // Consulta geral
    public static ArrayList<ItemPedido> consultarItensPedido() {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM item_pedido;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setCodItem(rs.getInt("codItem"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnit(rs.getDouble("precoUnit"));

                Produto produto = new Produto();
                produto.setCodProduto(rs.getInt("codProduto"));
                item.setProduto(produto);

                Pedido pedido = new Pedido();
                pedido.setCodPedido(rs.getInt("codPedido"));
                item.setPedido(pedido);

                lista.add(item);
            }

        } catch (Exception e) {
            System.out.println("❌ ERRO na consulta de itens_pedido. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }

        return lista;
    }
}
