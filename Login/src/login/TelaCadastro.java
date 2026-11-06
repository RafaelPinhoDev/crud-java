package login;

import java.awt.Color;
import java.awt.Font; // Trabalhar com fontes
import java.awt.HeadlessException;
import java.awt.SystemColor; // Trabalhar com cores
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton; // Trabalha com botoes 
import javax.swing.JFrame; // Trabalha com frames
import javax.swing.JLabel;
import javax.swing.JPanel; // Trabalhar com paineis
import javax.swing.JOptionPane;
import javax.swing.JPasswordField; // trabalhar com campos de senha
import javax.swing.JTextField; // Trabalhar com campos de texto

public class TelaCadastro extends JFrame {

 
    // Declaração de atributos de tela
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;

    // Validações de usuário e cadastro corretos
    private boolean usuarioValido;
    private boolean cadastroValido;

    // String de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0;

    // Método construtor da classe
    public TelaCadastro() {
        
        
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426,270);

        tela = new JPanel();
        tela.setBackground(new Color(173, 216, 230));
        setContentPane(tela);
        tela.setLayout(null);

        // Adicionando elementos na tela 
        JLabel lblIdentificacao = new JLabel("Informar campos para cadastro");
        lblIdentificacao.setBounds(60, 10, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao); // Adicionando o elemento na tela

        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setBounds(24, 60, 120, 15);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 60, 219, 19);
        tela.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblUsuario = new JLabel("Usuário: ");
        lblUsuario.setBounds(24, 85, 120, 15);
        tela.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 85, 219, 19);
        tela.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblsenha = new JLabel("Senha: ");
        lblsenha.setBounds(24, 110, 120, 15);
        tela.add(lblsenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(150, 110, 219, 19);
        tela.add(passSenha);

        JLabel lblconfsenha = new JLabel("Confirmar Senha: ");
        lblconfsenha.setBounds(24, 135, 120, 15);
        tela.add(lblconfsenha);

        passConfSenha = new JPasswordField();
        passConfSenha.setBounds(150, 135, 219, 19);
        tela.add(passConfSenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(250, 175, 117, 25);
        btnCadastrar.setBackground(new Color(255, 240, 150));
        btnCadastrar.setForeground(Color.BLACK);
        tela.add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(100, 175, 117, 25);
        btnCancelar.setBackground(new Color(200, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        tela.add(btnCancelar);

        // Ação para retornar pra tela de login quando clicar em cancelar
        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
            dispose();
        });

        btnCadastrar.addActionListener((ActionEvent e) -> {
            // Instanciando objeto
            Usuario usu = new Usuario();
            
            // Realizando os setters dos dados na tela
            usu.setNome(txtNome.getText());
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(passSenha.getText());
            
            // Validações de preeenchimento de dados
            //Validações de preenchimento dos dados
            if ("".equals(usu.getNome())) {
                mensagemJOption = "Campo nome do usuário precisa ser informado";
                mensagemTipo = 0;
            } else if ("".equals(usu.getUsuario())) {
                mensagemJOption = "Campo usuário precisa ser informado";
                mensagemTipo = 0;
            } else if (!usu.getSenha().equals(passConfSenha.getText())) {
                mensagemJOption = "Campos senha e confirmação de senha não coincidenm!";
                mensagemTipo = 0;
            } else if ("".equals(usu.getSenha())) {
                mensagemJOption = "A senha precisa ser informada!";
                mensagemTipo = 0;
            } else {
                try {
                    //Verifico se somente o usuário consta no banco, neste caso, faremos uma sobrecarga de método
                    usuarioValido = usu.verificaUsuario(usu.getUsuario());
                } catch (SQLException ex) {
                    System.getLogger(TelaCadastro.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                
                if (usuarioValido == true) {
                    mensagemJOption = "Usuário já existente na base de dados.";
                    mensagemTipo = 0;
                } else {
                    cadastroValido = usu.cadastraUsuario(usu.getNome(), usu.getUsuario(), usu.getSenha());
                    
                    if (cadastroValido == true) {
                        //Usuário cadastrado na base de dados
                        mensagemJOption = "Usuário cadastrado com sucesso.";
                        mensagemTipo = 1;
                    } else {
                        //Algum erro aconteceu
                        mensagemJOption = "Problemas ao inserir o usuário.";
                        mensagemTipo = 0;
                    }
                }
            }
            
            try {
                JOptionPane.showMessageDialog(null, mensagemJOption, "Atenção", mensagemTipo);
                if (mensagemTipo == 1) {
                    // Voltar pra tela de login
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    dispose();
                }
            } catch (HeadlessException ec) {
                System.out.println("Erro no cadastro do usario ");
            }
        });
    }

    public void abreTela() {
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }

}