package easynotes.swingui.dyncom;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DynamicCollectionPanel<T extends IsJComponent> extends JPanel {
    private final JPanel content = new JPanel();
    private final LinkedList<T> collection = new LinkedList<>();
    private final JButton addButton = new JButton("+");
    private final JButton removeButton = new JButton("-");
    private final PanelsFactory<T> factory;

    public DynamicCollectionPanel(PanelsFactory<T> factory) {
        this.factory = factory;
        
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        
        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.PAGE_AXIS));
        this.add(content);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        
        buttons.add(addButton);
        buttons.add(removeButton);
        this.add(buttons);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DynamicCollectionPanel.this.pushItem();
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DynamicCollectionPanel.this.popItem();
            }
        });
    }
    
    public T pushItem() {
        T newPanel = this.factory.getNewInstance();
        this.collection.push(newPanel);
        this.content.add(newPanel.asJComponent());
        this.revalidate();
        return newPanel;
    }
    
    public T popItem() {
        if(this.collection.isEmpty()) {
            return null;
        }
        T removedPanel = this.collection.pop();
        this.content.remove(removedPanel.asJComponent());
        this.revalidate();
        return removedPanel;
    }
    
    public void clearCollection() {
        while(!collection.isEmpty()) {
            popItem();
        }
    }
    
    public List<T> getCollection() {
        return this.collection;
    }
}
