package view;

import controller.FindNote;
import controller.PageController;
import entity.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsFindView extends JFrame {

    public StudentsFindView(PageController pageController, JTable table, List<Student> students) {
        super("find");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();

        List<JRadioButton> buttonList = new ArrayList<>();
        JRadioButton findByName = new JRadioButton("by name");
        JRadioButton findByGroup = new JRadioButton("by group");
        JRadioButton findByMarkAndExamName = new JRadioButton("by mark and exam name");
        JRadioButton findByMark = new JRadioButton("by mark");
        JTextField studentsFindInfo = new JTextField(20);
        JTextField minMark = new JTextField(10);
        JTextField maxMark = new JTextField(10);


        buttonList.add(findByName);
        buttonList.add(findByGroup);
        buttonList.add(findByMark);
        buttonList.add(findByMarkAndExamName);
        buttonList.add(findByGroup);

        JButton findButton = new JButton("find");
        findButton.addActionListener(e -> {


            FindNote find = new FindNote();
            find.setStudents(students);
            for (JRadioButton radioButton : buttonList) {
                if (radioButton.isSelected()) {
                    switch (radioButton.getText()) {
                        case "by name": {
                            new StudentFound(find.findByName(studentsFindInfo.getText()),table);
                            break;
                        }
                        case "by group": {
                            new StudentFound(find.findByGroup(Integer.parseInt(studentsFindInfo.getText())),table,this);
                            pageController.setStudents(find.findByGroup(Integer.parseInt(studentsFindInfo.getText())));
                            break;
                        }
                        case "by mark": {
                            new StudentFound(find.findByAverageMark(Double.parseDouble(minMark.getText()), Double.parseDouble(maxMark.getText())),table,this);
                            pageController.setStudents(find.findByAverageMark(Double.parseDouble(minMark.getText()), Double.parseDouble(maxMark.getText())));
                            break;
                        }
                        case "by mark and exam name": {
                            new StudentFound(find.findByMarkAndExamName(Double.parseDouble(minMark.getText()), Double.parseDouble(maxMark.getText()), studentsFindInfo.getText()),table,this);
                            pageController.setStudents(find.findByMarkAndExamName(Double.parseDouble(minMark.getText()), Double.parseDouble(maxMark.getText()), studentsFindInfo.getText()));
                            break;
                        }
                        default: {
                            JOptionPane.showMessageDialog(StudentsFindView.this,
                                    "такого нет");
                        }

                    }
                    break;
                }
            }

        });
        for (JRadioButton button : buttonList) {
            panel.add(button);
        }
        panel.add(findButton);
        panel.add(new Label("minMark"));
        panel.add(minMark);
        panel.add(new Label("maxMark"));
        panel.add(maxMark);
        panel.add(new Label("any student info"));
        panel.add(studentsFindInfo);

        add(panel);
        setSize(1000, 300);
        setResizable(true);
        setVisible(true);

    }

}
