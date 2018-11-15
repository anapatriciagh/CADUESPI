package entidades;

import dao.MetodosBD;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.SetorDAO;

public class Setor {

    public int id;
    public String nome;


   public String[] setorToLinha(Setor setor) {
        String[] novaLinha = new String[]{String.valueOf(setor.id), setor.nome};
        return novaLinha;
    }

   public void cadastrarSetor(Setor setor) throws SQLException {
        SetorDAO setorDAO = new SetorDAO();
        setorDAO.salvarSetor(setor);
        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");

    }

    public void deletarSetor() throws SQLException {
        SetorDAO setorDAO = new SetorDAO();
        setorDAO.deletarSetor(id);
        JOptionPane.showMessageDialog(null, "Deletado Com Sucesso");
    }

  public   void exibirTodosSetores() throws SQLException {

        SetorDAO setorDAO = new SetorDAO();
        ArrayList<Setor> vetorSetor = new ArrayList<Setor>();
        vetorSetor = setorDAO.pesquisarTodosSetores();
        for (int i = 0; i < vetorSetor.size(); i++) {
        }
    }
    public void atualizarSetor(Setor setor) throws SQLException{
        SetorDAO setorDAO = new SetorDAO();
        setorDAO.atualizar(setor);
    }
        

}

