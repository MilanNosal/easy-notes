package easynotes.persistence.filenotes;

import easynotes.Configuration;
import easynotes.model.abstractModel.Note;
import static easynotes.model.abstractModel.Note.DELIM;
import easynotes.model.abstractModel.Notes;
import easynotes.model.abstractModel.NotesException;
import easynotes.utilities.AutoFileCloser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class FilePersistenceManager {
    
    private final Notes notes;
    private FilesChangeObserver observer;
    
    private File source = null;
    public static final String EXTENSION = ".note";
    private final JFileChooser fcNotes = new JFileChooser();
    
    public FilePersistenceManager(Notes notes) {
        this.notes = notes;
        observer = new FilesChangeObserver(notes);
        notes.addObserver(observer);
        fcNotes.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fcNotes.setMultiSelectionEnabled(false);
        fcNotes.setCurrentDirectory(new File("."));
        fcNotes.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                int index = f.getAbsolutePath().lastIndexOf(".");
                if (index != -1) {
                    String extension = f.getAbsolutePath().substring(index);
                    if (extension.equals(EXTENSION)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "EasyNotes";
            }
        });
    }
    
    public void setSource(File source) {
        this.source = source;
    }
    
    public boolean setSource() throws NotesException {
        int returnVal = fcNotes.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            source = fcNotes.getSelectedFile();
            int index = source.getName().lastIndexOf('.');
            if (index == -1) {
                source.renameTo(new File(source.getAbsolutePath() + ".note"));
            } else if (!EXTENSION.equals(source.getName().substring(index))) {
                throw new NotesException("You should be able to open only .note files!");
            }
            return true;
        }
        return false;
    }

    public void saveNotes() throws NotesException {        
        if (source == null && !setSource()) {
            return;
        }
        
        try {
            if(source.exists()) {
                int answer = JOptionPane.showConfirmDialog(null, "File " + source.getCanonicalPath()
                        + " exists. Do you want to overwrite it?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            new AutoFileCloser() {
                @Override
                protected void doWork() throws IOException {
                    FileOutputStream fos = autoClose(new FileOutputStream(source));
                    OutputStreamWriter writer = autoClose(new OutputStreamWriter(fos, Configuration.ENCODING));
                    BufferedWriter bw = autoClose(new BufferedWriter(writer));

                    for (Note note : FilePersistenceManager.this.notes.getNotes()) {
                        bw.append(FilePersistenceManager.this.serializeNote(note));
                        bw.newLine();
                    }
                    
                    bw.flush();
                }
            };
            observer.resetModified();
        } catch (IOException ioExp) {
            throw new NotesException("Problem with writing to file " + source.getAbsolutePath() + ".", ioExp);
        }
    }

    public void loadNotes() throws NotesException {
        if (!setSource()) {
            return;
        }
        
        if(!source.exists()) {
            throw new NotesException("File " + source.getAbsolutePath() + " does not exist!");
        }
        
        notes.clearNotes();
        observer.close();
        // Predchadzajuci riadok by mal invalidnut aj observera a tak vytvaram novy
        // (pritom by nemal byt problem ze nedavam zavriet observera).
        observer = new FilesChangeObserver(notes);
        notes.addObserver(observer);

        final List<Note> loadedNotes = new LinkedList<>();

        try {
            new AutoFileCloser() {
                @Override
                protected void doWork() throws IOException {
                    FileInputStream fis = autoClose(new FileInputStream(source));
                    InputStreamReader reader = autoClose(new InputStreamReader(fis, Configuration.ENCODING));
                    BufferedReader br = autoClose(new BufferedReader(reader));

                    Note loadedNote = readNote(br);
                    
                    while (loadedNote != null) {
                        loadedNotes.add(loadedNote);
                        loadedNote = readNote(br);
                    }
                }
            };
        } catch (IOException ioException) {
            throw new NotesException("Problem with reading from file " + source.getAbsolutePath() + ".", ioException);
        }
        
        notes.setNotes(new ArrayList<>(loadedNotes));
        // TODO: nemal by som tu naviazat observer aj na nacitane notes?
    }

    public boolean isSaveNecessary() {
        return observer.areNotesModified();
    }
    
    public String getDatabaseID() {
        return (source == null) ? "No file" : source.getName();
    }
    
    private Note readNote(BufferedReader br) throws IOException {
        String readTitle = readNextSigLine(br);
        if(readTitle==null) {
            return null;
        }
        
        String readLink = readNextSigLine(br);
        if(readLink==null) {
            throw new IOException("Note \'"+readTitle+"\' is not terminated ");
        }
        
        StringTokenizer st = new StringTokenizer(readLink.trim(), DELIM);
        ArrayList<String> links = new ArrayList<>(st.countTokens());
        while(st.hasMoreTokens()) {
            links.add(st.nextToken().trim());
        }
        
        String readText = readNextSigLine(br);
        if(readText==null) {
            throw new IOException("Note \'"+readTitle+"\' is not terminated ");
        }
        
        String readPublicationID = readNextSigLine(br);
        if(readPublicationID==null) {
            throw new IOException("Note \'"+readTitle+"\' is not terminated ");
        }
        
        String readCitation = readNextSigLine(br);
        if(readCitation==null) {
            throw new IOException("Note \'"+readTitle+"\' is not terminated ");
        }
        
        String readTags = readNextSigLine(br);
        if(readTags==null) {
            throw new IOException("Note \'"+readTitle+"\' is not terminated ");
        }
        st = new StringTokenizer(readTags.trim(), DELIM);
        ArrayList<String> tags = new ArrayList<>(st.countTokens());
        while(st.hasMoreTokens()) {
            tags.add(st.nextToken().trim());
        }
        return new Note(readTitle, links, readText, readPublicationID, readCitation, tags);
    }
    
    private String readNextSigLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        while (line != null && line.equals("") && !line.startsWith("<<<")) {
            line = br.readLine();
        }
        if (line != null && line.startsWith("<<<")) {
            StringBuilder sb = new StringBuilder();
            line = br.readLine();
            while (line != null && !line.startsWith(">>>")) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            if(line != null && line.startsWith(">>>")) {
                return sb.toString().trim();
            }
        }
        return null;
    }
    
    private String serializeNote(Note note) {

        StringBuilder builder = new StringBuilder();
        
        builder.append("<<<\n").append(note.getTitle()).append("\n>>>\n<<<\n");
        
        for(String link : note.getLinks()) {
            builder.append(link).append(DELIM);
        }
        
        builder.append("\n>>>\n<<<\n").append(note.getText()).append("\n>>>\n");
        builder.append("<<<\n").append(note.getPublicationID()).append("\n>>>\n");
        builder.append("<<<\n").append(note.getCitation()).append("\n>>>\n<<<\n");

        for(String tag : note.getTags()) {
            builder.append(tag).append(DELIM);
        }
        
        builder.append("\n>>>\n");
        
        return builder.toString();
    }
}
