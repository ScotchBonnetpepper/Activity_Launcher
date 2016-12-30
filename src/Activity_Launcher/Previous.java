/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity_Launcher;

/**
 *
 * @author snienaber
 */
public class Previous {

    public String loadPrevious() {

        if (Variables.position >= 1) {
            //true only if there is a previous key seq in list. If index is 0, else statment is run
            return Variables.activityList.get(--Variables.position);
        } else {
            new PopupBox().infoBox("no prev. key seq in list", "FINISHED");
        }
        return Variables.activityList.get(Variables.position);
    }
}
