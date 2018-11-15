package dao;

import entidades.Professor;
import dao.conexao.ConnectionFactory;
import entidades.Dependentes;
import entidades.Formacao;
import entidades.util.CertificadoMilitar;
import entidades.util.Endereco;
import entidades.util.Rg;
import entidades.util.TituloEleitor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDAO {

    private String sql;
    //******************* PROFESSOR**********************

  public  void cadastrarProfessor(Professor professor) throws SQLException { // FALTA TESTAR
        DependenteDAO dependenteDAO = new DependenteDAO();
        FormacaoDAO formacaoDAO = new FormacaoDAO();
        String sql = "insert into professor (cpf,nome,rg,rg_orgao_emissor,rg_orgao_emissor_uf,rg_data_emissao,"
                + "data_nascimento,nacionalidade,naturalidade,naturalidade_uf,estado_civil,sexo,grau_escolaridade,"
                + "titulo_eleitor, titulo_eleitor_secao, titulo_eleitor_zona, titulo_eleitor_uf,titulo_eleitor_data_emissao,"
                + "certificado_militar_numero,certificado_militar_tipo, certificado_militar_serie, "
                + "certificado_militar_categoria,certificado_militar_csm, certificado_militar_rm, grupo_sanguineo, fator_rh,"
                + "filiacao_mae, filiacao_pai, email, telefone_1, telefone_2, endereco, numero, bairro,municipio,uf,cep,"
                + "pis,pis_data_inscricao,carteira_trabalho,carteira_trabalho_serie, carteira_trabalho_uf, carteira_trabalho_data_emissao,"
                + "cnh,cnh_categoria, cnh_data_emissao, conjuge, conjuge_cpf ) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, professor.getCpf());
            ps.setString(2, professor.getNome());
            ps.setString(3, professor.getRg().getRg());
            ps.setString(4, professor.getRg().getRgOrgaoEmissor());
            ps.setString(5, professor.getRg().getRgOrgaoEmissorUf());
            ps.setDate(6, new java.sql.Date(professor.getRg().getRgDataEmissao().getTime()));
            ps.setDate(7, new java.sql.Date(professor.getDataNascimento().getTime()));
            ps.setString(8, professor.getNacionalidade());
            ps.setString(9, professor.getNaturalidade());
            ps.setString(10, professor.getNaturalidadeUf());
            ps.setString(11, professor.getEstadoCivil());
            ps.setString(12, professor.getSexo());
            ps.setString(13, professor.getFormacao());
            ps.setString(14, professor.getTituloEleitor().getTituloEleitor());
            ps.setString(15, professor.getTituloEleitor().getTituloEleitorSecao());
            ps.setString(16, professor.getTituloEleitor().getTituloEleitorZona());
            ps.setString(17, professor.getTituloEleitor().getTituloEleitorUf());
            ps.setDate(18, new java.sql.Date(professor.getTituloEleitor().getTituloEleitorDataEmissao().getTime()));
            ps.setString(19, professor.getCertificadoMilitar().getCertificadoMilitarNumero());
            ps.setString(20, professor.getCertificadoMilitar().getCertificadoMilitarTipo());
            ps.setString(21, professor.getCertificadoMilitar().getCertificadoMilitarSerie());
            ps.setString(22, professor.getCertificadoMilitar().getCertificadoMilitarCategoria());
            ps.setString(23, professor.getCertificadoMilitar().getCertificadoMilitarCsm());
            ps.setString(24, professor.getCertificadoMilitar().getCertificadoMilitarRm());
            ps.setString(25, professor.getGrupoSanguineo());
            ps.setString(26, professor.getFatorRh());
            ps.setString(27, professor.getFiliacaoMae());
            ps.setString(28, professor.getFiliacaoPai());
            ps.setString(29, professor.getEmail());
            ps.setString(30, professor.getTelefone1());
            ps.setString(31, professor.getTelefone2());
            ps.setString(32, professor.getEndereco().getEndereco());
            ps.setString(33, professor.getEndereco().getNumero());
            ps.setString(34, professor.getEndereco().getBairro());
            ps.setString(35, professor.getEndereco().getMunicipio());
            ps.setString(36, professor.getEndereco().getUf());
            ps.setString(37, professor.getEndereco().getCep());
            ps.setString(38, professor.getPis());
            ps.setDate(39, new java.sql.Date(professor.getPisDataInscricao().getTime()));
            ps.setString(40, professor.getCarteiraTrabalho());
            ps.setString(41, professor.getCarteiraTrabalhoSerie());
            ps.setString(42, professor.getCarteiraTrabalhoUf());
            ps.setDate(43, new java.sql.Date(professor.getCarteiraTrabalhoDataEmissao().getTime()));
            ps.setString(44, professor.getCnh());
            ps.setString(45, professor.getCnhCategoria());
            ps.setDate(46, new java.sql.Date(professor.getCnhDataEmissao().getTime()));
            ps.setString(47, professor.getConjuge());
            ps.setString(48, professor.getConjugeCpf());
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < professor.getVetorDisciplina().size(); i++) {
            MetodosBD metodosBD = new MetodosBD();
            String sqlMinistra = "insert into disciplina_professor(id_disciplina,cpf) values(" + professor.getVetorDisciplina().get(i).id + ",'" + professor.getCpf() + "');";
            metodosBD.executarInstrucao(sqlMinistra);
            System.out.println(sqlMinistra);
        }
        dependenteDAO.cadastrarDependentesProfessor(professor);
        cadastrarRelacaoDependentesProfessor(professor);
        formacaoDAO.cadastrarFormacaoProfessor(professor);
        cadastrarRelacaoFormacaoProfessor(professor);
        if (professor.getMatricula() != null) {
            professor.cadastrarMatricula(professor);
        }

    }

    public void CadastrarProfessorSemDisciplinas(Professor professor) throws SQLException { // FALTA TESTAR
        String sql = "insert into professor (cpf,nome,rg,rg_orgao_emissor,rg_orgao_emissor_uf,rg_data_emissao,"
                + "data_nascimento,nacionalidade,naturalidade,naturalidade_uf,estado_civil,sexo,grau_escolaridade,"
                + "titulo_eleitor, titulo_eleitor_secao, titulo_eleitor_zona, titulo_eleitor_uf,titulo_eleitor_data_emissao,"
                + "certificado_militar_numero,certificado_militar_tipo, certificado_militar_serie, "
                + "certificado_militar_categoria,certificado_militar_csm, certificado_militar_rm, grupo_sanguineo, fator_rh,"
                + "filiacao_mae, filiacao_pai, email, telefone_1, telefone_2, endereco, numero, bairro,municipio,uf,cep,"
                + "pis,pis_data_inscricao,carteira_trabalho,carteira_trabalho_serie, carteira_trabalho_uf, carteira_trabalho_data_emissao,"
                + "cnh,cnh_categoria, cnh_data_emissao, conjuge, conjuge_cpf ) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, professor.getCpf());
            ps.setString(2, professor.getNome());
ps.setString(3, professor.getRg().getRg());
            ps.setString(4, professor.getRg().getRgOrgaoEmissor());
            ps.setString(5, professor.getRg().getRgOrgaoEmissorUf());
            ps.setDate(6, new java.sql.Date(professor.getRg().getRgDataEmissao().getTime()));
            ps.setDate(7, new java.sql.Date(professor.getDataNascimento().getTime()));
            ps.setString(8, professor.getNacionalidade());
            ps.setString(9, professor.getNaturalidade());
            ps.setString(10, professor.getNaturalidadeUf());
            ps.setString(11, professor.getEstadoCivil());
            ps.setString(12, professor.getSexo());
            ps.setString(13, professor.getFormacao());
            ps.setString(14, professor.getTituloEleitor().getTituloEleitor());
            ps.setString(15, professor.getTituloEleitor().getTituloEleitorSecao());
            ps.setString(16, professor.getTituloEleitor().getTituloEleitorZona());
            ps.setString(17, professor.getTituloEleitor().getTituloEleitorUf());
            ps.setDate(18, new java.sql.Date(professor.getTituloEleitor().getTituloEleitorDataEmissao().getTime()));
            ps.setString(19, professor.getCertificadoMilitar().getCertificadoMilitarNumero());
            ps.setString(20, professor.getCertificadoMilitar().getCertificadoMilitarTipo());
            ps.setString(21, professor.getCertificadoMilitar().getCertificadoMilitarSerie());
            ps.setString(22, professor.getCertificadoMilitar().getCertificadoMilitarCategoria());
            ps.setString(23, professor.getCertificadoMilitar().getCertificadoMilitarCsm());
            ps.setString(24, professor.getCertificadoMilitar().getCertificadoMilitarRm());
            ps.setString(25, professor.getGrupoSanguineo());
            ps.setString(26, professor.getFatorRh());
            ps.setString(27, professor.getFiliacaoMae());
            ps.setString(28, professor.getFiliacaoPai());
            ps.setString(29, professor.getEmail());
            ps.setString(30, professor.getTelefone1());
            ps.setString(31, professor.getTelefone2());
            ps.setString(32, professor.getEndereco().getEndereco());
            ps.setString(33, professor.getEndereco().getNumero());
            ps.setString(34, professor.getEndereco().getBairro());
            ps.setString(35, professor.getEndereco().getMunicipio());
            ps.setString(36, professor.getEndereco().getUf());
            ps.setString(37, professor.getEndereco().getCep());
            ps.setString(38, professor.getPis());
            ps.setDate(39, new java.sql.Date(professor.getPisDataInscricao().getTime()));
            ps.setString(40, professor.getCarteiraTrabalho());
            ps.setString(41, professor.getCarteiraTrabalhoSerie());
            ps.setString(42, professor.getCarteiraTrabalhoUf());
            ps.setDate(43, new java.sql.Date(professor.getCarteiraTrabalhoDataEmissao().getTime()));
            ps.setString(44, professor.getCnh());
            ps.setString(45, professor.getCnhCategoria());
            ps.setDate(46, new java.sql.Date(professor.getCnhDataEmissao().getTime()));
            ps.setString(47, professor.getConjuge());
            ps.setString(48, professor.getConjugeCpf());
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void deletarProfessor(Professor professor) throws SQLException { // FALTA TESTAR
        String sql = "delete  from professor where  cpf= '" + professor.getCpf() + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public void deletarMatricula(Professor professor) {

        MetodosBD bd = new MetodosBD();
        String sqlMatriculaProfessor = "delete from matricula_professor where id_matricula ='" + professor.getMatricula() + "';";
        String sqlMatricula = "delete from matricula where id_matricula ='" + professor.getMatricula() + "';";
        bd.executarInstrucao(sqlMatriculaProfessor);
        bd.executarInstrucao(sqlMatricula);
    }

   public ArrayList<Professor> pesquisarTodosProfessores() throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor left join matricula_professor using(cpf)";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Professor> vetorProfessor = new ArrayList<Professor>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Professor professor = new Professor();
                professor.setMatricula(res.getString("id_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setFormacao(res.getString("grau_escolaridade"));
                
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                
                professor.setPis(res.getString("pis"));
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                vetorProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorProfessor;
    }

    public ArrayList<Professor> pesquisarListaProfessorComMatricula() throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor join matricula_professor using (cpf) join matricula using (id_matricula);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Professor> vetorProfessor = new ArrayList<Professor>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Professor professor = new Professor();
                professor.setMatricula(res.getString("id_matricula"));
                professor.setDataMatricula(res.getDate("data_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setFormacao(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPis(res.getString("pis"));
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                vetorProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorProfessor;
    }

    public ArrayList<Professor> pesquisarProfessorPeloNome(String nome) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor left join matricula_professor using(cpf) where nome ilike '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Professor professor = new Professor();
                professor.setMatricula(res.getString("id_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                listaProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaProfessor;
    }

    public ArrayList<Professor> pesquisarProfessorPeloCpf(String cpf) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor left join matricula_professor using(cpf) left join matricula using(id_matricula) where cpf ilike '%" + cpf + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Professor professor = new Professor();
                professor.setMatricula(res.getString("id_matricula"));
                professor.setDataMatricula(res.getDate("data_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                listaProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaProfessor;
    }

    public ArrayList<Professor> pesquisarProfessorComMatriculaPeloNome(String nome) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor  join matricula_professor using(cpf) where nome ilike '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        System.out.println(sql);
        res = ps.executeQuery();
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        try {
            while (res.next()) {
                Professor professor = new Professor();
                professor.setMatricula(res.getString("id_matricula"));
                professor.setDataMatricula(res.getDate("data_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                listaProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaProfessor;
    }

    public ArrayList<Professor> pesquisarProfessorPelaMatricula(String matricula) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor join matricula_professor using (cpf) join matricula using (id_matricula) where id_matricula ILIKE '%" + matricula + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {

                Professor professor = new Professor();
                professor.setMatricula(res.getString("id_matricula"));
                professor.setDataMatricula(res.getDate("data_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                listaProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaProfessor;
    }

    public Professor pesquisarProfessorComMatricula(String cpf, String matricula) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor join matricula_professor using (cpf) join matricula using (id_matricula) where cpf ='" + cpf + "' and id_matricula  ='" + matricula + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Professor professor = new Professor();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                professor.setMatricula(res.getString("id_matricula"));
                professor.setDataMatricula(res.getDate("data_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setFormacao(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPis(res.getString("pis"));
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return professor;
    }

    public ArrayList<Professor> pesquisarListaProfessorComMatricula(String cpf) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor left join matricula_professor using (cpf) left join matricula using (id_matricula) where cpf ilike '" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Professor professor = new Professor();

                professor.setMatricula(res.getString("id_matricula"));
                professor.setDataMatricula(res.getDate("data_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setFormacao(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPis(res.getString("pis"));
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
                listaProfessor.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaProfessor;
    }

    public Professor pesquisarProfessor(String cpf) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from professor left join matricula_professor using(cpf) where cpf = '" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Professor professor = new Professor();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                professor.setMatricula(res.getString("id_matricula"));
                professor.setCpf(res.getString("cpf"));
                professor.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                professor.setRg(rg);
                professor.setDataNascimento(res.getDate("data_nascimento"));
                professor.setNacionalidade(res.getString("nacionalidade"));
                professor.setNaturalidade(res.getString("naturalidade"));
                professor.setNaturalidadeUf(res.getString("naturalidade_uf"));
                professor.setEstadoCivil(res.getString("estado_civil"));
                professor.setSexo(res.getString("sexo"));
                professor.setFormacao(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();                
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                professor.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();                
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                professor.setCertificadoMilitar(certificadoMilitar);
                professor.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                professor.setFatorRh(res.getString("fator_rh"));
                professor.setFiliacaoMae(res.getString("filiacao_mae"));
                professor.setFiliacaoPai(res.getString("filiacao_pai"));
                professor.setEmail(res.getString("email"));
                professor.setTelefone1(res.getString("telefone_1"));
                professor.setTelefone2(res.getString("telefone_2"));
                professor.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                professor.setEndereco(endereco);
                professor.setPis(res.getString("pis"));
                professor.setPisDataInscricao(res.getDate("pis_data_inscricao"));
                professor.setCarteiraTrabalho(res.getString("carteira_trabalho"));
                professor.setCarteiraTrabalhoSerie(res.getString("carteira_trabalho_serie"));
                professor.setCarteiraTrabalhoUf(res.getString("carteira_trabalho_uf"));
                professor.setCarteiraTrabalhoDataEmissao(res.getDate("carteira_trabalho_data_emissao"));
                professor.setCnh(res.getString("cnh"));
                professor.setCnhCategoria(res.getString("cnh_categoria"));
                professor.setCnhDataEmissao(res.getDate("cnh_data_emissao"));
                professor.setConjuge(res.getString("conjuge"));
                professor.setConjugeCpf(res.getString("conjuge_cpf"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return professor;
    }

     public void atualizarProfessor(Professor professor, String cpfAntigo) { // FALTA FAZER
        FormacaoDAO formacaoDAO = new FormacaoDAO();
        DependenteDAO dependenteDAO = new DependenteDAO();
        String sql = "update professor set cpf=?, nome=?, rg=?, rg_orgao_emissor=?, rg_orgao_emissor_uf=?, rg_data_emissao=?,"
                + "data_nascimento=? , nacionalidade=?, naturalidade=?, naturalidade_uf=?, estado_civil=?, sexo=?, grau_escolaridade=?,"
                + "titulo_eleitor=?, titulo_eleitor_secao=?, titulo_eleitor_zona=?, titulo_eleitor_uf=?, titulo_eleitor_data_emissao=?,"
                + "certificado_militar_numero=?, certificado_militar_tipo=?, certificado_militar_serie=?, certificado_militar_categoria=?,"
                + "certificado_militar_csm=?,certificado_militar_rm=?, grupo_sanguineo=?, fator_rh=?, filiacao_mae=?, filiacao_pai=?,"
                + "email=?, telefone_1=?, telefone_2=?, endereco=?, numero=?, bairro=?,municipio=?, uf=?, cep=?, pis=?, pis_data_inscricao=?,"
                + "carteira_trabalho=?, carteira_trabalho_serie=?, carteira_trabalho_uf=?, carteira_trabalho_data_emissao=?, cnh=?, cnh_categoria=?,"
                + "cnh_data_emissao=?, conjuge=?, conjuge_cpf=? where cpf=?";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, professor.getCpf());
            ps.setString(2, professor.getNome());
            ps.setString(3, professor.getRg().getRg());
            ps.setString(4, professor.getRg().getRgOrgaoEmissor());
            ps.setString(5, professor.getRg().getRgOrgaoEmissorUf());
            ps.setDate(6, new java.sql.Date(professor.getRg().getRgDataEmissao().getTime()));
            ps.setDate(7, new java.sql.Date(professor.getDataNascimento().getTime()));
            ps.setString(8, professor.getNacionalidade());
            ps.setString(9, professor.getNaturalidade());
            ps.setString(10, professor.getNaturalidadeUf());
            ps.setString(11, professor.getEstadoCivil());
            ps.setString(12, professor.getSexo());
            ps.setString(13, professor.getFormacao());
            ps.setString(14, professor.getTituloEleitor().getTituloEleitor());
            ps.setString(15, professor.getTituloEleitor().getTituloEleitorSecao());
            ps.setString(16, professor.getTituloEleitor().getTituloEleitorZona());
            ps.setString(17, professor.getTituloEleitor().getTituloEleitorUf());
            ps.setDate(18, new java.sql.Date(professor.getTituloEleitor().getTituloEleitorDataEmissao().getTime()));
            ps.setString(19, professor.getCertificadoMilitar().getCertificadoMilitarNumero());
            ps.setString(20, professor.getCertificadoMilitar().getCertificadoMilitarTipo());
            ps.setString(21, professor.getCertificadoMilitar().getCertificadoMilitarSerie());
            ps.setString(22, professor.getCertificadoMilitar().getCertificadoMilitarCategoria());
            ps.setString(23, professor.getCertificadoMilitar().getCertificadoMilitarCsm());
            ps.setString(24, professor.getCertificadoMilitar().getCertificadoMilitarRm());
            ps.setString(25, professor.getGrupoSanguineo());
            ps.setString(26, professor.getFatorRh());
            ps.setString(27, professor.getFiliacaoMae());
            ps.setString(28, professor.getFiliacaoPai());
            ps.setString(29, professor.getEmail());
            ps.setString(30, professor.getTelefone1());
            ps.setString(31, professor.getTelefone2());
            ps.setString(32, professor.getEndereco().getEndereco());
            ps.setString(33, professor.getEndereco().getNumero());
            ps.setString(34, professor.getEndereco().getBairro());
            ps.setString(35, professor.getEndereco().getMunicipio());
            ps.setString(36, professor.getEndereco().getUf());
            ps.setString(37, professor.getEndereco().getCep());
            ps.setString(38, professor.getPis());
            ps.setDate(39, new java.sql.Date(professor.getPisDataInscricao().getTime()));
            ps.setString(40, professor.getCarteiraTrabalho());
            ps.setString(41, professor.getCarteiraTrabalhoSerie());
            ps.setString(42, professor.getCarteiraTrabalhoUf());
            ps.setDate(43, new java.sql.Date(professor.getCarteiraTrabalhoDataEmissao().getTime()));
            ps.setString(44, professor.getCnh());
            ps.setString(45, professor.getCnhCategoria());
            ps.setDate(46, new java.sql.Date(professor.getCnhDataEmissao().getTime()));
            ps.setString(47, professor.getConjuge());
            ps.setString(48, professor.getConjugeCpf());
            ps.setString(49, cpfAntigo);
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < professor.getVetorDisciplina().size(); i++) {
            MetodosBD metodosBD = new MetodosBD();
            String sqlMinistra = "insert into disciplina_professor(id_disciplina,cpf) values(" + professor.getVetorDisciplina().get(i).id + ",'" + professor.getCpf() + "');";
            metodosBD.executarInstrucao(sqlMinistra);
            System.out.println(sqlMinistra);
        }
        dependenteDAO.cadastrarDependentesProfessor(professor);
        cadastrarRelacaoDependentesProfessor(professor);
        formacaoDAO.cadastrarFormacaoProfessor(professor);
        cadastrarRelacaoFormacaoProfessor(professor);
    }

    /**
     * ***************DEPENDENTES************
     */
     public void cadastrarRelacaoDependentesProfessor(Professor professor) {
        DependenteDAO dependenteDAO = new DependenteDAO();
        String sqlCadastrarRelacaoDependentesProfessor = "insert into dependente_professor(cpf, id_dependente) values(?,?);";
        for (int i = 0; i < professor.getVetorDependentes().size(); i++) {
            try {
                Dependentes dependentes = new Dependentes();
                dependentes = dependenteDAO.pesquisarDependente(professor.getVetorDependentes().get(i).nome, new java.sql.Date(professor.getVetorDependentes().get(i).dataNascimento.getTime()));
                PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlCadastrarRelacaoDependentesProfessor);
                ps.setString(1, professor.getCpf());
                ps.setInt(2, dependentes.idDependente);
                ps.executeUpdate();
                ConnectionFactory.getConexao().close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deletarDependenteProfessor(Professor professor, String cpfAntigo) throws SQLException {
        String sqlDependenteProfessor = "delete from dependente_professor where cpf ='" + cpfAntigo + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlDependenteProfessor);
        ps.executeUpdate();
        for (int i = 0; i < professor.getVetorDependentes().size(); i++) {
            String sqlDependente = "delete from dependente where nome ='" + professor.getVetorDependentes().get(i).nome + "' and data_nascimento = '" + professor.getVetorDependentes().get(i).dataNascimento + "';";
            PreparedStatement ps1 = ConnectionFactory.getConexao().prepareStatement(sqlDependente);
            ps1.executeUpdate();
        }
        ConnectionFactory.getConexao().close();
    }

    /**
     * ************FORMACAO *************
     */
    public void cadastrarRelacaoFormacaoProfessor(Professor professor) {
        FormacaoDAO formacaoDAO = new FormacaoDAO();
        String sqlCadastrarRelacaoFormacaoProfessor = "insert into formacao_professor(id_formacao, cpf) values(?,?);";
        for (int i = 0; i < professor.getVetorFormacao().size(); i++) {
            try {
                Formacao formacao = formacaoDAO.pesquisarFormacao(professor.getVetorFormacao().get(i).nome, professor.getCpf());
                PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlCadastrarRelacaoFormacaoProfessor);
                ps.setLong(1, formacao.id);
                ps.setString(2, professor.getCpf());
                ps.executeUpdate();
                ConnectionFactory.getConexao().close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

  public  ArrayList<Formacao> listarFormacaoProfessor(Professor professor) throws SQLException {
        ResultSet res;
        String sql = "select * from formacao_professor join formacao using (id_formacao) where formacao_professor.cpf ='" + professor.getCpf() + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Formacao> vetorFormacao = new ArrayList<Formacao>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Formacao formacao = new Formacao();
                formacao.id = res.getInt("id_formacao");
                formacao.nome = res.getString("nome");
                formacao.lotacao = res.getString("lotacao");
                formacao.titulacao = res.getString("titulacao");
                vetorFormacao.add(formacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorFormacao;
    }

    /**
     * ******* DISCIPLINAS ********
     */
    /*void alterarStatusDisciplina(String cpfAntigo, Professor professor) {
     String sql = "update disciplina_professor set status ='fechada' where cpf =? and id_disciplina =?;";
     for (int i = 0; i < professor.vetorDisciplinaFechada.size(); i++) {
     try {
     PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
     ps.setString(1, cpfAntigo);
     ps.setInt(2, professor.vetorDisciplinaFechada.get(i));
     ps.executeUpdate();
     ConnectionFactory.getConexao().close();
     } catch (SQLException ex) {
     Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     }*/
    /**
     * ********************MATRICULA************************
     */
    public void cadastrarRelacaoMatriculaProfessor(Professor professor) {
        String sqlMatriculaProfessor = "insert into matricula_professor(id_matricula, cpf, data_matricula) values(?,?,?);";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlMatriculaProfessor);
            ps.setString(1, professor.getMatricula());
            ps.setString(2, professor.getCpf());
            ps.setDate(3, new java.sql.Date(professor.getDataMatricula().getTime()));
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
