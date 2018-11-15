package dao;

import entidades.Setor;
import dao.DisciplinaDAO;
import entidades.Aluno;
import entidades.Curso;
import dao.conexao.ConnectionFactory;
import dao.MetodosBD;
import entidades.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetorDAO {

    public void salvarSetor(Setor setor) throws SQLException {

        String sql = "insert into setor(nome_setor) values(?);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, setor.nome);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

   public void deletarSetor(int id) throws SQLException {

        String sql = "delete  from setor where  id_setor = ? ;";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public ArrayList<Setor> pesquisarTodosSetores() throws SQLException {

        ResultSet res;
        String sql = "select * from setor order by 2 asc";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Setor> vetorSetor = new ArrayList<Setor>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Setor setor = new Setor();
                setor.id = res.getInt("id_setor");
                setor.nome = res.getString("nome_setor");
                vetorSetor.add(setor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close(); // ONDE FECHAR AS CONEXÕES ???????
        return vetorSetor;
    }

    public Setor pesquisarSetor(int id) throws SQLException {

        ResultSet res;
        String sql = "select * from setor where id_setor=" + id + ";";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Setor setor = new Setor();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {

                setor.id = res.getInt("id_setor");
                setor.nome = res.getString("nome_setor");

            }
        } catch (SQLException ex) {
            Logger.getLogger(SetorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return setor;
    }

   public void atualizar(Setor setor) throws SQLException {
        String sql = "";
        sql = "update setor set nome_setor = '" + setor.nome + "' where id_setor = '" + setor.id + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }
    
    
     public ArrayList<Setor> pesquisarSetorPeloNome(String nome) throws SQLException {

        ResultSet res;
        ArrayList<Setor> listaSetor = new ArrayList<Setor>();
        String sql = "select * from setor where nome_setor ilike  '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Setor setor = new Setor();
                setor.id = res.getInt("id_setor");
                setor.nome = res.getString("nome_setor");
                listaSetor.add(setor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaSetor;
    }
}