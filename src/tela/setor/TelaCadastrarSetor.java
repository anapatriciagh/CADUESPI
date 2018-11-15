/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.setor;

import dao.SetorDAO;
import entidades.Setor;
import entidades.util.Validacao;
import tela.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rlopes
 */
public class TelaCadastrarSetor extends javax.swing.JInternalFrame {

    Principal principal;

    void limparCampos() {
        campoNomeSetor.setText("");
    }

    private void desativarCampos() {
        campoNomeSetor.setEnabled(false);
    }

    private void ativarCampos() {
        campoNomeSetor.setEnabled(true);
    }

    public void editar() {
        limparCampos();
        ativarCampos();
        int linha = tabelaListarSetor.getSelectedRow();
        campoNomeSetor.setText(tabelaListarSetor.getValueAt(linha, 1).toString());
    }

    public void salvarSetor() {
        Setor setor = new Setor();
        setor.nome = campoNomeSetor.getText();
        int linhaSelecionada = tabelaListarSetor.getSelectedRow();
        if (linhaSelecionada == -1) {
            try {
                setor.cadastrarSetor(setor);
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastrarSetor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            setor.id = Integer.parseInt(tabelaListarSetor.getValueAt(linhaSelecionada, 0).toString());
            try {
                setor.atualizarSetor(setor);
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastrarSetor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            TelaCadastrarSetor.this.listarSetor();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarSetor.class.getName()).log(Level.SEVERE, null, ex);
        }
        limparCampos();
    }

    private void deletar() {
        Setor setor = new Setor();
        int linha = tabelaListarSetor.getSelectedRow();
        setor.id = Integer.parseInt(tabelaListarSetor.getValueAt(linha, 0).toString());
        try {
            setor.deletarSetor();
            listarSetor();
            limparCampos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarSetor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void listarSetor() throws SQLException {
        ArrayList<Setor> vetorSetor = new ArrayList<Setor>();
        SetorDAO setorDAO = new SetorDAO();
        Setor setor = new Setor();
        vetorSetor = setorDAO.pesquisarTodosSetores();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarSetor.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorSetor.size(); i++) {
            meuModelo.addRow(setor.setorToLinha(vetorSetor.get(i)));
        }
    }

    public void listarSetor(String nome) throws SQLException {
        SetorDAO setorDAO = new SetorDAO();
        Setor setor = new Setor();
        Validacao validacao = new Validacao();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarSetor.getModel();
        meuModelo.setNumRows(0);
        ArrayList<Setor> listaSetor = new ArrayList<Setor>();
        listaSetor = setorDAO.pesquisarSetorPeloNome(nome);
        if (listaSetor != null) {
            for (int i = 0; i < listaSetor.size(); i++) {
                meuModelo.addRow(setor.setorToLinha(listaSetor.get(i)));
            }
        } else {
            validacao.pesquisaErrada();
        }
    }

    public void atualizarTela() {
        this.repaint();
    }

    public void listarSetor(int id) throws SQLException {
        SetorDAO setorDAO = new SetorDAO();
        Setor setor = new Setor();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarSetor.getModel();
        meuModelo.setNumRows(0);
        setor = setorDAO.pesquisarSetor(id);
        meuModelo.addRow(setor.setorToLinha(setor));
    }

    public void zerarTabelaSetor() {
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarSetor.getModel();
        meuModelo.setNumRows(0);
    }

    public TelaCadastrarSetor(Principal principal) throws SQLException {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNomeSetor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaListarSetor = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nome Setor");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cadastrar Setores");

        campoNomeSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeSetorActionPerformed(evt);
            }
        });

        tabelaListarSetor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaListarSetor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaListarSetorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaListarSetor);
        if (tabelaListarSetor.getColumnModel().getColumnCount() > 0) {
            tabelaListarSetor.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaListarSetor.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        jButton4.setText("Pesquisar");
        jButton4.setToolTipText("Pesquisar");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/new.png"))); // NOI18N
        jButton6.setText("Novo");
        jButton6.setToolTipText("Novo");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jButton5.setText("Editar");
        jButton5.setToolTipText("Editar");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        jButton2.setText("Listar");
        jButton2.setToolTipText("Listar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

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

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tabela de Setores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(campoNomeSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoNomeSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 1043, 621);
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeSetorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salvarSetor();
        desativarCampos();
        limparCampos();
        //this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            desativarCampos();
            listarSetor();
            limparCampos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarSetor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        desativarCampos();
        deletar();
        limparCampos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabelaListarSetorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaListarSetorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaListarSetorMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        principal.exibirTelaPesquisarSetor();
        desativarCampos();
        limparCampos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        limparCampos();
        ativarCampos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        limparCampos();
        zerarTabelaSetor();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaSetor();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoNomeSetor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelaListarSetor;
    // End of variables declaration//GEN-END:variables
}
