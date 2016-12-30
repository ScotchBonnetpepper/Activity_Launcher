/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author snienaber
 */
public class Screenshot {

    public void screenShot(String capname) throws InterruptedException {
        ProcessBuilder adb = new ProcessBuilder(new String[]{"adb", "shell", 
            "screencap -p /data/local/tmp/" + capname + ".png"});
        ProcessBuilder adbPull = new ProcessBuilder("adb", "pull", 
                "/data/local/tmp/" + capname + ".png");
        try {
            Process adbStart = adb.start();
            adbStart.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(ActivityLaunch.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Process adbPullStart = adbPull.start();
        } catch (IOException ex) {
            Logger.getLogger(ActivityLaunch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
