package lab8;

import java.awt.*;
import java.util.*;
import javax.swing.*;


class JFrameGUI {
    public static void createGUI() {

        JFrame frame = new JFrame("Lab 8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Выберите: сдал или не сдал и введите количество баллов",
                SwingConstants.CENTER);
        topPanel.add(label);

        String[] items = {"Сдал", "Не сдал"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        topPanel.add(comboBox);

        JLabel numberFieldLabel = new JLabel("Введите количество баллов за экзамен");
        topPanel.add(numberFieldLabel);

        JTextField textField = new JTextField(10);
        topPanel.add(textField);

        JLabel resultLabel = new JLabel();
        topPanel.add(resultLabel);

        JButton button = new JButton("Узнать оценку");
        button.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            String inputText = textField.getText().trim();

            if (inputText.isEmpty()) {
                resultLabel.setText("Введите количество баллов.");
                return;
            }

            try {
                int number = Integer.parseInt(inputText);

                if (number > 100) {
                    resultLabel.setText("Введите число меньше 100.");
                } else {
                    if (Objects.equals(selectedItem, "Сдал")) {
                        resultLabel.setText("Оценка за экзамен: отлично");
                    } else if (Objects.equals(selectedItem, "Не сдал") && number >= 85 && number <= 100) {
                        resultLabel.setText("Оценка за экзамен: отлично");
                    } else if (Objects.equals(selectedItem, "Не сдал") && number >= 70 && number <= 84) {
                        resultLabel.setText("Оценка за экзамен: хорошо");
                    } else if (Objects.equals(selectedItem, "Не сдал") && number >= 55 && number <= 69) {
                        resultLabel.setText("Оценка за экзамен: удовлетворительно");
                    } else {
                        resultLabel.setText("Оценка за экзамен: неудовлетворительно");
                    }
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Введите корректное число.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        frame.setPreferredSize(new Dimension(640, 360));
        frame.pack();
        frame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JFrameGUI::createGUI);
    }
}
