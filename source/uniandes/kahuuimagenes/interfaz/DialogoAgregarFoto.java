package uniandes.kahuuimagenes.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class DialogoAgregarFoto extends JDialog implements ActionListener
{
    private static final String AGREGAR = "AGREGAR";
    
    private JTextField txtNombreFoto;
    
    private JTextField txtTag;
    
    private JButton btnAgregar;
    
    private InterfazKahuuImagenes interfaz;
    
    public DialogoAgregarFoto(InterfazKahuuImagenes interfaz)
    {
        this.interfaz = interfaz;
      
        setLayout( new GridLayout(3,0) );
        txtNombreFoto = new JTextField( );
        
        txtTag = new JTextField( );
        
        btnAgregar = new JButton(AGREGAR);
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );
        
        add(txtNombreFoto);
        add(txtTag);
        add(btnAgregar);
        
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        String mensaje = e.getActionCommand( );
        
        if(mensaje.equals( AGREGAR ))
        {
            interfaz.agregarFoto( txtNombreFoto.getText( ), txtTag.getText( ) );
        }   
    }
    
}
