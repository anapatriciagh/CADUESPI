package dao;

import dao.conexao.ConnectionFactory;
import entidades.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO {

    public String converterData(Date data) throws ParseException {
        SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");

        String result = out.format(in.parse(data.toString()));

        return result;

    }

    public String pesquisarPrimeiroNome(String cpf) throws SQLException {
        String sql = "select split_part(nome,' ',1)from pessoa where cpf ='" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        String nome = new String();
        ResultSet res;
        res = ps.executeQuery();
        while (res.next()) {
            nome = res.getString("split_part");
        }
        ConnectionFactory.getConexao().close();
        return nome;
    }

    public String verificarUsuario(String cpf, String senha) throws SQLException {
        String sql = "select * from pessoa where pessoa.cpf ='" + cpf + "' and pessoa.senha ='" + senha + "' and pessoa.nivel_acesso !='0' ;";
        String cpf1 = new String();
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ResultSet res;
        res = ps.executeQuery();
        while (res.next()) {
            cpf1 = res.getString("cpf");
        }
        ConnectionFactory.getConexao().close();
        return cpf1;
    }

    public String pesquisarSenha(String senha) throws SQLException {
        String sql = "select * from pessoa where pessoa.senha ='" + senha + "';";
        String senha1 = new String();
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ResultSet res;
        res = ps.executeQuery();
        while (res.next()) {
            senha1 = res.getString("senha");
        }
        ConnectionFactory.getConexao().close();
        return senha1;
    }

    public ArrayList<Pessoa> pesquisarTodosUsuario() throws SQLException {
        ResultSet res;
        String sql = "select cpf , nome , nivel_acesso from pessoa ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Pessoa> vetorPessoa = new ArrayList<>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf(res.getString("cpf"));
                pessoa.setNome(res.getString("nome"));
                pessoa.setNivelAcesso(res.getString("nivel_acesso"));
                vetorPessoa.add(pessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorPessoa;
    }

    public Pessoa pesquisarPessoa(String cpf) throws SQLException {
        ResultSet res;
        String sql = "select cpf , nome , nivel_acesso from pessoa  where  cpf = '" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Pessoa pessoa = new Pessoa();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                pessoa.setCpf(res.getString("cpf"));
                pessoa.setNome(res.getString("nome"));
                pessoa.setNivelAcesso(res.getString("nivel_acesso"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return pessoa;
    }

    public void atualizarPerfil(Pessoa pessoa) throws SQLException {

        String sql = "update  pessoa set  nivel_acesso = ?  where cpf ='" + pessoa.getCpf() + "'; ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, pessoa.getNivelAcesso());
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();

    }

    public void alterarSenha(Pessoa pessoa, String senha) throws SQLException {

        String sql = "update pessoa set senha = ? where cpf = '" + pessoa.getCpf() + "' ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, senha);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();

    }
}
