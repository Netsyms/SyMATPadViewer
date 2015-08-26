/* 
 * Copyright (c) 2015, Netsyms Technologies
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.netsyms.symat.padview;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author Skylar Ittner
 */
public class PrefStorage {

    private static final Preferences prefs = Preferences.userNodeForPackage(PrefStorage.class);

    public static void saveSetting(String key, String value) {
        prefs.put(key, value);
    }

    public static boolean isset(String key) {
        return !getSetting(key, "NULL").equals("NULL");
    }

    public static void unset(String key) {
        saveSetting(key, "");
        save();
        prefs.remove(key);
        save();
    }

    public static String getSetting(String key) {
        return prefs.get(key, "");
    }

    public static String getSetting(String key, String emptyResponse) {
        return prefs.get(key, emptyResponse);
    }

    public static boolean save() {
        try {
            prefs.flush();
            prefs.sync();
        } catch (BackingStoreException ex) {
            System.err.println("Settings could not be saved!");
            return false;
        }
        return true;
    }

    /**
     * Wipe all settings.
     *
     * @throws java.util.prefs.BackingStoreException
     */
    public static void wipe() throws BackingStoreException {
        prefs.clear();
    }
}
