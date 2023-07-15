
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {

    public Connection conn;
    public Statement stmt;

    public conectaDAO() {
        Conectar();

    }

    private void Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC carregado");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Driver JDBC nao encontrado : " + cnfe.getMessage());
            return;
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?allowPublicKeyRetrieval=true&user=root&password=teste123&useSSL=false");
            System.out.println("Conexao com o banco de dados estabelecida.");
        } catch (SQLException sqle) {
            System.out.println("Erro na conexao ao Bando de Dados : " + sqle.getMessage());
            return;
        }

        try {
            stmt = conn.createStatement();
            System.out.println("Pronto para execucao de comandos sql.");
        } catch (SQLException sqle) {
            System.out.println("Erro no acesso ao Bando de Dados : " + sqle.getMessage());
            return;
        }

    }

}
