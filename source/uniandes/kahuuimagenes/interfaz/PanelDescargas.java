package uniandes.kahuuimagenes.interfaz;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDescargas extends JPanel
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PanelDescargas()
    {
        TitledBorder title = BorderFactory.createTitledBorder("Descargas");
        
        setBorder( title );
    }
    
}
