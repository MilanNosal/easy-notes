package easynotes.swingui.dyncom;

import easynotes.concerns.VariableSubpanels;

@VariableSubpanels(VariableSubpanels.Role.SUBPANEL)
public interface LinksPanel extends IsJComponent {
    public void setLink(String link);
    public String getLink();
}
