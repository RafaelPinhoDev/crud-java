package login;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static login.Usuario.usuarioSistema;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;

public class TelaAlteracao extends JFrame{
    // Criando os atributos globais
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;
    private boolean atualizacaoValida;
    
    public TelaAlteracao(){
    
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Alteração");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426,270);
        
        tela = new JPanel();
        // Definindo a cor azul claro de background
        tela.setBackground(new Color(173, 216, 230));
        setContentPane(tela);
        tela.setLayout(null);

        // Adicionando elementos na tela
        JLabel lblIdentificacao = new JLabel ("Informar campos para alteração");
        lblIdentificacao.setBounds(60, 0, 500, 28);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao);
        
        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setBounds(24, 35, 120, 15);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 35, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblSenhaAtual = new JLabel("Senha Atual: ");
        lblSenhaAtual.setBounds(24, 60, 120, 15);
        tela.add(lblSenhaAtual);

        passAtual = new JPasswordField();
        passAtual.setBounds(150, 60, 219, 19);
        tela.add(passAtual);

        JLabel lblnovasenha = new JLabel("Nova Senha: ");
        lblnovasenha.setBounds(24, 85, 120, 15);
        tela.add(lblnovasenha);
    
        passSenha = new JPasswordField();
        passSenha.setBounds(150, 85, 219, 19);
        tela.add(passSenha);

        JLabel lblConfSenha = new JLabel("Confirmar Senha: ");
        lblConfSenha.setBounds(24, 110, 120, 15);
        tela.add(lblConfSenha);

        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(150, 110, 219, 19);
        tela.add(confPassSenha);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(222, 136, 117, 25);
        btnAlterar.setBackground(new Color(255, 215, 0));
        btnAlterar.setForeground(Color.BLACK);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(72, 136, 117, 25);
        btnCancelar.setBackground(new Color(200, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        tela.add(btnCancelar);
        
        btnCancelar.addActionListener ((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });
        
        // Botão de alteração
        btnAlterar.addActionListener((ActionEvent e) ->{
            try{
                Usuario usu = new Usuario();
                
                // Validações antes de efetivar a alteração
                // setando a senha e usuárip
                usu.setSenha(confPassSenha.getText());
                usu.setUsuario(usuarioSistema);
                
                if ("".equals(usu.getNome())){
                    // Adicionando mensagem na tela
                    JOptionPane.showMessageDialog(null,
                            "Campo nome do usuário precisa ser informado!",
                            "ATENÇÃO!!",
                            JOptionPane.ERROR_MESSAGE);
                    // Voltar o cursor para o campo txtNome
                    txtNome.grabFocus();
                    // Senha vazia
                }else if ("".equals(usu.getSenha())){
                    // Mensagem na tela
                    JOptionPane.showMessageDialog(null,
                            "Campo senha precisa ser informado!",
                            "ATENÇÃO!!",
                            JOptionPane.ERROR_MESSAGE);
                    // voltar o cursos para o campo passSenha
                    passSenha.grabFocus();
                }else if(usu.verificaUsuario(usu.getUsuario(), passAtual.getText()) == false){
                    JOptionPane.showMessageDialog(null,
                            "Senha inválida, verifique",
                            "ATENÇÃO!!",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();
                }else if(!passSenha.getText().equals(confPassSenha.getText())){
                    JOptionPane.showMessageDialog(null,
                            "Campos de Senha e Confirmação não são iguais!!",
                            "ATENÇÃO!!",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();
                }else{
                    
                    //Efetivando a alteracao do usuario
                    atualizacaoValida =
                            usu.alteraUsuario(txtNome.getText(),
                                    usu.getUsuario(), usu.getSenha());
                    if(atualizacaoValida == true){
                        JOptionPane.showMessageDialog(null,
                                "Dado(s) do usuário alterado(s) retornaremos"
                                        + "a tela de login.",
                                "ATENÇÃO!!", 
                                JOptionPane.INFORMATION_MESSAGE);
                        // Abrindo a tela de login novamente
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        
                        // Fechando a tela de cadastro
                        dispose();
                    }else{
                        // Usuario cadastrado na base de dados
                        JOptionPane.showMessageDialog(null, 
                                "Problemas ao atualizar o usuário",
                                "ATENÇÃO!!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch(HeadlessException ec){
                System.out.println("Erro ao alterar usuário "
                        + ec.getMessage());
            }
        });
        
        // Atribuir o atributo global ao objeto
        txtNome.setText(Usuario.nomeUsuario);
        
    }
    
    public void abreTela(){
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    
    }
}