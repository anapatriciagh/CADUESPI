package entidades;

import dao.BolsaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class Bolsa {

    private int id;
    private int idBolsa;
    private String nome;
    private Aluno aluno;
    private Setor setor;
    private Date dataInicio;
    private Date dataFinal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlunoBolsa() {
        return idBolsa;
    }

    public void setIdAlunoBolsa(int idAlunoBolsa) {
        this.idBolsa = idAlunoBolsa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String[] bolsaToLinha(Bolsa bolsa) {
        String[] novaLinha = new String[]{String.valueOf(bolsa.id), bolsa.nome};
        return novaLinha;
    }

    public String[] alunoBolsaToLinha(Bolsa bolsa) {
        String[] novaLinha = new String[]{String.valueOf(bolsa.idBolsa), bolsa.aluno.getMatricula(), bolsa.aluno.getCpf(), bolsa.aluno.getNome(), String.valueOf(bolsa.id), bolsa.nome, String.valueOf(bolsa.setor.id), bolsa.setor.nome, String.valueOf(bolsa.dataInicio), String.valueOf(bolsa.dataFinal)};
        return novaLinha;
    }

   public void cadastrarBolsa(Bolsa bolsa) throws SQLException {
        BolsaDAO bolsaDAO = new BolsaDAO();
        bolsaDAO.salvarBolsa(bolsa);
        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");

    }

    public void cadastrarRelacaoAlunoBolsa() throws SQLException {
        BolsaDAO bolsaDAO = new BolsaDAO();
        bolsaDAO.cadastrarRelacaoAlunoBolsa(this);
        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");

    }

    public void deletarRelacaoAlunoBolsa() throws SQLException {
        BolsaDAO bolsaDAO = new BolsaDAO();
        bolsaDAO.deletarRelacaoAlunoBolsa(this);
        JOptionPane.showMessageDialog(null, "Realizado Com Sucesso");
    }

    public void atualizarRelacaoAlunoBolsa() throws SQLException {
        BolsaDAO bolsaDAO = new BolsaDAO();
        bolsaDAO.atualizarRelacaoAlunoBolsa(this);
        JOptionPane.showMessageDialog(null, "Realizado Com Sucesso");
    }

    public void deletarBolsa() throws SQLException {
        BolsaDAO bolsaDAO = new BolsaDAO();
        bolsaDAO.deletarBolsa(id);
        JOptionPane.showMessageDialog(null, "Deletado Com Sucesso");
    }

    void exibirTodasBolsas() throws SQLException {

        BolsaDAO bolsaDAO = new BolsaDAO();
        ArrayList<Bolsa> vetorBolsa = new ArrayList<>();
        vetorBolsa = bolsaDAO.pesquisarTodasBolsas();
        for (int i = 0; i < vetorBolsa.size(); i++) {
        }
    }

   public void atualizarBolsa(Bolsa bolsa) throws SQLException {
        BolsaDAO bolsaDAO = new BolsaDAO();
        bolsaDAO.atualizar(bolsa);
    }

}
