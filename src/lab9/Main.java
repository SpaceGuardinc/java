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
    private static JTextArea searchArea;

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

        JLabel jLabelSearchStart = new JLabel("Введите начальную дату поиска");
        JTextField jTextFieldSearchStart = new JTextField(5);

        JLabel jLabelSearchEnd = new JLabel("Введите конечную дату поиска");
        JTextField jTextFieldSearchEnd = new JTextField(5);

        JButton addButton = new JButton("Добавить событие");
        JButton saveButton = new JButton("Сохранить в файл");
        JButton loadButton = new JButton("Загрузить из файла");
        JButton searchButton = new JButton("Поиск по дате");

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPaneDisplay = new JScrollPane(displayArea);

        searchArea = new JTextArea(10, 30);
        searchArea.setEditable(false);
        JScrollPane scrollPaneSearch = new JScrollPane(searchArea);

        jPanel.add(jLabelNameHistoryMoment);
        jPanel.add(jTextFieldNameHistoryMoment);
        jPanel.add(jLabelYearStart);
        jPanel.add(jTextFieldYearStart);
        jPanel.add(jLabelYearEnd);
        jPanel.add(jTextFieldYearEnd);
        jPanel.add(addButton);

        jPanel.add(jLabelSearchStart);
        jPanel.add(jTextFieldSearchStart);
        jPanel.add(jLabelSearchEnd);
        jPanel.add(jTextFieldSearchEnd);
        jPanel.add(searchButton);

        jPanel.add(saveButton);
        jPanel.add(loadButton);

        JLabel displayLabel = new JLabel("Добавленные события:");
        jPanel.add(displayLabel);
        jPanel.add(scrollPaneDisplay);

        JLabel searchLabel = new JLabel("Результаты поиска:");
        jPanel.add(searchLabel);
        jPanel.add(scrollPaneSearch);

        addButton.addActionListener(e -> {
            try {
                String name = jTextFieldNameHistoryMoment.getText();
                int startYear = Integer.parseInt(jTextFieldYearStart.getText());
                int endYear = Integer.parseInt(jTextFieldYearEnd.getText());
                HistoryMoment moment = new HistoryMoment(name, startYear, endYear);
                historyList.add(moment);
                displayArea.append(moment + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные годы.");
            }
        });

        saveButton.addActionListener(e -> saveHistoryMoments());

        loadButton.addActionListener(e -> loadHistoryMoments());

        searchButton.addActionListener(e -> {
            try {
                int searchStart = Integer.parseInt(jTextFieldSearchStart.getText());
                int searchEnd = Integer.parseInt(jTextFieldSearchEnd.getText());
                searchHistoryMoments(searchStart, searchEnd);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные годы для поиска.");
            }
        });

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

    private static void searchHistoryMoments(int start, int end) {
        searchArea.setText("");
        boolean found = false;
        for (HistoryMoment moment : historyList) {
            if (moment.yearStart >= start && moment.yearEnd <= end) {
                searchArea.append(moment + "\n");
                found = true;
            }
        }
        if (!found) {
            searchArea.append("Объектов в указанном диапазоне нет.\n");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JFrameGUI::createGUI);
    }
}
