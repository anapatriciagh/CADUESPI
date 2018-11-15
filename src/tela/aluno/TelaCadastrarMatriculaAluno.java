/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.aluno;

import dao.AlunoDAO;
import entidades.Aluno;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import entidades.util.MetodosMatricula;
import tela.Principal;

/**
 *
 * @author Rubens
 */
public class TelaCadastrarMatriculaAluno extends javax.swing.JInternalFrame {

    private Principal principal;

    void limparCampos() {
        campoCPF.setText("");
        campoDataMatricula.setText("");
        campoMatricula.setText("");
        campoNome.setText("");
    }

    private void desativarCampos() {
        campoCPF.setEnabled(false);
        campoDataMatricula.setEnabled(false);
        campoMatricula.setEnabled(false);
        campoNome.setEnabled(false);
    }

    private void ativarCampos() {
        campoCPF.setEnabled(true);
        campoDataMatricula.setEnabled(true);
        campoMatricula.setEnabled(true);
        campoNome.setEnabled(true);
        campoCPF.setEditable(true);
        campoDataMatricula.setEditable(true);
        campoMatricula.setEditable(true);
        campoNome.setEditable(true);
    }

    public void editar() {
        limparCampos();
        ativarCampos();
        int linha = tabelaMatricula.getSelectedRow();
        campoNome.setText(tabelaMatricula.getValueAt(linha, 2).toString());
    }

    public void zerarTabela() {
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
    }

    public void atualizarTela() {
        this.repaint();
    }

    private void salvar() {
        Aluno aluno = getDadosMatricula();
        aluno.cadastrarMatricula(aluno);
        try {
            listarMatricula();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deletar() {
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        int linha = tabelaMatricula.getSelectedRow();
        aluno.setMatricula(tabelaMatricula.getValueAt(linha, 0).toString());
        alunoDAO.deletarMatricula(aluno);
        campoMatricula.setText("");
        campoNome.setText("");
        campoCPF.setText("");
        campoDataMatricula.setText("");
        try {
            listarMatricula();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarMatricula() throws SQLException {
        ArrayList<Aluno> vetorAlunos = new ArrayList<>();
        AlunoDAO alunoDAO = new AlunoDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        vetorAlunos = alunoDAO.pesquisarListaAlunosComMatricula();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorAlunos.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(vetorAlunos.get(i)));
        }
    }

    public void preencherJtablePelaMatricula(String matricula) throws SQLException {
        Aluno Aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        Aluno = alunoDAO.pesquisarAlunoPelaMatricula(matricula);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        meuModelo.addRow(metodosMatricula.matriculaToLinha(Aluno));
    }

    public void preencherJtablePeloNome(String nome) throws SQLException {
        ArrayList<Aluno> vetorAlunos = new ArrayList<>();
        AlunoDAO alunoDAO = new AlunoDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        vetorAlunos = alunoDAO.pesquisarAlunoComMatriculaPeloNome(nome);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorAlunos.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(vetorAlunos.get(i)));
        }

    }

    public void preencherJtablePeloCPF(String cpf) throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        ArrayList<Aluno> listaAlunos = alunoDAO.pesquisarListaAlunoComMatricula(cpf);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaAlunos.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(listaAlunos.get(i)));
        }
    }

    Aluno getDadosMatricula() {
        Aluno aluno = new Aluno();
        DateFormat df = DateFormat.getDateInstance();
        df.setLenient(false);
        aluno.setCpf(campoCPF.getText());
        aluno.setMatricula(campoMatricula.getText());
        try {
            aluno.setDataMatricula(df.parse(campoDataMatricula.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastrarMatriculaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aluno;
    }

    void pesquisarAluno() {
        campoNome.setEditable(false);
        campoCPF.setEditable(false);
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            aluno = alunoDAO.pesquisarAluno(campoCPF.getText());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        campoNome.setText(aluno.getNome());
        campoMatricula.setText("");
    }

    /**
     * Creates new form CadastrarMatricula
     *
     * @param principal
     * @throws java.sql.SQLException
     */
    public TelaCadastrarMatriculaAluno(Principal principal) throws SQLException {
        this.principal = principal;
        initComponents();
        listarMatricula();
        desativarCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMatricula = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoDataMatricula = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter cpf1 = null;    try{  cpf1 = new javax.swing.text.MaskFormatter("###########");  cpf1.setPlaceholderCharacter(' ');    }catch(java.text.ParseException exc){    }
        campoCPF = new javax.swing.JFormattedTextField(cpf1);
        jButton4 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tabelaMatricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATRICULA", "DATA MATRICULA", "NOME", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMatriculaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMatricula);
        if (tabelaMatricula.getColumnModel().getColumnCount() > 0) {
            tabelaMatricula.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Matricula Aluno");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cadastrar Matricula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(814, 177));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Matricula");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Data Matricula");

        try {
            campoDataMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataMatriculaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CPF");

        campoCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCPFActionPerformed(evt);
            }
        });
        campoCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCPFKeyTyped(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar2.png"))); // NOI18N
        jButton4.setToolTipText("Pesquisar Cpf Para Matricula");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campoMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoNome))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(37, 37, 37)
                        .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(campoDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        jButton6.setText("Pesquisar");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/new.png"))); // NOI18N
        jButton9.setText("Novo");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jButton1.setText("Salvar");
        jButton1.setToolTipText("Salvar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jButton2.setText("Editar");
        jButton2.setToolTipText("Editar");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        jButton8.setText("Listar");
        jButton8.setToolTipText("Listar");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar.png"))); // NOI18N
        jButton5.setText("Limpar");
        jButton5.setToolTipText("Limpar");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/lixeira.png"))); // NOI18N
        jButton3.setText("Deletar");
        jButton3.setToolTipText("Deletar");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButton7.setText("Fechar");
        jButton7.setToolTipText("Fechar");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tabela Matriculas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salvar();
        desativarCampos();
        limparCampos();
        //this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        deletar();
        limparCampos();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabelaMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMatriculaMouseClicked


    }//GEN-LAST:event_tabelaMatriculaMouseClicked

    private void campoCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCPFActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        pesquisarAluno();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        principal.exibirTelaPesquisarMatriculaAluno();
        desativarCampos();
        limparCampos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        limparCampos();
        ativarCampos();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        try {
            listarMatricula();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        limparCampos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        limparCampos();
        zerarTabela();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaCadastrarMatriculaAluno();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void campoDataMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataMatriculaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void campoCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyTyped
        // TODO add your handling code here:
            String valor ="'';0123456789";
               if(!valor.contains(evt.getKeyChar()+"")){  
                   evt.consume();
               }
    }//GEN-LAST:event_campoCPFKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoCPF;
    private javax.swing.JFormattedTextField campoDataMatricula;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JTextField campoNome;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelaMatricula;
    // End of variables declaration//GEN-END:variables
}
