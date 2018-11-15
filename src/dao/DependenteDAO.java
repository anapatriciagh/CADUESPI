/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Dependentes;
import dao.conexao.ConnectionFactory;
import entidades.Professor;
import java.sql.Date;
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
public class DependenteDAO {

    public Dependentes pesquisarDependente(String nome, Date dataNascimento) throws SQLException {
        ResultSet res;
        String sql = "select * from dependente where nome ='" + nome + "' and data_nascimento ='" + dataNascimento + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        Dependentes dependente = new Dependentes();
        while (res.next()) {
            dependente.idDependente = res.getInt("id_dependente");
            dependente.nome = res.getString("nome");
            dependente.dataNascimento = res.getDate("data_nascimento");
            dependente.grauParentesco = res.getString("grau_parentesco");
        }
        ConnectionFactory.getConexao().close();
        return dependente;
    }

    public void cadastrarDependentesProfessor(Professor professor) {
        String sqlDependentesProfessor = "insert into dependente(nome, data_nascimento, grau_parentesco) values(?,?,?);";
        for (int i = 0; i < professor.getVetorDependentes().size(); i++) {
            try {
                PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlDependentesProfessor);
                ps.setString(1, professor.getVetorDependentes().get(i).nome);
                ps.setDate(2, new java.sql.Date(professor.getVetorDependentes().get(i).dataNascimento.getTime())); /* atenção pois esta é a data que Teresina vai mandar */

                ps.setString(3, professor.getVetorDependentes().get(i).grauParentesco);
                ps.executeUpdate();
                ConnectionFactory.getConexao().close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

  public  ArrayList<Dependentes> pesquisarListaDependentes(Professor professor) throws SQLException {
        ResultSet res;
        String sql = "select * from dependente_professor join dependente using (id_dependente) where cpf ='" + professor.getCpf() + "'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Dependentes> vetorDependentes = new ArrayList<Dependentes>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Dependentes dependentes = new Dependentes();
                dependentes.idDependente = res.getInt("id_dependente");
                dependentes.nome = res.getString("nome");
                dependentes.grauParentesco = res.getString("grau_parentesco");
                dependentes.dataNascimento = res.getDate("data_nascimento");
                vetorDependentes.add(dependentes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorDependentes;
    }

}
