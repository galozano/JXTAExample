package uniandes.kahuuimagenes.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PanelMenu extends JMenuBar implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String AGREGAR_FOTO = "Agregar Foto";

    private InterfazKahuuImagenes interfaz;
    
    public PanelMenu(InterfazKahuuImagenes interfaz)
    {      
        this.interfaz = interfaz;
        
        JMenu file = new JMenu( "File");
        this.add( file );
        
        JMenuItem abrir = new JMenuItem("Abrir" );
        file.add( abrir );

        file.addSeparator( );
        
        JMenuItem exit = new JMenuItem("Salir") ;
        file.add( exit );
        
        JMenu foto = new JMenu( "Foto");
        this.add( foto);
        
        JMenuItem agregarFoto = new JMenuItem(AGREGAR_FOTO);        
        agregarFoto.setActionCommand( AGREGAR_FOTO );
        agregarFoto.addActionListener( this );
        foto.add( agregarFoto );
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
      
        if(comando.equals( AGREGAR_FOTO ))
        {
            
            interfaz.dialogoAgregarFoto( );
        }       
    }

    
}
