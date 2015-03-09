package easynotes.swingui.dyncom;

import easynotes.concerns.Links;
import easynotes.concerns.UI;

@Links
@UI
public interface LinksPanel extends IsJComponent {

    public void setLink(String link);

    public String getLink();
}
