package Entities.layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogo extends JFrame {
    int pontuacao = 100;
    private String usuario;
    private Font barraFonte = new Font("Arial Black", Font.BOLD,10);
    private Font jogoFonte = new Font("Arial Black", Font.BOLD, 26);
    private JToolBar barraDeFerramenta = new JToolBar();
    private JButton menuIniciar = new JButton("Menu");
    private JButton reiniciarJogo = new JButton("Reiniciar");
    private JButton[] cards = new JButton[12];
    private GridLayout layout = new GridLayout(4,3);
    private JPanel grid = new JPanel();
    private JPanel barraPontos = new JPanel();
    private JLabel pontuacaoJogador = new JLabel();
    private List<Integer> valoresCartas = new ArrayList<>();
    private int primeiroBotao = 0;
    private int segundoBotao = 0;
    private int numeroDeCliques = 0, quantidade_acertos = 0;

    public Jogo(String usuario){
        super();
        this.usuario = usuario;
        configurarTelaJogo();
        iniciarJogo();
    }
    public Jogo(){
        super();
        configurarTelaJogo();
        iniciarJogo();
    }

    private void configurarTelaJogo(){
        setTitle("JOGO DA MEMÓRIA");
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void iniciarJogo(){
        //Criar a barra do menu
        menuIniciar.setFont(barraFonte);
        reiniciarJogo.setFont(barraFonte);
        barraDeFerramenta.add(menuIniciar);
        barraDeFerramenta.add(reiniciarJogo);
        add(barraDeFerramenta,BorderLayout.NORTH);
        pontuacaoJogador.setFont(barraFonte);
        pontuacaoJogador.setText("Pontos: " + pontuacao);
        barraPontos.add(pontuacaoJogador);
        add(barraPontos,BorderLayout.SOUTH);

        valoresCartas = gerarValores();
        //Criar Cards na tela
        for(int i = 0; i < 12; i++){
            int posicao = i;
            cards[i] = new JButton();
            grid.add(cards[i]);
            cards[i].setFont(jogoFonte);
            cards[i].setVisible(true);

            int valorBotao = valoresCartas.get(i);
            cards[i].putClientProperty("valor",valorBotao);

            cards[i].setText("");

            cards[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton cliqueBotao = (JButton) e.getSource();
                    int value = (int) cliqueBotao.getClientProperty("valor");
                    cliqueBotao.setText(String.valueOf(value));
                    cliqueBotao.setEnabled(false);

                    numeroDeCliques++;

                    if(numeroDeCliques == 1){
                        primeiroBotao = posicao;
                    }
                    if(numeroDeCliques == 2){
                        segundoBotao = posicao;
                        verificarValores();
                    }
                }
            });
        }

        grid.setLayout(layout);
        add(grid, BorderLayout.CENTER);

        //Ação do botão menu
        menuIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarMenu();
            }
        });

        //Ação do botão reiniciar
        reiniciarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });

    }
    public void voltarMenu(){
        int retorno = configuracaoTelaConfirmacao("Deseja voltar ao menu?");

        if(retorno == 0){
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                Menu mainFrame = new Menu();
                mainFrame.setVisible(true);
            });
        }
    }
    public List<Integer> gerarValores(){
        List<Integer> valor = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            valor.add(i);
            valor.add(i);
        }
        Collections.shuffle(valor);
        return valor;
    }
    public void verificarFimDoJogo(ConexaoSQL con) {
        if (quantidade_acertos == 6) {
            con.insert(this.usuario, this.pontuacao);
            exibirTelaFim("Parabéns, você venceu! Deseja voltar ao menu ou reiniciar o jogo?");
        } else if (pontuacao <= 0) {
            exibirTelaFim("Fim de jogo! Você perdeu. Deseja voltar ao menu ou reiniciar o jogo?");
            con.insert(this.usuario, this.pontuacao);
        }
    }
    public void exibirTelaFim(String mensagem) {
        int resposta = JOptionPane.showOptionDialog(
                this,
                mensagem,
                "Fim de Jogo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Voltar ao Menu", "Reiniciar"},
                "Voltar ao Menu"
        );

        if (resposta == JOptionPane.YES_OPTION) {
            voltarMenu();
        } else if (resposta == JOptionPane.NO_OPTION) {
            reiniciarJogo();
        }
    }
    public void verificarValores(){
        if (!cards[primeiroBotao].getClientProperty("valor").equals(cards[segundoBotao].getClientProperty("valor"))) {
            JOptionPane.showMessageDialog(Jogo.this, "Errado");
            cards[primeiroBotao].setText("");
            cards[segundoBotao].setText("");
            cards[primeiroBotao].setEnabled(true);
            cards[segundoBotao].setEnabled(true);
            pontuacao -= 5;
        } else {
            pontuacao += 10;
            quantidade_acertos++;
        }

        pontuacaoJogador.setText("Pontos: " + pontuacao);
        numeroDeCliques = 0;
        primeiroBotao = 0;
        segundoBotao = 0;

        verificarFimDoJogo(new ConexaoSQL());
    }
    public void reiniciarJogo(){
        int retorno = configuracaoTelaConfirmacao("Deseja Reiniciar o jogo?");
        if(retorno == 0){
            this.dispose();
            new Jogo().setVisible(true);
        }
    }
    public static int configuracaoTelaConfirmacao(String mensagem){
        return JOptionPane.showConfirmDialog(
                null,
                mensagem,
                "Confirmação",
                JOptionPane.YES_OPTION,
                JOptionPane.NO_OPTION
        );
    }
}