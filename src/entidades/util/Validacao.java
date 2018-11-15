/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.util;

import javax.swing.JOptionPane;

/**
 *
 * @author rlopes
 */
public class Validacao {

  public  boolean validarCamposEmBranco(String campo) {
        if (campo.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Obrigatorio em Branco", "Erro!!", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            return false;
        }

    }
  
  
  public void pesquisaErrada(){
      JOptionPane.showMessageDialog(null, "Dados Não Encontrados !!");
      
  }
    public void alunoSemCurso(){
            JOptionPane.showMessageDialog(null, "Curso Obrigatorio", "Erro!!", JOptionPane.ERROR_MESSAGE);
      
  }
    public void cadastroRealizado(){
      JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso !!");
      
  }
    
     public void cpfInvalido(){
            JOptionPane.showMessageDialog(null, "CPF Invalido", "Erro!!", JOptionPane.ERROR_MESSAGE);
      
  }
     
         public void dadosInvalidos(){
            JOptionPane.showMessageDialog(null, "Dados Invalidos", "Erro!!", JOptionPane.ERROR_MESSAGE);
      
  }

}
