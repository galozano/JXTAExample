package uniandes.kahuuimagenes.interfaz;

import java.awt.BorderLayout;
import java.util.logging.Level;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import net.jxta.logging.Logging;

import uniandes.kahuuimagenes.mundo.AdministradorJXTA;


public class InterfazKahuuImagenes extends JFrame
{
    //------------------------------------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JTabbedPane tabPanel;
    
    private PanelBusqueda panelBusqueda;
    
    private PanelLibreria panelLibreria;
    
    private PanelMenu menuBar;
    
    private AdministradorJXTA admin;
    
    //private KahuuJXTA admin;
    
    //------------------------------------------------------------------------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------------------------------------------------------------------------
    
    public InterfazKahuuImagenes( AdministradorJXTA admin )
    {
        setSize(700, 500);
        setLayout(new BorderLayout());
        setTitle("Kahuu Fotos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.admin = admin;
    
        tabPanel = new JTabbedPane( );
        
        panelBusqueda = new PanelBusqueda( this );
        tabPanel.addTab( "Busqueda",  panelBusqueda );
        
        panelLibreria = new PanelLibreria( );
        tabPanel.addTab( "Libreria", panelLibreria );
    
        menuBar = new PanelMenu( this );   
        setJMenuBar( menuBar );
        
        admin.addObserver( panelBusqueda );   
        add(tabPanel, BorderLayout.CENTER);    
        
        setLocationRelativeTo(null);        
    }
    
    //------------------------------------------------------------------------------------------------------------------------------
    // Metodos
    //------------------------------------------------------------------------------------------------------------------------------

    public void buscarImagenes(String nombreFoto) 
    {
        admin.buscarImagenes( nombreFoto );
    }
    
    public void agregarFoto(String nombreFoto, String tags)
    {
        try
        {
           admin.agregarFoto( nombreFoto, tags );
        }
        catch( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void dialogoAgregarFoto()
    {
        DialogoAgregarFoto dialogo = new DialogoAgregarFoto( this );
        dialogo.setVisible( true );
    }
    
    public void dispose()
    {
        admin.deneterJXTA( );        
        super.dispose();
    }
    
    //------------------------------------------------------------------------------------------------------------------------------
    // Main
    //------------------------------------------------------------------------------------------------------------------------------
    
    
    public static void main(String[] args)
    {
        String s = JOptionPane.showInputDialog(null, "Ingrese el nombre del nodo", "Nombre", JOptionPane.QUESTION_MESSAGE);
        System.setProperty(Logging.JXTA_LOGGING_PROPERTY, Level.OFF.toString());
        try 
        {
            //KahuuJXTA admin = new KahuuJXTA( s );
            AdministradorJXTA admin = new AdministradorJXTA( s );
             
            InterfazKahuuImagenes i = new InterfazKahuuImagenes(admin);
            i.setVisible(true);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    
    
    
}