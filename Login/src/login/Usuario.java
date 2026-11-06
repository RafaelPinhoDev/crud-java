package login;

import java.sql.SQLException; // Tratar as exceções do banco de dados

public class Usuario {

    // Atributos estaticos do sistema
    static String nomeUsuario;
    static String usuarioSistema;
    
    // Retorno para alteração e exclusão
    private boolean resultAlteracao;
    private boolean resultExclusao;
    
    
    // Criação de atributos privados da classe
    private String usuario;
    private String nome;
    private String senha;

    // Atributos para armazenar o retorno do banco de dados
    private boolean resultUsuario;
    private boolean resultCadastro;

    // Getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método de verificação da autenticidade do usuário
    public boolean verificaUsuario(String usuario, String senha) {
        // Reinicia o resultado para não reaproveitar valor de tentativa anterior
        resultUsuario = false;

        // Fazer instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abre conexão com o banco de dados
            banco.abrirConexao();

            // Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a consulta com o banco de dados
            // (sem espaço depois da aspa de abertura do usuário)
            banco.resultset = banco.stmt.executeQuery(
                    "SELECT * FROM usuario "
                    + "WHERE usuario = '" + usuario + "' "
                    + "AND senha = md5('" + senha + "')");

            // Verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                // Caso tenha
                resultUsuario = true;
                
                // Setters em Nome e Usuário
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));
                
                // Realizando atribuições nos atributos estáticos
                nomeUsuario = getNome();
                usuarioSistema = getUsuario();
                
            } else {
                // Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // Fecha a conexão com o bd

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuario " + ec.getMessage());
        }

        return resultUsuario;
    }

    // Método de verificação do usuário com sobrecarga de método
    public boolean verificaUsuario(String usuario) throws SQLException {
        // Reinicia o resultado
        resultUsuario = false;

        Conexao banco = new Conexao();

        try {
            // Abre conexão com o bd
            banco.abrirConexao();

            // Cria parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a consulta com o banco
            banco.resultset = banco.stmt.executeQuery(
                    "SELECT * FROM usuario "
                    + "WHERE usuario = '" + usuario + "'");

            // Verificando se existe retorno do banco de dados
            if (banco.resultset.next()) {
                // Caso tenha
                resultUsuario = true;
            } else {
                // Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // Fecha conexão com o banco

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuario " + ec.getMessage());
        }

        return resultUsuario;
    }

    // Método de cadastro do usuário
    public boolean cadastraUsuario(String nome, String usuario, String senha) {
        // Reinicia o resultado
        resultCadastro = false;

        // Conexão com o bd
        Conexao banco = new Conexao();

        try {
            // Abertura da conexão com o banco de dados
            banco.abrirConexao();

            // Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a inserção dos dados
            banco.stmt.execute(
                    "INSERT INTO usuario (nome, usuario, senha) "
                    + "VALUES ('" + nome + "','" + usuario + "', md5('"
                    + senha + "'))");

            resultCadastro = true;

            banco.fecharConexao(); // Fecha a conexão com o bd

        } catch (SQLException ec) {
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
            resultCadastro = false;
        }


        
        return resultCadastro;
    }
    
    // Método para alterar os dados no sistema
    public boolean alteraUsuario(String nome, String usuario, String senha){
        
         // Instancia conexao com o banco
        Conexao banco = new Conexao();
        
        try{
            // Abrir conexao com o bd
            banco.abrirConexao();
            
            // criando Parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            
            // Executando alteração no banco de dados
            banco.stmt.execute("UPDATE usuario SET nome = '" + nome
                    + "', senha = md5('" + senha + "') "
                    + "WHERE usuario = '" + usuario + "'");
            resultAlteracao = true;
        
        
        }catch(SQLException ec){
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        
        }
         
        banco.fecharConexao();
        return resultAlteracao;
    }
    
    // Método para exclusao do usuario
    public boolean excluiUsuario (String usuario){
        // Instanciar conexao com o bd
        Conexao banco = new Conexao();
        
        try{
            
            banco.abrirConexao();
            // Criando parametro de retorno
            banco.stmt = banco.con.createStatement();
            
            // Executando a alteração no banco de dados
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '"
                                    + usuario + "'");
            
            // Caso exclua 
            resultExclusao = true;
            
        
        }catch(SQLException ec){
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        
        }
        banco.fecharConexao();
        return resultExclusao;
    }
        
}