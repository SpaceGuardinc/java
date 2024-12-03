package lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class TestQuestion implements Serializable {
    String question;
    String[] options;
    int correctOption;

    public TestQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String toString() {
        return question + "\n1. " + options[0] + "\n2. " + options[1] + "\n3. " + options[2] +
                "\nПравильный ответ: " + (correctOption + 1);
    }
}

class TestApp {
    private static final String FILE_NAME = "testQuestions.dat";
    private static ArrayList<TestQuestion> questionList = new ArrayList<>();
    private static JTextArea displayArea, testResultArea;
    private static JLabel statusLabel;
    private static JButton addButton, saveButton, loadButton, startTestButton, nextButton;
    private static JRadioButton[] answerButtons;
    private static ButtonGroup buttonGroup;

    private static ArrayList<TestQuestion> currentTest;
    private static int currentQuestionIndex;
    private static int correctAnswers;
    private static int[] userAnswers;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestApp::createGUI);
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Test Application");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Input fields
        JTextField questionField = new JTextField(20);
        JTextField[] optionFields = {new JTextField(10), new JTextField(10), new JTextField(10)};
        JRadioButton[] correctOptionButtons = {new JRadioButton("1"), new JRadioButton("2"), new JRadioButton("3")};
        ButtonGroup correctOptionGroup = new ButtonGroup();
        for (JRadioButton button : correctOptionButtons) correctOptionGroup.add(button);

        // Buttons
        addButton = new JButton("Добавить");
        saveButton = new JButton("Сохранить");
        loadButton = new JButton("Загрузить");
        startTestButton = new JButton("Запуск");
        nextButton = new JButton("Далее");

        // Display areas
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        testResultArea = new JTextArea(10, 30);
        testResultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(testResultArea);

        // Status label
        statusLabel = new JLabel("Статус: Ожидание действий");

        // Answer section
        answerButtons = new JRadioButton[3];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            answerButtons[i] = new JRadioButton();
            buttonGroup.add(answerButtons[i]);
        }

        // Add input components to the panel
        panel.add(new JLabel("Введите вопрос:"));
        panel.add(questionField);
        for (int i = 0; i < 3; i++) {
            panel.add(new JLabel("Вариант ответа " + (i + 1) + ":"));
            panel.add(optionFields[i]);
        }
        panel.add(new JLabel("Выберите правильный вариант:"));
        for (JRadioButton button : correctOptionButtons) panel.add(button);
        panel.add(addButton);

        // Add control buttons
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(new JLabel("Добавленные вопросы:"));
        panel.add(scrollPane);

        panel.add(new JLabel("Введите количество вопросов для теста:"));
        JTextField testSizeField = new JTextField(5);
        panel.add(testSizeField);
        panel.add(startTestButton);
        for (JRadioButton button : answerButtons) panel.add(button);
        panel.add(nextButton);

        panel.add(new JLabel("Результаты теста:"));
        panel.add(resultScrollPane);
        panel.add(statusLabel);

        // Add actions
        addButton.addActionListener(e -> {
            try {
                String question = questionField.getText();
                String[] options = {optionFields[0].getText(), optionFields[1].getText(), optionFields[2].getText()};
                int correctOption = -1;
                for (int i = 0; i < correctOptionButtons.length; i++) {
                    if (correctOptionButtons[i].isSelected()) {
                        correctOption = i;
                        break;
                    }
                }

                if (question.isEmpty() || options[0].isEmpty() || options[1].isEmpty() || options[2].isEmpty() || correctOption == -1) {
                    statusLabel.setText("Статус: Данные заполнены некорректно");
                } else {
                    TestQuestion testQuestion = new TestQuestion(question, options, correctOption);
                    questionList.add(testQuestion);
                    displayArea.append(testQuestion + "\n\n");
                    statusLabel.setText("Статус: Тестовое задание создано");
                }
            } catch (Exception ex) {
                statusLabel.setText("Статус: Ошибка при добавлении");
            }
        });

        saveButton.addActionListener(e -> saveQuestions());
        loadButton.addActionListener(e -> loadQuestions());

        startTestButton.addActionListener(e -> {
            try {
                int testSize = Integer.parseInt(testSizeField.getText());
                if (testSize <= 0 || testSize > questionList.size()) {
                    statusLabel.setText("Статус: Некорректное число заданий");
                } else {
                    currentTest = new ArrayList<>(questionList);
                    Collections.shuffle(currentTest);
                    currentTest = new ArrayList<>(currentTest.subList(0, testSize));
                    userAnswers = new int[testSize];
                    currentQuestionIndex = 0;
                    correctAnswers = 0;
                    startTest();
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("Статус: Введите корректное число заданий");
            }
        });

        nextButton.addActionListener(e -> nextQuestion());

        // Configure frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void saveQuestions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(questionList);
            statusLabel.setText("Статус: Массив сохранен в файл");
        } catch (IOException e) {
            statusLabel.setText("Статус: Ошибка сохранения");
        }
    }

    private static void loadQuestions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            questionList = (ArrayList<TestQuestion>) ois.readObject();
            displayArea.setText("");
            for (TestQuestion question : questionList) {
                displayArea.append(question + "\n\n");
            }
            statusLabel.setText("Статус: Массив извлечен из файла");
        } catch (IOException | ClassNotFoundException e) {
            statusLabel.setText("Статус: Ошибка загрузки");
        }
    }

    private static void startTest() {
        addButton.setEnabled(false);
        saveButton.setEnabled(false);
        loadButton.setEnabled(false);
        startTestButton.setEnabled(false);
        nextButton.setEnabled(true);
        displayCurrentQuestion();
    }

    private static void displayCurrentQuestion() {
        TestQuestion question = currentTest.get(currentQuestionIndex);
        testResultArea.setText(question.question);
        for (int i = 0; i < 3; i++) {
            answerButtons[i].setText(question.options[i]);
            answerButtons[i].setSelected(false);
        }
        statusLabel.setText("Статус: Вопрос " + (currentQuestionIndex + 1) + " из " + currentTest.size());
    }

    private static void nextQuestion() {
        for (int i = 0; i < 3; i++) {
            if (answerButtons[i].isSelected()) {
                userAnswers[currentQuestionIndex] = i;
                if (i == currentTest.get(currentQuestionIndex).correctOption) {
                    correctAnswers++;
                }
                break;
            }
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < currentTest.size()) {
            displayCurrentQuestion();
        } else {
            finishTest();
        }
    }

    private static void finishTest() {
        addButton.setEnabled(true);
        saveButton.setEnabled(true);
        loadButton.setEnabled(true);
        startTestButton.setEnabled(true);
        nextButton.setEnabled(false);

        int totalQuestions = currentTest.size();
        double percentage = (double) correctAnswers / totalQuestions * 100;
        String result = percentage >= 55 ? "Зачет" : "НЕ зачет";

        testResultArea.setText("Результаты теста:\n" +
                "Всего заданий: " + totalQuestions + "\n" +
                "Правильных ответов: " + correctAnswers + "\n" +
                "Процент правильных ответов: " + percentage + "%\n" +
                "Итог: " + result);
        statusLabel.setText("Статус: Тест завершен");
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestApp::createGUI);
    }
}