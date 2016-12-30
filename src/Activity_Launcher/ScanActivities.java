/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author snienaber
 */
public class ScanActivities {

    /**
     *
     * @param apkName
     * @return
     * @throws FileNotFoundException
     */
    public boolean checkForActivities(String apkName) throws FileNotFoundException, IOException {
        // this method checks for activities

        Variables.position = 0;
        Variables.activityList.clear();
        Scanner scanner = new Scanner(new ActivityList().activityList(apkName));
        String activityDeclaration = "activity";
        String activityNameDeclaration = "android:name";
        String next;
        
        while (scanner.hasNext()) {
            next = scanner.next();
            if ((next.equals(activityDeclaration))) {
                while (scanner.hasNext()) {
                    next = scanner.next();
                    if (next.contains(activityNameDeclaration)) {
                        String activityName = next.substring(next.indexOf("\"") + 1,
                                next.lastIndexOf("\""));
                        Variables.activityList.add(activityName);
                        break;
                    }
                }
            }
        }
        if (Variables.activityList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void scanActivities(String apkName2, String filter) throws FileNotFoundException, IOException {
        // Variables.position is used for referencing index of activities as they are stored in an array list Variables.activityList
        Variables.position = 0;
        Variables.activityList.clear();

        // this block finds the package name of the application and sets the Package window in GUI to this value
        try (Scanner scanner = new Scanner(new ActivityList().activityList(apkName2))) {
            String packageDeclaration = "package=";
            String nextCode;
            while (scanner.hasNext()) {
                nextCode = scanner.next();
                if (nextCode.contains(packageDeclaration)) {
                    ActivityLaunch.jTextField1.setText(nextCode.substring(nextCode.indexOf("\"") + 1,
                            nextCode.lastIndexOf("\"")));
                    break;
                }
            }
            // run this block if filter drop down is set to None 
            if (filter.contentEquals("None")) {
                String activityDeclaration = "activity";
                String activityNameDeclaration = "android:name";
                String next;
                while (scanner.hasNext()) {
                    next = scanner.next();
                    if ((next.equals(activityDeclaration))) {
                        while (scanner.hasNext()) {
                            next = scanner.next();
                            if (next.contains(activityNameDeclaration)) {
                                String activityName = next.substring(next.indexOf("\"") + 1,
                                        next.lastIndexOf("\""));
                                if (!activityName.contains("$")) {
                                    Variables.activityList.add(activityName);
                                }
                                break;
                            }
                        }
                    }
                }
                // diplay activity at index 0 in text window (package name)
                ActivityLaunch.jTextField2.setText(Variables.activityList.get(Variables.position));

                // run this block if filter drop down is set to Keywords     
            } else if (filter.contentEquals("Keywords")) {
                String activityDeclaration = "activity";
                String activityNameDeclaration = "android:name";
                String next;
                while (scanner.hasNext()) {
                    next = scanner.next();
                    if (next.equals(activityDeclaration) && !next.contains("$")) {
                        while (scanner.hasNext()) {
                            next = scanner.next();
                            if (next.contains(activityNameDeclaration)) {
                                String activityName = next.substring(next.indexOf("\"") + 1,
                                        next.lastIndexOf("\""));
                                for (String s : Variables.Keywords) {
                                    if (activityName.toLowerCase().contains(s)
                                            && !activityName.toLowerCase().contains("$")) {
                                        Variables.activityList.add(activityName);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                // diplay activity at index 0 in text window (package name)
                ActivityLaunch.jTextField2.setText(Variables.activityList.get(Variables.position));
            }
        }
    }
}
