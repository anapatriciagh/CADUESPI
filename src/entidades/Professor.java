package entidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import dao.DisciplinaDAO;
import dao.FormacaoDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import dao.MatriculaDAO;
import dao.ProfessorDAO;
import entidades.util.CertificadoMilitar;
import entidades.util.Endereco;
import entidades.util.Rg;
import entidades.util.TituloEleitor;
import entidades.util.Validacao;
import entidades.util.ValidarCpf;
import tela.professor.TelaCadastrarProfessor;

public class Professor {

    private String status;
    private String login;
    private String matricula;
    private String cpf;
    private String nome;
    private TituloEleitor tituloEleitor;
    private Rg rg;
    private Date dataNascimento;
    private String nacionalidade;
    private String naturalidade;
    private String naturalidadeUf;
    private String estadoCivil;
    private String sexo;
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
    private String pis;
    private Date pisDataInscricao;
    private String grauEscolaridade;

    public String getGrauEscolaridade() {
        return grauEscolaridade;
    }

    public void setGrauEscolaridade(String grauEscolaridade) {
        this.grauEscolaridade = grauEscolaridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        ValidarCpf validarCpf = new ValidarCpf();
        if(validarCpf.validarCpf(cpf)){
        this.cpf = cpf;    
        }else{
            Validacao validacao = new Validacao();
            validacao.cpfInvalido();
         
        }
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNaturalidadeUf() {
        return naturalidadeUf;
    }

    public void setNaturalidadeUf(String naturalidadeUf) {
        this.naturalidadeUf = naturalidadeUf;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getFatorRh() {
        return fatorRh;
    }

    public void setFatorRh(String fatorRh) {
        this.fatorRh = fatorRh;
    }

    public String getFiliacaoMae() {
        return filiacaoMae;
    }

    public void setFiliacaoMae(String filiacaoMae) {
        this.filiacaoMae = filiacaoMae;
    }

    public String getFiliacaoPai() {
        return filiacaoPai;
    }

    public void setFiliacaoPai(String filiacaoPai) {
        this.filiacaoPai = filiacaoPai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public Date getPisDataInscricao() {
        return pisDataInscricao;
    }

    public void setPisDataInscricao(Date pisDataInscricao) {
        this.pisDataInscricao = pisDataInscricao;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public String getCarteiraTrabalhoSerie() {
        return carteiraTrabalhoSerie;
    }

    public void setCarteiraTrabalhoSerie(String carteiraTrabalhoSerie) {
        this.carteiraTrabalhoSerie = carteiraTrabalhoSerie;
    }

    public String getCarteiraTrabalhoUf() {
        return carteiraTrabalhoUf;
    }

    public void setCarteiraTrabalhoUf(String carteiraTrabalhoUf) {
        this.carteiraTrabalhoUf = carteiraTrabalhoUf;
    }

    public Date getCarteiraTrabalhoDataEmissao() {
        return carteiraTrabalhoDataEmissao;
    }

    public void setCarteiraTrabalhoDataEmissao(Date carteiraTrabalhoDataEmissao) {
        this.carteiraTrabalhoDataEmissao = carteiraTrabalhoDataEmissao;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCnhCategoria() {
        return cnhCategoria;
    }

    public void setCnhCategoria(String cnhCategoria) {
        this.cnhCategoria = cnhCategoria;
    }

    public Date getCnhDataEmissao() {
        return cnhDataEmissao;
    }

    public void setCnhDataEmissao(Date cnhDataEmissao) {
        this.cnhDataEmissao = cnhDataEmissao;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public String getConjugeCpf() {
        return conjugeCpf;
    }

    public void setConjugeCpf(String conjugeCpf) {
        this.conjugeCpf = conjugeCpf;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public ArrayList<Disciplina> getVetorDisciplina() {
        return vetorDisciplina;
    }

    public void setVetorDisciplina(ArrayList<Disciplina> vetorDisciplina) {
        this.vetorDisciplina = vetorDisciplina;
    }

    public ArrayList<Dependentes> getVetorDependentes() {
        return vetorDependentes;
    }

    public void setVetorDependentes(ArrayList<Dependentes> vetorDependentes) {
        this.vetorDependentes = vetorDependentes;
    }

    public ArrayList<Formacao> getVetorFormacao() {
        return vetorFormacao;
    }

    public void setVetorFormacao(ArrayList<Formacao> vetorFormacao) {
        this.vetorFormacao = vetorFormacao;
    }
    private String carteiraTrabalho;
    private String carteiraTrabalhoSerie;
    private String carteiraTrabalhoUf;
    private Date carteiraTrabalhoDataEmissao;
    private String cnh;
    private String cnhCategoria;
    private Date cnhDataEmissao;
    private String conjuge;
    private String conjugeCpf;
    private String formacao;
    private Date dataMatricula;
    private ArrayList<Disciplina> vetorDisciplina = new ArrayList<Disciplina>();
    private ArrayList<Dependentes> vetorDependentes = new ArrayList<Dependentes>();
    private ArrayList<Formacao> vetorFormacao = new ArrayList<Formacao>();
    // ArrayList<Integer> vetorDisciplinaFechada = new ArrayList<Integer>();

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
//import Professor.TelaCadastrarProfessor;
    public void atualizarProfessor(Professor professor, String cpfAntigo) throws SQLException {
        ProfessorDAO professorDAO = new ProfessorDAO();
        FormacaoDAO formacaoDAO = new FormacaoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.deletarDisciplinaProfessor(cpfAntigo);
        formacaoDAO.deletarFormacaoProfessor(cpfAntigo);
        professorDAO.deletarDependenteProfessor(professor, cpfAntigo);
        professorDAO.atualizarProfessor(professor, cpfAntigo);
        if (professor.getMatricula() != null) {
            professorDAO.deletarMatricula(professor);
            professor.cadastrarMatricula(professor);
        }
        
    }

    public void deletarProfessor(Professor professor, String cpfAntigo) throws SQLException {
        ProfessorDAO professorDAO = new ProfessorDAO();
        FormacaoDAO formacaoDAO = new FormacaoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.deletarDisciplinaProfessor(cpfAntigo);
        formacaoDAO.deletarFormacaoProfessor(cpfAntigo);
        professorDAO.deletarDependenteProfessor(professor, cpfAntigo);
        professorDAO.deletarMatricula(professor);
        professorDAO.deletarProfessor(professor);
    }

    public void cadastrarMatricula(Professor professor) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        matriculaDAO.CadastrarMatricula(professor.getMatricula());
        professorDAO.cadastrarRelacaoMatriculaProfessor(professor);

    }
    
    public void cadastrarProfessor(Professor professor) throws SQLException{
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.cadastrarProfessor(professor);
        
    }

    public String converterDate(String data) {
        DateFormat formatador;
        formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date novaData = new Date();
        try {
            novaData = formatador.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = formatador.format(novaData);
        return result;
    }

    public String[] professorToLinha(Professor professor) {
        String[] novaLinha = new String[]{professor.getCpf(), professor.getNome(), professor.getMatricula(), professor.getRg().getRg(), professor.getDataNascimento().toString(), professor.getNacionalidade(), professor.getNaturalidade(), professor.getSexo(), professor.getEmail(), professor.getTelefone1()};
        return novaLinha;
    }

    public String[] formacaoToLinha(Formacao formacao) {
        String[] novaLinha = new String[]{formacao.nome, formacao.titulacao, formacao.lotacao};
        return novaLinha;
    }

    public String[] dependenteToLinha(Dependentes dependentes) {
        String[] novaLinha = new String[]{dependentes.nome, dependentes.grauParentesco, dependentes.dataNascimento.toString()};
        return novaLinha;
    }

    public int removerDisciplina(Professor professor, int codigoDaDisciplina) {
        int posicao = +1;
        for (int i = 0; i < professor.getVetorDisciplina().size(); i++) {
            if (professor.getVetorDisciplina().get(i).id == codigoDaDisciplina) {
                posicao = i;
            }
        }
        return posicao;
    }

    public Disciplina adcionarDisciplinas(int id) throws SQLException {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Disciplina disciplina = disciplinaDAO.pesquisarDisciplina(id);
        return disciplina;
    }

    public ArrayList<Disciplina> carregarDisciplinaProfessor() throws SQLException {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Scanner leitura = new Scanner(System.in);
        ArrayList<Disciplina> vetorTodasDisciplina = new ArrayList<Disciplina>();
        ArrayList<Disciplina> vetorDisciplinaProfessor = new ArrayList<Disciplina>();
        vetorTodasDisciplina = disciplinaDAO.pesquisarTodasDisciplina();
        System.out.println("Selecionar Disciplinas Para o Professor \n");
        int opcao = -1;
        do {
            for (int i = 0; i < vetorTodasDisciplina.size(); i++) {
                System.out.println(vetorTodasDisciplina.get(i).id + " " + vetorTodasDisciplina.get(i).nome);
            }
            System.out.println("Entre com o codigo da disciplina ou digite 0 para sair  ");
            //leitura.nextLine();
            opcao = leitura.nextInt();
            if (opcao > 0) {
                Disciplina disciplinaProfessor = new Disciplina();
                disciplinaProfessor.id = opcao;
                vetorDisciplinaProfessor.add(disciplinaProfessor);
            }
        } while (opcao != 0);

        return vetorDisciplinaProfessor;
    }

    public void deletarProfessorConsole() throws SQLException {
        Professor professor = new Professor();
        Scanner leitura = new Scanner(System.in);
        ProfessorDAO professorDAO = new ProfessorDAO();
        System.out.println("Digite o CPF do Professor : ");
        leitura.nextLine();
        professor.setCpf(leitura.nextLine());
        professorDAO.deletarProfessor(professor);
    }

    public void exibirTodosProfessores() throws SQLException {
        ProfessorDAO professorDAO = new ProfessorDAO();
        ArrayList<Professor> vetorProfessor = new ArrayList<Professor>();
        vetorProfessor = professorDAO.pesquisarTodosProfessores();
        for (int i = 0; i < vetorProfessor.size(); i++) {
            System.out.println("NOME " + vetorProfessor.get(i).getNome() + " MATRICULA: " + vetorProfessor.get(i).getMatricula() + "  CPF: " + vetorProfessor.get(i).getCpf());
        }
    }

    public void pesquisarProfessor() throws SQLException {
        Professor professor = new Professor();
        Scanner leitura = new Scanner(System.in);
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ArrayList<Disciplina> disciplinasProfessor = new ArrayList<Disciplina>();
        System.out.println("INFORME O CPF DO PROFESSOR");
        leitura.nextLine();
        String cpf = leitura.nextLine();
        professor = professorDAO.pesquisarProfessor(cpf);
        System.out.println("NOME : " + professor.getNome() + " CPF : " + professor.getCpf());
        disciplinasProfessor = disciplinaDAO.pesquisarDisciplinasDoProfessor(professor);
        System.out.println("Disciplinas do Professor: ");
        for (int i = 0; i < disciplinasProfessor.size(); i++) {
            System.out.println(disciplinasProfessor.get(i).nome);
        }
    }

    public void alterarProfessor() throws SQLException {
        Professor professor = new Professor();
        Scanner leitura = new Scanner(System.in);
        String cpf;
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        System.out.println("INFORME O CPF DO PROFESSOR A SER ALTERADO: ");
        leitura.nextLine();
        cpf = leitura.nextLine();
        professor = professorDAO.pesquisarProfessor(cpf);
        professor.setVetorDisciplina(disciplinaDAO.pesquisarDisciplinasDoProfessor(professor));
        System.out.println("QUAL DADO DESEJA ALTERAR:");
        System.out.println("1- NOME:");
        System.out.println("2- MATRICULA:");
        int opcao = leitura.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("DIGITE O NOME:");
                leitura.nextLine();
                professor.setNome(leitura.nextLine());

                break;
            case 2:
                System.out.println("DIGITE A MATRICULA:");
                leitura.nextLine();
                professor.setMatricula(leitura.nextLine());
                break;
        }
        System.out.println("DISCIPLINAS QUE O  PROFESSOR MINISTRA ATUALMENTE:");
        for (int i = 0; i < professor.getVetorDisciplina().size(); i++) {
            System.out.println(professor.getVetorDisciplina().get(i).id + " " + professor.getVetorDisciplina().get(i).nome);
        }
        System.out.println("\nINSERINDO NOVAS DISCIPLINAS AO PROFESSOR");
        professor.setVetorDisciplina(carregarDisciplinaProfessor());
        //professorDAO.atualizarProfessor(professor);
    }

    public java.sql.Date converteDataUtilToSql(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

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

    public TituloEleitor getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(TituloEleitor tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public Rg getRg() {
        return rg;
    }

    public void setRg(Rg rg) {
        this.rg = rg;
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
