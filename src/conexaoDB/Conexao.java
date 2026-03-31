package conexaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/cauadm";
	private static final String usuario = "root";
	private static final String senha = "cauamysql";

	 public static Connection criarConexao() {
        Connection conexao = null;

        try {
            Class.forName(driver); // Carrega o driver
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("✅ Conexão com o banco estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Erro: Driver do MySQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar com o banco de dados!");
            e.printStackTrace();
        }

        return conexao;
    }

    public static void fecharConexao(Connection conn, PreparedStatement stat) {
        try {
            if (stat != null) stat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("⚠️ Erro ao fechar conexão ou statement: " + e.getMessage());
        }
    }


    public static void fecharConexao(Connection conn, PreparedStatement stat, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("⚠️ Erro ao fechar recursos: " + e.getMessage());
        }
    }
	
	

}
