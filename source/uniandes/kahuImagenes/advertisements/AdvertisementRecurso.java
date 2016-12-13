/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uniandes.kahuImagenes.advertisements;


import net.jxta.document.Advertisement;
import net.jxta.id.ID;
import net.jxta.protocol.PipeAdvertisement;

/**
 *
 * @author alvar-go@uniandes.edu.co
 */
public abstract class AdvertisementRecurso extends Advertisement 
{
    //------------------------------------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------------------------------------
    
    private final static String advertisementType = "AdvertisementRecurso";

    private String nombreFoto;

    private String tags;

    private PipeAdvertisement pipeAdv = null;

    //------------------------------------------------------------------------------------------------------------------------------
    // MŽtodos
    //-----------------------------------------------------------------------------------------------------------------------------
    
    public static String getAdvertisementType() 
    {
        return advertisementType;
    }

    public ID getID() 
    {
        return ID.nullID;
    }

    public PipeAdvertisement getPipeAdv() 
    {
        return pipeAdv;
    }
    
    public void setPipeAdv(PipeAdvertisement pipeAdv)
    {
        this.pipeAdv = pipeAdv;
    }

    public String getNombreFoto( )
    {
        return nombreFoto;
    }

    public void setNombreFoto( String nombreFoto )
    {
        this.nombreFoto = nombreFoto;
    }

    public String getTags( )
    {
        return tags;
    }

    public void setTags( String tags )
    {
        this.tags = tags;
    }

    public static String getAdvertisementtype( )
    {
        return advertisementType;
    }
    
    

}
