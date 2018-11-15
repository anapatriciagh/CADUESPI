package dao;



import dao.conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodosBD {

    /**
     * Executa consultas que retornem elementos como resultado.
     * Ex: select
     * @param consulta a consulta sql a ser executada
     * @return um resultset contendo as tuplas retornadas
     */
    public ResultSet executarConsulta(String consulta) {
        ConnectionFactory fabrica = new ConnectionFactory();
        Connection conexao;
        ResultSet resultado;
        try {
            conexao = fabrica.getConexao();
            Statement stm = (Statement) conexao.createStatement();
            stm.executeQuery(consulta);
            resultado = stm.getResultSet();
            conexao.close();
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(MetodosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Executa instrucoes que normalmente nao retornam resultado. Ex: insert e
     * update
     *
     * @param consulta consulta sql a ser executada
     * @return true se a consulta foi bem sucedida e false caso contrario
     */
    public boolean executarInstrucao(String consulta) {
        ConnectionFactory fabrica = new ConnectionFactory();
        Connection conexao;
        int isExecutado;
        try {
            conexao = fabrica.getConexao();
            Statement stm = (Statement) conexao.createStatement();
            isExecutado = stm.executeUpdate(consulta);
            conexao.close();

            if (isExecutado >= 1) {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MetodosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
