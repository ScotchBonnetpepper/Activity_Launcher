/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

import java.io.IOException;

/**
 *
 * @author snienaber
 */
public class Launch {

    public void launch(String packageName, String activityName) throws IOException {

        // Launch for Root Shell when selected in drop down
        if (ActivityLaunch.jComboBox1.getSelectedItem().toString().contentEquals("Shell")) {
            if (activityName.charAt(0) != '.' && (!activityName.startsWith("com"))) {
                ProcessBuilder adb = new ProcessBuilder("adb", "shell", "am", "start",
                        "-n", packageName + "/" + "." + activityName);
                Process adbShell = adb.start();
            } else {
                ProcessBuilder adb = new ProcessBuilder("adb", "shell", "am", "start",
                        "-n", packageName + "/" + activityName);
                Process adbShell = adb.start();
            }
            // Launch for SU root when selected in drop down    
        } else {
            if (activityName.charAt(0) != '.' && (!activityName.startsWith("com"))) {
                ProcessBuilder adb2 = new ProcessBuilder("adb", "shell", "su", "-c",
                        "am", "start", "-n", packageName + "/" + "." + activityName);
                Process adbSu = adb2.start();
            } else {
                ProcessBuilder adb2 = new ProcessBuilder("adb", "shell", "su", "-c",
                        "am", "start", "-n", packageName + "/" + activityName);
            }
            // Launch without root when None is selected in drop down
        } 
    }
}
