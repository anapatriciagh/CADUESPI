package entidades;

import dao.MetodosBD;
import dao.DisciplinaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Disciplina {

    public int id;
    public String nome;
    //String semestre;
    //String ano;

    public String[] disciplinasToLinha(Disciplina disciplina) {
        String[] novaLinha = new String[]{String.valueOf(disciplina.id), disciplina.nome};
        return novaLinha;
    }

    public void cadastrarDisciplina(Disciplina disciplina) throws SQLException {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.salvarDisciplina(disciplina);
        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");

    }

    public void deletarDisciplina() throws SQLException {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.deletarDisciplina(id);
        JOptionPane.showMessageDialog(null, "Deletado Com Sucesso");
    }

    public void exibirTodasDisciplinas() throws SQLException {

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ArrayList<Disciplina> vetorDisciplinas = new ArrayList<Disciplina>();
        vetorDisciplinas = disciplinaDAO.exibirTodasDisciplinas();
        for (int i = 0; i < vetorDisciplinas.size(); i++) {
            System.out.println("ID DISCIPLINA : " + vetorDisciplinas.get(i).id + "  NOME DISCIPLINA : " + vetorDisciplinas.get(i).nome);
        }
    }

    public void atualizar(Disciplina disciplina) {

        String sql = "";
        MetodosBD bd = new MetodosBD();
        sql = "update disciplina set nome_disciplina = '" + disciplina.nome + "' where id_disciplina = '" + disciplina.id + "';";
        System.out.println(sql);
        bd.executarInstrucao(sql);
    }

}
