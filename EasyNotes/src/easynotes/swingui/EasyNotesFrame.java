package easynotes.swingui;

import easynotes.model.abstractModel.Note;
import easynotes.model.abstractModel.Notes;
import easynotes.model.abstractModel.NotesException;
import easynotes.model.filters.AbstractNotesFilter;
import easynotes.model.filters.FiltersManager;
import easynotes.persistence.filenotes.FilePersistenceManager;
import easynotes.swingui.dyncom.ShowLinksPanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EasyNotesFrame extends javax.swing.JFrame implements EditingNotesCallbacks {

    private NotesTableModel tableModel;
    private Notes notes;
    private FilePersistenceManager persistenceManager;
    private NotePanel<ShowLinksPanel> notePanel;
    private FiltersManager filtersManager;
    private AbstractNotesFilter currentFilter;
    private Note currentlyChosenNote;
    public static Color bckgColour = new Color(67, 65, 53);
    public static Color bckg2Colour = new Color(212, 208, 200);
    public static Color frgColour = new Color(255, 132, 0);
    public static Color frg2Colour = new Color(0, 0, 0);
    public static Color frg3Colour = new Color(255, 255, 255);

    /**
     * Creates new form EasyNotesFrame
     */
    public EasyNotesFrame() {

        this.notes = new Notes(new ArrayList<Note>());
        this.persistenceManager = new FilePersistenceManager(this.notes);
        this.filtersManager = new FiltersManager();
        this.tableModel = new NotesTableModel(this.notes, this.filtersManager);

        initComponents();

        this.currentFilter = this.filtersManager.getFilter((String) this.filtersComboBox.getSelectedItem());
        this.currentFilter.activate();

        this.filtersComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EasyNotesFrame.this.refreshFilter();
            }
        });

        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.black));

        this.notePanel = new NotePanel<>(ShowLinksPanel.ShowLinksPanelFactory.getFactory());
        this.notePanelContainer.setLayout(new javax.swing.BoxLayout(this.notePanelContainer, javax.swing.BoxLayout.LINE_AXIS));
        this.notePanelContainer.add(this.notePanel);
        this.notePanelContainer.setBackground(Color.WHITE);

        this.notesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    JTable table = (JTable) evt.getSource();
                    int index = table.rowAtPoint(evt.getPoint());
                    if (index == -1) {
                        return;
                    }
                    Note note = EasyNotesFrame.this.tableModel.getNoteAt(index);
                    NoteDialog nd = new NoteDialog(EasyNotesFrame.this, EasyNotesFrame.this, note);
                    nd.setVisible(true);
                    // EasyNotesFrame.this.tableModel.updateView();
                    // countLabel.setText("Count: " + EasyNotesFrame.this.tableModel.getRowCount());
                }
            }
        });

        this.notesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    refreshCurrentNote();
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (persistenceManager.isSaveNecessary()) {
                    int answer = JOptionPane.showConfirmDialog(EasyNotesFrame.this, "Notes are not persisted, do you want to persist them before exit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        persistMenuItemActionPerformed(null);
                    }
                }
                // TODO co vsetko zatvarat?
                notes.close();
            }
        });

        updateTable();
    }

    private void refreshFilter() {
        this.currentFilter.deactivate();
        this.currentFilter = this.filtersManager.getFilter((String) this.filtersComboBox.getSelectedItem());
        this.currentFilter.activate();
        this.currentFilter.setCriterion(this.searchField.getText());
        updateTable();
    }

    private void refreshCurrentNote() {
        int index = EasyNotesFrame.this.notesTable.getSelectedRow();
        if (index == -1) {
            return;
        }
        EasyNotesFrame.this.setCurrentNote(EasyNotesFrame.this.tableModel.getNoteAt(index));
    }

    private void setCurrentNote(Note note) {
        this.currentlyChosenNote = note;
        this.citation.setText((note == null) ? "" : this.currentlyChosenNote.getCiteBy());
        this.notePanel.setNote(this.currentlyChosenNote);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        notesTable = new JTable() {
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                Note note = EasyNotesFrame.this.tableModel.getNoteAt(rowIndex);
                String tip = note.isNew()?"*** NEW! ***   ":"";
                tip += note.getText();
                return tip;
            }
        }
        ;
        jPanel2 = new javax.swing.JPanel();
        notePanelContainer = new javax.swing.JPanel();
        citation = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        countLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        filtersComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        runDirMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        persistMenuItem = new javax.swing.JMenuItem();
        saveNotesAsItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        clearMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        newNoteMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        editNoteMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        deleteNoteMenuItem = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EasyNotes");
        setBackground(new java.awt.Color(153, 153, 153));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(EasyNotesFrame.bckgColour, 3));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        notesTable.setModel(this.tableModel);
        notesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        notesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(notesTable);
        notesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel1.add(jScrollPane1);

        jPanel2.setMaximumSize(new java.awt.Dimension(550, 32767));
        jPanel2.setMinimumSize(new java.awt.Dimension(550, 0));
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 296));

        notePanelContainer.setPreferredSize(new java.awt.Dimension(265, 265));

        javax.swing.GroupLayout notePanelContainerLayout = new javax.swing.GroupLayout(notePanelContainer);
        notePanelContainer.setLayout(notePanelContainerLayout);
        notePanelContainerLayout.setHorizontalGroup(
            notePanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        notePanelContainerLayout.setVerticalGroup(
            notePanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );

        jLabel5.setText("Cite as:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notePanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(citation, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(notePanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(citation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1);

        jPanel3.setBackground(EasyNotesFrame.bckgColour);
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, EasyNotesFrame.frgColour));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel3.setMinimumSize(new java.awt.Dimension(0, 30));

        countLabel.setForeground(EasyNotesFrame.frgColour);
        countLabel.setText("jLabel1");

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        jLabel1.setForeground(EasyNotesFrame.frgColour);
        jLabel1.setText("Search in");

        filtersComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.filtersManager.getFilterIdentifiers()));

        jLabel2.setForeground(EasyNotesFrame.frgColour);
        jLabel2.setText(":");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(countLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 412, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countLabel)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(filtersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        getContentPane().add(jPanel3);

        jMenuBar2.setBackground(EasyNotesFrame.bckgColour);
        jMenuBar2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, EasyNotesFrame.frgColour));

        jMenu3.setBackground(EasyNotesFrame.bckgColour);
        jMenu3.setForeground(EasyNotesFrame.frgColour);
        jMenu3.setText("File");

        runDirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        runDirMenuItem.setBackground(EasyNotesFrame.bckgColour);
        runDirMenuItem.setForeground(EasyNotesFrame.frgColour);
        runDirMenuItem.setText("Load Notes");
        runDirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runDirMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(runDirMenuItem);

        jSeparator2.setBackground(EasyNotesFrame.bckgColour);
        jSeparator2.setForeground(EasyNotesFrame.frgColour);
        jSeparator2.setOpaque(true);
        jMenu3.add(jSeparator2);

        persistMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        persistMenuItem.setBackground(EasyNotesFrame.bckgColour);
        persistMenuItem.setForeground(EasyNotesFrame.frgColour);
        persistMenuItem.setText("Save Notes");
        persistMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                persistMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(persistMenuItem);

        saveNotesAsItem.setBackground(EasyNotesFrame.bckgColour);
        saveNotesAsItem.setForeground(EasyNotesFrame.frgColour);
        saveNotesAsItem.setText("Save Notes As");
        saveNotesAsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNotesAsItemActionPerformed(evt);
            }
        });
        jMenu3.add(saveNotesAsItem);

        jSeparator3.setBackground(EasyNotesFrame.bckgColour);
        jSeparator3.setForeground(EasyNotesFrame.frgColour);
        jSeparator3.setOpaque(true);
        jMenu3.add(jSeparator3);

        clearMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        clearMenuItem.setBackground(EasyNotesFrame.bckgColour);
        clearMenuItem.setForeground(EasyNotesFrame.frgColour);
        clearMenuItem.setText("Close Notes");
        clearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(clearMenuItem);

        jSeparator1.setBackground(EasyNotesFrame.bckgColour);
        jSeparator1.setForeground(EasyNotesFrame.frgColour);
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator1.setOpaque(true);
        jMenu3.add(jSeparator1);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setBackground(EasyNotesFrame.bckgColour);
        exitMenuItem.setForeground(EasyNotesFrame.frgColour);
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(exitMenuItem);

        jMenuBar2.add(jMenu3);

        jMenu5.setBackground(EasyNotesFrame.bckgColour);
        jMenu5.setForeground(EasyNotesFrame.frgColour);
        jMenu5.setText("Note");

        newNoteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newNoteMenuItem.setBackground(EasyNotesFrame.bckgColour);
        newNoteMenuItem.setForeground(EasyNotesFrame.frgColour);
        newNoteMenuItem.setText("New Note");
        newNoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newNoteMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(newNoteMenuItem);

        jSeparator4.setBackground(EasyNotesFrame.bckgColour);
        jSeparator4.setForeground(EasyNotesFrame.frgColour);
        jSeparator4.setOpaque(true);
        jMenu5.add(jSeparator4);

        editNoteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        editNoteMenuItem.setBackground(EasyNotesFrame.bckgColour);
        editNoteMenuItem.setForeground(EasyNotesFrame.frgColour);
        editNoteMenuItem.setText("Edit Note");
        editNoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNoteMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(editNoteMenuItem);

        jSeparator5.setBackground(EasyNotesFrame.bckgColour);
        jSeparator5.setForeground(EasyNotesFrame.frgColour);
        jSeparator5.setOpaque(true);
        jMenu5.add(jSeparator5);

        deleteNoteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deleteNoteMenuItem.setBackground(EasyNotesFrame.bckgColour);
        deleteNoteMenuItem.setForeground(EasyNotesFrame.frgColour);
        deleteNoteMenuItem.setText("Delete Note");
        deleteNoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNoteMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(deleteNoteMenuItem);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runDirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runDirMenuItemActionPerformed

        // TODO skontrolovat ci to je dobre, nie som si isty workflowom
        if (persistenceManager.isSaveNecessary()) {
            int answer = JOptionPane.showConfirmDialog(EasyNotesFrame.this, "Notes have changed since the last persisting,"
                    + " do you want to persist them before loading?", "Load", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                persistMenuItemActionPerformed(evt);
            } else if (answer == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        try {
            persistenceManager.loadNotes();
            countLabel.setText("Count: " + tableModel.getRowCount());
        } catch (NotesException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_runDirMenuItemActionPerformed

    private void newNoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newNoteMenuItemActionPerformed
        NoteDialog nd = new NoteDialog(this, this, null);
        nd.setVisible(true);
    }//GEN-LAST:event_newNoteMenuItemActionPerformed

    @Override
    public void newNoteCreated(Note newNote) {
        if (newNote != null) {
            notes.addNote(newNote);
            int index = tableModel.getIndexOf(newNote);
            notesTable.setRowSelectionInterval(index, index);
            refreshCurrentNote();
            countLabel.setText("Count: " + tableModel.getRowCount());
        }
    }

    private void persistMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_persistMenuItemActionPerformed
        if (persistenceManager.isSaveNecessary()) {
            try {
                persistenceManager.saveNotes();
            } catch (NotesException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "It seems that there were no changes or that notes are empty"
                    + " and therefore there is no need to perform save. If you are sure about changes"
                    + ", use \"Save as\" option.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_persistMenuItemActionPerformed

    private void clearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMenuItemActionPerformed
        if (persistenceManager.isSaveNecessary()) {
            int answer = JOptionPane.showConfirmDialog(EasyNotesFrame.this, "Notes have changed since the last persisting,"
                    + " do you want to persist them before clearing?", "Clear", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                persistMenuItemActionPerformed(evt);
            } else if (answer == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        notes.clearNotes();
        persistenceManager.setSource(null);
        searchField.setText("");
        setCurrentNote(null);
        updateTable();
    }//GEN-LAST:event_clearMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void saveNotesAsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNotesAsItemActionPerformed
        try {
            persistenceManager.setSource(null);
            persistenceManager.saveNotes();
        } catch (NotesException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveNotesAsItemActionPerformed

    private void editNoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNoteMenuItemActionPerformed
        if (currentlyChosenNote != null) {
            NoteDialog nd = new NoteDialog(this, this, currentlyChosenNote);
            nd.setVisible(true);
        }
    }//GEN-LAST:event_editNoteMenuItemActionPerformed

    @Override
    public void noteEdited() {
        refreshCurrentNote();
    }

    private void deleteNoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNoteMenuItemActionPerformed
        if (currentlyChosenNote != null) {
            int answer = JOptionPane.showConfirmDialog(EasyNotesFrame.this, "Do you really want to delete note?", "Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                notes.removeNote(currentlyChosenNote);
                setCurrentNote(null);
            }
        }
    }//GEN-LAST:event_deleteNoteMenuItemActionPerformed

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        this.currentFilter.setCriterion(this.searchField.getText());
        updateTable();        
    }//GEN-LAST:event_searchFieldKeyPressed

    private void updateTable() {
        this.tableModel.updateView();
        this.notesTable.repaint();
        countLabel.setText("Count: " + tableModel.getRowCount());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EasyNotesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EasyNotesFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField citation;
    private javax.swing.JMenuItem clearMenuItem;
    private javax.swing.JLabel countLabel;
    private javax.swing.JMenuItem deleteNoteMenuItem;
    private javax.swing.JMenuItem editNoteMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JComboBox filtersComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JMenuItem newNoteMenuItem;
    private javax.swing.JPanel notePanelContainer;
    private javax.swing.JTable notesTable;
    private javax.swing.JMenuItem persistMenuItem;
    private javax.swing.JMenuItem runDirMenuItem;
    private javax.swing.JMenuItem saveNotesAsItem;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
