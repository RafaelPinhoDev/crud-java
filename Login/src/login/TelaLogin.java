package login;

import java.awt.Color; // trabalhando com cores no java
import java.awt.Color;
import java.awt.Font; // Trabalhar com fontes
import java.awt.event.ActionEvent; // Trabalha com evento
import javax.swing.JButton; // Trabalha com botoes 
import javax.swing.JFrame; // Trabalha com frames
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Trabalha com mensagens
import javax.swing.JPanel; // Trabalhar com paineis
import javax.swing.JPasswordField; // trabalhar com campos de senha
import javax.swing.JTextField; // Trabalhar com campos de texto

public class TelaLogin  extends JFrame{
    
    // Validar se o usuário está correto
    private boolean usuarioValido;
    
    // tela Objeto Jpanel (tela em si)
    private final JPanel panelTela;
    
    // txtusuario Objeto JTextField (Campo de tela)
    private final JTextField txtUsuario;
    
    // pswsenha Obsejto PasswordField (Campo na tela)
    private final JPasswordField pswSenha;
    
    // Metodo Construtor
    public TelaLogin(){
    
        // Coloca o objeto na referencia do centro da tela
        setLocationRelativeTo(null);
        
        // Não permite que o objeto seja expandido 
        setResizable(false);
        
        // Coloca titulo na caixa JFrame
        setTitle(" Tela de Login");
        
        // Quando clicado no X encerra-se o programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // define o posicionamento e tamanho (x,y, width e height)
        setBounds(500,200,426,212);
        
        // Cria um obejto Jpanel e atribui a ele a variavel tela 
        panelTela = new JPanel();
        
        // Define a cor de fundo do Jpanel (tela)
        panelTela.setBackground(new Color(173, 216, 230));
        setContentPane(panelTela);
        
        // Utiliza o painel sem o padrao
        panelTela.setLayout(null);
        
        // Adicicionando os elementos da tela 
        // Criando um objeto do tipo Jlabel e atribuindo valor ao atributo
        JLabel lblIdentificacao = new JLabel("IDENTIFICAÇÃO");
        
        // Localização na tela
        lblIdentificacao.setBounds(144,0,160,39);
        
        // Define a fonte
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        
        // Adicionando o label no painel
        panelTela.add(lblIdentificacao);
        
        // Identificacao e posicionamento dos labels
        JLabel lblUsuario = new JLabel("Usuário: ");
        lblUsuario.setBounds(24,65,70,15);
        panelTela.add(lblUsuario);
        
        JLabel lblSenha = new JLabel("Senha: ");
        lblSenha.setBounds(24,92,70,15);
        panelTela.add(lblSenha);
        
        // Identificacao e posicionamento dos text fields
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112,63,219,19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        pswSenha = new JPasswordField();
        pswSenha.setBounds(112,90,219,19);
        panelTela.add(pswSenha);
       
        
        // Identificacao e posicionamento dos botoes
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(222,136,117,25);
        btnEntrar.setBackground(new Color(46, 139, 87));
        btnEntrar.setForeground(Color.WHITE);
        panelTela.add(btnEntrar);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(72,136,117,25);
        btnCadastrar.setBackground(new Color(255, 240, 150));
        btnCadastrar.setForeground(Color.BLACK);
        panelTela.add(btnCadastrar);
        
        //Ação no botão para cadastrar Usuário
        btnCadastrar.addActionListener((ActionEvent e) -> {
            //Instancio a classe TelaCadastro
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.abreTela();
            dispose();
        });

        // Ação do botão de entrar no sistema 
        btnEntrar.addActionListener((ActionEvent e) -> {
            
            // Instancia a classe usuario
            Usuario usu = new Usuario();
            
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(pswSenha.getText());
            
            if ("".equals(txtUsuario.getText())){
                // Mensagem da tela
                JOptionPane.showMessageDialog(null,
                        "Campo usuario precisa ser informado!!",
                        "Atencao",
                        JOptionPane.ERROR_MESSAGE);
                // Voltar o cursor para o txtUsuario        
                txtUsuario.grabFocus();
            }else if("".equals(pswSenha.getText())){
                // Mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa de informacao!!",
                        "Atencao!!",
                        JOptionPane.ERROR_MESSAGE);
                // Voltar o cursor para o campo txtUsuario
                pswSenha.grabFocus();
            
            }else{
                // Verifica se o usuario consta no banco de dados
                usuarioValido = usu.verificaUsuario(usu.getUsuario(), usu.getSenha());
            
            }
            
            if(usuarioValido == true){
                // Usuario e senha bateram no banco estão corretos
                JOptionPane.showMessageDialog(null, 
                        "Usuario valido em nossa base de dados",
                        "Atencao!!",
                        JOptionPane.INFORMATION_MESSAGE);
                
                TelaInicio telaInicio = new TelaInicio();
                telaInicio.abreTela();
                
                dispose();
                
            }else{
                // Usuario e senha bateram no banco e estão incorretos
                JOptionPane.showMessageDialog(null,
                        "Usuário invalido ou inexistente",
                        "Atencao!!",
                        JOptionPane.ERROR_MESSAGE);
                
                // Limpagem de texto
                limpaText();
                
                // Manda o foco para o campo usuario
                txtUsuario.grabFocus();

            }
            
        });   
        
    }   
    
    public void abreTela(){
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }
    
    public void limpaText(){
        txtUsuario.setText("");
        pswSenha.setText("");
    
    }
}  