package dao;

import dao.MetodosBD;
import dao.conexao.ConnectionFactory;
import entidades.Aluno;
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

public class AlunoDAO {

    public void cadastrarAluno(Aluno aluno) throws SQLException { // FALTA TESTAR
        String sql = "insert into aluno (cpf,nome,rg,rg_orgao_emissor,rg_orgao_emissor_uf,rg_data_emissao,"
                + "data_nascimento,nacionalidade,naturalidade,naturalidade_uf,estado_civil,sexo,grau_escolaridade,"
                + "titulo_eleitor, titulo_eleitor_secao, titulo_eleitor_zona, titulo_eleitor_uf,titulo_eleitor_data_emissao,"
                + "certificado_militar_numero,certificado_militar_tipo, certificado_militar_serie, "
                + "certificado_militar_categoria,certificado_militar_csm, certificado_militar_rm, grupo_sanguineo, fator_rh,"
                + "filiacao_mae, filiacao_pai, email, telefone_1, telefone_2, endereco, numero, bairro,municipio,uf,cep)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
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
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        cadastrarCursosAluno(aluno);
        if (aluno.getMatricula() != null) {
            aluno.cadastrarMatricula(aluno);
        }

    }

    public void cadastrarCursosAluno(Aluno aluno) throws SQLException {
        String sql = "insert into aluno_curso(cpf, id_curso) values(?,?);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, aluno.getCpf());
        ps.setInt(2, aluno.getCurso().id);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public void deletarCursosAluno(String cpf) throws SQLException {
        String sql = "delete from aluno_curso where cpf = ? ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, cpf);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
        System.out.println(sql);
    }

    public void deletarAluno(Aluno aluno) throws SQLException { // FALTA TESTAR
        String sql = "delete  from aluno where  cpf= '" + aluno.getCpf() + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public void deletarMatricula(Aluno aluno) {
        MetodosBD bd = new MetodosBD();
        String sqlMatriculaAluno = "delete from matricula_aluno where id_matricula ='" + aluno.getMatricula() + "';";
        String sqlMatricula = "delete from matricula where id_matricula ='" + aluno.getMatricula() + "';";
        bd.executarInstrucao(sqlMatriculaAluno);
        bd.executarInstrucao(sqlMatricula);
    }

    public ArrayList<Aluno> pesquisarTodosAlunos() throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno left join matricula_aluno using(cpf) ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Aluno> vetorAluno = new ArrayList<Aluno>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Aluno aluno = new Aluno();

                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));

                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);

                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);

                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));

                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);

                vetorAluno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorAluno;
    }

    public ArrayList<Aluno> pesquisarListaAlunosComMatricula() throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno join matricula_aluno using (cpf) join matricula using (id_matricula);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Aluno> vetorAluno = new ArrayList<>();
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setDataMatricula(res.getDate("data_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);
                vetorAluno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return vetorAluno;
    }

    public Aluno pesquisarAlunoComMatricula(String cpf) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno left join matricula_aluno using (cpf) left join matricula using (id_matricula) where cpf ='" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Aluno aluno = new Aluno();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setDataMatricula(res.getDate("data_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));

                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);

                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return aluno;
    }

    public Aluno pesquisarAlunoComMatricula(String cpf, String matricula) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno left join matricula_aluno using (cpf) left join matricula using (id_matricula) where cpf ='" + cpf + "' and id_matricula = '" + matricula + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Aluno aluno = new Aluno();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setDataMatricula(res.getDate("data_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return aluno;
    }

    public ArrayList<Aluno> pesquisarListaAlunoComMatricula(String cpf) throws SQLException { // FALTA TESTAR
        ResultSet res;
        ArrayList<Aluno> listaAluno = new ArrayList();
        String sql = "select * from aluno left join matricula_aluno using (cpf) left join matricula using (id_matricula) where cpf ='" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setDataMatricula(res.getDate("data_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);
                listaAluno.add(aluno);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaAluno;
    }

    public ArrayList<Aluno> pesquisarAlunoComMatriculaPeloNome(String nome) throws SQLException { // FALTA TESTAR
        ResultSet res;
        ArrayList<Aluno> listaAluno = new ArrayList<>();
        String sql = "select * from aluno left join matricula_aluno using (cpf)  join matricula using (id_matricula) where nome ilike'%" + nome + "%';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setDataMatricula(res.getDate("data_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);
                listaAluno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaAluno;
    }

    public Aluno pesquisarAluno(String cpf) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno  left join matricula_aluno using (cpf) where cpf = '" + cpf + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Aluno aluno = new Aluno();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return aluno;
    }

    public ArrayList<Aluno> pesquisarAlunoPeloNome(String nome) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno where nome ilike '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Aluno> listaAluno = new ArrayList<>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Aluno aluno = new Aluno();
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);
                listaAluno.add(aluno);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaAluno;
    }

    public void atualizarAluno(Aluno aluno, String cpfAntigo) { // FALTA FAZER

        String sql = "update aluno set cpf=?, nome=?, rg=?, rg_orgao_emissor=?, rg_orgao_emissor_uf=?, rg_data_emissao=?,"
                + "data_nascimento=? , nacionalidade=?, naturalidade=?, naturalidade_uf=?, estado_civil=?, sexo=?, grau_escolaridade=?,"
                + "titulo_eleitor=?, titulo_eleitor_secao=?, titulo_eleitor_zona=?, titulo_eleitor_uf=?, titulo_eleitor_data_emissao=?,"
                + "certificado_militar_numero=?, certificado_militar_tipo=?, certificado_militar_serie=?, certificado_militar_categoria=?,"
                + "certificado_militar_csm=?,certificado_militar_rm=?, grupo_sanguineo=?, fator_rh=?, filiacao_mae=?, filiacao_pai=?,"
                + "email=?, telefone_1=?, telefone_2=?, endereco=?, numero=?, bairro=?,municipio=?, uf=?, cep=? where cpf=?";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
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
            ps.setString(38, cpfAntigo);
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Aluno pesquisarAlunoPelaMatricula(String matricula) throws SQLException { // FALTA TESTAR
        ResultSet res;
        String sql = "select * from aluno join matricula_aluno using (cpf) join matricula using (id_matricula) where id_matricula ILIKE '%" + matricula + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Aluno aluno = new Aluno();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                aluno.setMatricula(res.getString("id_matricula"));
                aluno.setDataMatricula(res.getDate("data_matricula"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setNome(res.getString("nome"));
                Rg rg = new Rg();
                rg.setRg(res.getString("rg"));
                rg.setRgOrgaoEmissor(res.getString("rg_orgao_emissor"));
                rg.setRgOrgaoEmissorUf(res.getString("rg_orgao_emissor_uf"));
                rg.setRgDataEmissao(res.getDate("rg_data_emissao"));
                aluno.setRg(rg);
                aluno.setDataNascimento(res.getDate("data_nascimento"));
                aluno.setNacionalidade(res.getString("nacionalidade"));
                aluno.setNaturalidade(res.getString("naturalidade"));
                aluno.setNaturalidadeUf(res.getString("naturalidade_uf"));
                aluno.setEstadoCivil(res.getString("estado_civil"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setGrauEscolaridade(res.getString("grau_escolaridade"));
                TituloEleitor tituloEleitor = new TituloEleitor();
                tituloEleitor.setTituloEleitor(res.getString("titulo_eleitor"));
                tituloEleitor.setTituloEleitorSecao(res.getString("titulo_eleitor_secao"));
                tituloEleitor.setTituloEleitorZona(res.getString("titulo_eleitor_zona"));
                tituloEleitor.setTituloEleitorUf(res.getString("titulo_eleitor_uf"));
                tituloEleitor.setTituloEleitorDataEmissao(res.getDate("titulo_eleitor_data_emissao"));
                aluno.setTituloEleitor(tituloEleitor);
                CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
                certificadoMilitar.setCertificadoMilitarNumero(res.getString("certificado_militar_numero"));
                certificadoMilitar.setCertificadoMilitarTipo(res.getString("certificado_militar_tipo"));
                certificadoMilitar.setCertificadoMilitarSerie(res.getString("certificado_militar_serie"));
                certificadoMilitar.setCertificadoMilitarCategoria(res.getString("certificado_militar_categoria"));
                certificadoMilitar.setCertificadoMilitarCsm(res.getString("certificado_militar_csm"));
                certificadoMilitar.setCertificadoMilitarRm(res.getString("certificado_militar_rm"));
                aluno.setCertificadoMilitar(certificadoMilitar);
                aluno.setGrupoSanguineo(res.getString("grupo_sanguineo"));
                aluno.setFatorRh(res.getString("fator_rh"));
                aluno.setFiliacaoMae(res.getString("filiacao_mae"));
                aluno.setFiliacaoPai(res.getString("filiacao_pai"));
                aluno.setEmail(res.getString("email"));
                aluno.setTelefone1(res.getString("telefone_1"));
                aluno.setTelefone2(res.getString("telefone_2"));
                aluno.setDataCadastro(res.getDate("data_cadastro"));
                Endereco endereco = new Endereco();
                endereco.setEndereco(res.getString("endereco"));
                endereco.setNumero(res.getString("numero"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setMunicipio(res.getString("municipio"));
                endereco.setUf(res.getString("uf"));
                endereco.setCep(res.getString("cep"));
                aluno.setEndereco(endereco);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return aluno;
    }

    public void cadastrarRelacaoMatriculaAluno(Aluno aluno) {
        String sqlMatriculaAluno = "insert into matricula_aluno(id_matricula, cpf, data_matricula) values(?,?,?);";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlMatriculaAluno);
            ps.setString(1, aluno.getMatricula());
            ps.setString(2, aluno.getCpf());
            ps.setDate(3, new java.sql.Date(aluno.getDataMatricula().getTime()));
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verificarMatriculaAluno(String cpf) throws SQLException {

        String sql = "select * from aluno join matricula_aluno using (cpf) join matricula using (id_matricula) where cpf ='" + cpf + "';";
        ResultSet res;
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        String matricula = null;
        while (res.next()) {
            matricula = res.getString("id_matricula");
            ConnectionFactory.getConexao().close();
        }
        if (matricula != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarBolsaAluno(String cpf) throws SQLException {
        String sql = "select * from aluno_bolsa where cpf ='" + cpf + "';";
        ResultSet res;
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        while (res.next()) {
            cpf = res.getString("cpf");
            ConnectionFactory.getConexao().close();
        }
        if (cpf != null) {
            return true;
        } else {
            return false;
        }
    }
}
