/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.professor;

import entidades.Professor;
import entidades.Aluno;
import dao.AlunoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.ProfessorDAO;
import tela.Principal;

/**
 *
 * @author rlopes
 */
public class TelaVisualizarProfessor extends javax.swing.JInternalFrame {

    Principal principal;
    
      public void zerarTabela() {
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarProfessores.getModel();
        meuModelo.setNumRows(0);
    }

    private void preencherJtableProfessor() throws SQLException {
        ArrayList<Professor> vetorProfessores = new ArrayList<Professor>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professor = new Professor();
        vetorProfessores = professorDAO.pesquisarTodosProfessores();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarProfessores.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorProfessores.size(); i++) {
            meuModelo.addRow(professor.professorToLinha(vetorProfessores.get(i)));
        }
    }

    public void preencherJtableProfessorPeloNome(String nome) throws SQLException {
        ArrayList<Professor> vetorProfessor = new ArrayList<Professor>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professor = new Professor();
        vetorProfessor = professorDAO.pesquisarProfessorPeloNome(nome);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarProfessores.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorProfessor.size(); i++) {
            meuModelo.addRow(professor.professorToLinha(vetorProfessor.get(i)));
        }
    }

    public void preencherJtableAlunoPeloCpf(String cpf) throws SQLException {

        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professor = new Professor();
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        listaProfessor = professorDAO.pesquisarListaProfessorComMatricula(cpf);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarProfessores.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaProfessor.size(); i++) {
            meuModelo.addRow(professor.professorToLinha(listaProfessor.get(i)));

        }

    }

    public void preencherJtableAlunoPelaMatricula(String matricula) throws SQLException {

        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professor = new Professor();
        ArrayList<Professor> listaProfessor = new ArrayList<>();
        listaProfessor = professorDAO.pesquisarProfessorPelaMatricula(matricula);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarProfessores.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaProfessor.size(); i++) {
            meuModelo.addRow(professor.professorToLinha(listaProfessor.get(i)));

        }

    }

    public void atualizarTela() {
        this.repaint();
    }

    /**
     * Creates new form PesquisarProfessor
     */
    public TelaVisualizarProfessor(Principal principal) {
        this.principal = principal;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaListarProfessores = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Professores Cadastrados");

        tabelaListarProfessores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "NOME", "Matricula", "RG", "DATA NASCIMENTO", "NACIONALIDADE", "NATURALIDADE", "SEXO", "E-MAIL", "TELEFONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaListarProfessores);
        if (tabelaListarProfessores.getColumnModel().getColumnCount() > 0) {
            tabelaListarProfessores.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabelaListarProfessores.getColumnModel().getColumn(1).setPreferredWidth(300);
            tabelaListarProfessores.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabelaListarProfessores.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabelaListarProfessores.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabelaListarProfessores.getColumnModel().getColumn(6).setPreferredWidth(300);
            tabelaListarProfessores.getColumnModel().getColumn(7).setPreferredWidth(50);
            tabelaListarProfessores.getColumnModel().getColumn(8).setPreferredWidth(300);
            tabelaListarProfessores.getColumnModel().getColumn(9).setPreferredWidth(150);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        jButton4.setText("Pesquisar");
        jButton4.setToolTipText("Pesquisar");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        jButton1.setText("Listar");
        jButton1.setToolTipText("Listar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jButton2.setText("Alterar");
        jButton2.setToolTipText("Alterar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

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

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButton6.setText("Fechar");
        jButton6.setToolTipText("Fechar");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1279, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1295, 658);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            // TODO add your handling code here:
            preencherJtableProfessor();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVisualizarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int linha = tabelaListarProfessores.getSelectedRow();
        String cpf = (String) tabelaListarProfessores.getValueAt(linha, 0);
        String matricula = String.valueOf(tabelaListarProfessores.getValueAt(linha, 2));
        try {
            principal.exibirTelaCadastrarProfessor();
            principal.getTelaCadastrarProfessor().setDadosProfessor(cpf, matricula);
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(TelaVisualizarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        ProfessorDAO professorDAO = new ProfessorDAO();
        int linha = tabelaListarProfessores.getSelectedRow();
        int coluna = 0;
        String cpf = (String) tabelaListarProfessores.getValueAt(linha, coluna);
        Professor professor;
        try {
            professor = professorDAO.pesquisarProfessor(cpf);
            String cpfAntigo = professor.getCpf();
            professor.deletarProfessor(professor, cpfAntigo);
            JOptionPane.showMessageDialog(null, "PROFESSOR DELETADO !!");
            preencherJtableProfessor();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVisualizarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        zerarTabela();
        principal.exibirTelaPesquisarProfessor();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        zerarTabela();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaVisualizarProfessor();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelaListarProfessores;
    // End of variables declaration//GEN-END:variables
}
