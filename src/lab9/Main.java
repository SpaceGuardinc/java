package lab9;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

class HistoryMoment implements Serializable {
    String nameHistoryMoment;
    Integer yearStart;
    Integer yearEnd;

    public HistoryMoment(String nameHistoryMoment, Integer yearStart, Integer yearEnd) {
        this.nameHistoryMoment = nameHistoryMoment;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
    }

    public String toString() {
        return nameHistoryMoment + " год начала: " + yearStart + " год окончания: " + yearEnd;
    }
}

class JFrameGUI {
    private static final String FILE_NAME = "historyMoments.dat";
    private static ArrayList<HistoryMoment> historyList = new ArrayList<>();
    private static JTextArea displayArea;

    public static void createGUI() {
        JFrame jFrame = new JFrame("Lab 9");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JLabel jLabelNameHistoryMoment = new JLabel("Введите название исторического события");
        JTextField jTextFieldNameHistoryMoment = new JTextField(5);

        JLabel jLabelYearStart = new JLabel("Введите год начала исторического события");
        JTextField jTextFieldYearStart = new JTextField(5);

        JLabel jLabelYearEnd = new JLabel("Введите год окончания исторического события");
        JTextField jTextFieldYearEnd = new JTextField(5);

        JButton addButton = new JButton("Добавить событие");
        JButton saveButton = new JButton("Сохранить в файл");
        JButton loadButton = new JButton("Загрузить из файла");

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        jPanel.add(jLabelNameHistoryMoment);
        jPanel.add(jTextFieldNameHistoryMoment);
        jPanel.add(jLabelYearStart);
        jPanel.add(jTextFieldYearStart);
        jPanel.add(jLabelYearEnd);
        jPanel.add(jTextFieldYearEnd);
        jPanel.add(addButton);
        jPanel.add(saveButton);
        jPanel.add(loadButton);
        jPanel.add(scrollPane);

        addButton.addActionListener(e -> {
            String name = jTextFieldNameHistoryMoment.getText();
            int startYear = Integer.parseInt(jTextFieldYearStart.getText());
            int endYear = Integer.parseInt(jTextFieldYearEnd.getText());
            HistoryMoment moment = new HistoryMoment(name, startYear, endYear);
            historyList.add(moment);
            displayArea.append(moment + "\n");
        });

        saveButton.addActionListener(e -> saveHistoryMoments());

        loadButton.addActionListener(e -> loadHistoryMoments());

        jFrame.getContentPane().add(jPanel, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(640, 480));
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static void saveHistoryMoments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(historyList);
            JOptionPane.showMessageDialog(null, "Данные успешно сохранены.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    private static void loadHistoryMoments() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            historyList = (ArrayList<HistoryMoment>) ois.readObject();
            displayArea.setText("");
            for (HistoryMoment moment : historyList) {
                displayArea.append(moment + "\n");
            }
            JOptionPane.showMessageDialog(null, "Данные успешно загружены.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке данных: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JFrameGUI::createGUI);
    }
}
