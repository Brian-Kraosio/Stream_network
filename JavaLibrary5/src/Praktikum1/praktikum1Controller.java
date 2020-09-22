/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Praktikum1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class praktikum1Controller {
     private praktikum1 view;

     public praktikum1Controller(praktikum1 view) {
         this.view = view;
         this.view.getjButtonBaca().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 proses();
             }
         });
     }
     
     private void proses(){
         JFileChooser loadFile = view.getLoadFile();
         StyledDocument doc = view.getjTextPane().getStyledDocument();
         if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
             InputStream inputStream = null;
             try {
                 inputStream = new FileInputStream(loadFile.getSelectedFile());
                 InputStreamReader reader = new InputStreamReader(inputStream);
                 BufferedReader br= new BufferedReader(reader);
                 StringBuilder sb=new StringBuilder();
                 String str;
                 while ((str = br.readLine())!=null) {
                     sb.append(str);
                 }
                 doc.insertString(0, sb.toString(), null);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(praktikum1Controller.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException | BadLocationException ex) {
                 Logger.getLogger(praktikum1Controller.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 if (inputStream != null) {
                     try {
                         inputStream.close();
                     } catch (IOException ex) {
                         Logger.getLogger(praktikum1Controller.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
     

}
