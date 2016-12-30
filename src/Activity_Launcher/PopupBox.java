/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

import javax.swing.JOptionPane;

/**
 *
 * @author snienaber
 */
public class PopupBox {

    public void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "Activity Launcher: " + titleBar,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
