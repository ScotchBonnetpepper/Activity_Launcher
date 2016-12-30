/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author snienaber
 */
public class ActivityList {

    public BufferedReader activityList(String ApkName) throws IOException {
        String lineAAPT; // used in while loop below
        String appPath = APK_Main.jComboBox1.getSelectedItem().toString();
        InputStream streamAAPT; // input stream to read output from aapt command
        BufferedReader readerAAPT; // buffered reader for input stream
        
        // run aapt command on APK on the device
        ProcessBuilder aapt = new ProcessBuilder(new String[]{"adb", "shell",
            "/data/local/tmp/aapt", "d", "xmltree", appPath+ApkName+"/"+ApkName+".apk", 
            "AndroidManifest.xml"});
        Process aaptStart = aapt.start();
        streamAAPT = aaptStart.getInputStream();
        readerAAPT = new BufferedReader(new InputStreamReader(streamAAPT));

        return readerAAPT;
    }
}
