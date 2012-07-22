/********************************************************************************
 *
 * jAddressBook - advanced address book
 *
 * Copyright (C) 2006 jAddressBook development team
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 ********************************************************************************/

package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author binary
 */
public class programSettings {

    private Properties settings;
    //private String fileName="programsettings.Properties";
    
    
    private String fileName="D:/ramazan/personal_projects/backup/jaddressbook/properties/programsettings.Properties";
    
    public programSettings() {
        settings=new Properties();
    }
    
    /*
     * Load Settings from File     
     */
    public int loadSettings() {
        try {
        settings.load(new FileInputStream(fileName));
        return 0;
        }catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /*
     * Save settings to File
     */
    public void saveSettings() {
        try {
            settings.store(new FileOutputStream(fileName),"");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Get Settings
     * @return Settings of Program
     */
    public Properties getSettings() {
        return settings;
    }
    
    /*
     * Set program's settings
     * @param psettings New settings for program
     */
    public void setSettings(Properties psettings ) {
        settings=psettings;
    }
    
}
