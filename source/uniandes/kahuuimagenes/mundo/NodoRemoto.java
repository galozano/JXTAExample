/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uniandes.kahuuimagenes.mundo;

/**
 *
 * @author alvar-go@uniandes.edu.co
 */
public class NodoRemoto
{

    private String direccionIP;

    private String nombreNodo;

    public NodoRemoto() {
    }

    public NodoRemoto(String direccionIP, String nombreNodo) {
        this.direccionIP = direccionIP;
        this.nombreNodo = nombreNodo;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public void setNombreNodo(String nombreNodo) {
        this.nombreNodo = nombreNodo;
    }
}
