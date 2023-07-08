
/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutosDAO {
    ResultSet st;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

    }
    public int salvar(ProdutosDTO produto) {
        int status;
        PreparedStatement st;
        var conectaDAO = new conectaDAO();
        
        try {
            st = conectaDAO.conn.prepareStatement("insert into produtos (nome, valor, status) VALUES (?,?,?)");

            st.setString(1, produto.getNome());
            st.setInt(2, Integer.valueOf(produto.getValor())); // Convert string to integer
            st.setString(3, produto.getStatus());

            status = st.executeUpdate();
            return status; // return 1 

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
