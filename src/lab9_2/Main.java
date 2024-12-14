package lab9_2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

class Researcher implements Serializable {
    String name;
    String workplace;
    int publications;

    public Researcher(String name, String workplace, int publications) {
        this.name = name;
        this.workplace = workplace;
        this.publications = publications;
    }

    public String toString() {
        return "ФИО: " + name +
                ", Место работы: " + workplace +
                ", Количество публикаций: " + publications;
    }
}

class JFrameGUI {
    private static final String FILE_NAME = "ResearchersInfo.dat";
    private static ArrayList<Researcher> researcherList = new ArrayList<>();
    private static JTextArea displayArea;
    private static JTextArea searchArea;

    public static void createGUI() {
        JFrame jFrame = new JFrame("lab4");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JLabel jLabelName = new JLabel("Введите ФИО: ");
        JTextField jTextFieldName = new JTextField();
        JLabel jLabelWorkplace = new JLabel("Введите место работы: ");
        JTextField jTextFieldWorkplace = new JTextField();
        JLabel jLabelPublications = new JLabel("Введите кол-во публикаций: ");
        JTextField jTextFieldPublications = new JTextField();
        JButton addButton = new JButton("Добавить сотрудника");
        JButton saveButton = new JButton("Сохранить");
        JButton loadButton = new JButton("Загрузить");
        JButton searchButton = new JButton("Поиск сотрудника");
        JLabel jLabelStart = new JLabel("Введите начальное кол-во публикаций: ");
        JTextField jTextFieldStart = new JTextField();
        JLabel jLabelEnd = new JLabel("Введите конечное кол-во публикаций: ");
        JTextField jTextFieldEnd = new JTextField();

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPaneDisplay = new JScrollPane(displayArea);

        searchArea = new JTextArea(10, 30);
        searchArea.setEditable(false);
        JScrollPane scrollPaneSearch = new JScrollPane(searchArea);

        addButton.addActionListener(e -> {
            try {
                String name = jTextFieldName.getText();
                String workplace = jTextFieldWorkplace.getText();
                int publications = Integer.parseInt(jTextFieldPublications.getText());
                Researcher researcher = new Researcher(name, workplace, publications);
                researcherList.add(researcher);
                displayArea.append(researcher + "\n");
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Введите корректное кол-во публикациий");
            }
        });

        loadButton.addActionListener(e -> {
            try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                researcherList = (ArrayList<Researcher>) OIS.readObject();
                displayArea.setText("");
                researcherList.forEach(researcher -> displayArea.append(researcher + "\n"));
                JOptionPane.showMessageDialog(null, "Данные успешно загружены");
            } catch (IOException|ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Ошибка" + exception.getMessage());
            }
            });

        saveButton.addActionListener(e -> {
            try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                OOS.writeObject(researcherList);
                JOptionPane.showMessageDialog(null, "Данные успешно сохранены");
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, "Ошибка" + exception.getMessage());
            }
            });


        searchButton.addActionListener(e -> {
            try {
                int searchStart = Integer.parseInt(jTextFieldStart.getText());
                int searchEnd = Integer.parseInt(jTextFieldEnd.getText());
                searchArea.setText("");
                boolean found = false;
                for (Researcher researcher : researcherList) {
                    if (researcher.publications >= searchStart && researcher.publications <= searchEnd) {
                        searchArea.append(researcher + "\n");
                        found = true;
                    }
                }
                if (!found) {
                    searchArea.append("Объектов в указанном диапазоне нет.\n");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректное кол-во публикаций.");
            }
        });

        jPanel.add(jLabelName);
        jPanel.add(jTextFieldName);
        jPanel.add(jLabelWorkplace);
        jPanel.add(jTextFieldWorkplace);
        jPanel.add(jLabelPublications);
        jPanel.add(jTextFieldPublications);
        jPanel.add(addButton);
        jPanel.add(saveButton);
        jPanel.add(loadButton);
        jPanel.add(searchButton);
        JLabel displayLabel = new JLabel("Добавленные сотрудники: ");
        jPanel.add(displayLabel);
        jPanel.add(scrollPaneDisplay);
        jPanel.add(jLabelStart);
        jPanel.add(jTextFieldStart);
        jPanel.add(jLabelEnd);
        jPanel.add(jTextFieldEnd);
        jPanel.add(scrollPaneSearch);
        jFrame.getContentPane().add(jPanel, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(640, 480));
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JFrameGUI::createGUI);
    }
}