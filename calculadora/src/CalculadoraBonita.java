import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraBonita extends JFrame {

    private JTextField display;
    private String operador = "";
    private double num1 = 0;

    public CalculadoraBonita() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // Painel de botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4, 5, 5));
        painel.setBackground(Color.DARK_GRAY);

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);

            // Estilo
            if ("+-*/".contains(texto)) {
                btn.setBackground(new Color(255, 140, 0)); // laranja
                btn.setForeground(Color.WHITE);
            } else if (texto.equals("=")) {
                btn.setBackground(new Color(0, 150, 0)); // verde
                btn.setForeground(Color.WHITE);
            } else if (texto.equals("C")) {
                btn.setBackground(Color.RED);
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(Color.LIGHT_GRAY);
            }

            btn.addActionListener(e -> clicarBotao(texto));
            painel.add(btn);
        }

        add(painel, BorderLayout.CENTER);
    }

    private void clicarBotao(String texto) {
        if (texto.matches("[0-9]")) {
            display.setText(display.getText() + texto);

        } else if ("+-*/".contains(texto)) {
            num1 = Double.parseDouble(display.getText());
            operador = texto;
            display.setText("");

        } else if (texto.equals("=")) {
            double num2 = Double.parseDouble(display.getText());
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Divisão por zero!");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(resultado));

        } else if (texto.equals("C")) {
            display.setText("");
            operador = "";
            num1 = 0;
        }
    }

    public static void main(String[] args) {
        new CalculadoraBonita().setVisible(true);
    }
}