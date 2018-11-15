/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.curso;

import dao.CursoDAO;
import entidades.Curso;
import entidades.util.Validacao;
import tela.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rlopes
 */
public class TelaCadastrarCursos extends javax.swing.JInternalFrame {

    private Principal principal;

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;

    }

    public void atualizarTela() {
        this.repaint();
    }

    void limparCampos() {
        campoNomeCurso.setText("");
    }

    private void desativarCampos() {
        campoNomeCurso.setEnabled(false);
    }

    private void ativarCampos() {
        campoNomeCurso.setEnabled(true);
    }

    void listarCursos() throws SQLException {
        ArrayList<Curso> vetorCursos = new ArrayList<Curso>();
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = new Curso();
        vetorCursos = cursoDAO.pesquisarTodosCursos();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarCursos.getModel();
        meuModelo.setNumRows(0);
        for (int i = 0; i < vetorCursos.size(); i++) {
            meuModelo.addRow(curso.cursoToLinha(vetorCursos.get(i)));
        }
    }

    public void zerarTabelaCursos() {
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarCursos.getModel();
        meuModelo.setNumRows(0);
    }

    public void listarCursos(int id) throws SQLException {
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = new Curso();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarCursos.getModel();
        curso = cursoDAO.pesquisarCurso(id);
        meuModelo.setNumRows(0);
        meuModelo.addRow(curso.cursoToLinha(curso));
        System.out.println("ID " + id);
        System.out.println("NOME " + curso.nome);

    }

    public JTable getTabelaListarCursos() {
        return tabelaListarCursos;
    }

    public void setTabelaListarCursos(JTable tabelaListarCursos) {
        this.tabelaListarCursos = tabelaListarCursos;
    }

    public void listarCursos(String nome) throws SQLException {
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = new Curso();
        Validacao validacao = new Validacao();
        DefaultTableModel meuModelo = (DefaultTableModel) tabelaListarCursos.getModel();
        meuModelo.setNumRows(0);
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        listaCursos = cursoDAO.pesquisarCursoPeloNome(nome);
        if (listaCursos != null) {
            for (int i = 0; i < listaCursos.size(); i++) {
                meuModelo.addRow(curso.cursoToLinha(listaCursos.get(i)));
            }
        } else {
            validacao.pesquisaErrada();
        }
    }

    public void editar() {
        limparCampos();
        ativarCampos();
        int linha = tabelaListarCursos.getSelectedRow();
        campoNomeCurso.setText(tabelaListarCursos.getValueAt(linha, 1).toString());
    }

    public void salvarCurso() {
        Curso curso = new Curso();
        curso.nome = campoNomeCurso.getText();
        int linhaSelecionada = tabelaListarCursos.getSelectedRow();
        if (linhaSelecionada == -1) {
            try {
                curso.cadastrarCurso(curso);
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastrarCursos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            curso.id = Integer.parseInt(tabelaListarCursos.getValueAt(linhaSelecionada, 0).toString());
            try {
                curso.atualizarCurso(curso);
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastrarCursos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            listarCursos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarCursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        limparCampos();
    }

    void deletar() {
        Curso curso = new Curso();
        int linha = tabelaListarCursos.getSelectedRow();
        curso.id = Integer.parseInt(tabelaListarCursos.getValueAt(linha, 0).toString());
        try {
            curso.deletarCurso();
            listarCursos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarCursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        limparCampos();
    }

    public TelaCadastrarCursos(Principal principal) throws SQLException {
        initComponents();
        this.principal = principal;
        desativarCampos();
        //listarCursos();
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
        campoNomeCurso = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaListarCursos = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nome Curso");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cadastrar Cursos");

        campoNomeCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeCursoActionPerformed(evt);
            }
        });

        tabelaListarCursos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaListarCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaListarCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaListarCursos);
        if (tabelaListarCursos.getColumnModel().getColumnCount() > 0) {
            tabelaListarCursos.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaListarCursos.getColumnModel().getColumn(1).setPreferredWidth(400);
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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/new.png"))); // NOI18N
        jButton7.setText("Novo");
        jButton7.setToolTipText("Novo");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

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

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar.png"))); // NOI18N
        jButton6.setText("Limpar");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tabela de Cursos");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(campoNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 1037, 611);
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeCursoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salvarCurso();
        desativarCampos();
        limparCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelaListarCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaListarCursosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaListarCursosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            desativarCampos();
            listarCursos();
            limparCampos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarCursos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        desativarCampos();
        deletar();
        limparCampos();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            principal.exibirTelaPesquisarCurso();
            desativarCampos();
            limparCampos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarCursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        desativarCampos();
        limparCampos();
        zerarTabelaCursos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        limparCampos();
        ativarCampos();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        principal.fecharTelaCadastrarCursos();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoNomeCurso;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelaListarCursos;
    // End of variables declaration//GEN-END:variables
}
