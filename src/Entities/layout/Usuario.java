package Entities.layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Usuario extends JFrame {
    private String nome = null;
    private JTextField labelNome = new JTextField();
    private JLabel pergunta = new JLabel("Informe o nome do usuário: "), texto;
    private JPanel painel = new JPanel(new GridBagLayout());
    private JButton ok = new JButton("Iniciar");
    private JLabel aviso = new JLabel("Digite um nome!");
    public Usuario(){
        configurarTela();
    }
    public void configurarTela(){
        GridBagConstraints gbc = new GridBagConstraints();

        //Configuração para aparecer centralizado
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-100,0,30,0);

        //Adicionar o texto do menu
        texto = new JLabel("JOGO DA MEMÓRIA");
        texto.setFont(new Font("Arial Black", Font.BOLD, 24));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(texto,gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10,0,0,0);
        pergunta.setPreferredSize(new Dimension(400,40));
        pergunta.setFont(new Font("Arial Black",Font.BOLD, 12));
        pergunta.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(pergunta,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10,0,0,0);
        labelNome.setPreferredSize(new Dimension(300,20));
        labelNome.setFont(new Font("Arial Black",Font.BOLD, 12));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(labelNome,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10,0,0,0);
        ok.setPreferredSize(new Dimension(120,40));
        ok.setFont(new Font("Arial Black", Font.BOLD, 12));
        painel.add(ok,gbc);

        nome = labelNome.getText();

        add(painel);
        setTitle("JOGO DA MEMÓRIA");
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(nome);
            }
        });
    }
    public void iniciarJogo(String nome){
        this.dispose();
        Jogo jogo = new Jogo(nome);
        jogo.setVisible(true);
    }
}
