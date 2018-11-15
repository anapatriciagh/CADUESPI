/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.util;

import dao.PessoaDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author rlopes
 */
public class MetodosAplicacao {

    public String dataToString(Date data) throws ParseException {

        SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        String result = out.format(in.parse(data.toString()));
        return result;
    }

    public boolean AutenticarUsuario(String senhaUsuario, String loginUsuario) throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Validacao validacao = new Validacao();
        String cpfAutenticado = pessoaDAO.verificarUsuario(loginUsuario, senhaUsuario);
        String nome = pessoaDAO.pesquisarPrimeiroNome(cpfAutenticado);
        while (validacao.validarCamposEmBranco(senhaUsuario) != true && validacao.validarCamposEmBranco(loginUsuario) != true) {
            if (loginUsuario.equals(cpfAutenticado)) {
                JOptionPane.showMessageDialog(null, "Bem Vindo!!\n" + nome);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Acesso Negado!");
                return false;
            }
        }
        return false;
    }
    
    
      public boolean isAlterarSenhaUsuario(String senha, String novaSenha) throws SQLException {
        Validacao validacao = new Validacao();
        //String cpfAutenticado = pessoaDAO.verificarUsuario(novaSenha, senha);
        //String nome = pessoaDAO.pesquisarPrimeiroNome(cpfAutenticado);
        while (validacao.validarCamposEmBranco(senha) != true && validacao.validarCamposEmBranco(novaSenha) != true) {
            if (novaSenha.equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha Alterada!!\n");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Acesso Negado!");
                return false;
            }
        }
        return false;
    }
    
    
    

    public java.sql.Date converteDataUtilToSql(Date data) {

        Date dataUtil = data;
        java.sql.Date dataSql = null;

        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converte data para sql: " + e.getMessage());
        }

        return dataSql;
    }

}
