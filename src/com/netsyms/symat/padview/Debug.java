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

/**
 * This class handles debug logging, so it's easy to disable.
 *
 * @author Skylar
 */
public class Debug {

    // If output should be on or off
    public static boolean debug = true;

    /**
     * Turn debug output on and off.
     *
     * @param b
     */
    public static void setDebug(boolean b) {
        debug = b;
    }

    /**
     * Check if debug output is enabled.
     *
     * @return true if it is.
     */
    public static boolean getDebug() {
        return debug;
    }

    /**
     * Call System.out.println(data) if debug output enabled.
     *
     * @param data Data to print.
     */
    public static void println(Object data) {
        if (debug) {
            System.out.println(data);
        }
    }

    /**
     * Call System.err.println(data) if debug output enabled.
     *
     * @param data Data to print.
     */
    public static void printerr(Object data) {
        if (debug) {
            System.err.println(data);
        }
    }

    /**
     * Call e.printStackTrace() if debug output enabled.
     *
     * @param e an Exception.
     */
    @SuppressWarnings(value = {"CallToPrintStackTrace"})
    public static void stacktrace(Exception e) {
        if (debug) {
            e.printStackTrace();
        }
    }
}
