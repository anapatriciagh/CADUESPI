/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.util;

import entidades.Aluno;
import entidades.Professor;

/**
 *
 * @author Rubens
 */
public class MetodosMatricula {
    
      public String[] matriculaToLinha(Professor professor) {
        String[] novaLinha = new String[]{professor.getMatricula(),professor.getDataMatricula().toString(), professor.getNome(), professor.getCpf(), professor.getDataNascimento().toString(), professor.getNacionalidade(), professor.getNaturalidade(), professor.getSexo(), professor.getEmail(), professor.getTelefone1()};
        return novaLinha;
    }
      
       public String[] matriculaToLinha(Aluno aluno) {
        String[] novaLinha = new String[]{aluno.getMatricula(),aluno.getDataMatricula().toString(), aluno.getNome(), aluno.getCpf(), aluno.getDataNascimento().toString(), aluno.getNacionalidade(), aluno.getNaturalidade(), aluno.getSexo(), aluno.getEmail(), aluno.getTelefone1()};
        return novaLinha;
    }
      
    
}
