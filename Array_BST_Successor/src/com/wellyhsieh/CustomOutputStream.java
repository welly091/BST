package com.wellyhsieh;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.*;

public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException{
        //Convert the byte to a character and append it to the JTextArea
        //Everything written to this output stream will be placed into the text area
        textArea.append(String.valueOf((char)b));

        //Scroll down to the button of the data in textArea.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
