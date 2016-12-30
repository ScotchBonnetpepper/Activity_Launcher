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
public class Back {

    public void back() throws IOException {
        ProcessBuilder adbBack = new ProcessBuilder(new String[] {"adb", "shell", "input keyevent 4"});
        adbBack.start();
    }
}
