package easynotes.model.abstractModel;

import easynotes.concerns.Citing;
import easynotes.concerns.Configuration;
import easynotes.concerns.DomainEntity;
import easynotes.concerns.Links;
import easynotes.concerns.NoteAdding;
import easynotes.concerns.NotesDataModel;
import easynotes.concerns.NoteEventHandling;
import easynotes.concerns.NotesPersistenceFormat;
import easynotes.concerns.Sorting;
import easynotes.concerns.Tagging;
import easynotes.concerns.Unused;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

@NotesDataModel
@DomainEntity
public final class Note extends Observable implements Comparable<Note> {

    @NoteEventHandling(type = NoteEventHandling.Type.EVENT)
    public enum ChangeEventType {

        NEW_NOTE, CITATION_CHANGED, TAG_REMOVED, TAG_ADDED, TITLE_CHANGED, TEXT_CHANGED,
        NOTE_DELETED, LINK_CHANGED, PUBLICATIONID_CHANGED, NOTES_CLEARED, NOTES_LOADED
    }

    @Tagging
    @Configuration
    public static final String USED = "used";
    
    @Tagging
    @Configuration
    public static final String NEW = "new";
    
    @Tagging
    @Configuration
    public static final String DELIM = ";";

    @Tagging
    private List<String> tags = new LinkedList<>();

    private String title;

    @Links
    private final List<String> links = new LinkedList<>();

    private String text;

    @Citing
    private String publicationID;

    @Citing
    private String citation;

    @Tagging
    @Links
    @NoteAdding
    public Note(String title, List<String> links, String text, String publicationID, String citation, List<String> tags) {
        this.title = title.trim();
        for (String link : links) {
            this.links.add(link.replace(System.getProperty("file.separator"), "/").trim());
        }
        this.text = text;
        this.publicationID = publicationID.trim();
        this.citation = citation.trim();
        this.tags = tags;
    }

    @Citing
    public String getCitation() {
        return citation;
    }

    @Citing
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setCitation(String citation) {
        this.citation = citation.trim();
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.CITATION_CHANGED, this));
    }

    /**
     * Use only for getting, never for setting. Returns copy.
     *
     * @return
     */
    @Links
    public String[] getLinks() {
        return links.toArray(new String[links.size()]);
    }

    @Links
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setLinks(List<String> links) {
        this.links.clear();
        for (String link : links) {
            this.links.add(link.replace(System.getProperty("file.separator"), "/").trim());
        }
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.LINK_CHANGED, this));
    }

    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    @Links
    public void addLink(String link) {
        this.links.add(link.replace(System.getProperty("file.separator"), "/").trim());
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.LINK_CHANGED, this));
    }

    /**
     * Use only for getting, never for setting. Returns copy.
     *
     * @return
     */
    @Tagging
    public String[] getTags() {
        return tags.toArray(new String[tags.size()]);
    }

    @Tagging
    public void addTag(String tag) {
        this.tags.add(tag.trim());
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TAG_ADDED, this));
    }

    @Tagging
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setTags(List<String> tags) {
        this.tags = tags;
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TAG_ADDED, this));
    }

    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    @Tagging
    public void removeTag(String tag) {
        this.tags.remove(tag.trim());
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TAG_REMOVED, this));
    }

    public String getText() {
        return text;
    }

    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setText(String text) {
        this.text = text;
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TEXT_CHANGED, this));
    }

    public String getTitle() {
        return title;
    }

    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setTitle(String title) {
        this.title = title.trim();
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TITLE_CHANGED, this));
    }

    @Citing
    public String getPublicationID() {
        return publicationID;
    }

    @Citing
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setPublicationID(String citeBy) {
        this.publicationID = citeBy.trim();
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.PUBLICATIONID_CHANGED, this));
    }

    @Unused
    @NotesPersistenceFormat
    public void print(OutputStream os) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(title);
            bw.newLine();
            bw.newLine();
            bw.write(links.toString());
            bw.newLine();
            bw.newLine();
            bw.write(text);
            bw.newLine();
            bw.newLine();
            bw.write("<<<");
            bw.newLine();
            bw.write(publicationID);
            bw.newLine();
            bw.write(citation);
            bw.newLine();
            bw.write(">>>");
            bw.newLine();
            bw.newLine();
            bw.write("// ");
            for (String tag : tags) {
                bw.write(tag);
                bw.write(DELIM);
            }
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Tagging
    public boolean isNew() {
        for (String tag : tags) {
            if (tag.equalsIgnoreCase(NEW)) {
                return true;
            }
        }
        return false;
    }

    @Tagging
    public boolean isUsed() {
        for (String tag : tags) {
            if (tag.equalsIgnoreCase(USED)) {
                return true;
            }
        }
        return false;
    }

    @Citing
    public String getCiteBy() {
        StringBuilder sb = new StringBuilder("\\cite{");
        sb.append(getPublicationID()).append("}");
        return sb.toString();
    }

    @Sorting
    @Override
    public int compareTo(Note o) {
        boolean thisNew = isNew();
        boolean oNew = o.isNew();
        if (thisNew == true && oNew == false) {
            return -1;
        } else if (oNew == true && thisNew == false) {
            return 1;
        }
        return this.title.compareToIgnoreCase(o.title);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Note && ((Note) o).getPublicationID().equals(this.getPublicationID()));
    }

    @Override
    public int hashCode() {
        return this.getPublicationID().hashCode();
    }

    @NoteEventHandling(type = NoteEventHandling.Type.EVENT)
    public static class ChangeEvent {

        private final ChangeEventType changeType;
        @NoteEventHandling(type = NoteEventHandling.Type.SOURCE)
        private final Note objectOfChange;

        public ChangeEvent(ChangeEventType changeType, Note objectOfChange) {
            this.changeType = changeType;
            this.objectOfChange = objectOfChange;
        }

        public ChangeEventType getChangeType() {
            return changeType;
        }

        public Note getObjectOfChange() {
            return objectOfChange;
        }
    }
}
