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

import java.awt.BorderLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.PromptData;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author Skylar
 */
public class WebView extends javax.swing.JPanel {

    private javafx.scene.web.WebView browser;
    private WebEngine webEngine;
    private JFXPanel jfxPanel;
    private Group root;
    private Scene scene;

    private String loaded = "";

    /**
     * Creates new form WebView
     */
    public WebView() {
        initComponents();
        jfxPanel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                browser = new javafx.scene.web.WebView();
                browser.setPrefSize(getWidth(), getHeight());
                root = new Group();
                scene = new Scene(root);
                ObservableList<Node> children = root.getChildren();
                children.add(browser);
                jfxPanel.setScene(scene);
                webEngine = browser.getEngine();
                webEngine.getLoadWorker().stateProperty().addListener(
                        new ChangeListener<Worker.State>() {
                            @Override
                            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                                if (newState == Worker.State.RUNNING) {
                                    GUI.statusLbl.setText("Loading...");
                                } else if (newState == Worker.State.SCHEDULED
                                || newState == Worker.State.READY) {

                                } else {
                                    GUI.statusLbl.setText(loaded);
                                }
                            }
                        });
                webEngine.setOnAlert(
                        new EventHandler<WebEvent<String>>() {
                            @Override
                            public void handle(WebEvent<String> t) {
                                JOptionPane.showMessageDialog(jfxPanel, t.getData(), "Message from webpage", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });

                webEngine.setPromptHandler(
                        new Callback<PromptData, String>() {
                            @Override
                            public String call(PromptData p) {
                                return JOptionPane.showInputDialog(jfxPanel, p.getMessage(), "Question from webpage", JOptionPane.QUESTION_MESSAGE);
                            }
                        });
                webEngine.setConfirmHandler(
                        new Callback<String, Boolean>() {

                            @Override
                            public Boolean call(String p) {
                                return (JOptionPane.showConfirmDialog(
                                        jfxPanel,
                                        p,
                                        "Question from webpage",
                                        JOptionPane.OK_CANCEL_OPTION)
                                == JOptionPane.OK_OPTION);
                            }

                        });
                webEngine.setUserAgent("Mozilla/5.0 SyMAT/PadView1.0");
            }
        });
        add(jfxPanel, BorderLayout.CENTER);
        loadHomepage();
    }

    public void loadURL(final String url, String finishedText) {
        loaded = finishedText;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webEngine.load(url);
            }
        });
    }

    public void loadHomepage() {
        String homepage = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title></title>"
                + "<style>"
                + "html,body{font-family: Tahoma, sans-serif;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h1>" + GUI.SYMAT + " " + GUI.PRODUCT + " " + GUI.VERSION + "</h1>"
                + "<p>To add a pad, press the <b>+</b> button above, and enter the pad ID or URL."
                + "<br />To preview a pad before loading it, press the <b>Preview</b> button."
                + "<br />To open a pad for viewing or editing, press <b>Open</b>."
                + "<br />To save a pad's contents to your computer, press <b>Save...</b>"
                + "</p>"
                + "</body>"
                + "</html>";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webEngine.loadContent(homepage);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxPanel.setSize(getWidth(), getHeight());
                browser.setPrefSize(getWidth(), getHeight());
                browser.resize(getWidth(), getHeight());
            }
        });
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
