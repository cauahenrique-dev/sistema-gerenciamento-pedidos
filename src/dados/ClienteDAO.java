package dados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import conexaoDB.Conexao;
import java.util.ArrayList;

// Caua
public class ClienteDAO {
    
    public static boolean criarTabelaCliente() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "CREATE TABLE IF NOT EXISTS cliente (" +
                         "codCliente INT PRIMARY KEY AUTO_INCREMENT, " +
                         "nome VARCHAR(50) NOT NULL, " +
                         "email VARCHAR(50) NOT NULL, " +
                         "fone VARCHAR(15));";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            System.out.println("✅ Tabela 'cliente' criada com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: A tabela 'cliente' não foi criada. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean excluirTabelaCliente() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DROP TABLE IF EXISTS cliente";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            System.out.println("✅ Tabela 'cliente' excluída com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: A tabela 'cliente' não foi excluída. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean incluirCliente(Cliente cli) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "INSERT INTO cliente (nome, email, fone) VALUES (?, ?, ?);";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, cli.getNome());
            stat.setString(2, cli.getEmail());
            stat.setString(3, cli.getFone());
            stat.execute();

            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                cli.setCodCliente(rs.getInt(1));
            }

            System.out.println("✅ Cliente inserido com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: Cliente não inserido. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean alterarCliente(Cliente cli) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "UPDATE cliente SET nome=?, email=?, fone=? WHERE codCliente=?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setString(1, cli.getNome());
            stat.setString(2, cli.getEmail());
            stat.setString(3, cli.getFone());
            stat.setInt(4, cli.getCodCliente());
            stat.execute();

            System.out.println("✅ Cliente alterado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: Cliente não alterado. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean excluirCliente(int cod) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DELETE FROM cliente WHERE codCliente=?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            stat.execute();

            System.out.println("✅ Cliente excluído com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("❌ ERRO: Cliente não excluído. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static Cliente consultarCliente(int cod) {
        Cliente c1 = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM cliente WHERE codCliente = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            rs = stat.executeQuery();

            if (rs.next()) {
                c1 = new Cliente();
                c1.setCodCliente(rs.getInt("codCliente"));
                c1.setNome(rs.getString("nome"));
                c1.setEmail(rs.getString("email"));
                c1.setFone(rs.getString("fone"));
            }
        } catch (Exception e) {
            System.out.println("❌ ERRO na consulta do cliente. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }
        return c1;
    }

    public static ArrayList<Cliente> consultarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM cliente;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                Cliente c1 = new Cliente();
                c1.setCodCliente(rs.getInt("codCliente"));
                c1.setNome(rs.getString("nome"));
                c1.setEmail(rs.getString("email"));
                c1.setFone(rs.getString("fone"));
                lista.add(c1);
            }
        } catch (Exception e) {
            System.out.println("❌ ERRO na consulta de clientes. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }

        return lista;
    }
}
