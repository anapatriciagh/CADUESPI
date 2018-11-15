/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.aluno;

import dao.AlunoDAO;
import entidades.Aluno;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tela.Principal;

/**
 *
 * @author rlopes
 */
public class TelaVisualizarAlunos extends javax.swing.JInternalFrame {

    private Principal principal;

    public void atualizarTela() {
        this.repaint();
    }

    public void zerarTabela() {
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarAlunos.getModel();
        meuModelo.setNumRows(0);
    }

    public void preencherJtableAluno() throws SQLException {
        ArrayList<Aluno> vetorAluno = new ArrayList<Aluno>();
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        vetorAluno = alunoDAO.pesquisarTodosAlunos();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarAlunos.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorAluno.size(); i++) {
            meuModelo.addRow(aluno.alunoToLinha(vetorAluno.get(i)));
        }
    }

    private void alterar() throws SQLException {
        try {
            int linha = tabelaListarAlunos.getSelectedRow();
            String cpf = (String) tabelaListarAlunos.getValueAt(linha, 0);
            
            // String matricula = (String) tabelaListarAlunos.getValueAt(linha, 2);
            AlunoDAO alunoDAO = new AlunoDAO();
            Aluno aluno = alunoDAO.pesquisarAlunoComMatricula(cpf);
            //System.out.println("CPF" + aluno.getCpf());
            try {
                principal.exibirTelaCadastrarAluno();
                principal.getTelaCadastrarAluno().setDadosAluno(aluno);
            } catch (SQLException ex) {
                Logger.getLogger(TelaVisualizarAlunos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(TelaVisualizarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deletar() {
        //zerarTabela();
        AlunoDAO alunoDAO = new AlunoDAO();
        int linha = tabelaListarAlunos.getSelectedRow();
        System.out.println("linha" + linha);
        // coluna = 0;
        String cpf = tabelaListarAlunos.getValueAt(linha, 0).toString();
        System.out.println("CPF" + cpf);
        Aluno aluno = new Aluno();
        try {
            aluno = alunoDAO.pesquisarAluno(cpf);

            if (!alunoDAO.verificarMatriculaAluno(cpf)) {
                String cpfAntigo = aluno.getCpf();
                aluno.deletarAluno(aluno, cpfAntigo);
                JOptionPane.showMessageDialog(null, "ALUNO DELETADO !!");
                preencherJtableAluno();
            } else {
                JOptionPane.showMessageDialog(null, "Aluno Não Pode Ser Deletado !!");

            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaVisualizarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preencherJtableAlunoPeloNome(String nome) throws SQLException {
        ArrayList<Aluno> vetorAluno = new ArrayList<>();
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        vetorAluno = alunoDAO.pesquisarAlunoPeloNome(nome);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarAlunos.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorAluno.size(); i++) {
            meuModelo.addRow(aluno.alunoToLinha(vetorAluno.get(i)));
        }
    }

    public void preencherJtableAlunoPeloCpf(String cpf) throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        ArrayList<Aluno> listaAluno = new ArrayList<>();
        listaAluno = alunoDAO.pesquisarListaAlunoComMatricula(cpf);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarAlunos.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < listaAluno.size(); i++) {
            meuModelo.addRow(aluno.alunoToLinha(listaAluno.get(i)));
        }
    }

    public void preencherJtableAlunoPelaMatricula(String matricula) throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        aluno = alunoDAO.pesquisarAlunoPelaMatricula(matricula);
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarAlunos.getModel();
        meuModelo.setNumRows(0);
        meuModelo.addRow(aluno.alunoToLinha(aluno));
    }

    /**
     * Creates new form PesquisarProfessor
     *
     * @param principal
     */
    public TelaVisualizarAlunos(Principal principal) {
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
        tabelaListarAlunos = new javax.swing.JTable();
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
        setPreferredSize(new java.awt.Dimension(1295, 662));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Alunos Cadastrados");

        tabelaListarAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "NOME", "MATRICULA", "RG", "DATA NASCIMENTO", "NACIONALIDADE", "NATURALIDADE", "SEXO", "E-MAIL", "TELEFONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaListarAlunos);
        if (tabelaListarAlunos.getColumnModel().getColumnCount() > 0) {
            tabelaListarAlunos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabelaListarAlunos.getColumnModel().getColumn(1).setPreferredWidth(350);
            tabelaListarAlunos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabelaListarAlunos.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabelaListarAlunos.getColumnModel().getColumn(4).setPreferredWidth(150);
            tabelaListarAlunos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabelaListarAlunos.getColumnModel().getColumn(6).setPreferredWidth(200);
            tabelaListarAlunos.getColumnModel().getColumn(7).setPreferredWidth(50);
            tabelaListarAlunos.getColumnModel().getColumn(8).setPreferredWidth(200);
            tabelaListarAlunos.getColumnModel().getColumn(9).setPreferredWidth(200);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1214, 671);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            preencherJtableAluno();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVisualizarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            alterar();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVisualizarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        deletar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        zerarTabela();
        principal.exibirTelaPesquisar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaVisuallizarAlunos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        zerarTabela();
    }//GEN-LAST:event_jButton5ActionPerformed


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
    private javax.swing.JTable tabelaListarAlunos;
    // End of variables declaration//GEN-END:variables
}
