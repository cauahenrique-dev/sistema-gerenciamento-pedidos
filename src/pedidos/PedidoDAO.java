package pedidos;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import conexaoDB.Conexao;
import dados.Cliente;

// Caua
public class PedidoDAO {

    public static boolean criarTabelaPedido() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "CREATE TABLE pedido ("
                    + "codPedido INT PRIMARY KEY AUTO_INCREMENT, "
                    + "data DATE NOT NULL, "
                    + "status VARCHAR(20) NOT NULL, "
                    + "codCliente INT NOT NULL, "
                    + "FOREIGN KEY (codCliente) REFERENCES cliente(codCliente));";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: A tabela pedido não foi criada. - " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean excluirTabelaPedido() {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DROP TABLE pedido;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: A tabela pedido não foi excluída. - " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean incluirPedido(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
          
            String sql = "INSERT INTO pedido (data, status, codCliente) VALUES (?, ?, ?);";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            stat.setString(2, pedido.getStatus());
            stat.setInt(3, pedido.getCliente().getCodCliente());

            stat.execute();

            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                pedido.setCodPedido(rs.getInt(1));
            }

            return true;
        } catch (Exception e) {
            System.out.println("ERRO: Pedido não inserido. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean alterarPedido(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
          
            String sql = "UPDATE pedido SET data = ?, status = ?, codCliente = ? WHERE codPedido = ?;";

            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            stat.setString(2, pedido.getStatus());
            stat.setInt(3, pedido.getCliente().getCodCliente());
            stat.setInt(4, pedido.getCodPedido());

            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: Pedido não alterado. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static boolean excluirPedido(int cod) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            String sql = "DELETE FROM pedido WHERE codPedido = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            stat.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO: Pedido não excluído. " + e.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(conn, stat);
        }
    }

    public static Pedido consultarPedido(int cod) {
        Pedido pedido = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
           
            String sql = "SELECT * FROM pedido WHERE codPedido = ?;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            stat.setInt(1, cod);
            rs = stat.executeQuery();

            if (rs.next()) {
                pedido = new Pedido();
                pedido.setCodPedido(rs.getInt("codPedido"));
                pedido.setData(rs.getDate("data"));
                pedido.setStatus(rs.getString("status"));

                Cliente c1 = new Cliente();
                c1.setCodCliente(rs.getInt("codCliente"));

                
                pedido.setCliente(c1);
            }

        } catch (Exception e) {
            System.out.println("ERRO na consulta do pedido. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }

        return pedido;
    }

    public static ArrayList<Pedido> consultarPedidos() {
        ArrayList<Pedido> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM pedido;";
            conn = Conexao.criarConexao();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodPedido(rs.getInt("codPedido"));
                pedido.setData(rs.getDate("data"));
                pedido.setStatus(rs.getString("status"));

                Cliente c1 = new Cliente();
                c1.setCodCliente(rs.getInt("codCliente"));
                pedido.setCliente(c1);

                lista.add(pedido);
            }

        } catch (Exception e) {
            System.out.println("ERRO na consulta de pedidos. " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, stat, rs);
        }

        return lista;
    }
}
