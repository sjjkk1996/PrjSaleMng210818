package ui;

import dao.DaoCustomer;
import dao.DaoProduct;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlSale extends JPanel {
    private JDatePicker dPic;
    private JTextField tfSearch;
    private JTable table;
    private JLabel lbProdImgBack, lbProdImg;
    private JTextField tfAmount;


    public PnlSale() {
        setLayout(null);

        JLabel lbDate = new JLabel("\uC804\uD45C \uC791\uC131\uC77C");
        lbDate.setBounds(12, 10, 64, 15);
        add(lbDate);

        JLabel lbCusName = new JLabel("    \uACE0\uAC1D\uBA85");
        lbCusName.setBounds(345, 10, 57, 15);
        add(lbCusName);

        JComboBox cbCate = new JComboBox(new DaoProduct().getCate());
        cbCate.setBounds(12, 41, 130, 19);
        add(cbCate);

        JComboBox cbCusName = new JComboBox(new DaoCustomer().getCustAll());
        cbCusName.setBounds(411, 8, 188, 19);
        add(cbCusName);



        JLabel lbProdImgTitle = new JLabel("제품 이미지");
        lbProdImgTitle.setBounds(715, 43, 72, 15);
        add(lbProdImgTitle);


        DaoProduct daoProduct = new DaoProduct();
        JComboBox cbProd = new JComboBox(daoProduct.getProdList(cbCate.getSelectedItem().toString()));
        cbProd.setBounds(147, 41, 209, 19);
        add(cbProd);

        tfSearch = new JTextField();
        tfSearch.setBounds(507, 40, 92, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 70, 587, 329);
        add(scrollPane);


        table = new JTable();
        scrollPane.setColumnHeaderView(table);

        JButton btnAdd = new JButton("\uCD94\uAC00");
        btnAdd.setBounds(345, 416, 60, 23);
        add(btnAdd);

        JButton btnCancle = new JButton("\uCDE8\uC18C");
        btnCancle.setBounds(414, 416, 64, 23);
        add(btnCancle);

        JButton btnPay = new JButton("\uACB0\uC81C");
        btnPay.setBounds(490, 416, 109, 23);
        add(btnPay);

        JTextField tfTotal = new JTextField();
        tfTotal.setBackground(Color.GRAY);
        tfTotal.setBounds(12, 360, 243, 30);
        add(tfTotal);

        JTextField tfPrice = new JTextField();
        tfPrice.setBounds(365, 41, 50, 19);
        add(tfPrice);
        String pId = cbProd.getSelectedItem().toString().split("/")[0];
        tfPrice.setText(daoProduct.getProdPrice(pId));

        JLabel lbProdImg = new JLabel(daoProduct.getProdImg(pId));
        lbProdImg.setBounds(606, 70, 249, 279);
        add(lbProdImg);
        ImageIcon img = daoProduct.getProdImg(pId);
        lbProdImg = new JLabel(resizeImg(img));

        JLabel ProdImgBack= new JLabel();
        ProdImgBack.setBounds(606, 70, 249, 279);
        add(ProdImgBack);
        ProdImgBack.setOpaque(true);
        ProdImgBack.setBackground(Color.WHITE);
        Border bevelBorder = new BevelBorder(BevelBorder.RAISED,
                Color.LIGHT_GRAY, Color.LIGHT_GRAY.darker(),
                Color.LIGHT_GRAY, Color.LIGHT_GRAY.brighter());
        setBorder(bevelBorder);

        JDatePicker dPic = new JDatePicker();
        dPic.setBounds(76, 6, 262, 26);
        add(dPic);

        JTextField tfAmount = new JTextField();
        tfAmount.setBounds(422,41,64,20);
        add(tfAmount);
        tfAmount.setColumns(10);


        cbCate.addActionListener(new ActionListener() {
            Object[] oArr = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                String cateW = cbCate.getSelectedItem().toString();
                cbProd.removeAllItems();
                try {
                    oArr = daoProduct.getProdList(cateW);
                } catch (Exception e1) {
                }
                if (oArr.length > 0)
                    for (int i = 0; i < oArr.length; i++) {
                        cbProd.addItem(oArr[i]);
                    }

                //
                try {
                    oArr = daoProduct.getProdList(cateW);
                } catch (Exception e1) {
                }
                if (oArr.length > 0)
                    for (int i = 0; i < oArr.length; i++)
                        cbProd.addItem(oArr[i]);
                try {
                    String pId1 = cbProd.getSelectedItem().toString().split("/")[0];
                    tfPrice.setText(daoProduct.getProdPrice(pId1));
                } catch (Exception e1) {
                }
                tfSearch.setText("");
                tfAmount.setText("");
                tfAmount.requestFocus();

            }

        });
        JLabel finalLbProdImg = lbProdImg;
        cbProd.addActionListener(e->{
            if (cbProd.getSelectedItem() != null) {
                String pId1 = cbProd.getSelectedItem().toString().split("/")[0];
                tfPrice.setText(daoProduct.getProdPrice(pId1));
                finalLbProdImg.setIcon(daoProduct.getProdImg(pId1));
            }
            tfAmount.setText("");
            tfTotal.setText("");
        });
    }
    private ImageIcon resizeImg(ImageIcon img){
        int imgW = img.getIconWidth();
        int imgH = img.getIconHeight();
        int dynamicH = imgH*230/imgW;
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(230, dynamicH,
                java.awt.Image.SCALE_SMOOTH);
        return img = new ImageIcon(newimg);
    }
}
