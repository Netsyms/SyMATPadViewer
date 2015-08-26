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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import javax.swing.JOptionPane;
import org.etherpad_lite_client.EPLiteClient;
import org.etherpad_lite_client.EPLiteException;

/**
 *
 * @author Skylar
 */
public class PadUtils {

    public static final String PADS_URL = "http://pad.symatapp.com";

    /**
     * Add the given pad ID to the local pad list.
     *
     * @param id the pad ID.
     */
    public static void addPad(String id) {
        String pads = PrefStorage.getSetting("pads");

        // Check for dupes
        for (String p : pads.split("\\|")) {
            if (p.equals(id)) {
                return;
            }
        }

        if (pads.equals("")) {
            pads = id;
        } else {
            pads += "|" + id;
        }
        PrefStorage.saveSetting("pads", pads);
    }

    /**
     * Delete the pad with the given ID from local memory.
     * <br />It will still exist online.
     *
     * @param id the pad ID.
     */
    public static void delPad(String id) {
        String pads = PrefStorage.getSetting("pads");
        String result = "";
        int i = 0;
        for (String pad : pads.split("\\|")) {
            if (!pad.equals(id)) {
                if (i > 0) {
                    result += "|";
                }
                result += pad;
                i++;
            }
        }

        PrefStorage.saveSetting("pads", result);
    }

    /**
     * Get an array of saved pads.
     *
     * @return String[] of pad IDs
     */
    public static String[] getPads() {
        String pads = PrefStorage.getSetting("pads");
        if (!pads.equals("")) {
            if (pads.contains("|")) {
                return pads.split("\\|");
            } else {
                String[] padlist = {pads};
                return padlist;
            }
        } else {
            String[] padlist = {};
            return padlist;
        }
    }

    /**
     * Create a new pad on the server with the given ID.
     *
     * @param id The pad ID to create. If blank is auto-generated.
     * @param content Text to initialize the pad with.
     * @return Created pad ID.
     * @throws Exception if things break.
     */
    public static String genPad(String id, String content) throws Exception {
        // Generate ID if blank
        if (id.equals("")) {
            id = genID();
        }

        // Create pad with given text.
        try {
            getClient().createPad(id, content);
        } catch (EPLiteException ex) {
            getClient().setText(id, content);
        }
        return id;
    }

    /**
     * Generate a random pad ID with length 15.
     * <br />There are about 1.217 x 10^26 possibilities (121 septillion).
     * <br />If this starts giving out used IDs, I'll be too rich to care.
     *
     * @return the ID.
     */
    public static String genID() {
        int length = 15;
        String[] chars = ("0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz").split("");
        String out = "";
        Random rng = new Random();

        for (int i = 0; i < length; i++) {
            out += chars[rng.nextInt(chars.length)];
        }

        return out;
    }

    /**
     * Get a client for the pad API.
     *
     * @return the client.
     * @throws Exception if things break.
     */
    public static EPLiteClient getClient() throws Exception {
        String apikey;
        // Enable key change without full SyMAT update
        if (!PrefStorage.getSetting("padkey-override", "").equals("")) {
            apikey = PrefStorage.getSetting("padkey-override", "");
        } else {
            // Load the API key from a file, so it's not included with Git.
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            PadUtils.class
                            .getResourceAsStream("/padkey")));
            apikey = reader.readLine();
        }
        // New client
        return new EPLiteClient(PADS_URL, apikey);
    }

    /**
     * Get the pad text.
     *
     * @param id the pad ID.
     * @return the text, or error message.
     */
    public static String getPad(String id) {
        String text = "";
        try {
            text = getClient().getText(id).getOrDefault("text", "").toString();
        } catch (Exception ex) {
            text = "Error: " + ex.getMessage();
        }
        return text;
    }

    /**
     * Set pad text.
     *
     * @param id Pad ID
     * @param content Pad text
     */
    public static void setPad(String id, String content) {
        try {
            getClient().setText(id, content);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not sync pad contents: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Delete the given pad ID.
     *
     * @param id the pad ID.
     * @param purge If true will delete from server also.
     */
    public static void delPad(String id, boolean purge) {
        delPad(id);
        if (purge) {
            try {
                getClient().deletePad(id);
            } catch (Exception ex) {
                // Meh.  No big deal.
            }
        }
    }
}
