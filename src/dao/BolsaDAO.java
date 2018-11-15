package dao;

import entidades.Bolsa;
import dao.AlunoDAO;
import dao.conexao.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BolsaDAO {

     public void atualizarRelacaoAlunoBolsa(Bolsa bolsa) throws SQLException{
     
String sql = "update aluno_bolsa set id_bolsa = ?, id_setor = ?, cpf = ?, data_inicio = ?, data_final = ? where id_aluno_bolsa = ?";

            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setInt(1, bolsa.getId());
            ps.setInt(2, bolsa.getSetor().id);
            ps.setString(3, bolsa.getAluno().getCpf());
            ps.setDate(4, new java.sql.Date(bolsa.getDataInicio().getTime()));
        ps.setDate(5, new java.sql.Date(bolsa.getDataFinal().getTime()));
        ps.setInt(6, bolsa.getIdAlunoBolsa());
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }
    
 public   void cadastrarRelacaoAlunoBolsa(Bolsa bolsa) throws SQLException {
        String sql = "insert into aluno_bolsa(id_setor,id_bolsa,cpf,data_inicio,data_final) values(?,?,?,?,?)";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1, bolsa.getSetor().id);
        ps.setInt(2, bolsa.getId());
        ps.setString(3, bolsa.getAluno().getCpf());
        ps.setDate(4, new java.sql.Date(bolsa.getDataInicio().getTime()));
        ps.setDate(5, new java.sql.Date(bolsa.getDataFinal().getTime()));
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();

    }

   public void deletarRelacaoAlunoBolsa(Bolsa bolsa) throws SQLException {
        String sql = "delete from aluno_bolsa where id_aluno_bolsa = ? ";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1, bolsa.getIdAlunoBolsa());
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();

    }

   public void salvarBolsa(Bolsa bolsa) throws SQLException {

        String sql = "insert into bolsa(nome_bolsa) values(?);";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, bolsa.getNome());
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

  public  void deletarBolsa(int id) throws SQLException {

        String sql = "delete  from bolsa where  id_bolsa = ? ;";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public ArrayList<Bolsa> pesquisarTodasBolsas() throws SQLException {

        ResultSet res;
        String sql = "select * from bolsa order by 2 asc";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ArrayList<Bolsa> vetorBolsa = new ArrayList<>();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Bolsa bolsa = new Bolsa();
                bolsa.setId(res.getInt("id_bolsa"));
                bolsa.setNome(res.getString("nome_bolsa"));
                vetorBolsa.add(bolsa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BolsaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close(); // ONDE FECHAR AS CONEXÕES ???????
        return vetorBolsa;
    }

    public Bolsa pesquisarBolsa(int id) throws SQLException {

        ResultSet res;
        String sql = "select * from bolsa where id_bolsa=" + id + ";";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        Bolsa bolsa = new Bolsa();
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                bolsa.setId(res.getInt("id_bolsa"));
                bolsa.setNome(res.getString("nome_bolsa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BolsaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return bolsa;
    }

    public Bolsa pesquisarRelacaoAlunoBolsa(String cpf) throws SQLException {
        ResultSet res;
        Bolsa bolsa = new Bolsa();
        AlunoDAO alunoDAO = new AlunoDAO();
        SetorDAO setorDAO = new SetorDAO();
        String sql = "select * from aluno_bolsa where cpf=?";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, cpf);
        res = ps.executeQuery();
        while (res.next()) {
            bolsa.setIdAlunoBolsa( res.getInt("id_aluno_bolsa"));
            bolsa.setId(res.getInt("id_bolsa"));
            bolsa.setSetor(setorDAO.pesquisarSetor(res.getInt("id_setor")));
            bolsa.setAluno(alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setDataInicio(res.getDate("data_inicio"));
            bolsa.setDataFinal(res.getDate("data_final"));
        }
        return bolsa;
    }
    
    public ArrayList<Bolsa> pesquisarTodasBolsasDeUmAluno(String nome) throws SQLException {
        ResultSet res;
        ArrayList<Bolsa> listaBolsa = new ArrayList<>();
        AlunoDAO alunoDAO = new AlunoDAO();
        SetorDAO setorDAO = new SetorDAO();
        String sql = "select  * from bolsa join aluno_bolsa using(id_bolsa) join aluno using(cpf) where nome ilike '%"+nome+"%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        while (res.next()) {
            Bolsa bolsa = new Bolsa();
            bolsa.setIdAlunoBolsa(res.getInt("id_aluno_bolsa"));
            bolsa.setId(res.getInt("id_bolsa"));
            bolsa.setNome(res.getString("nome_bolsa"));
            bolsa.setSetor(setorDAO.pesquisarSetor(res.getInt("id_setor")));
            bolsa.setAluno(alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setDataInicio(res.getDate("data_inicio"));
            bolsa.setDataFinal(res.getDate("data_final"));  
            listaBolsa.add(bolsa);
        }
        return listaBolsa;
    }
    
    
       public ArrayList<Bolsa> pesquisarTodasBolsasDeUmAlunoPeloCpf(String cpf) throws SQLException {
        ResultSet res;
        ArrayList<Bolsa> listaBolsa = new ArrayList<>();
        AlunoDAO alunoDAO = new AlunoDAO();
        SetorDAO setorDAO = new SetorDAO();
        String sql = "select * from bolsa join aluno_bolsa using(id_bolsa) join aluno using(cpf) where cpf = ?";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, cpf);
        res = ps.executeQuery();
        while (res.next()) {
            Bolsa bolsa = new Bolsa();
            bolsa.setIdAlunoBolsa(res.getInt("id_aluno_bolsa"));
            bolsa.setId(res.getInt("id_bolsa"));
            bolsa.setNome(res.getString("nome_bolsa"));
            bolsa.setSetor(setorDAO.pesquisarSetor(res.getInt("id_setor")));
            bolsa.setAluno( alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setDataInicio(res.getDate("data_inicio"));
            bolsa.setDataFinal(res.getDate("data_final"));
            listaBolsa.add(bolsa);
        }
        return listaBolsa;
    }
       
       public ArrayList<Bolsa> pesquisarTodasBolsasDeUmAlunoPelaMatricula(String matricula) throws SQLException {
        ResultSet res;
        ArrayList<Bolsa> listaBolsa = new ArrayList<>();
        AlunoDAO alunoDAO = new AlunoDAO();
        SetorDAO setorDAO = new SetorDAO();
        String sql = "select  * from bolsa join aluno_bolsa using(id_bolsa) join aluno using(cpf) join matricula_aluno using(cpf) where id_matricula ILIKE ?";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setString(1, matricula);
        res = ps.executeQuery();
        while (res.next()) {
            Bolsa bolsa = new Bolsa();
            bolsa.setIdAlunoBolsa(res.getInt("id_aluno_bolsa"));
            bolsa.setId(res.getInt("id_bolsa")); 
            bolsa.setNome(res.getString("nome_bolsa"));
            bolsa.setSetor(setorDAO.pesquisarSetor(res.getInt("id_setor")));
            bolsa.setAluno(alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setDataInicio(res.getDate("data_inicio"));
            bolsa.setDataFinal(res.getDate("data_final"));
            listaBolsa.add(bolsa);
        }
        return listaBolsa;
    }
    
    
    
        public Bolsa pesquisarRelacaoAlunoBolsa(int idAlunoBolsa) throws SQLException {
        ResultSet res;
        Bolsa bolsa = new Bolsa();
        AlunoDAO alunoDAO = new AlunoDAO();
        SetorDAO setorDAO = new SetorDAO();
        String sql = "select * from aluno_bolsa where id_aluno_bolsa=?";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1, idAlunoBolsa);
        res = ps.executeQuery();
        while (res.next()) {
            bolsa.setIdAlunoBolsa(res.getInt("id_aluno_bolsa"));
            bolsa.setId( res.getInt("id_bolsa"));
            bolsa.setSetor( setorDAO.pesquisarSetor(res.getInt("id_setor")));
            bolsa.setAluno(alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setDataInicio(res.getDate("data_inicio"));
            bolsa.setDataFinal( res.getDate("data_final"));
        }
        return bolsa;
    }
    
        public Bolsa verificaRelacaoAlunoBolsa(Bolsa bolsa) throws SQLException {
        ResultSet res;
        AlunoDAO alunoDAO = new AlunoDAO();
        SetorDAO setorDAO = new SetorDAO();
        String sql = "select * from aluno_bolsa  where id_setor =  ? and id_bolsa = ? and cpf = ? and data_inicio = ? and data_final = ?";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.setInt(1, bolsa.getSetor().id);
        ps.setInt(2, bolsa.getId());
        ps.setString(3,bolsa.getAluno().getCpf());
        ps.setDate(4,new java.sql.Date( bolsa.getDataInicio().getTime()));
                ps.setDate(5,new java.sql.Date( bolsa.getDataFinal().getTime()));
        res = ps.executeQuery();
        while (res.next()) {
            bolsa.setIdAlunoBolsa(res.getInt("id_aluno_bolsa"));
            bolsa.setId( res.getInt("id_bolsa"));
            bolsa.setSetor(setorDAO.pesquisarSetor(res.getInt("id_setor")));
            bolsa.setAluno(alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setDataInicio(res.getDate("data_inicio"));
            bolsa.setDataFinal( res.getDate("data_final"));
        }
        return bolsa;
    }
    
    public ArrayList<Bolsa> pesquisarTodasRelacaoAlunoBolsa() throws SQLException {
        ResultSet res;
        String sql = "select id_matricula,cpf,nome,id_aluno_bolsa,id_bolsa,nome_bolsa,id_setor,nome_setor,data_inicio,data_final  from aluno_bolsa left join setor using (id_setor) left join bolsa using(id_bolsa) left join aluno using(cpf) left join matricula_aluno using(cpf) ";
        ArrayList<Bolsa> vetorBolsa = new ArrayList<>();
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        res = ps.executeQuery();
        while (res.next()) {
            Bolsa bolsa = new Bolsa();
            AlunoDAO alunoDAO = new AlunoDAO();
            SetorDAO setorDAO = new SetorDAO();
            bolsa.setAluno(alunoDAO.pesquisarAlunoComMatricula(res.getString("cpf")));
            bolsa.setSetor(setorDAO.pesquisarSetor( res.getInt("id_setor")));
            bolsa.setIdAlunoBolsa(res.getInt("id_aluno_bolsa"));
            bolsa.setId(res.getInt("id_bolsa"));
            bolsa.setNome(res.getString("nome_bolsa"));
            bolsa.setDataInicio( res.getDate("data_inicio"));
            bolsa.setDataFinal(res.getDate("data_final"));
            vetorBolsa.add(bolsa);
        }
        return vetorBolsa;
        
    }

  public  void atualizar(Bolsa bolsa) throws SQLException {
        String sql = "";
        sql = "update bolsa set nome_bolsa = '" + bolsa.getNome() + "' where id_bolsa = '" + bolsa.getId() + "';";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        ps.executeUpdate();
        ConnectionFactory.getConexao().close();
    }

    public ArrayList<Bolsa> pesquisarBolsaPeloNome(String nome) throws SQLException {

        ResultSet res;
        ArrayList<Bolsa> listaBolsa = new ArrayList<>();
        String sql = "select * from bolsa where nome_bolsa ilike  '%" + nome + "%'";
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
        System.out.println(sql);
        res = ps.executeQuery();
        try {
            while (res.next()) {
                Bolsa bolsa = new Bolsa();
                bolsa.setId(res.getInt("id_bolsa"));
                bolsa.setNome(res.getString("nome_bolsa"));
                listaBolsa.add(bolsa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.getConexao().close();
        return listaBolsa;
    }

}
