package entidades;

import dao.AlunoDAO;
import dao.CursoDAO;
import dao.MatriculaDAO;
import dao.conexao.ConnectionFactory;
import entidades.util.CertificadoMilitar;
import entidades.util.Endereco;
import entidades.util.Rg;
import entidades.util.TituloEleitor;
import entidades.util.Validacao;
import entidades.util.ValidarCpf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Aluno  extends Pessoa{

    private String status;
    private String login;
    private String matricula;
    private String cpf;
    private String nome;
    private Rg rg;
    private Date dataNascimento;
    private String nacionalidade;
    private String naturalidade;
    private String naturalidadeUf;
    private String estadoCivil;
    private String sexo;
    private TituloEleitor tituloEleitor;
    private CertificadoMilitar certificadoMilitar;
    private String grupoSanguineo;
    private String fatorRh;
    private String filiacaoMae;
    private String filiacaoPai;
    private String email;
    private String telefone1;
    private String telefone2;
    private Date dataCadastro;
    private Endereco endereco;
    private String grauEscolaridade;
    private Date dataMatricula;
    private Curso curso;

    public void cadastrarAluno(Aluno aluno) throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.cadastrarAluno(aluno);
    }

    public void atualizarAluno(Aluno aluno, String cpfAntigo) throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.deletarCursosAluno(cpfAntigo);
        alunoDAO.atualizarAluno(aluno, cpfAntigo);
        if (aluno.curso != null) {
            alunoDAO.cadastrarCursosAluno(aluno);
        }
        if (aluno.getMatricula() != null) {
            alunoDAO.deletarMatricula(aluno);
            aluno.cadastrarMatricula(aluno);
        }
    }

    public void deletarAluno(Aluno aluno, String cpfAntigo) throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.deletarMatricula(aluno);
        alunoDAO.deletarCursosAluno(cpf);
        alunoDAO.deletarAluno(aluno);
        
    }

    public void cadastrarMatricula(Aluno aluno) {
        AlunoDAO alunoDAO = new AlunoDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        matriculaDAO.CadastrarMatricula(aluno.getMatricula());
        alunoDAO.cadastrarRelacaoMatriculaAluno(aluno);
    }

    public String[] alunoToLinha(Aluno aluno) {
        String[] novaLinha = new String[]{aluno.getCpf(), aluno.getNome(), aluno.getMatricula(), aluno.getRg().getRg(), aluno.getDataNascimento().toString(), aluno.getNacionalidade(), aluno.getNaturalidade(), aluno.getSexo(), aluno.getEmail(), aluno.getTelefone1()};
        return novaLinha;
    }

    public Curso adcionarCurso(int id) throws SQLException {
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso1 = cursoDAO.pesquisarCurso(id);
        return curso1;
    }

    public boolean verificarMatriculaAluno() throws SQLException {

        String sql = "select * from aluno join matricula_aluno using (cpf) join matricula using (id_matricula) where cpf ='" + this.cpf + "';";
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

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        
        ValidarCpf validarCpf = new ValidarCpf();
        if(validarCpf.validarCpf(cpf)){
            this.cpf = cpf;
        }else{
            Validacao validacao = new Validacao();
            validacao.cpfInvalido();
            
        }     
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {

        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the nacionalidade
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     * @param nacionalidade the nacionalidade to set
     */
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    /**
     * @return the naturalidade
     */
    public String getNaturalidade() {
        return naturalidade;
    }

    /**
     * @param naturalidade the naturalidade to set
     */
    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    /**
     * @return the naturalidadeUf
     */
    public String getNaturalidadeUf() {
        return naturalidadeUf;
    }

    /**
     * @param naturalidadeUf the naturalidadeUf to set
     */
    public void setNaturalidadeUf(String naturalidadeUf) {
        this.naturalidadeUf = naturalidadeUf;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



    /**
     * @return the grupoSanguineo
     */
    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    /**
     * @param grupoSanguineo the grupoSanguineo to set
     */
    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    /**
     * @return the fatorRh
     */
    public String getFatorRh() {
        return fatorRh;
    }

    /**
     * @param fatorRh the fatorRh to set
     */
    public void setFatorRh(String fatorRh) {
        this.fatorRh = fatorRh;
    }

    /**
     * @return the filiacaoMae
     */
    public String getFiliacaoMae() {
        return filiacaoMae;
    }

    /**
     * @param filiacaoMae the filiacaoMae to set
     */
    public void setFiliacaoMae(String filiacaoMae) {
        this.filiacaoMae = filiacaoMae;
    }

    /**
     * @return the filiacaoPai
     */
    public String getFiliacaoPai() {
        return filiacaoPai;
    }

    /**
     * @param filiacaoPai the filiacaoPai to set
     */
    public void setFiliacaoPai(String filiacaoPai) {
        this.filiacaoPai = filiacaoPai;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone1
     */
    public String getTelefone1() {
        return telefone1;
    }

    /**
     * @param telefone1 the telefone1 to set
     */
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    /**
     * @return the telefone2
     */
    public String getTelefone2() {
        return telefone2;
    }

    /**
     * @param telefone2 the telefone2 to set
     */
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    /**
     * @return the grauEscolaridade
     */
    public String getGrauEscolaridade() {
        return grauEscolaridade;
    }

    /**
     * @param grauEscolaridade the grauEscolaridade to set
     */
    public void setGrauEscolaridade(String grauEscolaridade) {
        this.grauEscolaridade = grauEscolaridade;
    }

    /**
     * @return the dataMatricula
     */
    public Date getDataMatricula() {
        return dataMatricula;
    }

    /**
     * @param dataMatricula the dataMatricula to set
     */
    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Rg getRg() {
        return rg;
    }

    public void setRg(Rg rg) {
        this.rg = rg;
    }
    
     public TituloEleitor getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(TituloEleitor tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public CertificadoMilitar getCertificadoMilitar() {
        return certificadoMilitar;
    }

    public void setCertificadoMilitar(CertificadoMilitar certificadoMilitar) {
        this.certificadoMilitar = certificadoMilitar;
    }
     public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
