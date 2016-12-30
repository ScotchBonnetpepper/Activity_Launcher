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
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author snienaber
 */
public class ScanSystem {

    /**
     *
     * @param filter
     * @throws IOException
     * @throws java.lang.InterruptedException
     */
    public void Scan(String filter) throws IOException, InterruptedException {
        String line; // String line is use in while loop below

        // Create process to run adb shell command for listing apps in /system/app or /system/priv-app
        ProcessBuilder adb;
        String appPath = APK_Main.jComboBox1.getSelectedItem().toString();
        adb = new ProcessBuilder(new String[]{"adb", "shell", "ls", appPath});

        // if "Keywords" is selected from "filter" jComboBox1 (drop-down in APK_Main jFrame) 
        if (filter.contentEquals("Keywords")) {
            Process adbStart = adb.start();
            BufferedReader reader;
            ArrayList<String> dupRemList;
            HashSet<String> dupRemSet;
            try ( // Create input stream to read from process adbStart
                    InputStream stream = adbStart.getInputStream()) {
                reader = new BufferedReader(new InputStreamReader(stream));
                // Create Hashlist to eliminate duplicates due to app names matching more than one keyword
                dupRemList = new ArrayList<>();
                dupRemSet = new HashSet<>();

                // Scan input for keywords and add to Array List if match occurs
                while ((line = reader.readLine()) != null) {
                    BufferedReader reader2;
                    ProcessBuilder aapt;
                    aapt = new ProcessBuilder(new String[]{"adb", "shell",
                        "/data/local/tmp/aapt", "d", "xmlstrings",
                        appPath + line + "/" + line + ".apk", "AndroidManifest.xml"});
                    Process aaptStart = aapt.start();
                    InputStream stream2 = aaptStart.getInputStream();
                    reader2 = new BufferedReader(new InputStreamReader(stream2));
                    String line2;
                    while ((line2 = reader2.readLine()) != null) {
                        for (String s : Variables.Keywords) {
                            if (line2.toLowerCase().contains(s) && !line2.toLowerCase().contains("error")) {
                                dupRemList.add(line);
                            }
                        }
                    }
                }
            }
            reader.close(); // close BufferedReader
            dupRemSet.addAll(dupRemList); //Add all items from the Array List to the Hash Set
            dupRemList.clear(); // clear the Array List
            dupRemList.addAll(dupRemSet); // Add the items from the Hash List to the Array List now that dups are removed

            //instantiate method scanAppWindow() in ScanAppWindow class and pass the Array List as an argument
            new ScanAppWindow().scanAppWindow(dupRemList);
            
        // if "None" is selected from "filter" jComboBox1 (drop-down in APK_Main jFrame) 
        } else { 
            Process adbStart = adb.start();
            BufferedReader reader;
            ArrayList<String> appList;
            try ( // Create input stream to read from process adbStart
                    InputStream stream = adbStart.getInputStream()) {
                reader = new BufferedReader(new InputStreamReader(stream));
                // Create Arraylist to hold APK names
                appList = new ArrayList<>();
                // Scan input 
                while ((line = reader.readLine()) != null) {
                    appList.add(line);
                }
            }
            
            //instantiate method scanAppWindow() in ScanAppWindow class and pass the Array List as an argument
            new ScanAppWindow().scanAppWindow(appList);
        }
    }
}
