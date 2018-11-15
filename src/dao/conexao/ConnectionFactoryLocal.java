package dao.conexao;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryLocal {

    public static  Connection getConexao() throws SQLException {
        
        //nome do usuario do banco
        String usuario = "postgres";
        
        //senha do usuario do banco
        String senha = "pgadmim";
        //String senha = "Sobral10*";
        
        //ip do banco. para banco no mesmo computador utilize localhost
        String ip = "192.86.221.5";
        //String ip = "192.86.221.196";
        
        //nome do banco de dados. padrao do postgre eh postgres
        String bd = "cad_uespi";
        
        //porta de conexao com o banco de dados. Padrao do postgre eh 5432
        int porta = 5432;
        
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + porta + "/" + bd, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
        
    }
}