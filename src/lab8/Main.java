package lab8;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import static lab8.JFrameGUI.createGUI;

class Grade{
    int grade;
    public Grade(int grade){
        this.grade = grade;
    }

    public int getGrade(){
        return grade;
    }
}

class JFrameGUI{
    public static void createGUI(){

        JFrame frame = new JFrame("Lab 8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Выберите в пункте меню: сдал или не сдал", SwingConstants.CENTER);
        topPanel.add(label);

        String[] items = {"Сдал", "Не сдал"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        topPanel.add(comboBox);

        comboBox.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            System.out.println("Вы выбрали: " + selectedItem);
        });

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        JButton button = new JButton("Узнать оценку");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(640,360));
        frame.pack();
        frame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JFrameGUI::createGUI);
    }
}
