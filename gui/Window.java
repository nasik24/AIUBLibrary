package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import library.*;

public class Window extends JFrame implements ActionListener {

    JTextField idField;
    JTextField titleField;
    JTextField authorField;
    JTextArea outputArea;

    JButton addBtn;
    JButton viewBtn;
    JButton searchBtn;
    JButton removeBtn;
    JButton updateBtn;
           

    BookList bookList = new BookList();

    public Window() {

        // load saved books when app starts
        bookList.loadFromFile();        

        setTitle("AIUB Library");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== HEADER =====
        ImageIcon icon = new ImageIcon("logo.png");
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        JLabel header = new JLabel("AIUB Library", icon, JLabel.CENTER);
        header.setHorizontalTextPosition(JLabel.RIGHT);
        header.setVerticalTextPosition(JLabel.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 90));
        header.setOpaque(true);
        header.setBackground(new Color(25, 118, 210));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        // ===== LEFT SIDE PANEL =====
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(245, 245, 245));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.setPreferredSize(new Dimension(420, 600));

        // ID input
        leftPanel.add(Buttons.makeLabel("Book ID:"));
        idField = Buttons.makeTextField();
        leftPanel.add(idField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Title input
        leftPanel.add(Buttons.makeLabel("Title:"));
        titleField = Buttons.makeTextField();
        leftPanel.add(titleField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Author input
        leftPanel.add(Buttons.makeLabel("Author:"));
        authorField = Buttons.makeTextField();
        leftPanel.add(authorField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ===== BUTTONS =====
        addBtn    = Buttons.makeButton("Add");
        viewBtn   = Buttons.makeButton("View");
        searchBtn = Buttons.makeButton("Search");
        removeBtn = Buttons.makeButton("Remove");
        updateBtn = Buttons.makeButton("Update");
               

        leftPanel.add(addBtn);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(viewBtn);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(searchBtn);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(removeBtn);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(updateBtn);
                               

        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        updateBtn.addActionListener(this);
                     
		
		// Scroll Button 
		
        JScrollPane leftScroll = new JScrollPane(leftPanel);
	leftScroll.setPreferredSize(new Dimension(440, 600));
	leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	add(leftScroll, BorderLayout.WEST);

        // ===== OUTPUT AREA =====
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Serif", Font.PLAIN, 28));
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(15, 15, 15, 15));
        outputArea.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scroll, BorderLayout.CENTER);

        // ===== FOOTER =====
        JLabel footer = new JLabel("Developed by Nafi & Nazmul", JLabel.CENTER);
        footer.setFont(new Font("Serif", Font.BOLD, 28));
        footer.setOpaque(true);
        footer.setBackground(new Color(25, 118, 210));
        footer.setForeground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String cmd    = e.getActionCommand();
        String title  = titleField.getText();
        String author = authorField.getText();
        int id = 0;

        try {
            if (!idField.getText().isEmpty()) {
                id = Integer.parseInt(idField.getText());
            }
        } catch (Exception ex) {
            outputArea.setText("Please enter a valid number for ID!");
            return;
        }

        if (cmd.equals("Add")) {
            outputArea.setText(bookList.addBook(id, title, author));
        }
        else if (cmd.equals("View")) {
            outputArea.setText(bookList.viewAll());
        }
        else if (cmd.equals("Search")) {
            outputArea.setText(bookList.searchBook(id, title));
        }
        else if (cmd.equals("Remove")) {
            outputArea.setText(bookList.removeBook(id));
        }
        else if (cmd.equals("Update")) {
            outputArea.setText(bookList.updateBook(id, title, author));
        }
    }
}
