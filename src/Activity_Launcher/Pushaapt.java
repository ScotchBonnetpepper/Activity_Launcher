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
public class Pushaapt {

    public void pushaapt() throws IOException, InterruptedException {
        ProcessBuilder pushaapt = new ProcessBuilder(new String[]{"adb", "push", "aapt", "data/local/tmp"});
        Process push = pushaapt.start();
        push.waitFor();
        ProcessBuilder chmodaapt = new ProcessBuilder(new String[]{"adb", "shell", "chmod 755 data/local/tmp/aapt"});
        Process chmod = chmodaapt.start();
        chmod.waitFor();
    }
}
