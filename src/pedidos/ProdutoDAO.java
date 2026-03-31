package pedidos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import conexaoDB.Conexao;
import java.util.ArrayList;

// Caua
public class ProdutoDAO {

    public static boolean criarTabelaProduto() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "CREATE TABLE produto ("
                    + "codProduto INT PRIMARY KEY AUTO_INCREMENT, "
                    + "nome VARCHAR(50) NOT NULL, "
                    + "preco DOUBLE NOT NULL, "
                    + "estoque INT NOT NULL);";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: A tabela produto não foi criada. - " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean excluirTabelaProduto() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DROP TABLE produto;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: A tabela produto não foi excluída. - " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean incluirProduto(Produto prod) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "INSERT INTO produto (nome, preco, estoque) VALUES (?, ?, ?);";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, prod.getNome());
            stat.setDouble(2, prod.getPreco());
            stat.setInt(3, prod.getEstoque());

            stat.execute();

            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                prod.setCodProduto(rs.getInt(1));
            }

            return true;
        } catch (Exception e) {
            System.out.println("ERRO: Produto não inserido. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean alterarProduto(Produto prod) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "UPDATE produto SET nome = ?, preco = ?, estoque = ? WHERE codProduto = ?;";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setString(1, prod.getNome());
            stat.setDouble(2, prod.getPreco());
            stat.setInt(3, prod.getEstoque());
            stat.setInt(4, prod.getCodProduto());

            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: Produto não alterado. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean excluirProduto(int cod) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DELETE FROM produto WHERE codProduto = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: Produto não excluído. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    // ✅ Método para consultar um produto pelo código
    public static Produto consultarProduto(int cod) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Produto prod = null;

        try {
            String sql = "SELECT * FROM produto WHERE codProduto = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            rs = stat.executeQuery();

            if (rs.next()) {
                prod = new Produto();
                prod.setCodProduto(rs.getInt("codProduto"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setEstoque(rs.getInt("estoque"));
            }
        } catch (Exception e) {
            System.out.println("ERRO na consulta do produto. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }

        return prod;
    }

    // ✅ Método para listar todos os produtos
    public static ArrayList<Produto> consultarProdutos() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Produto> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCodProduto(rs.getInt("codProduto"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setEstoque(rs.getInt("estoque"));
                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println("ERRO na consulta de produtos. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }

        return lista;
    }
}
