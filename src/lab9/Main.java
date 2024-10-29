package lab9;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.PanelUI;

class historyMoments implements Serializable{
    String nameHistoryMoment;
    Integer yearStart;
    Integer yearEnd;

    public historyMoments(String nameHistoryMoment, Integer yearStart, Integer yearEnd){
        this.nameHistoryMoment = nameHistoryMoment;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
    }

    public String getNameHistoryMoment() {
        return nameHistoryMoment;
    }

    public Integer getYearStart(){
        return yearStart;
    }

    public Integer getYearEnd(){
        return yearEnd;
    }

    public void setNameHistoryMoment(String nameHistoryMoment) {
        this.nameHistoryMoment = nameHistoryMoment;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }
}

class jFrameGUI{
    public static void createGUI(){
        JFrame jFrame = new JFrame("Lab 9");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JLabel jLabelNameHistoryMoment = new JLabel("Введите название исторического события");
        jPanel.add(jLabelNameHistoryMoment);
        JTextField jTextFieldNameHistoryMoment = new JTextField( 5);
        jPanel.add(jTextFieldNameHistoryMoment);

        JLabel jLabelYearStart = new JLabel("Введите год начала исторического события");
        jPanel.add(jLabelYearStart);
        JTextField jTextFieldYearStart = new JTextField( 5);
        jPanel.add(jTextFieldYearStart);

        JLabel jLabelYearEnd = new JLabel("Введите год окончания исторического события");
        jPanel.add(jLabelYearEnd);
        JTextField jTextFieldYearEnd = new JTextField( 5);
        jPanel.add(jTextFieldYearEnd);

        jFrame.getContentPane().add(jPanel, BorderLayout.NORTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(640, 360));
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(jFrameGUI::createGUI);
    }
}
