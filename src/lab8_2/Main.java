package lab8_2;

import java.awt.*;
import java.util.*;
import javax.swing.*;


class JFrameGUI {
    public static void createGUI() {

        JFrame frame = new JFrame("Lab 8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Выберите: Отлично или Не отлично",
                SwingConstants.CENTER);
        topPanel.add(label);

        String[] items = {"Отлично", "Не отлично"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        topPanel.add(comboBox);

        JLabel numberFieldLabel = new JLabel("Введите количество баллов за научную работу");
        topPanel.add(numberFieldLabel);

        JTextField textField = new JTextField(10);
        topPanel.add(textField);

        JLabel sumFieldLabel = new JLabel("Введите стипендию без надбавки");
        topPanel.add(sumFieldLabel);

        JTextField sumTextField = new JTextField(10);
        topPanel.add(sumTextField);


        JLabel resultLabel = new JLabel();
        topPanel.add(resultLabel);

        JButton button = new JButton("Узнать надбавку");
        button.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            String inputText = textField.getText().trim();
            String sumInputText = sumTextField.getText().trim();

            if (inputText.isEmpty()) {
                resultLabel.setText("Введите количество баллов за научную работу.");
                return;
            }

            if (sumInputText.isEmpty()){
                resultLabel.setText("Введите сумму степендии студента.");
                return;
            }

            int number = Integer.parseInt(inputText);
            float sumNumber = Float.parseFloat(sumInputText);

            if (Objects.equals(selectedItem, "Отлично")) {
                resultLabel.setText("Начислено будет: 50%"  + " " + sumNumber * 0.5);
            } else if (Objects.equals(selectedItem, "Не отлично") && number >= 13) {
                resultLabel.setText("Начислено будет: 50%" + " " + sumNumber * 0.5);
            } else if (Objects.equals(selectedItem, "Не отлично") && number >= 8 && number <= 13) {
                resultLabel.setText("Начислено будет: 35%" + " " + sumNumber * 0.35);
            } else {
                resultLabel.setText("Не предоставляется");
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