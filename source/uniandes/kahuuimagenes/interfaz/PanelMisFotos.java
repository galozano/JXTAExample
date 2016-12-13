package uniandes.kahuuimagenes.interfaz;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelMisFotos extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PanelMisFotos()
    {
        TitledBorder title = BorderFactory.createTitledBorder("Mis Fotos");
        
        setBorder( title );
    }
    
    
}
