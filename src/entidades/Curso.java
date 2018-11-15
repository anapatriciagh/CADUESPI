/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import dao.CursoDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rlopes
 */
public class Curso {
 public   int id;
   public String nome;
   
   
   
      public String[] cursoToLinha(Curso curso) {
        String[] novaLinha = new String[]{String.valueOf(curso.id), curso.nome};
        return novaLinha;
    }
      
      
         public Curso adcionarCurso(int id) throws SQLException {
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = cursoDAO.pesquisarCurso(id);
        return curso;
    }
         
         
        public   void cadastrarCurso(Curso curso) throws SQLException {
        CursoDAO cursoDAO = new  CursoDAO();
        cursoDAO.salvarCurso(curso);
        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");
        Aluno aluno = new Aluno();
        

    }
        
           public void deletarCurso() throws SQLException {
               System.out.println(id);
        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.deletarCurso(id);
        JOptionPane.showMessageDialog(null, "Deletado Com Sucesso");
    }
           
           public void atualizarCurso(Curso curso) throws SQLException{
               CursoDAO cursoDAO = new CursoDAO();
               cursoDAO.atualizar(curso);
           }

      
      
}
