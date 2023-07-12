
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
import java.util.List;

public class ProdutosDAO {

    ResultSet st;

    public List<ProdutosDTO> listarProduto = new ArrayList<ProdutosDTO>();

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
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!!");

            return status; // return 1 

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

            return ex.getErrorCode();
        }
    }

    public int Consulta(ProdutosDTO produtos) {
        int status;
        var conectaDAO = new conectaDAO();

        try {

            String sql = "select * from produtos";

            /* Executando o comando select */
            ResultSet rs = conectaDAO.stmt.executeQuery(sql);

            listarProduto.removeAll(listarProduto);
            /* Exibindo os resultados */
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listarProduto.add(produto);

            }

            rs.close();
            conectaDAO.stmt.close();
            conectaDAO.conn.close();

            return status = 0;
        } catch (SQLException sqle) {
            System.out.println("Erro efetuando consulta : " + sqle.getMessage());
            status = sqle.getErrorCode();
        }

        return status;
    }

    public boolean VenderProduto(ProdutosDTO Produtos) {
        PreparedStatement st;
        var conectaDAO = new conectaDAO();

        try {
            st = conectaDAO.conn.prepareStatement("UPDATE produtos SET status = ?");
            st.setString(3, "vendido");
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar dados " + ex.getMessage());
            return false;
        }

    }

    public ProdutosDTO consultarDado(String status) {
        try {

            ProdutosDTO produtos = new ProdutosDTO();
            ResultSet rs;
            PreparedStatement st;
            var conectaDAO = new conectaDAO();

            st = conectaDAO.conn.prepareStatement("SELECT * from filmes WHERE status = ? ");
            st.setString(3, "vendido");
            rs = st.executeQuery();
            //verificar se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário, vamos carregar os dados

                produtos.setNome(rs.getString("nome"));
                produtos.setValor(Integer.SIZE);
                produtos.setStatus(rs.getString("status"));
                //retorna um produto consultado
                return produtos;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }
}
