/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIDefaults;

/**
 *
 * @author snienaber
 */
public class ScanAppWindow {

    public void scanAppWindow(ArrayList<String> dupRemList) {
        // Create Jframe popup window
        JFrame frame = new JFrame("Activity Launcher");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create left and right panels for the jframe
        JPanel panelLeft = new JPanel();
        panelLeft.setBounds(3, 3, 3, 3);
        JPanel panelRight = new JPanel();
        panelRight.setBounds(3, 3, 3, 3);

        // create String lists for left and right Jlists in the Jframe
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        DefaultListModel<String> defaultListModel2 = new DefaultListModel<>();

        // Iterate through the array list and add each element to the left jlist
        for (int i = 0; i < dupRemList.size(); i++) {
            defaultListModel.addElement(dupRemList.get(i));
        }

        /* Show default text in right Jlist, without this, the dimensions of the 
        empty right jlist do not match those populated left Jlist */
        defaultListModel2.add(0, "<Add APKs Here>");

        // set minimum size for scroll bar thumb, otherwise is disabppers for long JList dimensions
        UIDefaults def = new UIDefaults();
        def.put("ScrollBar.minimumThumbSize", new Dimension(30, 30));

        // set dimensions of Jlist Left window and set to display defaultListModel
        final JList<String> list = new JList<>();
        list.setModel(defaultListModel);

        // set dimensions of Jlist Right window and set to display defaultListModel2 "<Add APKs Here>"
        final JList<String> list2 = new JList<>();
        list2.setPreferredSize(new Dimension(200, 600));
        list2.setModel(defaultListModel2);

        // Declaration for ADD Button
        JButton Addbutton = new JButton("Add");
        Addbutton.setBounds(1, 1, 1, 1);
        Addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (defaultListModel2.getElementAt(0).contentEquals("<Add APKs Here>")
                        && list.getSelectedValue() != null) {
                    defaultListModel2.clear();
                    defaultListModel2.addElement(list.getSelectedValue());
                } else if (!defaultListModel2.contains(list.getSelectedValue())) {
                    defaultListModel2.addElement(list.getSelectedValue());
                    list2.setModel(defaultListModel2);
                }
            }
        });

        // Declaration for Remove Buton
        JButton Rembutton = new JButton("Remove");
        Rembutton.setBounds(1, 1, 1, 1);
        Rembutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (defaultListModel2.size() == 1
                        && !defaultListModel2.getElementAt(0).contentEquals("<Add APKs Here>")
                        && list2.getSelectedValue() != null) {
                    defaultListModel2.removeElement(list2.getSelectedValue());
                    defaultListModel2.add(0, "<Add APKs Here>");
                } else if (!defaultListModel2.getElementAt(0).contentEquals("<Add APKs Here>")
                        && list2.getSelectedValue() != null) {
                    defaultListModel2.removeElement(list2.getSelectedValue());
                }
            }
        });

        // Declaration for Pull APK button
        JButton PullButton = new JButton("Test Selected APKs");
        PullButton.setBounds(1, 1, 1, 1);
        PullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if statement confirms that APKs have actually been seen selected for testing
                if (!defaultListModel2.getElementAt(0).contentEquals("<Add APKs Here>")) {
                    // launch next jFrame and pass list of selected APKs
                    new SelectAPK().setList(defaultListModel2); 
                    frame.dispose();
                }
            }
        });

        // arrange panels and Jlists in the Jframe popup
        panelLeft.add(list);
        panelLeft.add(Addbutton);
        JScrollPane jScrollpane1 = new JScrollPane(list);
        jScrollpane1.getVerticalScrollBar().putClientProperty("Nimbus.Overrides", def);
        panelLeft.add(jScrollpane1, BorderLayout.NORTH);
        panelRight.add(list2);
        panelRight.add(Rembutton);
        JScrollPane jScrollpane2 = new JScrollPane(list2);
        jScrollpane1.getVerticalScrollBar().putClientProperty("Nimbus.Overrides", def);
        panelRight.add(jScrollpane2, BorderLayout.NORTH);
        frame.setLayout(new BorderLayout(4, 4));
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelRight, BorderLayout.EAST);
        frame.add(PullButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
