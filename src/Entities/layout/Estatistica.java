package Entities.layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Estatistica extends JFrame {
    private JTextField buscarUsuario;
    private Font fonte = new Font("Arial Black", Font.BOLD, 12);
    private JButton botaoBuscar, menu;
    private JLabel texto = new JLabel("Buscar usuário:");
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private ConexaoSQL con = new ConexaoSQL();
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
        buscarUsuario.setFont(fonte);
        botaoBuscar.setFont(fonte);
        texto.setFont(fonte);
        painelBusca.add(texto);
        painelBusca.add(buscarUsuario);
        painelBusca.add(botaoBuscar);

        // Tabela para exibir resultados
        modeloTabela = new DefaultTableModel(new String[]{"ID,Nome do Usuário", "Pontuação", "Data"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane painelRolagem = new JScrollPane(tabelaResultados);
        painelRolagem.setFont(fonte);

        //Adicionar botão de retorno ao menu
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        menu = new JButton("Menu");
        menu.setFont(fonte);
        menu.setBackground(Color.RED); // Cor vermelha para o botão
        menu.setForeground(Color.WHITE); // Texto branco para melhor contraste
        painelInferior.add(menu);

        // Adiciona componentes à tela
        add(painelBusca, BorderLayout.NORTH);
        add(painelRolagem, BorderLayout.CENTER);
        add(painelInferior,BorderLayout.SOUTH);

        // Ação do botão de busca
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = buscarUsuario.getText();
                gerarLinha(nomeUsuario);
            }
        });
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarMenu();
            }
        });
    }
    public void voltarMenu(){
        this.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public void gerarLinha(String nome){
        ResultSet rs = con.query(nome);
        try {
            if (rs != null) {
                for(int i = 1; i <= modeloTabela.getRowCount() ; i++){
                    mode.
                    modeloTabela.removeRow(i);
                }
                while (rs.next()) {
                    String nomeResult = rs.getString("nome");
                    int pontos = rs.getInt("pontos");
                    Date data = rs.getDate("data");
                    int id = rs.getInt("id_partida");
                    modeloTabela.addRow(new Object[]{id,nomeResult,pontos,data});
                    modeloTabela.removeRow();
                }
                rs.close(); // Fecha o ResultSet
            }
        } catch (Exception e) {
            System.out.print(e);}
    }
}
