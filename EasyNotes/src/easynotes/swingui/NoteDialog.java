package easynotes.swingui;

import easynotes.model.abstractModel.Note;
import easynotes.swingui.dyncom.EditLinksPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class NoteDialog extends javax.swing.JDialog {

    private Note note;
    private final NotePanel<EditLinksPanel> notePanel;
    private final EditingNotesCallbacks callback;

    public NoteDialog(java.awt.Frame parent, EditingNotesCallbacks callback, Note note) {
        super(parent, false);
        this.callback = callback;
        this.notePanel = new NotePanel<>(EditLinksPanel.EditLinksPanelFactory.getInstance(), true);
        initComponents();
        if (note != null) {
            this.note = note;
            this.notePanel.setNote(note);
            setTitle(note.getTitle());
        } else {
            this.notePanel.setTags(Note.NEW);
            setTitle("New Note");
        }
        
        this.contentJPanel.add(notePanel);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                NoteDialog.this.note = null;
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

        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        contentJPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        contentJPanel.setLayout(new javax.swing.BoxLayout(contentJPanel, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(576, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
            .addComponent(contentJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contentJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        Note newNote = this.notePanel.getNote();
        if (this.note != null) {
            if(!note.getTitle().equals(newNote.getTitle())) {
                note.setTitle(newNote.getTitle());
            }
            if(!note.getCitation().equals(newNote.getCitation())) {
                note.setCitation(newNote.getCitation());
            }
            if(!note.getPublicationID().equals(newNote.getPublicationID())) {
                note.setPublicationID(newNote.getPublicationID());
            }
            if(!note.getText().equals(newNote.getText())) {
                note.setText(newNote.getText());
            }
            if(!Arrays.equals(note.getTags(), newNote.getTags())) {
                note.setTags(Arrays.asList(newNote.getTags()));
            }
            if(!Arrays.equals(note.getLinks(), newNote.getLinks())) {
                note.setLinks(Arrays.asList(newNote.getLinks()));
            }
            this.callback.noteEdited(note);
        } else {
            this.callback.newNoteCreated(newNote);
        }
        this.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel contentJPanel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
