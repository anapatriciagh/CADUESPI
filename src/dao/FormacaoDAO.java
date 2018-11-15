
package dao;

import entidades.Formacao;
import dao.conexao.ConnectionFactory;
import entidades.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rlopes
 */
public class FormacaoDAO {

    public Formacao pesquisarFormacao(String nome, String cpf) throws SQLException {
        ResultSet res;
        String sql = "select * from formacao where nome ='" + nome + "' and cpf ='" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);

        res = ps.executeQuery();
        Formacao formacao = new Formacao();
        while (res.next()) {
            formacao.id = res.getInt("id_formacao");
            formacao.nome = res.getString("nome");
            formacao.titulacao = res.getString("titulacao");
            formacao.lotacao = res.getString("lotacao");
            formacao.cpf = res.getString("cpf");
        }
        ConnectionFactory.getConexao().close();
        return formacao;
    }

    public void cadastrarFormacaoProfessor(Professor professor) {
        for (int i = 0; i < professor.getVetorFormacao().size(); i++) {   // OBS complete as instruções do FOR
            String sqlFormacaoProfessor = "insert into formacao(nome,titulacao,lotacao,cpf) values(?,?,?,?);";
            try {
                PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlFormacaoProfessor);
                ps.setString(1, professor.getVetorFormacao().get(i).nome);
                ps.setString(2, professor.getVetorFormacao().get(i).titulacao);
                ps.setString(3, professor.getVetorFormacao().get(i).lotacao);
                ps.setString(4, professor.getCpf());
                ps.executeUpdate();
                ConnectionFactory.getConexao().close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void deletarFormacaoProfessor(String cpf) throws SQLException {
        String sqlFormacaoProfessor = "delete from formacao_professor where cpf ='" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlFormacaoProfessor);
        ps.executeUpdate();
        String sqlFormacao = "delete from formacao where cpf ='" + cpf + "';";
        PreparedStatement ps1 = ConnectionFactory.getConexao().prepareStatement(sqlFormacao);
        ps1.executeUpdate();
        ConnectionFactory.getConexao().close();
    }
}
