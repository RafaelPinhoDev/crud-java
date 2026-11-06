package login;

import java.awt.Color; // trabalhando com cores no java
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaInicio extends JFrame {

    private final JPanel tela;

    // Atributo que será usado para validar a exclusão
    private boolean exclusaoValida;

    public TelaInicio() {

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Menu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 220);

        tela = new JPanel();
        tela.setBackground(new Color(173, 216, 230));
        setContentPane(tela);
        tela.setLayout(null);

        // Concatenação o Label com o atributo global nome da classe usuário
        JLabel lblUsuario = new JLabel("Bem vindo " + Usuario.nomeUsuario);
        lblUsuario.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        lblUsuario.setBounds(24, 65, 370, 35);
        tela.add(lblUsuario);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(30, 130, 117, 25);
        btnExcluir.setBackground(new Color(200, 50, 50));
        btnExcluir.setForeground(Color.WHITE);
        tela.add(btnExcluir);

        JButton btnAlterar = new JButton("Alterar Dados");
        btnAlterar.setBounds(155, 130, 117, 25);
        btnAlterar.setBackground(new Color(255, 215, 0));
        btnAlterar.setForeground(Color.BLACK);
        tela.add(btnAlterar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(280, 130, 117, 25);
        btnVoltar.setBackground(new Color(46, 139, 87));
        btnVoltar.setForeground(Color.WHITE);
        tela.add(btnVoltar);

        btnVoltar.addActionListener((ActionEvent e) -> {
            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();

            } catch (Exception ec) {
                System.out.println("Erro ao acessar tela de login "
                        + ec.getMessage());
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                TelaAlteracao telaAlterar = new TelaAlteracao();
                telaAlterar.setVisible(true);
                dispose();

            } catch (Exception ec) {
                System.out.println("Erro ao acessar tela de alteração "
                        + ec.getMessage());

            }

        });

        btnExcluir.addActionListener((ActionEvent e) -> {
            try {
                // Array que recebe opções
                Object[] options = {"Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, Usuario.nomeUsuario
                        + ", deseja se excluir? ", "ATENÇÃO",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[0]);

                if (opcao == 0) {
                    // Excluir o usuario
                    // Instanciar classe do usuario
                    Usuario usu = new Usuario();

                    exclusaoValida
                            = usu.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoValida == true) {
                        // Usuario excluido na base de dados
                        JOptionPane.showMessageDialog(null,
                                " Usuario deletado corretamente, voltaremos "
                                + "a tela de login. ",
                                "ATENÇÃO ",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Abrindo tela de login
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();

                    }else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao excluir o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao excluir usuário " + ec.getMessage());
                                                               
            }

        });

    }
    public void abreTela(){
        TelaInicio telaInicio = new TelaInicio();
        telaInicio.setVisible(true);
    
    
    }
}