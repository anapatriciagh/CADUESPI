package replicar;


import dao.AlunoDAO;
import dao.conexao.ConnectionFactoryLocal;
import entidades.Aluno;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GARCIA
 */
public class replicar {
    
   public void cpf(String cpf) throws SQLException{
    AlunoDAO alunoDAO = new AlunoDAO();
    Aluno aluno = new Aluno();
    aluno = alunoDAO.pesquisarAlunoComMatricula(cpf);
        cadastrarAlunoLocal(aluno);
    };
   
  public void cadastrarAlunoLocal(Aluno aluno) throws SQLException { // FALTA TESTAR
        String sql = "insert into aluno (cpf,nome,rg,rg_orgao_emissor,rg_orgao_emissor_uf,rg_data_emissao,"
                + "data_nascimento,nacionalidade,naturalidade,naturalidade_uf,estado_civil,sexo,grau_escolaridade,"
                + "titulo_eleitor, titulo_eleitor_secao, titulo_eleitor_zona, titulo_eleitor_uf,titulo_eleitor_data_emissao,"
                + "certificado_militar_numero,certificado_militar_tipo, certificado_militar_serie, "
                + "certificado_militar_categoria,certificado_militar_csm, certificado_militar_rm, grupo_sanguineo, fator_rh,"
                + "filiacao_mae, filiacao_pai, email, telefone_1, telefone_2, endereco, numero, bairro,municipio,uf,cep)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ConnectionFactoryLocal.getConexao().prepareStatement(sql);
            ps.setString(1, aluno.getCpf());
            ps.setString(2, aluno.getNome());
            ps.setString(3, aluno.getRg().getRg());
            ps.setString(4, aluno.getRg().getRgOrgaoEmissor());
            ps.setString(5, aluno.getRg().getRgOrgaoEmissorUf());
            ps.setDate(6, new java.sql.Date(aluno.getRg().getRgDataEmissao().getTime()));
            ps.setDate(7, new java.sql.Date(aluno.getDataNascimento().getTime()));
            ps.setString(8, aluno.getNacionalidade());
            ps.setString(9, aluno.getNaturalidade());
            ps.setString(10, aluno.getNaturalidadeUf());
            ps.setString(11, aluno.getEstadoCivil());
            ps.setString(12, aluno.getSexo());
            ps.setString(13, aluno.getGrauEscolaridade());
            ps.setString(14, aluno.getTituloEleitor().getTituloEleitor());
            ps.setString(15, aluno.getTituloEleitor().getTituloEleitorSecao());
            ps.setString(16, aluno.getTituloEleitor().getTituloEleitorZona());
            ps.setString(17, aluno.getTituloEleitor().getTituloEleitorUf());
            ps.setDate(18, new java.sql.Date(aluno.getTituloEleitor().getTituloEleitorDataEmissao().getTime()));
            ps.setString(19, aluno.getCertificadoMilitar().getCertificadoMilitarNumero());
            ps.setString(20, aluno.getCertificadoMilitar().getCertificadoMilitarTipo());
            ps.setString(21, aluno.getCertificadoMilitar().getCertificadoMilitarSerie());
            ps.setString(22, aluno.getCertificadoMilitar().getCertificadoMilitarCategoria());
            ps.setString(23, aluno.getCertificadoMilitar().getCertificadoMilitarCsm());
            ps.setString(24, aluno.getCertificadoMilitar().getCertificadoMilitarRm());
            ps.setString(25, aluno.getGrupoSanguineo());
            ps.setString(26, aluno.getFatorRh());
            ps.setString(27, aluno.getFiliacaoMae());
            ps.setString(28, aluno.getFiliacaoPai());
            ps.setString(29, aluno.getEmail());
            ps.setString(30, aluno.getTelefone1());
            ps.setString(31, aluno.getTelefone2());
            ps.setString(32, aluno.getEndereco().getEndereco());
            ps.setString(33, aluno.getEndereco().getNumero());
            ps.setString(34, aluno.getEndereco().getBairro());
            ps.setString(35, aluno.getEndereco().getMunicipio());
            ps.setString(36, aluno.getEndereco().getUf());
            ps.setString(37, aluno.getEndereco().getCep());
            ps.executeUpdate();
            ConnectionFactoryLocal.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*cadastrarCursosAluno(aluno);
        if (aluno.getMatricula() != null) {
            aluno.cadastrarMatricula(aluno);
        }*/

    }
  
    
}
