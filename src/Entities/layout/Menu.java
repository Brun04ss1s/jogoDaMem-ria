package Entities.layout;

import Entities.layout.Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private JPanel painel;
    private JLabel texto;
    private JButton iniciar = new JButton("Iniciar");
    private JButton estatistica = new JButton("Estatisticas");
    private JButton fechar = new JButton("Finalizar");

    public Menu() {
        super();
        configuracaoFrame();
        menu();
        add(painel);
    }

    public void configuracaoFrame() {
        setTitle("JOGO DA MEMÓRIA");
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void menu() {
        painel = new JPanel(new GridBagLayout());
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

        //Adicionar os botões nas linhas abaixo
        gbc.gridy = 1;
        gbc.insets = new Insets(10,0,0,0);
        iniciar.setPreferredSize(new Dimension(120,40));
        iniciar.setFont(new Font("Arial Black",Font.BOLD, 12));
        iniciar.setBackground(Color.green);
        painel.add(iniciar,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10,0,0,0);
        estatistica.setPreferredSize(new Dimension(120,40));
        estatistica.setFont(new Font("Arial Black", Font.BOLD, 12));
        painel.add(estatistica,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10,0,0,0);
        fechar.setPreferredSize(new Dimension(120,40));
        fechar.setFont(new Font("Arial Black", Font.BOLD, 12));
        fechar.setBackground(Color.red);
        painel.add(fechar,gbc);

        //Adicionar funcionalidade aos botões

        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharJogo();
            }
        });

    }
    private void iniciarJogo(){
        this.dispose();
        Usuario jogo = new Usuario();
        jogo.setVisible(true);
    }
    private void fecharJogo(){
        this.dispose();
    }
}
