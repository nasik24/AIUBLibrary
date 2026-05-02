package gui;

import javax.swing.*;
import java.awt.*;

public class Buttons {

    public static JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Serif", Font.BOLD, 22));
        btn.setMaximumSize(new Dimension(380, 55));
        btn.setBackground(new Color(66, 165, 245));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    public static JTextField makeTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Serif", Font.PLAIN, 22));
        field.setMaximumSize(new Dimension(380, 45));
        return field;
    }

    public static JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 22));
        return label;
    }
}
