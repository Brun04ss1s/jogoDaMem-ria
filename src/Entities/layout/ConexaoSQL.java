package Entities.layout;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class ConexaoSQL {

    private final String url = "jdbc:mysql://localhost:3306/jogodamemoria";
    private final String user = "root";
    private final String password = "321206";

    // Metodo de Conexão
    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de conexao: " + e.getMessage());
            return null;
        }
    }

    // Metodo QUERY para buscar dados filtrados por nome, agora retornando o ResultSet
    public ResultSet query(String nome) {
        String sql = "SELECT nome, pontos, data FROM estatistica WHERE nome = ?";

        try {
            Connection conn = conectaBD();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nome); // Define o parâmetro para o filtro por nome
                return pstmt.executeQuery(); // Retorna o ResultSet
            }
        } catch (SQLException e) {
            System.out.println("Erro na execução da query: " + e.getMessage());
        }
        return null;
    }

    // Metodo INSERT para inserir dados, agora retornando um booleano para sucesso
    public boolean insert(String nome, int pontos) {
        String sql = "INSERT INTO estatistica (nome, pontos, data) VALUES (?, ?, ?)";

        try (Connection conn = conectaBD(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setInt(2, pontos);
            pstmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // Retorna true se pelo menos uma linha foi inserida

        } catch (SQLException e) {
            System.out.println("Erro na insercao: " + e.getMessage());
            return false; // Retorna false em caso de falha
        }
    }

    public static void main(String[] args) {
        ConexaoSQL conexao = new ConexaoSQL();

        // Executa query para listar dados de um nome específico
        ResultSet rs = conexao.query("Arsenio");

        // Exibe os resultados vindos do banco de dados
        try {
            if (rs != null) {
                while (rs.next()) {
                    String nomeResult = rs.getString("nome");
                    int pontos = rs.getInt("pontos");
                    java.sql.Date data = rs.getDate("data");

                    System.out.println("Nome: " + nomeResult + ", Pontos: " + pontos + ", Data: " + data);
                }
                rs.close(); // Fecha o ResultSet
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler dados do ResultSet: " + e.getMessage());
        }

        // Insere um novo registro e verifica o sucesso
        boolean sucessoInsercao = conexao.insert("ArsenioTeste", 123);
        if (sucessoInsercao) {
            System.out.println("Insercao bem-sucedida!");
        } else {
            System.out.println("Falha na insercao.");
        }

    }
}
