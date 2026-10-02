package login;

import java.sql.Connection; // Conexão com banco de dados
import java.sql.DriverManager; // driver de conexao mysql
import java.sql.ResultSet; // resultado das operacoes em banco de dados
import java.sql.SQLException; // interpretacao dos comandos sql - crud
import java.sql.Statement;


public class Conexao {
    // Atributos de conexao ligados a biblioteca importada
    public Connection con= null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    // atribuicao da conexao
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_login";
                                        // servidor de banco de dados
    private final String usuario = "root"; // usuario do bd
    private final String senha = "1234"; // senha do bd
    private final String driver = "com.mysql.cj.jdbc.Driver"; // driver de conexao 
    
    // metodo de abertura de conexao com o bd 
    public Connection abrirConexao(){
        try{
            Class.forName(driver); // driver de utilizacao
            // atributos conexao
            con = DriverManager.getConnection(servidor,usuario,senha);
            
            stmt = con.createStatement(); // fazendo conexao com banco de dados
            // mensagem informando que a conexao foi realizada com sucesso
            System.out.println("Conexao aberta com sucesso!");
      
        }catch(ClassNotFoundException | SQLException e)
        {
             // mensagem de saida de erro caso haja algum
            System.out.println("Erro ao conectar o banco, verifique!" + e.getMessage());
        
        }
        return con; // retorna a conexao
        
    }
    
    // metodo de fechamento da conexao
    public void fecharConexao(){
        try{
            con.close(); // Fechamento da conexao com bd
            // Mensagem que fechou a conexao
            System.out.println("Conexao encerrada sucesso!"); 

        
        }catch(SQLException e){
            // mensagem de erro ao fechar conexao
            System.out.println("Erro ao encerrar conexão, verifique!" + e.getMessage());
        }
        
    }
}


