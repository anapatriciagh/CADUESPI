/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Curso;
import entidades.Aluno;
import dao.conexao.ConnectionFactory;
import entidades.Disciplina;
import entidades.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rlopes
 */
public class CursoDAO {

    public Curso pesquisarCursoALuno(Aluno aluno) throws SQLException { // ESSE É UM MÉTODO DE DISCIPLINAS OU PROFESSOR????
        ResultSet res;
        String sql = "select * from aluno_curso join curso using (id_curso) where cpf = '" + aluno.getCpf() + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Curso curso = new Curso();
        res = ps.executeQuery();
        try {
            while (res.next()) {

                curso.id = res.getInt("id_curso");
                curso.nome = res.getString("nome_curso");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return curso;
    }

    public ArrayList<Curso> pesquisarTodosCursos() throws SQLException {

        ResultSet res;
        String sql = "select * from curso order by 2 asc";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Curso> vertorCurso = new ArrayList<Curso>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Curso curso = new Curso();
                curso.id = res.getInt("id_curso");
                curso.nome = res.getString("nome_curso");
                vertorCurso.add(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close(); // ONDE FECHAR AS CONEXÕES ???????
        return vertorCurso;
    }

    public Curso pesquisarCurso(int id) throws SQLException {

        ResultSet res;
        String sql = "select * from curso where id_curso=" + id + ";";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Curso curso = new Curso();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {

                curso.id = res.getInt("id_curso");
                curso.nome = res.getString("nome_curso");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return curso;
    }

    public ArrayList<Curso> pesquisarCursoPeloNome(String nome) throws SQLException {

        ResultSet res;
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        String sql = "select * from curso where nome_curso ilike  '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Curso curso = new Curso();
                curso.id = res.getInt("id_curso");
                curso.nome = res.getString("nome_curso");
                listaCursos.add(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaCursos;
    }

    public void salvarCurso(Curso curso) throws SQLException {

        String sql = "insert into curso(nome_curso) values(?);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, curso.nome);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public void deletarCurso(int id) throws SQLException {

        String sql = "delete  from curso where  id_curso = ? ;";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public void atualizar(Curso curso) throws SQLException {
        String sql = "";
        sql = "update curso set nome_curso = '" + curso.nome + "' where id_curso = '" + curso.id + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

}
