package dao;

import entidades.Disciplina;
import entidades.Curso;
import dao.conexao.ConnectionFactory;
import entidades.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisciplinaDAO {

    public void salvarDisciplina(Disciplina disciplina) throws SQLException {

        String sql = "insert into disciplina(nome_disciplina) values(?);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, disciplina.nome);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

  public  void deletarDisciplina(int id) throws SQLException {

        String sql = "delete  from disciplina where  id_disciplina = ? ;";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public ArrayList<Disciplina> pesquisarTodasDisciplina() throws SQLException {

        ResultSet res;
        String sql = "select * from disciplina order by 2 asc";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Disciplina> vetorDisciplina = new ArrayList<Disciplina>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.id = res.getInt("id_disciplina");
                disciplina.nome = res.getString("nome_disciplina");
                vetorDisciplina.add(disciplina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close(); // ONDE FECHAR AS CONEXÕES ???????
        return vetorDisciplina;
    }

    public Disciplina pesquisarDisciplina(int id) throws SQLException {

        ResultSet res;
        String sql = "select * from disciplina where id_disciplina=" + id + ";";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Disciplina disciplina = new Disciplina();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {

                disciplina.id = res.getInt("id_disciplina");
                disciplina.nome = res.getString("nome_disciplina");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return disciplina;
    }

    public ArrayList<Disciplina> pesquisarDisciplinasDoProfessor(Professor professor) throws SQLException { // ESSE É UM MÉTODO DE DISCIPLINAS OU PROFESSOR????
        ResultSet res;
        String sql = "select * from disciplina_professor join disciplina using (id_disciplina) where cpf = '" + professor.getCpf() + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Disciplina> vetorDisciplina = new ArrayList<Disciplina>();

        res = ps.executeQuery();
        try {
            while (res.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.id = res.getInt("id_disciplina");
                disciplina.nome = res.getString("nome_disciplina");
                vetorDisciplina.add(disciplina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorDisciplina;
    }

    public void atualizar(Disciplina disciplina) throws SQLException {
        String sql = "";
        sql = "update disciplina set nome_disciplina = '" + disciplina.nome + "' where id_disciplina = '" + disciplina.id + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

 public   void deletarDisciplinaProfessor(String cpf) throws SQLException {
        String sql = "delete from disciplina_professor where cpf ='" + cpf + "'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();

    }

 public   ArrayList<Disciplina> exibirTodasDisciplinas() throws SQLException {

        ResultSet res;
        String sql = "select * from disciplina ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ArrayList<Disciplina> vetorDisciplinas = new ArrayList<Disciplina>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.id = res.getInt("id_disciplina");
                disciplina.nome = res.getString("nome_disciplina");
                vetorDisciplinas.add(disciplina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorDisciplinas;
    }
    
     public ArrayList<Disciplina> pesquisarDisciplinaPeloNome(String nome) throws SQLException {

        ResultSet res;
        ArrayList<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        String sql = "select * from disciplina where nome_disciplina ilike  '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.id = res.getInt("id_disciplina");
                disciplina.nome = res.getString("nome_disciplina");
                listaDisciplina.add(disciplina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaDisciplina;
    }

}
