/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.aluno;

import dao.AlunoDAO;
import entidades.Aluno;
import entidades.Curso;
import dao.CursoDAO;
import entidades.util.CertificadoMilitar;
import entidades.util.Endereco;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import entidades.util.Validacao;
import entidades.util.MetodosAplicacao;
import entidades.util.Rg;
import entidades.util.TituloEleitor;
import entidades.util.ValidarCpf;
import tela.Principal;

/**
 *
 * @author Rubens
 */
public final class TelaCadastrarAluno extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastrarAluno
     */
    private Aluno aluno = new Aluno();
    private Principal principal;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void atualizarTela() {
        this.repaint();
    }

    void inicializarCursoAluno() throws SQLException {
        Curso curso = new Curso();
        CursoDAO cursoDAO = new CursoDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = alunoDAO.pesquisarAluno(campoCPF.getText());
        curso = cursoDAO.pesquisarCursoALuno(aluno);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaCursoAluno.getModel();
        if (curso.nome != null) {
            meuModelo.setNumRows(0);
            meuModelo.addRow(curso.cursoToLinha(curso));
            this.aluno.setCurso(curso);
            System.out.println("curso do aluno " + this.aluno.getCurso().nome);
        }
    }

    void listarCurso() throws SQLException {
        ArrayList<Curso> listaCurso = new ArrayList<>();
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = new Curso();
        listaCurso = cursoDAO.pesquisarTodosCursos();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaCursosDisponiveis.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaCurso.size(); i++) {
            meuModelo.addRow(curso.cursoToLinha(listaCurso.get(i)));
        }
    }

    Aluno getDadosAluno() throws ParseException {
        DateFormat df = DateFormat.getDateInstance();
        df.setLenient(false);
        if (!campoMatricula.getText().isEmpty()) {
            aluno.setMatricula(campoMatricula.getText());
            aluno.setDataMatricula(df.parse(campoDataMatricula.getText()));
        }
        aluno.setDataNascimento(df.parse(campoDataNascimento.getText()));
        aluno.setNome(campoNome.getText());
        aluno.setNacionalidade(campoNacionalidade.getText());
        aluno.setNaturalidade(campoNaturalidade.getText());
        aluno.setNaturalidadeUf(caixaCombinacaoNaturalidadeUF.getSelectedItem().toString());
        aluno.setEstadoCivil(caixaCombinacaoEstadoCivil.getSelectedItem().toString());
        aluno.setSexo(caixaCombinacaoSexo.getSelectedItem().toString());
        aluno.setTelefone1(campoFone1.getText());
        aluno.setTelefone2(campoFone2.getText());
        aluno.setEmail(campoEmail.getText());
        aluno.setFiliacaoPai(campoNomePai.getText());
        aluno.setFiliacaoMae(campoNomeMae.getText());

        Endereco endereco = new Endereco();
        endereco.setEndereco(campoEndereco.getText());
        endereco.setNumero(campoNºEndereco.getText());
        endereco.setBairro(campoBairro.getText());
        endereco.setMunicipio(campoCidade.getText());
        endereco.setCep(campoCep.getText());
        endereco.setUf(caixaCombinacaoEnderecoUF.getSelectedItem().toString());
        aluno.setEndereco(endereco);

        aluno.setGrauEscolaridade(caixaCombinacaoGrauEscolaridade.getSelectedItem().toString());

        Rg rg = new Rg();
        rg.setRg(campoCarteiraIdentidade.getText());
        rg.setRgOrgaoEmissor(campoOrgaoEmissorRG.getText());
        rg.setRgOrgaoEmissorUf(caixaCombinacaoCarteiraIdentidadeUF.getSelectedItem().toString());

        if (campoDataEmissaoRG.getText().equals("  /  /    ")) {
            rg.setRgDataEmissao(null);
        } else {
            rg.setRgDataEmissao(df.parse(campoDataEmissaoRG.getText()));
        }

        aluno.setRg(rg);

        aluno.setCpf(campoCPF.getText());

        TituloEleitor tituloEleitor = new TituloEleitor();

        tituloEleitor.setTituloEleitor(campoTituloEleitor.getText());
        tituloEleitor.setTituloEleitorZona(campoTituloEleitorZona.getText());
        tituloEleitor.setTituloEleitorSecao(campoTituloEleitorSecao.getText());
        tituloEleitor.setTituloEleitorUf(caixaCombinacaoTituloUF.getSelectedItem().toString());
        tituloEleitor.setTituloEleitorDataEmissao(df.parse(campoTituloEleitorDataEmissao.getText()));
        aluno.setTituloEleitor(tituloEleitor);

        CertificadoMilitar certificadoMilitar = new CertificadoMilitar();
        certificadoMilitar.setCertificadoMilitarTipo(campoCertMilitar.getText());
        certificadoMilitar.setCertificadoMilitarNumero(campoCertMilitarNum.getText());
        certificadoMilitar.setCertificadoMilitarCsm(campoCsmOam.getText());
        certificadoMilitar.setCertificadoMilitarSerie(campoCertMilitarSerie.getText());
        certificadoMilitar.setCertificadoMilitarRm(campoRmDn.getText());
        certificadoMilitar.setCertificadoMilitarCategoria(campoCertMilitarCategoria.getText());
        aluno.setCertificadoMilitar(certificadoMilitar);

        aluno.setGrupoSanguineo(caixaCombinacaoGrupoSanguineo.getSelectedItem().toString());
        aluno.setFatorRh(caixaCombinacaoFatorRH.getSelectedItem().toString());
        return aluno;
    }

    void setDadosAluno(Aluno aluno) throws ParseException, SQLException {
        System.out.println("CPF" + aluno.getCpf());
        MetodosAplicacao metodosAplicacao = new MetodosAplicacao();
        if (aluno.verificarMatriculaAluno() == true) {
            //aluno = alunoDAO.pesquisarAlunoComMatricula(cpf, matricula);
            campoMatricula.setText(aluno.getMatricula());
            campoDataMatricula.setText(metodosAplicacao.dataToString(aluno.getDataMatricula()));
            campoMatricula.setEditable(false);
            campoDataMatricula.setEditable(false);

        } /*else {
         aluno = alunoDAO.pesquisarAluno(cpf);
         }*/

        caixaCombinacaoNaturalidadeUF.setSelectedItem(aluno.getNaturalidadeUf());
        campoCPF.setText(aluno.getCpf());
        campoNome.setText(aluno.getNome());
        //campoStatus.setText("0");
        // campoSenha.setText("descobreeu");
        campoCarteiraIdentidade.setText(aluno.getRg().getRg());
        campoOrgaoEmissorRG.setText(aluno.getRg().getRgOrgaoEmissor());
        campoDataEmissaoRG.setText(metodosAplicacao.dataToString(aluno.getRg().getRgDataEmissao()));
        campoDataNascimento.setText(metodosAplicacao.dataToString(aluno.getDataNascimento()));
        campoNacionalidade.setText(aluno.getNacionalidade());
        campoNaturalidade.setText(aluno.getNaturalidade());
        caixaCombinacaoEstadoCivil.setSelectedItem(aluno.getEstadoCivil());
        caixaCombinacaoSexo.setSelectedItem(aluno.getSexo());
        caixaCombinacaoCarteiraIdentidadeUF.setSelectedItem(aluno.getRg().getRgOrgaoEmissorUf());
        campoTituloEleitor.setText(aluno.getTituloEleitor().getTituloEleitor());
        campoTituloEleitorSecao.setText(aluno.getTituloEleitor().getTituloEleitorSecao());
        campoTituloEleitorZona.setText(aluno.getTituloEleitor().getTituloEleitorZona());
        caixaCombinacaoTituloUF.setSelectedItem(aluno.getTituloEleitor().getTituloEleitorUf());
        campoTituloEleitorDataEmissao.setText(metodosAplicacao.dataToString(aluno.getTituloEleitor().getTituloEleitorDataEmissao()));
        campoCertMilitarNum.setText(aluno.getCertificadoMilitar().getCertificadoMilitarNumero());
        campoCertMilitar.setText(aluno.getCertificadoMilitar().getCertificadoMilitarTipo());
        campoCertMilitarSerie.setText(aluno.getCertificadoMilitar().getCertificadoMilitarSerie());
        campoCertMilitarCategoria.setText(aluno.getCertificadoMilitar().getCertificadoMilitarCategoria());
        campoCsmOam.setText(aluno.getCertificadoMilitar().getCertificadoMilitarCsm());
        campoRmDn.setText(aluno.getCertificadoMilitar().getCertificadoMilitarRm());
        caixaCombinacaoFatorRH.setSelectedItem(aluno.getFatorRh());
        caixaCombinacaoGrupoSanguineo.setSelectedItem(aluno.getGrupoSanguineo().toCharArray());
        campoNomeMae.setText(aluno.getFiliacaoMae());
        campoNomePai.setText(aluno.getFiliacaoPai());
        campoEmail.setText(aluno.getEmail());
        campoFone1.setText(aluno.getTelefone1());
        campoFone2.setText(aluno.getTelefone2());
        campoEndereco.setText(aluno.getEndereco().getEndereco());
        campoNºEndereco.setText(aluno.getEndereco().getNumero());
        campoBairro.setText(aluno.getEndereco().getBairro());
        campoCidade.setText(aluno.getEndereco().getMunicipio());
        caixaCombinacaoEnderecoUF.setSelectedItem(aluno.getEndereco().getUf());
        campoCep.setText(aluno.getEndereco().getCep());
        this.aluno = aluno;
        inicializarCursoAluno();

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

    }

    void adcionarCursosArray() throws SQLException {
        int linha = tabelaCursosDisponiveis.getSelectedRow();
        int coluna = 0;
        int idCurso = Integer.parseInt(tabelaCursosDisponiveis.getValueAt(linha, coluna).toString());
        Curso curso = aluno.adcionarCurso(idCurso);
        aluno.setCurso(curso);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaCursoAluno.getModel();
        meuModelo.addRow(curso.cursoToLinha(curso));
    }

    void removerCursoArray() {
        int linha = tabelaCursoAluno.getSelectedRow();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaCursoAluno.getModel();
        meuModelo.removeRow(linha);
        aluno.setCurso(null);
    }

    void salvarAluno() throws SQLException, ParseException {
        Validacao validacao = new Validacao();
        String cpfAntigo = aluno.getCpf();
        ValidarCpf validarCpf = new ValidarCpf();
        aluno = getDadosAluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        if (aluno.getCurso() != null) {
            Aluno verificarAluno = alunoDAO.pesquisarAluno(cpfAntigo);
            System.out.println("cpf antigo" + cpfAntigo);
            if (verificarAluno.getCpf() != null && validarCpf.validarCpf(campoCPF.getText())) {
                aluno.atualizarAluno(aluno, cpfAntigo);
            } else {
                aluno.cadastrarAluno(aluno);
            }
            validacao.cadastroRealizado();
        } else {
            validacao.alunoSemCurso();
        }
    }

    public TelaCadastrarAluno(Principal principal) {
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
        jLabel15 = new javax.swing.JLabel();
        caixaCombinacaoGrauEscolaridade = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelaCursosDisponiveis = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabelaCursoAluno = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        javax.swing.text.MaskFormatter cpf1 = null;

        try{
            cpf1 = new javax.swing.text.MaskFormatter("###########");
            cpf1.setPlaceholderCharacter(' ');

        }catch(java.text.ParseException exc){

        }
        campoCPF = new javax.swing.JFormattedTextField(cpf1);
        jLabel24 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        campoFone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoFone1KeyTyped(evt);
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
                    .addComponent(campoOrgaoEmissorRG)
                    .addComponent(campoDataCadPisPasep, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(campoCarteiraTrabalhoSerie, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoTituloEleitorZona, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoCertMilitarNum, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoRmDn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CaixaCombinacaoCarteiraHabilitacaoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(caixaCombinacaoTituloUF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoOrgaoEmissorRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoCarteiraIdentidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("GRAU ESCOLARIDADE");

        caixaCombinacaoGrauEscolaridade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "Fundamental Completo", "Fundamental Incomple", "Médio Completo", "Médio incompleto", "Tecnólogo", "Superior Completo", "Superior Inconpleto ", "Pós Grad. Mestrado", "Pós Grad. Doutorado", "PHD" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(15, 15, 15)
                .addComponent(caixaCombinacaoGrauEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(caixaCombinacaoGrauEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jScrollPane6.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("FORMAÇÃO", jPanel8);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("CURSO DISPONIVEIS:");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        jButton4.setToolTipText("Listar Cursos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tabelaCursosDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tabelaCursosDisponiveis);
        if (tabelaCursosDisponiveis.getColumnModel().getColumnCount() > 0) {
            tabelaCursosDisponiveis.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaCursosDisponiveis.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("CURSO SELECIONADO");

        tabelaCursoAluno.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(tabelaCursoAluno);
        if (tabelaCursoAluno.getColumnModel().getColumnCount() > 0) {
            tabelaCursoAluno.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaCursoAluno.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jButton3.setToolTipText("Selecionar Curso");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remover.png"))); // NOI18N
        jButton5.setToolTipText("Remover Curso");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel48)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jLabel49)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
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
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            listarCurso();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            try {
                salvarAluno();
            } catch (ParseException ex) {
                Logger.getLogger(TelaCadastrarAluno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            // TODO add your handling code here:
            adcionarCursosArray();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        removerCursoArray();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaCadastrarAluno();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void campoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoMatriculaActionPerformed

    private void campoTituloEleitorSecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTituloEleitorSecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTituloEleitorSecaoActionPerformed

    private void campoOrgaoEmissorRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoOrgaoEmissorRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoOrgaoEmissorRGActionPerformed

    private void campoCarteiraTrabalhoSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCarteiraTrabalhoSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCarteiraTrabalhoSerieActionPerformed

    private void campoCarteiraIdentidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCarteiraIdentidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCarteiraIdentidadeActionPerformed

    private void campoPisPasepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPisPasepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPisPasepActionPerformed

    private void campoCarteiraTrabalhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCarteiraTrabalhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCarteiraTrabalhoActionPerformed

    private void campoTituloEleitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTituloEleitorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTituloEleitorActionPerformed

    private void campoNomePaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomePaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomePaiActionPerformed

    private void campoFone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFone1ActionPerformed

    private void caixaCombinacaoSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caixaCombinacaoSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caixaCombinacaoSexoActionPerformed

    private void campoDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataNascimentoActionPerformed

    private void campoFone1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoFone1KeyTyped
        // TODO add your handling code here:
        String valores = "1234567890";
        if(!valores.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_campoFone1KeyTyped

    private void campoCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyTyped
        // TODO add your handling code here:
         String valor ="'';0123456789";
               if(!valor.contains(evt.getKeyChar()+"")){  
                   evt.consume();
               }
    }//GEN-LAST:event_campoCPFKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CaixaCombinacaoCarteiraHabilitacaoCategoria;
    private javax.swing.JComboBox caixaCombinacaoCarteiraIdentidadeUF;
    private javax.swing.JComboBox caixaCombinacaoCarteiraTrabalhoUF;
    private javax.swing.JComboBox caixaCombinacaoEnderecoUF;
    private javax.swing.JComboBox caixaCombinacaoEstadoCivil;
    private javax.swing.JComboBox caixaCombinacaoFatorRH;
    private javax.swing.JComboBox caixaCombinacaoGrauEscolaridade;
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
    private javax.swing.JTextField campoCsmOam;
    private javax.swing.JFormattedTextField campoDataCadPisPasep;
    private javax.swing.JFormattedTextField campoDataEmissaoRG;
    private javax.swing.JFormattedTextField campoDataMatricula;
    private javax.swing.JFormattedTextField campoDataNascimento;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoFone1;
    private javax.swing.JTextField campoFone2;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JTextField campoNacionalidade;
    private javax.swing.JTextField campoNaturalidade;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNomeMae;
    private javax.swing.JTextField campoNomePai;
    private javax.swing.JTextField campoNºEndereco;
    private javax.swing.JTextField campoOrgaoEmissorRG;
    private javax.swing.JTextField campoPisPasep;
    private javax.swing.JTextField campoRmDn;
    private javax.swing.JTextField campoTituloEleitor;
    private javax.swing.JFormattedTextField campoTituloEleitorDataEmissao;
    private javax.swing.JTextField campoTituloEleitorSecao;
    private javax.swing.JTextField campoTituloEleitorZona;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelaCursoAluno;
    private javax.swing.JTable tabelaCursosDisponiveis;
    // End of variables declaration//GEN-END:variables

}
