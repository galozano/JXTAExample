/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uniandes.kahuuimagenes.mundo;

import java.util.Observer;

/**
 *
 * @author alvar-go@uniandes.edu.co
 */
public class EjemploJXTA 
{

    private AdministradorJXTA admin;

    private String nombre;

    public EjemploJXTA(String nNombre) throws Exception
    {
        nombre = nNombre;
        admin = new AdministradorJXTA(nombre);
       
    }

    public void terminarJXTA()
    {
        admin.deneterJXTA();
    }

    public void addObserver(Observer ob){
        admin.addObserver(ob);
    }

    public void buscarNodos(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
