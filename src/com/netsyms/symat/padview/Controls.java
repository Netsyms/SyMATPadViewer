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

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Skylar
 */
public class Controls extends javax.swing.JPanel {

    private JFileChooser fc = new JFileChooser();

    /**
     * Creates new form Controls
     */
    public Controls() {
        FileFilter filter = new FileNameExtensionFilter("Plain Text (txt, text)", "txt", "text");
        fc.setFileFilter(filter);
        fc.addChoosableFileFilter(filter);
        fc.addChoosableFileFilter(new FileNameExtensionFilter(
                "Javascript (syjs, js)", "syjs", "js"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter(
                "Python (sypy, py)", "sypy", "py"));
        initComponents();
        updateList();
    }

    public void saveFile(String contents) {
        contents = contents.replaceAll("(?<!\r)\n", "\r\n");
        int r = fc.showSaveDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                File filedata = FileUtils.getFileWithExtension(fc);
                FileUtils.saveFile(contents, filedata.getAbsolutePath(), true);
                GUI.statusLbl.setText("Saved " + filedata.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error:  Cannot save file: " + ex.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        padPane = new javax.swing.JList();
        openBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        previewPane = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        previewBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        expandBtn = new javax.swing.JButton();

        jLabel1.setText("My Pads:");

        addBtn.setText("+");
        addBtn.setToolTipText("Add pad by ID");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        padPane.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        padPane.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                padPaneValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(padPane);

        openBtn.setText("Open");
        openBtn.setToolTipText("Open pad for editing");
        openBtn.setEnabled(false);
        openBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Save...");
        saveBtn.setToolTipText("Save pad locally");
        saveBtn.setEnabled(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        previewPane.setEditable(false);
        previewPane.setColumns(1);
        previewPane.setRows(1);
        previewPane.setTabSize(4);
        jScrollPane2.setViewportView(previewPane);

        jLabel2.setText("Preview:");

        previewBtn.setText("Preview");
        previewBtn.setToolTipText("Preview pad contents");
        previewBtn.setEnabled(false);
        previewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewBtnActionPerformed(evt);
            }
        });

        delBtn.setText("Delete");
        delBtn.setToolTipText("Remove pad from list");
        delBtn.setEnabled(false);
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        expandBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/netsyms/symat/padview/pop.png"))); // NOI18N
        expandBtn.setEnabled(false);
        expandBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(delBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(previewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                        .addComponent(expandBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(addBtn)
                    .addComponent(expandBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previewBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delBtn)
                        .addContainerGap())
                    .addComponent(jScrollPane2)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String id = JOptionPane.showInputDialog(this,
                "What is the pad ID?",
                "Add Pad",
                JOptionPane.QUESTION_MESSAGE);
        if (id != null && !id.equals("")) {
            if (id.contains("pad.symatapp.com/p/")) {
                id = id.substring(id.lastIndexOf('/') + 1);
            }
            PadUtils.addPad(id);
        }
        updateList();
    }//GEN-LAST:event_addBtnActionPerformed

    private String getSelectedPad() {
        return padPane.getSelectedValue().toString();
    }

    private void updateList() {
        int sel = padPane.getSelectedIndex();
        padPane.setListData(PadUtils.getPads());
        try {
            padPane.setSelectedIndex(sel);
        } catch (Exception ex) {
            
        }
    }

    private void padPaneValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_padPaneValueChanged
        // Enable/disable action buttons to prevent errors.
        boolean enable = true;
        if (padPane.getSelectedValue() == null) {
            enable = false;
        }
        openBtn.setEnabled(enable);
        saveBtn.setEnabled(enable);
        previewBtn.setEnabled(enable);
        delBtn.setEnabled(enable);
        expandBtn.setEnabled(enable);
    }//GEN-LAST:event_padPaneValueChanged

    private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBtnActionPerformed
        GUI.loadPad(getSelectedPad());
    }//GEN-LAST:event_openBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        saveFile(PadUtils.getPad(getSelectedPad()));
        updateList();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        previewPane.setText(PadUtils.getPad(getSelectedPad()));
        previewPane.setCaretPosition(0);

        updateList();
    }//GEN-LAST:event_previewBtnActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        int ans = JOptionPane.showConfirmDialog(this,
                "Remove pad from list?  It will not be removed from the server.",
                "Delete?",
                JOptionPane.OK_CANCEL_OPTION);
        if (ans == JOptionPane.OK_OPTION) {
            PadUtils.delPad(getSelectedPad());
        }
        updateList();
    }//GEN-LAST:event_delBtnActionPerformed

    private void expandBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expandBtnActionPerformed
        PopoutView pop = new PopoutView(PadUtils.getPad(getSelectedPad()), getSelectedPad());
        pop.setLocationRelativeTo(this);
        pop.setVisible(true);
    }//GEN-LAST:event_expandBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton expandBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton openBtn;
    private javax.swing.JList padPane;
    private javax.swing.JButton previewBtn;
    private javax.swing.JTextArea previewPane;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables
}
