/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.professor;

import entidades.Professor;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import entidades.util.MetodosMatricula;
import dao.ProfessorDAO;
import tela.Principal;

/**
 *
 * @author Rubens
 */
public class TelaCadastrarMatriculaProfessor extends javax.swing.JInternalFrame {

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

    private void listarMatricula() throws SQLException {
        ArrayList<Professor> vetorProfessores = new ArrayList<Professor>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        vetorProfessores = professorDAO.pesquisarListaProfessorComMatricula();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorProfessores.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(vetorProfessores.get(i)));
        }

    }

    public void preencherJtablePelaMatricula(String matricula) throws SQLException {
        ProfessorDAO professorDAO = new ProfessorDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        listaProfessor = professorDAO.pesquisarProfessorPelaMatricula(matricula);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaProfessor.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(listaProfessor.get(i)));
        }
    }

    public void preencherJtablePeloNome(String nome) throws SQLException {
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        listaProfessor = professorDAO.pesquisarProfessorComMatriculaPeloNome(nome);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaProfessor.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(listaProfessor.get(i)));
        }
    }

    public void preencherJtablePeloCPF(String cpf) throws SQLException {
        ArrayList<Professor> listaProfessor = new ArrayList<Professor>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        MetodosMatricula metodosMatricula = new MetodosMatricula();
        listaProfessor = professorDAO.pesquisarProfessorPeloCpf(cpf);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaMatricula.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaProfessor.size(); i++) {
            meuModelo.addRow(metodosMatricula.matriculaToLinha(listaProfessor.get(i)));

        }
    }

    Professor getDadosMatricula() {
        Professor professor = new Professor();
        DateFormat df = DateFormat.getDateInstance();
        df.setLenient(false);
        professor.setCpf(campoCPF.getText());
        professor.setMatricula(campoMatricula.getText());
        try {
            professor.setDataMatricula(df.parse(campoDataMatricula.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastrarMatriculaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return professor;
    }

    private void salvar() {
        Professor professor = getDadosMatricula();
        professor.cadastrarMatricula(professor);
        try {
            listarMatricula();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deletar() {
        Professor professor = new Professor();
        ProfessorDAO professorDAO = new ProfessorDAO();

        int linha = tabelaMatricula.getSelectedRow();
        professor.setMatricula(tabelaMatricula.getValueAt(linha, 0).toString());
        professorDAO.deletarMatricula(professor);
        campoMatricula.setText("");
        campoNome.setText("");
        campoCPF.setText("");
        campoDataMatricula.setText("");
        try {
            listarMatricula();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarTela() {
        this.repaint();
    }

    /**
     * Creates new form CadastrarMatricula
     *
     * @param principal
     * @throws java.sql.SQLException
     */
    public TelaCadastrarMatriculaProfessor(Principal principal) throws SQLException {
        this.principal = principal;
        initComponents();
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
        jToolBar1 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        jSeparator2 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(1027, 616));

        tabelaMatricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATRICULA", "DATA MATRICULA", "NOME", "CPF"
            }
        ));
        tabelaMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMatriculaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMatricula);

        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        jButton6.setText("Pesquisar");
        jButton6.setToolTipText("Pesquisar");
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
        jButton9.setToolTipText("Novo");
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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        jButton5.setText("Listar");
        jButton5.setToolTipText("Listar");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar.png"))); // NOI18N
        jButton7.setText("Limpar");
        jButton7.setToolTipText("Limpar");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

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

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButton8.setText("Fechar");
        jButton8.setToolTipText("Fechar");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Matricula Professor");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tabela de Matriculas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cadastrar Matricula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("MATRICULA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("DATA MATRICULA");

        try {
            campoDataMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("NOME");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jButton4.setText("Pesquisar CPF");
        jButton4.setToolTipText("Pesquisar CPF Para Cadastrar Matricula");
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(56, 56, 56)
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(296, 296, 296)
                        .addComponent(campoDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(campoDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(0, 169, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
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
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaMatriculaMouseClicked

    private void campoCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCPFActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        campoNome.setEditable(false);
        campoCPF.setEditable(false);
        Professor professor = new Professor();
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            professor = professorDAO.pesquisarProfessor(campoCPF.getText());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        campoNome.setText(professor.getNome());
        campoMatricula.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        principal.exibirTelaPesquisarMatriculaProfessor();
        desativarCampos();
        limparCampos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        limparCampos();
        ativarCampos();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //editar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        try {
            listarMatricula();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarMatriculaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        limparCampos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        limparCampos();
        zerarTabela();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaCadastrarMatriculaProfessor();
    }//GEN-LAST:event_jButton8ActionPerformed

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
