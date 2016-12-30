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
public class Next {

    public String setTextBoxToNextCode() {

        if (Variables.position == (Variables.activityList.size() - 1)) {
            new PopupBox().infoBox("end of activity list", "FINISHED");
            // if index value is smaller than total number of key seqs in list
        } else if (Variables.position < Variables.activityList.size()) {
            // setNextVars.setPosition(position1);
            return Variables.activityList.get(++Variables.position);
        }
        return Variables.activityList.get(Variables.position);
    }
}
