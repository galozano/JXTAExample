/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uniandes.kahuuimagenes.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import uniandes.kahuuimagenes.mundo.AdministradorJXTA;
import uniandes.kahuuimagenes.mundo.Imagen;


/**
 *
 * @author alvar-go@uniandes.edu.co
 */
public class PanelBusqueda extends JPanel implements ActionListener, Observer
{
    //------------------------------------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final static String[] ENCABEZADOS = {"Nombre", "Tags"};

    private final static String BUSCAR = "Buscar";

    private JTable tabla;

    private DefaultTableModel modelo;

    private JButton btnBuscar;
    
    private JTextField txtBusqueda;

    private InterfazKahuuImagenes principal;

    //------------------------------------------------------------------------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------------------------------------------------------------------------
    
    public PanelBusqueda(InterfazKahuuImagenes interfaz) 
    {
        setLayout( new BorderLayout());
        principal = interfaz;

        modelo = new DefaultTableModel(ENCABEZADOS, 0);
        tabla = new JTable();
        tabla.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);

        btnBuscar = new JButton(BUSCAR);
        btnBuscar.setActionCommand(BUSCAR);
        btnBuscar.addActionListener(this);
        
        txtBusqueda = new JTextField( );
        
        JPanel panelArriba = new JPanel( );
        panelArriba.setLayout( new BorderLayout( ) );
        
        panelArriba.add( txtBusqueda, BorderLayout.CENTER );
        panelArriba.add( btnBuscar, BorderLayout.EAST );
        
        add(panelArriba, BorderLayout.NORTH);
    }

    //------------------------------------------------------------------------------------------------------------------------------
    // MŽtodos
    //------------------------------------------------------------------------------------------------------------------------------
    
    public void actionPerformed(ActionEvent e) 
    {
       String comando = e.getActionCommand();
      
       if( comando.equals( BUSCAR ))
       {
           principal.buscarImagenes(txtBusqueda.getText( ) );
       }
    }

    public void update(Observable o, Object ob) 
    {
        if( o instanceof AdministradorJXTA)
        {
            @SuppressWarnings("unchecked")
            ArrayList<Imagen> lista = (ArrayList<Imagen>)ob;
            
            modelo = new DefaultTableModel(ENCABEZADOS, 0);
            
            String[] fila;
            for(Imagen n: lista)
            {
                fila = new String[2];
                fila[1] = n.getNombreFoto( );
                fila[0] = n.getTags( );
                modelo.addRow(fila);
            }
            tabla.setModel(modelo);
        }
    }


}
