package ui;

import javax.swing.*;

import dao.DaoCustomer;
import dao.DaoItems;
import dao.DaoProduct;
import javafx.scene.control.ComboBox;
import org.jdatepicker.JDatePicker;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlSale extends JPanel {
    private JDatePicker dPic;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JComboBox cbCate;
    private JComboBox cbCustomer;

    public PnlSale() {
        setLayout(null);
        dPic = new JDatePicker();
        dPic.setBounds(90, 43, 250, 21);
        add(dPic);


        JLabel lblNewLabel = new JLabel("\uC804\uD45C \uC791\uC131\uC77C");
        lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
        lblNewLabel.setBounds(21, 43, 75, 21);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\uACE0\uAC1D\uBA85");
        lblNewLabel_1.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
        lblNewLabel_1.setBounds(346, 43, 49, 21);
        add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 103, 591, 294);
        add(scrollPane);

        table = new JTable();
        scrollPane.setColumnHeaderView(table);

        JList list = new JList();
        list.setBounds(189, 81, 1, 1);
        add(list);

        textField = new JTextField();
        textField.setBounds(515, 79, 97, 21);
        add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(450, 79, 62, 21);
        add(textField_1);

        JButton btnNewButton = new JButton("\uACB0\uC81C");
        btnNewButton.setBounds(515, 407, 97, 23);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\uC0AD\uC81C");
        btnNewButton_1.setBounds(415, 407, 97, 23);
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\uCD94\uAC00");
        btnNewButton_2.setBounds(316, 407, 97, 23);
        add(btnNewButton_2);

        JLabel lblNewLabel_2 = new JLabel("\uC81C\uD488 \uC774\uBBF8\uC9C0");
        lblNewLabel_2.setFont(lblNewLabel_2.getFont().deriveFont(lblNewLabel_2.getFont().getStyle() | Font.BOLD));
        lblNewLabel_2.setBounds(726, 81, 80, 15);
        add(lblNewLabel_2);

        JComboBox comboBox = new JComboBox(new DaoCustomer().getCustAll());
        comboBox.setBounds(390, 43, 222, 21);
        add(comboBox);

        JComboBox comboBox_1 = new JComboBox(new DaoProduct().getCate());
        comboBox_1.setBounds(21, 79, 140, 21);
        add(comboBox_1);

        JComboBox comboBox_2 = new JComboBox(new DaoItems().getItem());
        comboBox_2.setBounds(162, 79, 215, 21);
        add(comboBox_2);

        JTextPane textPane = new JTextPane();
        textPane.setBounds(21, 407, 147, 21);
        add(textPane);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(624, 104, 314, 294);
        add(lblNewLabel_3);
        cbCate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] arr = daoProduct.getprodList(cbCate.getSelectedItem().toString());
                cbProd = new JComboBox(arr);
            }
        });
    }


    }

