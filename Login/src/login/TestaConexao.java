package login;

public class TestaConexao {

    // Teste para verificar conexao
    public static void main(String[] args) {
        Conexao c = new Conexao(); // Instancia a conexao
        c.abrirConexao(); // Metodo de abertura de conexao
        
        try {
            
            Thread.sleep(4000); // Pausa de 4 segundos
            c.fecharConexao(); // Fechar conexao
            
        }catch (InterruptedException ex){
            // Mensagem de saida caso de algum erro
            System.out.println("Houve algum problema no teste de conexao. " + ex.getMessage());
        
        }
       
    }
    
}
