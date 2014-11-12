package easynotes.swingui.dyncom;

import easynotes.concerns.VariableSubpanels;
import javax.swing.JComponent;

@VariableSubpanels(VariableSubpanels.Role.SUBPANEL)
public interface IsJComponent {

    public JComponent asJComponent();
}
