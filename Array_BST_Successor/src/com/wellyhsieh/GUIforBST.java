package com.wellyhsieh;
import java.io.PipedInputStream;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;

public class GUIforBST implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton summitButton;
    private JButton clearButton;
    private JTextArea ta;
    private JTextField tf;
    private JLabel message;
    private BST bst;
    private BinaryPrinter bstPrinter;

    public GUIforBST(){

        frame = new JFrame("Binary Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        panel = new JPanel();
        label = new JLabel("Enter Text");
        tf = new JTextField(10);
        summitButton = new JButton("Summit");
        summitButton.addActionListener(this);
        clearButton = new JButton(new AbstractAction("Clear"){
            @Override
            public void actionPerformed(ActionEvent e){
                bst = null;
                bstPrinter = null;
                message.setText("Cleared");
                tf.setText("");
                ta.setText("");
            }
        });

        message = new JLabel();

        ta = new JTextArea(40,30);
        ta.setEditable(false);

        //Initiate a new printStream class with customed OutputStream
        PrintStream printStream = new PrintStream(new CustomOutputStream(ta));

        System.setOut(printStream);
        System.setErr(printStream);

        panel.add(label);
        panel.add(tf);
        panel.add(summitButton);
        panel.add(clearButton);
        panel.add(message);
        panel.add(ta);


        frame.add(panel, BorderLayout.CENTER);
        frame.add(message, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new GUIforBST();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        summitAction();
        bstPrinter = new BinaryPrinter(bst.nodesOfInputArray);

    }

    public void summitAction(){
        String input = this.tf.getText();

        boolean checker = false;
        List<Integer> mylist = new ArrayList<>();

        int i = 0;
        while(i < input.length()){
            if(!input.substring(i,i+1).equals(",") && !input.substring(i,i+1).equals(" ")){
                int a = i+1;
                while(a < input.length() && !input.substring(a,a + 1).equals(",") && !input.substring(a, a+1).equals(" ")) {
                    a++;
                }
                try {
                    Integer num = Integer.parseInt(input.substring(i, a));
                    mylist.add(num);
                    i = a;
                }catch(NumberFormatException e1){
                    checker = true;
                    break;
                }
            }else i++;
        }
        if(checker == false) {
            int[] temp = new int[mylist.size()];
            int index = 0;
            for(int a : mylist){
                temp[index++] = a;
            }
            bst = new BST(temp);
            message.setText(bst.preorderToString() + " " + bst.inorderToString() + " " + bst.postorderToString());
            }else {
            message.setText("Contains invalid input numbers.");
        }
    }

}
