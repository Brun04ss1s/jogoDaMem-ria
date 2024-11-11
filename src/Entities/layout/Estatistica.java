package Entities.layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Estatistica extends JFrame {
    private JTextField buscarUsuario;
    private JButton botaoBuscar;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;

    public Estatistica() {
        // Configurações básicas da tela
        setTitle("Busca de Usuários - Jogo da Memória");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de busca com campo de texto e botão
        JPanel painelBusca = new JPanel();
        buscarUsuario = new JTextField(30);
        botaoBuscar = new JButton("Buscar");
        painelBusca.add(new JLabel("Buscar usuário:"));
        painelBusca.add(buscarUsuario);
        painelBusca.add(botaoBuscar);

        // Tabela para exibir resultados
        modeloTabela = new DefaultTableModel(new String[]{"Nome do Usuário", "Pontuação"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane painelRolagem = new JScrollPane(tabelaResultados);

        // Adiciona componentes à tela
        add(painelBusca, BorderLayout.NORTH);
        add(painelRolagem, BorderLayout.CENTER);

        // Ação do botão de busca
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = buscarUsuario.getText();
            }
        });
    }
}
