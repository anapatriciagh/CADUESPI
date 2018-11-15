/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.professor;

import entidades.Professor;
import dao.DependenteDAO;
import entidades.Dependentes;
import entidades.Disciplina;
import dao.DisciplinaDAO;
import entidades.Formacao;
import entidades.util.MetodosAplicacao;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.MatriculaDAO;
import dao.ProfessorDAO;
import entidades.util.CertificadoMilitar;
import entidades.util.Endereco;
import entidades.util.Rg;
import entidades.util.TituloEleitor;
import entidades.util.Validacao;
import entidades.util.ValidarCpf;
import tela.Principal;

/**
 *
 * @author Rubens
 */
public final class TelaCadastrarProfessor extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastrarProfessor
     */
    Principal principal;
    Professor professor = new Professor();
    // ArrayList<Disciplina> vetorDisciplinas = new ArrayList<>(); //disciplina tem que ser global... configurar as funções de add e rem

    void inicializarDisciplinasProfessor() throws SQLException {
        ArrayList<Disciplina> vetorDisciplinas = new ArrayList<Disciplina>();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Disciplina disciplina = new Disciplina();
        vetorDisciplinas = disciplinaDAO.pesquisarDisciplinasDoProfessor(professor);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDisciplinaProfessor.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorDisciplinas.size(); i++) {
            meuModelo.addRow(disciplina.disciplinasToLinha(vetorDisciplinas.get(i)));
            professor.getVetorDisciplina().add(vetorDisciplinas.get(i)); //linha de teste
        }
    }

    void inicializarDependentesProfessor() throws SQLException {
        DependenteDAO dependenteDAO = new DependenteDAO();
        ArrayList<Dependentes> vetorDependentes = new ArrayList<Dependentes>();
        vetorDependentes = dependenteDAO.pesquisarListaDependentes(professor);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDependentes.getModel();
        for (int i = 0; i < vetorDependentes.size(); i++) {
            meuModelo.addRow(professor.dependenteToLinha(vetorDependentes.get(i)));
            professor.getVetorDependentes().add(vetorDependentes.get(i)); //linha de teste
        }
    }

    ///terminar funcao para retornar as formacoes do professor
    void inicializarFormacao() throws SQLException {
        ProfessorDAO professorDAO = new ProfessorDAO();
        ArrayList<Formacao> vetorFormacao = new ArrayList<Formacao>();
        vetorFormacao = professorDAO.listarFormacaoProfessor(professor);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaFormacao.getModel();
        for (int i = 0; i < vetorFormacao.size(); i++) {
            meuModelo.addRow(professor.formacaoToLinha(vetorFormacao.get(i)));
            professor.getVetorFormacao().add(vetorFormacao.get(i)); //linha de teste
        }
    }

    void limparCamposDependentes() {
        campoNomeDependente.setText("");
        campoGrauParentesco.setText("");
        campoDataNascimentoDependente.setText("");
    }

    //FUNÇÕES DE TELA
    void listarDisciplinas() throws SQLException {
        ArrayList<Disciplina> listaDisciplina = new ArrayList<>();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Disciplina disciplina = new Disciplina();
        listaDisciplina = disciplinaDAO.pesquisarTodasDisciplina();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDisciplinasDisponiveis.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaDisciplina.size(); i++) {
            meuModelo.addRow(disciplina.disciplinasToLinha(listaDisciplina.get(i)));
        }
    }

    Professor getDadosProfessor() throws ParseException {
        DateFormat df = DateFormat.getDateInstance();
        df.setLenient(false);

        if (!campoMatricula.getText().isEmpty()) {
            professor.setMatricula(campoMatricula.getText());
            professor.setDataMatricula(df.parse(campoDataMatricula.getText()));
        }

        professor.setDataNascimento(df.parse(campoDataNascimento.getText()));
        professor.setNome(campoNome.getText());
        professor.setNacionalidade(campoNacionalidade.getText());
        professor.setNaturalidade(campoNaturalidade.getText());
        professor.setNaturalidadeUf(caixaCombinacaoNaturalidadeUF.getSelectedItem().toString());
        professor.setEstadoCivil(caixaCombinacaoEstadoCivil.getSelectedItem().toString());
        professor.setSexo(caixaCombinacaoSexo.getSelectedItem().toString());
        professor.setTelefone1(campoFone1.getText());
        professor.setTelefone2(campoFone2.getText());
        professor.setEmail(campoEmail.getText());
        professor.setFiliacaoPai(campoNomePai.getText());
        professor.setFiliacaoMae(campoNomeMae.getText());
        Endereco endereco = new Endereco();
        endereco.setEndereco(campoEndereco.getText());
        endereco.setNumero(campoNºEndereco.getText());
        endereco.setBairro(campoBairro.getText());
        endereco.setMunicipio(campoCidade.getText());
        endereco.setCep(campoCep.getText());
        endereco.setUf(caixaCombinacaoEnderecoUF.getSelectedItem().toString());
        professor.setEndereco(endereco);

        professor.setFormacao(caixaCombinacaoFormacao.getSelectedItem().toString());

        Rg rg = new Rg();
        rg.setRg(campoCarteiraIdentidade.getText());
        rg.setRgOrgaoEmissor(campoOrgaoEmissorRG.getText());
        rg.setRgOrgaoEmissorUf(caixaCombinacaoCarteiraIdentidadeUF.getSelectedItem().toString());
        rg.setRgDataEmissao(df.parse(campoDataEmissaoRG.getText()));
        professor.setRg(rg);

        professor.setCpf(campoCPF.getText());
        professor.setPis(campoPisPasep.getText());
        professor.setPisDataInscricao(df.parse(campoDataCadPisPasep.getText()));
        professor.setCarteiraTrabalho(campoCarteiraTrabalho.getText());
        professor.setCarteiraTrabalhoSerie(campoCarteiraTrabalhoSerie.getText());
        professor.setCarteiraTrabalhoUf(caixaCombinacaoCarteiraTrabalhoUF.getSelectedItem().toString());
        professor.setCarteiraTrabalhoDataEmissao(df.parse(campoCarteiraTrabalhoDataEmissao.getText()));

        TituloEleitor tituloEleitor = new TituloEleitor();
        tituloEleitor.setTituloEleitor(campoTituloEleitor.getText());
        tituloEleitor.setTituloEleitorZona(campoTituloEleitorZona.getText());
        tituloEleitor.setTituloEleitorSecao(campoTituloEleitorSecao.getText());
        tituloEleitor.setTituloEleitorUf(caixaCombinacaoTituloUF.getSelectedItem().toString());
        tituloEleitor.setTituloEleitorDataEmissao(df.parse(campoTituloEleitorDataEmissao.getText()));
        professor.setTituloEleitor(tituloEleitor);
        professor.setCnh(campoCarteiraHabilitacao.getText());
        professor.setCnhCategoria(CaixaCombinacaoCarteiraHabilitacaoCategoria.getSelectedItem().toString());
        professor.setCnhDataEmissao(df.parse(campoCarteiraHabilitacaoData.getText()));

        CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
        certificadoMilitar.setCertificadoMilitarTipo(campoCertMilitar.getText());
        certificadoMilitar.setCertificadoMilitarNumero(campoCertMilitarNum.getText());
        certificadoMilitar.setCertificadoMilitarCsm(campoCsmOam.getText());
        certificadoMilitar.setCertificadoMilitarSerie(campoCertMilitarSerie.getText());
        certificadoMilitar.setCertificadoMilitarRm(campoRmDn.getText());
        certificadoMilitar.setCertificadoMilitarCategoria(campoCertMilitarCategoria.getText());
        professor.setCertificadoMilitar(certificadoMilitar);

        professor.setConjuge(campoNomeConjugue.getText());
        professor.setConjugeCpf(campoCpfConjugue.getText());
        professor.setGrupoSanguineo(caixaCombinacaoGrupoSanguineo.getSelectedItem().toString());
        professor.setFatorRh(caixaCombinacaoFatorRH.getSelectedItem().toString());
        return professor;
    }

    void setDadosProfessor(String cpf, String matricula) throws ParseException, SQLException {

        MetodosAplicacao metodosAplicacao = new MetodosAplicacao();
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        if (matriculaDAO.verificarMatriculaProfessor(cpf) == true) {
            professor = professorDAO.pesquisarProfessorComMatricula(cpf, matricula);
            campoMatricula.setText(professor.getMatricula());
            campoDataMatricula.setText(metodosAplicacao.dataToString(professor.getDataMatricula()));
            campoMatricula.setEditable(false);
            campoDataMatricula.setEditable(false);
                        System.out.println("grupo sanguinneo"+ professor.getGrupoSanguineo());

        } else {
            professor = professorDAO.pesquisarProfessor(cpf);
            System.out.println("grupo sanguinneo"+ professor.getGrupoSanguineo());
        }
        caixaCombinacaoNaturalidadeUF.setSelectedItem(professor.getNaturalidadeUf());
        campoCPF.setText(professor.getCpf());
        campoNome.setText(professor.getNome());
        //campoStatus.setText("0");
        // campoSenha.setText("descobreeu");
        campoCarteiraIdentidade.setText(professor.getRg().getRg());
        campoOrgaoEmissorRG.setText(professor.getRg().getRgOrgaoEmissor());
        campoDataEmissaoRG.setText(metodosAplicacao.dataToString(professor.getRg().getRgDataEmissao()));
        campoDataNascimento.setText(metodosAplicacao.dataToString(professor.getDataNascimento()));
        campoNacionalidade.setText(professor.getNacionalidade());
        campoNaturalidade.setText(professor.getNaturalidade());
        caixaCombinacaoEstadoCivil.setSelectedItem(professor.getEstadoCivil());
        caixaCombinacaoSexo.setSelectedItem(professor.getSexo());
        caixaCombinacaoCarteiraIdentidadeUF.setSelectedItem(professor.getRg().getRgOrgaoEmissorUf());
        campoTituloEleitor.setText(professor.getTituloEleitor().getTituloEleitor());
        campoTituloEleitorSecao.setText(professor.getTituloEleitor().getTituloEleitorSecao());
        campoTituloEleitorZona.setText(professor.getTituloEleitor().getTituloEleitorZona());
        caixaCombinacaoTituloUF.setSelectedItem(professor.getTituloEleitor().getTituloEleitorUf());
        campoTituloEleitorDataEmissao.setText(metodosAplicacao.dataToString(professor.getTituloEleitor().getTituloEleitorDataEmissao()));
        campoCertMilitarNum.setText(professor.getCertificadoMilitar().getCertificadoMilitarNumero());
        campoCertMilitar.setText(professor.getCertificadoMilitar().getCertificadoMilitarTipo());
        campoCertMilitarSerie.setText(professor.getCertificadoMilitar().getCertificadoMilitarSerie());
        campoCertMilitarCategoria.setText(professor.getCertificadoMilitar().getCertificadoMilitarCategoria());
        campoCsmOam.setText(professor.getCertificadoMilitar().getCertificadoMilitarCsm());
        campoRmDn.setText(professor.getCertificadoMilitar().getCertificadoMilitarRm());
        caixaCombinacaoFatorRH.setSelectedItem(professor.getFatorRh());
        caixaCombinacaoGrupoSanguineo.setSelectedItem(professor.getGrupoSanguineo());
        campoNomeMae.setText(professor.getFiliacaoMae());
        campoNomePai.setText(professor.getFiliacaoPai());
        campoEmail.setText(professor.getEmail());
        campoFone1.setText(professor.getTelefone1());
        campoFone2.setText(professor.getTelefone2());
        campoEndereco.setText(professor.getEndereco().getEndereco());
        campoNºEndereco.setText(professor.getEndereco().getNumero());
        campoBairro.setText(professor.getEndereco().getBairro());
        campoCidade.setText(professor.getEndereco().getMunicipio());
        caixaCombinacaoEnderecoUF.setSelectedItem(professor.getEndereco().getUf());
        campoCep.setText(professor.getEndereco().getCep());
        campoPisPasep.setText(professor.getPis());
        campoDataCadPisPasep.setText(metodosAplicacao.dataToString(professor.getPisDataInscricao()));
        campoCarteiraTrabalho.setText(professor.getCarteiraTrabalho());
        campoCarteiraTrabalhoSerie.setText(professor.getCarteiraTrabalhoUf());
        caixaCombinacaoCarteiraTrabalhoUF.setSelectedItem(professor.getCarteiraTrabalhoUf());
        CaixaCombinacaoCarteiraHabilitacaoCategoria.setSelectedItem(professor.getCnhCategoria());
        caixaCombinacaoFormacao.setSelectedItem(professor.getFormacao());
        campoCarteiraTrabalhoDataEmissao.setText(metodosAplicacao.dataToString(professor.getCarteiraTrabalhoDataEmissao()));
        campoCarteiraHabilitacao.setText(professor.getCnh());
        campoCarteiraHabilitacaoData.setText(metodosAplicacao.dataToString(professor.getCnhDataEmissao()));
        campoNomeConjugue.setText(professor.getConjuge());
        campoCpfConjugue.setText(professor.getConjugeCpf());
        inicializarDisciplinasProfessor();
        inicializarDependentesProfessor();
        inicializarFormacao();
    }

    void setDadosProfessor() {

        campoCPF.setText("11111111011");
        campoNome.setText("Andre de souza silva pinto dos santos");
     //campoStatus.setText("0");
        // campoSenha.setText("descobreeu");
        campoCarteiraIdentidade.setText("1111111111020 2via");
        campoOrgaoEmissorRG.setText("ssp");
        //campoOrgaoEmissoUF.setText("ma");
        campoDataEmissaoRG.setText("25/12/1993");
        campoDataNascimento.setText("25/12/1993");
        campoNacionalidade.setText("brasileiro");
        campoNaturalidade.setText("Quixeranobim da ponta do leste");
     //campoNaturalidadeUF.setText("PB");
        //caixaCombinacaoEstadoCivil.add("Sollteiro");
        //caixaCombinacaoSexo.setText("M");
        //campoFormacao.setText("Ensino superior incompleto");
        campoTituloEleitor.setText("111111111012");
        campoTituloEleitorSecao.setText("11105");
        campoTituloEleitorZona.setText("11105");
        //campoTituloEleitorUF.setText("Pi");
        campoTituloEleitorDataEmissao.setText("25/12/1993");
        campoCertMilitarNum.setText("11111111111015");
        campoCertMilitar.setText("contigente");
        campoCertMilitarSerie.setText("11111111111015");
        campoCertMilitarCategoria.setText("11111111111015");
        campoCsmOam.setText("7 csm 11111015");
        campoRmDn.setText("reservista");
     //campoGrupoSanguineo.setText("ab");
        //campoFatorRH.setText("positivo");
        campoNomeMae.setText("Sebastiana andorinha que voa alto");
        campoNomePai.setText("Ludovico que tenha vida muito longa amem");
        campoEmail.setText("euseiqueesse_enderecoeestranho@gmail.com");
        campoFone1.setText("89911111011");
        campoFone2.setText("89922222011");
        campoEndereco.setText("Rua desembargador everton da baixada");
        campoNºEndereco.setText("111108");
        campoBairro.setText("Manguinha do Norte");
        campoCidade.setText("Quixeranobim do meio norte");
        //campoUFEndereco.setText("PI");
        campoCep.setText("11111108");
        campoPisPasep.setText("111111111012");
        campoDataCadPisPasep.setText("25/12/1993");
        campoCarteiraTrabalho.setText("111111111111111020");
        campoCarteiraTrabalhoSerie.setText("111111010");
        //campoCarteiraTrabalhoUF.setText("PI");
        campoCarteiraTrabalhoDataEmissao.setText("25/12/1993");
        campoCarteiraHabilitacao.setText("111111111012");
        campoCarteiraHabilitacaoData.setText("25/12/1993");
        //CaixaCombinacaoCarteiraHabilitacaoCategoria.setText("abc");
        campoCarteiraTrabalhoDataEmissao.setText("25/12/1993");
        campoNomeConjugue.setText("Franca que fica la do outro lado na Europa");
        campoCpfConjugue.setText("11111111011");
    }

    void adcionarDependentes() throws ParseException {
        DateFormat df = DateFormat.getDateInstance();
        df.setLenient(false);
        Dependentes dependentes = new Dependentes();
        dependentes.nome = campoNomeDependente.getText();
        dependentes.grauParentesco = campoGrauParentesco.getText();
        dependentes.dataNascimento = df.parse(campoDataNascimentoDependente.getText());
        professor.getVetorDependentes().add(dependentes);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDependentes.getModel();
        meuModelo.addRow(professor.dependenteToLinha(dependentes));
        limparCamposDependentes();

    }

    void limparCamposFormacao() {
        campoNomeFormacao.setText("");
        campoLotacao.setText("");
        campoTitulacao.setText("");
    }

    void adcionarFormacao() {
        Formacao formacao = new Formacao();
        formacao.nome = campoNomeFormacao.getText();
        formacao.lotacao = campoLotacao.getText();
        formacao.titulacao = campoTitulacao.getText();
        professor.getVetorFormacao().add(formacao);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaFormacao.getModel();
        meuModelo.addRow(professor.formacaoToLinha(formacao));
        limparCamposFormacao();
    }

    void removerFormacao() {
        int linha = tabelaFormacao.getSelectedRow();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaFormacao.getModel();
        meuModelo.removeRow(linha);
        professor.getVetorFormacao().remove(linha);
        limparCamposFormacao();
    }

    void adcionarDisciplinasArray() throws SQLException {
        int linha = tabelaDisciplinasDisponiveis.getSelectedRow();
        int coluna = 0;
        int idDisciplina = Integer.parseInt(tabelaDisciplinasDisponiveis.getValueAt(linha, coluna).toString());
        Disciplina disciplina = professor.adcionarDisciplinas(idDisciplina);
        professor.getVetorDisciplina().add(disciplina);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDisciplinaProfessor.getModel();
        meuModelo.addRow(disciplina.disciplinasToLinha(disciplina));
    }

    void removerDependentes() {
        int linha = tabelaDependentes.getSelectedRow();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDependentes.getModel();
        meuModelo.removeRow(linha);
        professor.getVetorDependentes().remove(linha);
        limparCamposDependentes();
    }

    void removerDisciplinasArray() {
        int linha = tabelaDisciplinaProfessor.getSelectedRow();
        int coluna = 0;
        int idDisciplina = Integer.parseInt(tabelaDisciplinaProfessor.getValueAt(linha, coluna).toString());
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaDisciplinaProfessor.getModel();
        meuModelo.removeRow(linha);
        int disciplina = professor.removerDisciplina(professor, idDisciplina);
        professor.getVetorDisciplina().remove(disciplina);
    }

    void salvarProfessor() throws SQLException {
        try {
            String cpfAntigo = professor.getCpf();
            professor = getDadosProfessor();
            ValidarCpf validarCpf = new ValidarCpf();
            ProfessorDAO professorDAO = new ProfessorDAO();
            Professor verificarProfessor = professorDAO.pesquisarProfessor(cpfAntigo);
            Validacao validacao = new Validacao();
            if (!campoCPF.getText().equals("") && validarCpf.validarCpf(professor.getCpf())) {
                try {
                    if (verificarProfessor.getCpf() != null) {
                        professor.atualizarProfessor(professor, cpfAntigo);
                        System.out.println("Grupo Ssanguineo" + professor.getGrupoSanguineo());
                    }
                    if (verificarProfessor.getCpf() == null) {
                        professor.cadastrarProfessor(professor);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
                }
                validacao.cadastroRealizado();
            }

        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");
    }

    public TelaCadastrarProfessor(Principal principal) {
        this.principal = principal;
        initComponents();
        setDadosProfessor();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campoDataNascimento = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        campoNacionalidade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        campoNaturalidade = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        caixaCombinacaoNaturalidadeUF = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        caixaCombinacaoEstadoCivil = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        caixaCombinacaoSexo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        campoFone1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        campoFone2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        campoNomeMae = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoNomePai = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        campoEndereco = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoNºEndereco = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        campoBairro = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        campoCidade = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        campoCep = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        caixaCombinacaoEnderecoUF = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        campoDataMatricula = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        campoCsmOam = new javax.swing.JTextField();
        campoCertMilitar = new javax.swing.JTextField();
        campoCarteiraHabilitacao = new javax.swing.JTextField();
        campoTituloEleitor = new javax.swing.JTextField();
        campoCarteiraTrabalho = new javax.swing.JTextField();
        campoPisPasep = new javax.swing.JTextField();
        campoCarteiraIdentidade = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        campoRmDn = new javax.swing.JTextField();
        campoCertMilitarNum = new javax.swing.JTextField();
        CaixaCombinacaoCarteiraHabilitacaoCategoria = new javax.swing.JComboBox();
        campoTituloEleitorZona = new javax.swing.JTextField();
        campoCarteiraTrabalhoSerie = new javax.swing.JTextField();
        campoDataCadPisPasep = new javax.swing.JFormattedTextField();
        campoOrgaoEmissorRG = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        campoCertMilitarSerie = new javax.swing.JTextField();
        campoCarteiraHabilitacaoData = new javax.swing.JFormattedTextField();
        campoTituloEleitorSecao = new javax.swing.JTextField();
        caixaCombinacaoCarteiraTrabalhoUF = new javax.swing.JComboBox();
        campoDataEmissaoRG = new javax.swing.JFormattedTextField();
        jLabel50 = new javax.swing.JLabel();
        caixaCombinacaoCarteiraIdentidadeUF = new javax.swing.JComboBox();
        campoCarteiraTrabalhoDataEmissao = new javax.swing.JFormattedTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        caixaCombinacaoTituloUF = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        campoTituloEleitorDataEmissao = new javax.swing.JFormattedTextField();
        campoCertMilitarCategoria = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        campoNomeConjugue = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter cpf11 = null;    try{  cpf11 = new javax.swing.text.MaskFormatter("###########");  cpf11.setPlaceholderCharacter(' ');    }catch(java.text.ParseException exc){    }
        campoCpfConjugue =      new javax.swing.JFormattedTextField(cpf11);
        jLabel54 = new javax.swing.JLabel();
        campoNomeDependente = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        campoGrauParentesco = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        campoDataNascimentoDependente = new javax.swing.JFormattedTextField();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaDependentes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        caixaCombinacaoFatorRH = new javax.swing.JComboBox();
        caixaCombinacaoGrupoSanguineo = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        campoNomeFormacao = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        campoTitulacao = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        campoLotacao = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelaFormacao = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        caixaCombinacaoFormacao = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelaDisciplinasDisponiveis = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabelaDisciplinaProfessor = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        javax.swing.text.MaskFormatter cpf1 = null;    try{  cpf1 = new javax.swing.text.MaskFormatter("###########");  cpf1.setPlaceholderCharacter(' ');    }catch(java.text.ParseException exc){    }
        campoCPF =  new javax.swing.JFormattedTextField(cpf1);
        jLabel24 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastrar Professor");

        jToolBar1.setFloatable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jButton1.setText("Salvar");
        jButton1.setToolTipText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButton2.setText("Fechar");
        jButton2.setToolTipText("Fechar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("DATA NASC");

        try {
            campoDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataNascimentoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("NACIONALIDADE");

        campoNacionalidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("NATURALIDADE");

        campoNaturalidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("UF");

        caixaCombinacaoNaturalidadeUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " ", " " }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ESTADO CIVIL");

        caixaCombinacaoEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "CASADO", "SOLTEIRO", " " }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("SEXO");

        caixaCombinacaoSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "M", "F" }));
        caixaCombinacaoSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caixaCombinacaoSexoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("FONE 1");

        campoFone1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoFone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFone1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("FONE 2");

        campoFone2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("NOME MÃE");

        campoNomeMae.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("NOME PAI");

        campoNomePai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoNomePai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomePaiActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("E-MAIL");

        campoEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("ENDEREÇO");

        campoEndereco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Nº");

        campoNºEndereco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("BAIRRO");

        campoBairro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("CIDADE");

        campoCidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("CEP");

        campoCep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("UF");

        caixaCombinacaoEnderecoUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("DATA MATRICULA");

        try {
            campoDataMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(498, 498, 498)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel47)
                            .addComponent(jLabel11))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoNacionalidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoFone1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(campoDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel46)
                                .addGap(7, 7, 7)
                                .addComponent(campoDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(campoCidade, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoEndereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(campoNºEndereco))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoCep, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(campoNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caixaCombinacaoEnderecoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(campoNaturalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(campoFone2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(caixaCombinacaoNaturalidadeUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caixaCombinacaoEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(caixaCombinacaoSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(campoDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(campoNacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(campoNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4)
                    .addComponent(caixaCombinacaoEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caixaCombinacaoSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caixaCombinacaoNaturalidadeUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(campoFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel18)
                    .addComponent(campoNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(campoNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel47)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(campoNºEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(campoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(campoCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(caixaCombinacaoEnderecoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoFone1, campoFone2});

        jTabbedPane1.addTab("DADOS PESSOAIS", jPanel10);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CARTEIRA IDENTIDADE");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("PIS/PASEP");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("CARTEIRA PROF.");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("TITULO ELEITOR");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("CARTEIRA HAB.");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("CERT. MILITAR TIPO");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("CSM/OAM");

        campoCsmOam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoCertMilitar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoCarteiraHabilitacao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoTituloEleitor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoTituloEleitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTituloEleitorActionPerformed(evt);
            }
        });

        campoCarteiraTrabalho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoCarteiraTrabalho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCarteiraTrabalhoActionPerformed(evt);
            }
        });

        campoPisPasep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoPisPasep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPisPasepActionPerformed(evt);
            }
        });

        campoCarteiraIdentidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoCarteiraIdentidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCarteiraIdentidadeActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("ORGÃO EMISSOR");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("DATA CAD.");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("SÉRIE");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("ZONA");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("CATEGORIA");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Nº");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("RM/DN/COMAR");

        campoRmDn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoCertMilitarNum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        CaixaCombinacaoCarteiraHabilitacaoCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "A", "AB", "B", "C", "D ", "E" }));

        campoTituloEleitorZona.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoCarteiraTrabalhoSerie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoCarteiraTrabalhoSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCarteiraTrabalhoSerieActionPerformed(evt);
            }
        });

        try {
            campoDataCadPisPasep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataCadPisPasep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoOrgaoEmissorRG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoOrgaoEmissorRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoOrgaoEmissorRGActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("DATA EMISSÃO");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("UF");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("SEÇÃO");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("DATA EMISSÃO");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("SÉRIE");

        campoCertMilitarSerie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            campoCarteiraHabilitacaoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCarteiraHabilitacaoData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoTituloEleitorSecao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoTituloEleitorSecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTituloEleitorSecaoActionPerformed(evt);
            }
        });

        caixaCombinacaoCarteiraTrabalhoUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        try {
            campoDataEmissaoRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataEmissaoRG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("UF");

        caixaCombinacaoCarteiraIdentidadeUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        try {
            campoCarteiraTrabalhoDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCarteiraTrabalhoDataEmissao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("DATA EMISSÃO");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("UF");

        caixaCombinacaoTituloUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("DATA EMISSÃO");

        try {
            campoTituloEleitorDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTituloEleitorDataEmissao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        campoCertMilitarCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("CATEGORIA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel25)
                    .addComponent(jLabel27)
                    .addComponent(jLabel31)
                    .addComponent(jLabel36)
                    .addComponent(jLabel39)
                    .addComponent(jLabel43))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCarteiraIdentidade, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPisPasep, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCarteiraTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCarteiraHabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTituloEleitor, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCertMilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCsmOam, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jLabel32)
                    .addComponent(jLabel37)
                    .addComponent(jLabel40)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CaixaCombinacaoCarteiraHabilitacaoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoOrgaoEmissorRG)
                    .addComponent(campoDataCadPisPasep, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(campoCarteiraTrabalhoSerie, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoTituloEleitorZona, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoCertMilitarNum, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoRmDn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel29)
                    .addComponent(jLabel33)
                    .addComponent(jLabel38)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(campoCarteiraHabilitacaoData, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campoCertMilitarSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCertMilitarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campoDataEmissaoRG, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caixaCombinacaoCarteiraIdentidadeUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoTituloEleitorSecao, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                    .addComponent(caixaCombinacaoCarteiraTrabalhoUF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(caixaCombinacaoTituloUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoCarteiraTrabalhoDataEmissao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTituloEleitorDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoOrgaoEmissorRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCarteiraIdentidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(campoDataEmissaoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(caixaCombinacaoCarteiraIdentidadeUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPisPasep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDataCadPisPasep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCarteiraTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCarteiraTrabalhoSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCarteiraTrabalhoDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caixaCombinacaoCarteiraTrabalhoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel31)
                    .addComponent(campoTituloEleitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(campoTituloEleitorZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(campoTituloEleitorSecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTituloEleitorDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caixaCombinacaoTituloUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel36)
                    .addComponent(campoCarteiraHabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(CaixaCombinacaoCarteiraHabilitacaoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCarteiraHabilitacaoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel39)
                    .addComponent(campoCertMilitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(campoCertMilitarNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(campoCertMilitarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCertMilitarSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel43)
                    .addComponent(campoCsmOam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(campoRmDn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane2.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("DOCUMENTAÇÃO", jPanel2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("NOME CONJUGUE");

        campoNomeConjugue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("CPF");

        campoCpfConjugue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoCpfConjugue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCpfConjugueKeyTyped(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("NOME DEPENDENTE");

        campoNomeDependente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("GRAU PARENTESCO");

        campoGrauParentesco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("DATA NASCIMENTO");

        try {
            campoDataNascimentoDependente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remover.png"))); // NOI18N
        jButton7.setToolTipText("Remover");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jButton6.setToolTipText("Adicionar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jScrollPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane5MouseClicked(evt);
            }
        });

        tabelaDependentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "GRAU PARENTESCO", "DATA NASCIMENTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaDependentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaDependentesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelaDependentes);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(campoNomeConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCpfConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNomeDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoGrauParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoDataNascimentoDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(campoCpfConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(campoNomeConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(campoNomeDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(campoGrauParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(campoDataNascimentoDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)))
        );

        jScrollPane3.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(" CONJUGUE/DEPENDENTES", jPanel3);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("FATOR RH");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("GRUPO SANGUINEO");

        caixaCombinacaoFatorRH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "+", "-" }));

        caixaCombinacaoGrupoSanguineo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "A", "B", "AB", "O" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel51))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(caixaCombinacaoGrupoSanguineo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(caixaCombinacaoFatorRH, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(caixaCombinacaoFatorRH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(caixaCombinacaoGrupoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane4.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ADCIONAIS", jPanel4);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText(" FORMACAO");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("TITULAÇÃO");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("LOTAÇÃO");

        jScrollPane7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane7MouseClicked(evt);
            }
        });

        tabelaFormacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FORMAÇÃO", "TITULAÇÃO", "LOTAÇÃO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaFormacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFormacaoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabelaFormacao);
        if (tabelaFormacao.getColumnModel().getColumnCount() > 0) {
            tabelaFormacao.getColumnModel().getColumn(0).setResizable(false);
            tabelaFormacao.getColumnModel().getColumn(1).setResizable(false);
            tabelaFormacao.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jButton8.setToolTipText("Adicionar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remover.png"))); // NOI18N
        jButton9.setToolTipText("Remover");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("GRAU ESCOLARIDADE");

        caixaCombinacaoFormacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "Fundamental Completo", "Fundamental Incomple", "Médio Completo", "Médio incompleto", "Tecnólogo", "Superior Completo", "Superior Inconpleto ", "Pós Grad. Mestrado", "Pós Grad. Doutorado", "PHD" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(15, 15, 15)
                                .addComponent(caixaCombinacaoFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeFormacao)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTitulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(caixaCombinacaoFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel57)
                    .addComponent(campoNomeFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTitulacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(campoLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jScrollPane6.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("FORMAÇÃO", jPanel8);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("DISCIPLINA DISPONIVEIS:");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        jButton4.setToolTipText("Listar Disciplinas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tabelaDisciplinasDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tabelaDisciplinasDisponiveis);
        if (tabelaDisciplinasDisponiveis.getColumnModel().getColumnCount() > 0) {
            tabelaDisciplinasDisponiveis.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaDisciplinasDisponiveis.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("DISCIPLINAS MINISTRADAS:");

        tabelaDisciplinaProfessor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tabelaDisciplinaProfessor);
        if (tabelaDisciplinaProfessor.getColumnModel().getColumnCount() > 0) {
            tabelaDisciplinaProfessor.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaDisciplinaProfessor.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jButton3.setToolTipText("Adicionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remover.png"))); // NOI18N
        jButton5.setToolTipText("Remover");
        jButton5.setMaximumSize(new java.awt.Dimension(55, 55));
        jButton5.setMinimumSize(new java.awt.Dimension(55, 55));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MATRÍCULA");

        campoMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoMatriculaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("NOME");

        campoNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });

        campoCPF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCPFKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("CPF");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addComponent(jLabel48))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel48)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jLabel49)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

    private void campoDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataNascimentoActionPerformed

    private void caixaCombinacaoSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caixaCombinacaoSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caixaCombinacaoSexoActionPerformed

    private void campoNomePaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomePaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomePaiActionPerformed

    private void campoTituloEleitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTituloEleitorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTituloEleitorActionPerformed

    private void campoCarteiraTrabalhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCarteiraTrabalhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCarteiraTrabalhoActionPerformed

    private void campoPisPasepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPisPasepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPisPasepActionPerformed

    private void campoCarteiraIdentidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCarteiraIdentidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCarteiraIdentidadeActionPerformed

    private void campoCarteiraTrabalhoSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCarteiraTrabalhoSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCarteiraTrabalhoSerieActionPerformed

    private void campoOrgaoEmissorRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoOrgaoEmissorRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoOrgaoEmissorRGActionPerformed

    private void campoTituloEleitorSecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTituloEleitorSecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTituloEleitorSecaoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        removerDependentes();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try {
            // TODO add your handling code here:
            adcionarDependentes();
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tabelaDependentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaDependentesMouseClicked
        /*int linha = tabelaDependentes.getSelectedRow();
         campoNomeDependente.setText(tabelaDependentes.getValueAt(linha, 0).toString());
         campoGrauParentesco.setText(tabelaDependentes.getValueAt(linha, 1).toString());
         campoDataNascimentoDependente.setText(tabelaDependentes.getValueAt(linha, 2).toString());*/

    }//GEN-LAST:event_tabelaDependentesMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            listarDisciplinas();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            salvarProfessor();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            // TODO add your handling code here:
            adcionarDisciplinasArray();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        removerDisciplinasArray();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaCadastrarProfessor();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabelaFormacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFormacaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaFormacaoMouseClicked

    private void jScrollPane7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane7MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        adcionarFormacao();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        removerFormacao();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void campoFone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFone1ActionPerformed

    private void jScrollPane5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane5MouseClicked

    private void campoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoMatriculaActionPerformed

    private void campoCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyTyped
        // TODO add your handling code here:
        
String valor ="'';0123456789";
               if(!valor.contains(evt.getKeyChar()+"")){  
                   evt.consume();
               }
    }//GEN-LAST:event_campoCPFKeyTyped

    private void campoCpfConjugueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCpfConjugueKeyTyped
        // TODO add your handling code here:
        
        
String valor ="'';0123456789";
               if(!valor.contains(evt.getKeyChar()+"")){  
                   evt.consume();
               }
    }//GEN-LAST:event_campoCpfConjugueKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CaixaCombinacaoCarteiraHabilitacaoCategoria;
    private javax.swing.JComboBox caixaCombinacaoCarteiraIdentidadeUF;
    private javax.swing.JComboBox caixaCombinacaoCarteiraTrabalhoUF;
    private javax.swing.JComboBox caixaCombinacaoEnderecoUF;
    private javax.swing.JComboBox caixaCombinacaoEstadoCivil;
    private javax.swing.JComboBox caixaCombinacaoFatorRH;
    private javax.swing.JComboBox caixaCombinacaoFormacao;
    private javax.swing.JComboBox caixaCombinacaoGrupoSanguineo;
    private javax.swing.JComboBox caixaCombinacaoNaturalidadeUF;
    private javax.swing.JComboBox caixaCombinacaoSexo;
    private javax.swing.JComboBox caixaCombinacaoTituloUF;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoCarteiraHabilitacao;
    private javax.swing.JFormattedTextField campoCarteiraHabilitacaoData;
    private javax.swing.JTextField campoCarteiraIdentidade;
    private javax.swing.JTextField campoCarteiraTrabalho;
    private javax.swing.JFormattedTextField campoCarteiraTrabalhoDataEmissao;
    private javax.swing.JTextField campoCarteiraTrabalhoSerie;
    private javax.swing.JTextField campoCep;
    private javax.swing.JTextField campoCertMilitar;
    private javax.swing.JTextField campoCertMilitarCategoria;
    private javax.swing.JTextField campoCertMilitarNum;
    private javax.swing.JTextField campoCertMilitarSerie;
    private javax.swing.JTextField campoCidade;
    private javax.swing.JTextField campoCpfConjugue;
    private javax.swing.JTextField campoCsmOam;
    private javax.swing.JFormattedTextField campoDataCadPisPasep;
    private javax.swing.JFormattedTextField campoDataEmissaoRG;
    private javax.swing.JFormattedTextField campoDataMatricula;
    private javax.swing.JFormattedTextField campoDataNascimento;
    private javax.swing.JFormattedTextField campoDataNascimentoDependente;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoFone1;
    private javax.swing.JTextField campoFone2;
    private javax.swing.JTextField campoGrauParentesco;
    private javax.swing.JTextField campoLotacao;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JTextField campoNacionalidade;
    private javax.swing.JTextField campoNaturalidade;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNomeConjugue;
    private javax.swing.JTextField campoNomeDependente;
    private javax.swing.JTextField campoNomeFormacao;
    private javax.swing.JTextField campoNomeMae;
    private javax.swing.JTextField campoNomePai;
    private javax.swing.JTextField campoNºEndereco;
    private javax.swing.JTextField campoOrgaoEmissorRG;
    private javax.swing.JTextField campoPisPasep;
    private javax.swing.JTextField campoRmDn;
    private javax.swing.JTextField campoTitulacao;
    private javax.swing.JTextField campoTituloEleitor;
    private javax.swing.JFormattedTextField campoTituloEleitorDataEmissao;
    private javax.swing.JTextField campoTituloEleitorSecao;
    private javax.swing.JTextField campoTituloEleitorZona;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelaDependentes;
    private javax.swing.JTable tabelaDisciplinaProfessor;
    private javax.swing.JTable tabelaDisciplinasDisponiveis;
    private javax.swing.JTable tabelaFormacao;
    // End of variables declaration//GEN-END:variables
}
