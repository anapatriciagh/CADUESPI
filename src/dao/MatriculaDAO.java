
package dao;

import dao.conexao.ConnectionFactory;
import dao.MetodosBD;
import entidades.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rlopes
 */
public class MatriculaDAO {

 

    public void CadastrarMatricula(String  matricula) {
        String sqlMatricula = "insert into matricula(id_matricula) values(?);";
        try {
            PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sqlMatricula);
            ps.setString(1,matricula);
            ps.executeUpdate();
            ConnectionFactory.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean verificarMatriculaProfessor(String cpf) throws SQLException{
        
        
        String sql = "select * from professor join matricula_professor using (cpf) join matricula using (id_matricula) where cpf ='"+cpf+"';";
         ResultSet res;
        PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql);
                res = ps.executeQuery();
            String matricula = null;

        while(res.next()){
            matricula = res.getString("id_matricula");
                    ConnectionFactory.getConexao().close();

        }
        if(matricula != null){
            return true;
        }else{
        return false;    
        }
        
        
    }
    
    
    
}
