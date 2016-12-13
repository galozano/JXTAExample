package uniandes.kahuuimagenes.interfaz;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelLibreria extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private PanelDescargas panelDescargas;
    
    private PanelMisFotos panelFotos;
    
    
    public PanelLibreria()
    {
        setLayout( new GridLayout( 2, 0 ) );
        
        panelDescargas = new PanelDescargas( );
        panelFotos = new PanelMisFotos( );
        
        add(panelFotos);
        add(panelDescargas);
    }
    
    
}
