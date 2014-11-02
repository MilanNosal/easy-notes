package easynotes.model.abstractModel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public final class Note extends Observable implements Comparable<Note> {
    
    public enum ChangeEventType {
        NEW_NOTE, CITATION_CHANGED, TAG_REMOVED, TAG_ADDED, TITLE_CHANGED, TEXT_CHANGED,
        NOTE_DELETED, LINK_CHANGED, PUBLICATIONID_CHANGED, NOTES_CLEARED, NOTES_LOADED
    }    
    
    public static final String USED = "used";
    public static final String NEW = "new";
    public static final String DELIM = ";";
    
    private List<String> tags = new LinkedList<>();
    
    private String title;
    
    private List<String> links = new LinkedList<>();
    
    private String text;
    
    private String publicationID;
    
    private String citation;
    
    public Note(String title, List<String> links, String text, String publicationID, String citation, List<String> tags) {
        this.title = title.trim();
        for(String link : links) {
            this.links.add(link.replace(System.getProperty("file.separator"), "/").trim());
        }
        this.text = text;
        this.publicationID = publicationID.trim();
        this.citation = citation.trim();
        this.tags = tags;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation.trim();
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.CITATION_CHANGED, this));
    }

    /**
     * Use only for getting, never for setting. Returns copy.
     * @return 
     */
    public String[] getLinks() {
        return links.toArray(new String[links.size()]);
    }

    public void setLinks(List<String> links) {
        this.links.clear();
        for(String link : links) {
            this.links.add(link.replace(System.getProperty("file.separator"), "/").trim());
        }
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.LINK_CHANGED, this));
    }
    
    public void addLink(String link) {
        this.links.add(link.replace(System.getProperty("file.separator"), "/").trim());
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.LINK_CHANGED, this));
    }

    /**
     * Use only for getting, never for setting. Returns copy.
     * @return 
     */
    public String[] getTags() {
        return tags.toArray(new String[tags.size()]);
    }
    
    public void addTag(String tag) {
        this.tags.add(tag.trim());
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TAG_ADDED, this));
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TAG_ADDED, this));
    }
    
    public void removeTag(String tag) {
        this.tags.remove(tag.trim());
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TAG_REMOVED, this));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TEXT_CHANGED, this));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.TITLE_CHANGED, this));
    }

    public String getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(String citeBy) {
        this.publicationID = citeBy.trim();
        this.setChanged();
        this.notifyObservers(new ChangeEvent(ChangeEventType.PUBLICATIONID_CHANGED, this));
    }
    
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
            for(String tag : tags) {
                bw.write(tag);
                bw.write(DELIM);
            }
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public boolean isNew() {
        for(String tag : tags) {
            if(tag.equalsIgnoreCase(NEW)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isUsed() {
        for(String tag : tags) {
            if(tag.equalsIgnoreCase(USED)) {
                return true;
            }
        }
        return false;
    }
    
    public String getCiteBy() {
        StringBuilder sb = new StringBuilder("\\cite{");
        sb.append(getPublicationID()).append("}");
        return sb.toString();
    }

    @Override
    public int compareTo(Note o) {
        boolean thisNew = isNew();
        boolean oNew = o.isNew();
        if (thisNew == true && oNew == false) {
            return -1;
        } else if(oNew == true && thisNew == false) {
            return 1;
        }
        return this.title.compareToIgnoreCase(o.title);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Note && ((Note)o).getPublicationID().equals(this.getPublicationID())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getPublicationID().hashCode();
    }
    
    public static class ChangeEvent {
        private ChangeEventType changeType;
        private Note objectOfChange;

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