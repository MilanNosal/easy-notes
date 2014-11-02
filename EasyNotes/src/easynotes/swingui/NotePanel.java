package easynotes.swingui;

import easynotes.swingui.dyncom.PanelsFactory;
import easynotes.swingui.dyncom.DynamicCollectionPanel;
import easynotes.swingui.dyncom.LinksPanel;
import easynotes.model.abstractModel.Note;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NotePanel<T extends LinksPanel> extends JPanel {

    private DynamicCollectionPanel<T> linksCollection;

    /**
     * Creates new form NotePanel
     */
    public NotePanel(PanelsFactory<T> factory) {
        initComponents();
        linksCollection = new DynamicCollectionPanel<>(factory);
        JScrollPane linksPane = new JScrollPane();
        linksPane.setViewportView(linksCollection);
        linksPanel.add(linksPane);
    }

    public void setNote(Note note) {
        if (note != null) {
            this.titleField.setText(note.getTitle());
            this.pubIDField.setText(note.getPublicationID());
            this.linksCollection.clearCollection();
            for (String link : note.getLinks()) {
                linksCollection.pushItem().setLink(link);
            }
            this.textArea.setText(note.getText());
            this.citationArea.setText(note.getCitation());
            StringBuilder sb = new StringBuilder();
            for (String tag : note.getTags()) {
                sb.append(tag).append(Note.DELIM);
            }
            this.tagsArea.setText(sb.toString());
        } else {
            this.titleField.setText("");
            this.pubIDField.setText("");
            this.linksCollection.clearCollection();
            this.textArea.setText("");
            this.citationArea.setText("");
            this.tagsArea.setText("");
        }
    }

    public Note getNote() {
        List<String> links = new LinkedList<>();
        for (T item : this.linksCollection.getCollection()) {
            links.add(item.getLink());
        }
        StringTokenizer st = new StringTokenizer(this.tagsArea.getText().trim(), Note.DELIM);
        List<String> tags = new ArrayList<>(st.countTokens());
        while (st.hasMoreTokens()) {
            tags.add(st.nextToken());
        }
        return new Note(this.titleField.getText(), links, this.textArea.getText(), this.pubIDField.getText(), this.citationArea.getText(), tags);
    }

    public void setTags(String tags) {
        this.tagsArea.setText(tags);
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
        titleField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pubIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        citationArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tagsArea = new javax.swing.JTextArea();
        linksPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setText("Title:");

        jLabel2.setText("PubID:");

        jLabel3.setText("Text:");

        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        textArea.setLineWrap(true);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        jLabel4.setText("Citation:");

        citationArea.setColumns(20);
        citationArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        citationArea.setLineWrap(true);
        citationArea.setRows(5);
        jScrollPane2.setViewportView(citationArea);

        jLabel5.setText("Tags:");

        tagsArea.setColumns(20);
        tagsArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        tagsArea.setLineWrap(true);
        tagsArea.setRows(5);
        jScrollPane3.setViewportView(tagsArea);

        linksPanel.setLayout(new javax.swing.BoxLayout(linksPanel, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setText("Links:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pubIDField)
                            .addComponent(titleField)
                            .addComponent(linksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(titleField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pubIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(linksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea citationArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JTextField pubIDField;
    private javax.swing.JTextArea tagsArea;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
}