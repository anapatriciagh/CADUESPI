/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import replicar.replicar;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import tela.admin.TelaAdmin;
import tela.aluno.PrincipalAluno;
import tela.aluno.TelaCadastrarAluno;
import tela.aluno.TelaCadastrarBolsaDoAluno;
import tela.aluno.TelaCadastrarMatriculaAluno;
import tela.aluno.TelaPesquisar;
import tela.aluno.TelaPesquisarBolsaDoAluno;
import tela.aluno.TelaPesquisarBolsaParaAluno;
import tela.aluno.TelaPesquisarBolsaPorNomeDaBolsa;
import tela.aluno.TelaPesquisarBolsaPorNomeSetor;
import tela.aluno.TelaPesquisarMatriculaAluno;
import tela.aluno.TelaVisualizarAlunos;
import tela.bolsa.TelaCadastrarBolsa;
import tela.bolsa.TelaPesquisarBolsa;
import tela.curso.TelaCadastrarCursos;
import tela.curso.TelaPesquisarCurso;
import tela.disciplina.TelaCadastrarDisciplina;
import tela.disciplina.TelaPesquisarDisciplina;
import tela.professor.PrincipalProfessor;
import tela.professor.TelaCadastrarMatriculaProfessor;
import tela.professor.TelaCadastrarProfessor;
import tela.professor.TelaPesquisarMatriculaProfessor;
import tela.professor.TelaPesquisarProfessor;
import tela.professor.TelaVisualizarProfessor;
import tela.setor.TelaCadastrarSetor;
import tela.setor.TelaPesquisarSetor;
import replicar.replicar;

/**
 *
 * @author Rubens
 */
public final class Principal extends javax.swing.JFrame {

    private TelaPesquisarCurso telaPesquisarCurso;
    private TelaCadastrarCursos telaCadastrarCursos;
    private TelaCadastrarAluno telaCadastrarAluno;
    private TelaVisualizarAlunos telaVisuallizarAlunos;
    private PrincipalAluno principalAluno;
    private TelaPesquisar telaPesquisar;
    private Login login2;
    private TelaCadastrarSetor telaCadastrarSetor;
    private TelaPesquisarSetor telaPesquisarSetor;
    private TelaAdministrativo telaAdministrativo;
    private TelaCadastrarBolsa telaCadastrarBolsa;
    private TelaCadastrarBolsaDoAluno telaBolsaAluno;
    private TelaPesquisarBolsaParaAluno telaPesquisarBolsaAluno;
    private TelaPesquisarBolsaPorNomeDaBolsa TelaPesquisarBolsaPorNomeDaBolsa;
    private TelaPesquisarBolsaPorNomeSetor telaPesquisarBolsaPorNomeSetor;
    private TelaPesquisarBolsa telaPesquisarBolsa;
    private TelaCadastrarDisciplina telaCadastrarDisciplina;
    private TelaPesquisarDisciplina telaPesquisarDisciplina;
    private PrincipalProfessor principalProfessor;
    private TelaCadastrarProfessor telaCadastrarProfessor;
    private TelaVisualizarProfessor telaVisualizarProfessor;
    private TelaPesquisarProfessor telaPesquisarProfessor;
    private TelaCadastrarMatriculaProfessor telaCadastrarMatriculaProfessor;
    private TelaPesquisarMatriculaProfessor telaPesquisarMatriculaProfessor;
    private TelaCadastrarMatriculaAluno telaCadastrarMatriculaAluno;
    private TelaPesquisarMatriculaAluno telaPesquisarMatriculaAluno;
    private TelaPesquisarBolsaDoAluno telaPesquisarBolsaDoAluno;
    private TelaAdmin telaAdmin;
    private TelaNovaSenha telaNovaSenha;

    public TelaNovaSenha getTelaNovaSenha() {
        return telaNovaSenha;
    }

    public void setTelaNovaSenha(TelaNovaSenha telaNovaSenha) {
        this.telaNovaSenha = telaNovaSenha;
    }

    public TelaAdmin getTelaAdmin() {
        return telaAdmin;
    }

    public void setTelaAdmin(TelaAdmin telaAdmin) {
        this.telaAdmin = telaAdmin;
    }

    public void removerTodasTelas() {
        fecharPrincipalAluno();
        fecharPrincipalProfessor();
        fecharTelaAdministrativo();
        fecharTelaBolsaAluno();
        fecharTelaCadastrarAluno();
        fecharTelaCadastrarBolsa();
    }

    public TelaPesquisarBolsaDoAluno getTelaPesquisarBolsaDoAluno() {
        return telaPesquisarBolsaDoAluno;
    }

    public void setTelaPesquisarBolsaDoAluno(TelaPesquisarBolsaDoAluno telaPesquisarBolsaDoAluno) {
        this.telaPesquisarBolsaDoAluno = telaPesquisarBolsaDoAluno;
    }

    public TelaPesquisarMatriculaAluno getTelaPesquisarMatriculaAluno() {
        return telaPesquisarMatriculaAluno;
    }

    public void setTelaPesquisarMatriculaAluno(TelaPesquisarMatriculaAluno telaPesquisarMatriculaAluno) {
        this.telaPesquisarMatriculaAluno = telaPesquisarMatriculaAluno;
    }

    public TelaCadastrarMatriculaAluno getTelaCadastrarMatriculaAluno() {
        return telaCadastrarMatriculaAluno;
    }

    public void setTelaCadastrarMatriculaAluno(TelaCadastrarMatriculaAluno telaCadastrarMatriculaAluno) {
        this.telaCadastrarMatriculaAluno = telaCadastrarMatriculaAluno;
    }

    public TelaPesquisarMatriculaProfessor getTelaPesquisarMatriculaProfessor() {
        return telaPesquisarMatriculaProfessor;
    }

    public void setTelaPesquisarMatriculaProfessor(TelaPesquisarMatriculaProfessor telaPesquisarMatriculaProfessor) {
        this.telaPesquisarMatriculaProfessor = telaPesquisarMatriculaProfessor;
    }

    public TelaCadastrarMatriculaProfessor getTelaCadastrarMatriculaProfessor() {
        return telaCadastrarMatriculaProfessor;
    }

    public void setTelaCadastrarMatriculaProfessor(TelaCadastrarMatriculaProfessor telaCadastrarMatriculaProfessor) {
        this.telaCadastrarMatriculaProfessor = telaCadastrarMatriculaProfessor;
    }

    public TelaPesquisarProfessor getTelaPesquisarProfessor() {
        return telaPesquisarProfessor;
    }

    public void setTelaPesquisarProfessor(TelaPesquisarProfessor telaPesquisarProfessor) {
        this.telaPesquisarProfessor = telaPesquisarProfessor;
    }

    public TelaVisualizarProfessor getTelaVisualizarProfessor() {
        return telaVisualizarProfessor;
    }

    public void setTelaVisualizarProfessor(TelaVisualizarProfessor telaVisualizarProfessor) {
        this.telaVisualizarProfessor = telaVisualizarProfessor;
    }

    public TelaCadastrarProfessor getTelaCadastrarProfessor() {
        return telaCadastrarProfessor;
    }

    public void setTelaCadastrarProfessor(TelaCadastrarProfessor telaCadastrarProfessor) {
        this.telaCadastrarProfessor = telaCadastrarProfessor;
    }

    public PrincipalProfessor getPrincipalProfessor() {
        return principalProfessor;
    }

    public void setPrincipalProfessor(PrincipalProfessor principalProfessor) {
        this.principalProfessor = principalProfessor;
    }

    public TelaPesquisarDisciplina getTelaPesquisarDisciplina() {
        return telaPesquisarDisciplina;
    }

    public void setTelaPesquisarDisciplina(TelaPesquisarDisciplina telaPesquisarDisciplina) {
        this.telaPesquisarDisciplina = telaPesquisarDisciplina;
    }

    public TelaCadastrarDisciplina getTelaCadastrarDisciplina() {
        return telaCadastrarDisciplina;
    }

    public void setTelaCadastrarDisciplina(TelaCadastrarDisciplina telaCadastrarDisciplina) {
        this.telaCadastrarDisciplina = telaCadastrarDisciplina;
    }

    public TelaPesquisarBolsaPorNomeSetor getTelaPesquisarBolsaPorNomeSetor() {
        return telaPesquisarBolsaPorNomeSetor;
    }

    public void setTelaPesquisarBolsaPorNomeSetor(TelaPesquisarBolsaPorNomeSetor telaPesquisarBolsaPorNomeSsetor) {
        this.telaPesquisarBolsaPorNomeSetor = telaPesquisarBolsaPorNomeSsetor;
    }

    public TelaPesquisarBolsaPorNomeDaBolsa getTelaPesquisarBolsaPorNomeDaBolsa() {
        return TelaPesquisarBolsaPorNomeDaBolsa;
    }

    public void setTelaPesquisarBolsaPorNomeDaBolsa(TelaPesquisarBolsaPorNomeDaBolsa telaPesquisarBolsaPorNome) {
        this.TelaPesquisarBolsaPorNomeDaBolsa = telaPesquisarBolsaPorNome;
    }

    public TelaPesquisarBolsaParaAluno getTelaPesquisarBolsaAluno() {
        return telaPesquisarBolsaAluno;
    }

    public void setTelaPesquisarBolsaAluno(TelaPesquisarBolsaParaAluno telaPesquisarBolsaAluno) {
        this.telaPesquisarBolsaAluno = telaPesquisarBolsaAluno;
    }

    public TelaCadastrarBolsaDoAluno getTelaBolsaAluno() {
        return telaBolsaAluno;
    }

    public void setTelaBolsaAluno(TelaCadastrarBolsaDoAluno telaBolsaAluno) {
        this.telaBolsaAluno = telaBolsaAluno;
    }

    public TelaCadastrarBolsa getTelaCadastrarBolsa() {
        return telaCadastrarBolsa;
    }

    public void setTelaCadastrarBolsa(TelaCadastrarBolsa telaCadastrarBolsa) {
        this.telaCadastrarBolsa = telaCadastrarBolsa;
    }

    public TelaPesquisarBolsa getTelaPesquisarBolsa() {
        return telaPesquisarBolsa;
    }

    public void setTelaPesquisarBolsa(TelaPesquisarBolsa telaPesquisarBolsa) {
        this.telaPesquisarBolsa = telaPesquisarBolsa;
    }

    public TelaAdministrativo getTelaAdministrativo() {
        return telaAdministrativo;
    }

    public void setTelaAdministrativo(TelaAdministrativo telaAdministrativo) {
        this.telaAdministrativo = telaAdministrativo;
    }

    public TelaPesquisarSetor getTelaPesquisarSetor() {
        return telaPesquisarSetor;
    }

    public void setTelaPesquisarSetor(TelaPesquisarSetor telaPesquisarSetor) {
        this.telaPesquisarSetor = telaPesquisarSetor;
    }

    public TelaCadastrarSetor getTelaCadastrarSetor() {
        return telaCadastrarSetor;
    }

    public void setTelaCadastrarSetor(TelaCadastrarSetor telaCadastrarSetor) {
        this.telaCadastrarSetor = telaCadastrarSetor;
    }

    public Login getLogin2() {
        return login2;
    }

    public void setLogin2(Login login2) {
        this.login2 = login2;
    }

    public TelaPesquisar getTelaPesquisar() {
        return telaPesquisar;
    }

    public void setTelaPesquisar(TelaPesquisar telaPesquisar) {
        this.telaPesquisar = telaPesquisar;
    }

    public PrincipalAluno getPrincipalAluno() {
        return principalAluno;
    }

    public void setPrincipalAluno(PrincipalAluno principalAluno) {
        this.principalAluno = principalAluno;
    }

    public TelaCadastrarAluno getTelaCadastrarAluno() {
        return telaCadastrarAluno;
    }

    public void setTelaCadastrarAluno(TelaCadastrarAluno telaCadastrarAluno) {
        this.telaCadastrarAluno = telaCadastrarAluno;
    }

    public TelaVisualizarAlunos getTelaVisuallizarAlunos() {
        return telaVisuallizarAlunos;
    }

    public void setTelaVisuallizarAlunos(TelaVisualizarAlunos telaVisuallizarAlunos) {
        this.telaVisuallizarAlunos = telaVisuallizarAlunos;
    }

    public TelaPesquisarCurso getTelaPesquisarCurso() {
        return telaPesquisarCurso;
    }

    public void setTelaPesquisarCurso(TelaPesquisarCurso telaPesquisarCurso) {
        this.telaPesquisarCurso = telaPesquisarCurso;
    }

    public TelaCadastrarCursos getTelaCadastrarCursos() {
        return telaCadastrarCursos;
    }

    public void setTelaCadastrarCursos(TelaCadastrarCursos telaCadastrarCursos) {
        this.telaCadastrarCursos = telaCadastrarCursos;
    }

    public void fecharTelaNovaSenha() {
        jDesktopPane1.remove(telaNovaSenha);
        telaNovaSenha.dispose();
    }

    public void fecharTelaAdmin() {
        jDesktopPane1.remove(telaAdmin);
        telaAdmin.dispose();
    }

    public void fecharTelaPesquisarBolsaDoAluno() {
        jDesktopPane1.remove(telaPesquisarBolsaDoAluno);
        telaPesquisarBolsaDoAluno.dispose();
    }

    public void fecharTelaPesquisarMatriculaAluno() {
        jDesktopPane1.remove(telaPesquisarMatriculaAluno);
        telaPesquisarMatriculaAluno.dispose();
    }

    public void fecharTelaCadastrarMatriculaAluno() {
        jDesktopPane1.remove(telaCadastrarMatriculaAluno);
        telaCadastrarMatriculaAluno.dispose();
    }

    public void fecharTelaPesquisarMatriculaProfessor() {
        jDesktopPane1.remove(telaPesquisarMatriculaProfessor);
        telaPesquisarMatriculaProfessor.dispose();
    }

    public void fecharTelaCadastrarMatriculaProfessor() {
        jDesktopPane1.remove(telaCadastrarMatriculaProfessor);
        telaCadastrarMatriculaProfessor.dispose();
    }

    public void fecharTelaPesquisarProfessor() {
        jDesktopPane1.remove(telaPesquisarProfessor);
        telaPesquisarProfessor.dispose();
    }

    public void fecharTelaVisualizarProfessor() {
        jDesktopPane1.remove(telaVisualizarProfessor);
        telaVisualizarProfessor.dispose();
    }

    public void fecharTelaCadastrarProfessor() {
        jDesktopPane1.remove(telaCadastrarProfessor);
        telaCadastrarProfessor.dispose();
    }

    public void fecharPrincipalProfessor() {
        jDesktopPane1.remove(principalProfessor);
        principalProfessor.dispose();
    }

    public void fecharTelaPesquisarDisciplina() {
        jDesktopPane1.remove(telaPesquisarDisciplina);
        telaPesquisarDisciplina.dispose();
    }

    public void fecharTelaCadastrarDisciplina() {
        jDesktopPane1.remove(telaCadastrarDisciplina);
        telaCadastrarDisciplina.dispose();
    }

    public void fecharTelaPesquisarBolsaPorNomeSetor() {
        jDesktopPane1.remove(telaPesquisarBolsaPorNomeSetor);
        telaPesquisarBolsaPorNomeSetor.dispose();
    }

    public void fecharTelaPesquisarBolsaPorNome() {
        jDesktopPane1.remove(TelaPesquisarBolsaPorNomeDaBolsa);
        TelaPesquisarBolsaPorNomeDaBolsa.dispose();
    }

    public void fecharTelaPesquisarBolsaAluno() {
        jDesktopPane1.remove(telaPesquisarBolsaAluno);
        telaPesquisarBolsaAluno.dispose();
    }

    public void fecharTelaBolsaAluno() {
        jDesktopPane1.remove(telaBolsaAluno);
        telaBolsaAluno.dispose();

    }

    public void fecharTelaCadastrarBolsa() {
        jDesktopPane1.remove(telaCadastrarBolsa);
        telaCadastrarBolsa.dispose();
    }

    public void fecharTelaPesquisarBolsa() {
        jDesktopPane1.remove(telaPesquisarBolsa);
        telaPesquisarBolsa.dispose();
    }

    public void fecharTelaAdministrativo() {
        jDesktopPane1.remove(telaAdministrativo);
        telaAdministrativo.dispose();
    }

    public void fecharTelaPesquisarSetor() {
        jDesktopPane1.remove(telaPesquisarSetor);
        telaPesquisarSetor.dispose();
    }

    public void fecharTelaSetor() {
        jDesktopPane1.remove(telaCadastrarSetor);
        telaCadastrarSetor.dispose();
    }

    public void fecharTelaPesquisar() {
        jDesktopPane1.remove(telaPesquisar);
        telaPesquisar.dispose();
    }

    public void fecharPrincipalAluno() {
        jDesktopPane1.remove(principalAluno);
        principalAluno.dispose();
    }

    public void fecharTelaVisuallizarAlunos() {
        jDesktopPane1.remove(telaVisuallizarAlunos);
        telaVisuallizarAlunos.dispose();
    }

    public void fecharTelaCadastrarCursos() {
        jDesktopPane1.remove(telaCadastrarCursos);
        telaCadastrarCursos.dispose();
    }

    public void fecharTelaPesquisarCurso() {
        jDesktopPane1.remove(telaPesquisarCurso);
        telaPesquisarCurso.dispose();

    }

    public void fecharTelaGetLogin2() {
        jDesktopPane1.remove(login2);
        login2.dispose();
    }

    public void fecharTelaCadastrarAluno() {
        jDesktopPane1.remove(telaCadastrarAluno);
        telaCadastrarAluno.dispose();

    }

    public void exibirTelaNovaSenha() {
        telaNovaSenha = new TelaNovaSenha(this);
        jDesktopPane1.add(telaNovaSenha);
        telaNovaSenha.setVisible(true);
    }

    public void exibirTelaAdmin() {
        telaAdmin = new TelaAdmin(this);
        jDesktopPane1.add(telaAdmin);
        telaAdmin.setVisible(true);
    }

    public void exibirTelaPesquisarBolsaDoAluno() {
        telaPesquisarBolsaDoAluno = new TelaPesquisarBolsaDoAluno(this);
        jDesktopPane1.add(telaPesquisarBolsaDoAluno);
        telaPesquisarBolsaDoAluno.setVisible(true);
    }

    public void exibirTelaPesquisarMatriculaAluno() {
        telaPesquisarMatriculaAluno = new TelaPesquisarMatriculaAluno(this);
        jDesktopPane1.add(telaPesquisarMatriculaAluno);
        telaPesquisarMatriculaAluno.setVisible(true);
    }

    public void exibirTelaCadastrarMatriculaAluno() throws SQLException {
        telaCadastrarMatriculaAluno = new TelaCadastrarMatriculaAluno(this);
        jDesktopPane1.add(telaCadastrarMatriculaAluno);
        telaCadastrarMatriculaAluno.setVisible(true);
    }

    public void exibirTelaPesquisarMatriculaProfessor() {
        telaPesquisarMatriculaProfessor = new TelaPesquisarMatriculaProfessor(this);
        jDesktopPane1.add(telaPesquisarMatriculaProfessor);
        telaPesquisarMatriculaProfessor.setVisible(true);
    }

    public void exibirTelaCadastrarMatriculaProfessor() throws SQLException {
        telaCadastrarMatriculaProfessor = new TelaCadastrarMatriculaProfessor(this);
        jDesktopPane1.add(telaCadastrarMatriculaProfessor);
        telaCadastrarMatriculaProfessor.setVisible(true);
    }

    public void exibirTelaPesquisarProfessor() {
        telaPesquisarProfessor = new TelaPesquisarProfessor(this);
        jDesktopPane1.add(telaPesquisarProfessor);
        telaPesquisarProfessor.setVisible(true);
    }

    public void exibirTelaVisualizarProfessor() {
        telaVisualizarProfessor = new TelaVisualizarProfessor(this);
        jDesktopPane1.add(telaVisualizarProfessor);
        telaVisualizarProfessor.setVisible(true);
    }

    public void exibirTelaCadastrarProfessor() {
        telaCadastrarProfessor = new TelaCadastrarProfessor(this);
        jDesktopPane1.add(telaCadastrarProfessor);
        telaCadastrarProfessor.setVisible(true);
    }

    public void exibirPrincipalProfessor() {
        principalProfessor = new PrincipalProfessor(this);
        jDesktopPane1.add(principalProfessor);
        principalProfessor.setVisible(true);
    }

    public void exibirTelaPesquisarDisciplina() {
        telaPesquisarDisciplina = new TelaPesquisarDisciplina(this);
        jDesktopPane1.add(telaPesquisarDisciplina);
        telaPesquisarDisciplina.setVisible(true);
    }

    public void exibirTelaCadastrarDisciplina() throws SQLException {
        telaCadastrarDisciplina = new TelaCadastrarDisciplina(this);
        jDesktopPane1.add(telaCadastrarDisciplina);
        telaCadastrarDisciplina.setVisible(true);
    }

    public void exibirTelaPesquisarBolsaPorNomeSetor() {
        telaPesquisarBolsaPorNomeSetor = new TelaPesquisarBolsaPorNomeSetor(this);
        jDesktopPane1.add(telaPesquisarBolsaPorNomeSetor);
        telaPesquisarBolsaPorNomeSetor.setVisible(true);
    }

    public void exibirTelaPesquisarBolsaPorNome() {
        TelaPesquisarBolsaPorNomeDaBolsa = new TelaPesquisarBolsaPorNomeDaBolsa(this);
        jDesktopPane1.add(TelaPesquisarBolsaPorNomeDaBolsa);
        TelaPesquisarBolsaPorNomeDaBolsa.setVisible(true);
    }

    public void exibirTelaPesquisarBolsaAluno() {
        telaPesquisarBolsaAluno = new TelaPesquisarBolsaParaAluno(this);
        jDesktopPane1.add(telaPesquisarBolsaAluno);
        telaPesquisarBolsaAluno.setVisible(true);
    }

    public void exibirTelaBolsaAluno() throws SQLException {
        telaBolsaAluno = new TelaCadastrarBolsaDoAluno(this);
        jDesktopPane1.add(telaBolsaAluno);
        telaBolsaAluno.setVisible(true);
    }

    public void exibirTelaCadastrarBolsa() throws SQLException {
        telaCadastrarBolsa = new TelaCadastrarBolsa(this);
        jDesktopPane1.add(telaCadastrarBolsa);
        telaCadastrarBolsa.setVisible(true);
    }

    public void exibirTelaPesquisarBolsa() {
        telaPesquisarBolsa = new TelaPesquisarBolsa(this);
        jDesktopPane1.add(telaPesquisarBolsa);
        telaPesquisarBolsa.setVisible(true);
    }

    public void exibirTelaAdministrativo() {
        telaAdministrativo = new TelaAdministrativo(this);
        jDesktopPane1.add(telaAdministrativo);
        telaAdministrativo.setVisible(true);
    }

    public void exibirTelaPesquisarSetor() {
        telaPesquisarSetor = new TelaPesquisarSetor(this);
        jDesktopPane1.add(telaPesquisarSetor);
        telaPesquisarSetor.setVisible(true);
    }

    public void exibirTelaCadastrarSetor() throws SQLException {
        telaCadastrarSetor = new TelaCadastrarSetor(this);
        jDesktopPane1.add(telaCadastrarSetor);
        telaCadastrarSetor.setVisible(true);

    }

    public void exibirTelaLogin() {
        login2 = new Login(this);
        jDesktopPane1.add(login2);
        login2.setVisible(true);
    }

    public void exibirTelaPesquisar() {
        telaPesquisar = new TelaPesquisar(this);
        jDesktopPane1.add(telaPesquisar);
        telaPesquisar.setVisible(true);
    }

    public void exibirprincipalAluno() throws PropertyVetoException {
        principalAluno = new PrincipalAluno(this);
        jDesktopPane1.add(principalAluno);
        principalAluno.setVisible(true);
    }

    public void exibirTelaVisualizarAlunos() {
        telaVisuallizarAlunos = new TelaVisualizarAlunos(this);
        jDesktopPane1.add(telaVisuallizarAlunos);
        telaVisuallizarAlunos.setVisible(true);
    }

    public void exibirTelaCadastrarAluno() {
        telaCadastrarAluno = new TelaCadastrarAluno(this);
        jDesktopPane1.add(telaCadastrarAluno);
        telaCadastrarAluno.setVisible(true);
    }

    public void exibirTelaPesquisarCurso() throws SQLException {
        telaPesquisarCurso = new TelaPesquisarCurso(this);
        jDesktopPane1.add(telaPesquisarCurso);
        telaPesquisarCurso.setVisible(true);
    }

    public void exibirTelaCadastrarCursos() throws SQLException {
        telaCadastrarCursos = new TelaCadastrarCursos(this);
        jDesktopPane1.add(telaCadastrarCursos);
        telaCadastrarCursos.setVisible(true);
    }

    public void desvativarBotao() {
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton8.setEnabled(false);
        jButton9.setEnabled(false);
        jButton11.setEnabled(false);
        jMenuBar1.setEnabled(false);
        jMenuItem1.setEnabled(false);
        jMenuItem2.setEnabled(false);
        jMenuItem4.setEnabled(false);
        jMenuItem5.setEnabled(false);
        jMenuItem7.setEnabled(false);
        jMenuItem8.setEnabled(false);
        jMenuBar1.setEnabled(false);

    }

    public void ativarBotao() {
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton8.setEnabled(true);
        jButton9.setEnabled(true);
        jButton11.setEnabled(true);
        jMenuBar1.setEnabled(true);
        jMenuItem1.setEnabled(true);
        jMenuItem2.setEnabled(true);
        jMenuItem4.setEnabled(true);
        jMenuItem5.setEnabled(true);
        jMenuItem7.setEnabled(true);
        jMenuItem8.setEnabled(true);

    }


    public void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 3,
                (desktopSize.height - jInternalFrameSize.height) / 4);
    }

    /**
     * Creates new form Interface
     */
    public Principal() throws SQLException {

        initComponents();
        
        replicar replicar = new replicar();
        replicar.cpf("41255852100");
        exibirTelaLogin();
        
        
       
    }

    /**
     * This metod is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/professor.png"))); // NOI18N
        jButton5.setText("Professor");
        jButton5.setToolTipText("Funções do Professor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aluno_original.png"))); // NOI18N
        jButton6.setText("Aluno");
        jButton6.setToolTipText("Funções do Aluno");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/servidor.png"))); // NOI18N
        jButton8.setText("Servidor");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/administrativo.png"))); // NOI18N
        jButton11.setText("Administrativo");
        jButton11.setToolTipText("Funções do Administrativo");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/terceirizado.png"))); // NOI18N
        jButton9.setText("Terceirizado");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cad jpge fundo branco 1.0 azul.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(293, 293, 293)
                        .addComponent(jButton11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11)
                    .addComponent(jButton9))
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("Arquivo");

        jMenuItem8.setText("Logoff");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Professor");

        jMenuItem7.setText("Listar Professores");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem2.setText("Cadastrar Professor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Aluno");

        jMenuItem5.setText("Listar Alunos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem4.setText("Cadastrar Aluno");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Opções");

        jMenuItem3.setText("Fechar Todas as Janelas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Administrador");

        jMenuItem9.setText("Usuarios");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        exibirTelaCadastrarProfessor();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        exibirTelaCadastrarAluno();

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        exibirTelaVisualizarProfessor();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        exibirTelaLogin();
        desvativarBotao();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        exibirPrincipalProfessor();
        try {
            getPrincipalProfessor().setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try {
            exibirprincipalAluno();
            getPrincipalAluno().setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        exibirTelaAdministrativo();
        try {
            getTelaAdministrativo().setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        exibirTelaVisualizarAlunos();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        exibirTelaAdmin();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
