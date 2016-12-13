/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uniandes.kahuuimagenes.mundo;


import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;


import uniandes.kahuImagenes.advertisements.AdvertisementRecurso;
import uniandes.kahuImagenes.advertisements.MyAdvertisementRecurso;
import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.Advertisement;
import net.jxta.document.AdvertisementFactory;
import net.jxta.id.ID;
import net.jxta.id.IDFactory;
import net.jxta.impl.id.UUID.PipeID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.PipeAdvertisement;

/**
 *
 * @author alvar-go@uniandes.edu.co
 */
public class AdministradorJXTA extends Observable implements DiscoveryListener
{
    //------------------------------------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------------------------------------
    
    private final static String RUTA_CACHE = "data/cache";

    private NetworkManager manager;

    private PeerGroup netPeerGroup;

    private DiscoveryService discovery;

    private PipeAdvertisement pipeAdv;

    //------------------------------------------------------------------------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------------------------------------------------------------------------
    
    public AdministradorJXTA(String nombre) throws Exception
    {      
        try 
        {
            manager = new NetworkManager(NetworkManager.ConfigMode.ADHOC, nombre);       
            manager.startNetwork();

            //Registrar advertisements
            AdvertisementFactory.registerAdvertisementInstance(AdvertisementRecurso.getAdvertisementType(), new MyAdvertisementRecurso.Instantiator());

            netPeerGroup = manager.getNetPeerGroup();
            
            PeerGroupID id = netPeerGroup.getPeerGroupID();
            PipeID idPipe = crearPipeID(id);
            pipeAdv = crearPipeAdvertisement(idPipe);
            
            discovery = netPeerGroup.getDiscoveryService();
            discovery.addDiscoveryListener(this);
        } 
        catch (Exception e)
        {
            throw new Exception("No fue posible conectarse a la red: \n" + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------
    // Metodos
    //------------------------------------------------------------------------------------------------------------------------------

    public void buscarImagenes(String nombreFoto)
    {
        System.out.println("BUSQUEDA:"+nombreFoto);
        
        discovery.getRemoteAdvertisements(null, DiscoveryService.ADV, MyAdvertisementRecurso.NOMBRE_FOTO, "ExisteNodo", 10, this);
    }

    public void agregarFoto(String nombreFoto, String tags) throws Exception
    {
        try 
        {
            System.out.println("AGREGAR FOTO:" + nombreFoto);
                    
            MyAdvertisementRecurso adv = new MyAdvertisementRecurso();
            adv.setNombreFoto( "ExisteNodo" );
            adv.setTags( tags );         
            adv.setPipeAdv(pipeAdv);
            
            discovery.publish(adv);
            
        } 
        catch (Exception e)
        {
            throw new Exception("No se pudo publicar la informaci—n del nodo en la red: \n" + e.getMessage());
        }
    }

    public void deneterJXTA()
    {
        manager.stopNetwork();
    }

    public void discoveryEvent(DiscoveryEvent de)
    {
        System.out.println("DISCOVER ELEMENTS");
        
        Enumeration<Advertisement> respuestas = de.getSearchResults();
        ArrayList<Imagen> imagenesEncontrados = new ArrayList<Imagen>();

        while (respuestas.hasMoreElements())
        {
            Object advertisement = respuestas.nextElement();
            
            if(advertisement instanceof MyAdvertisementRecurso)
            {
                MyAdvertisementRecurso ad = (MyAdvertisementRecurso)advertisement;

                Imagen imagen = new Imagen( );
                imagen.setNombreFoto( ad.getNombreFoto( ) );
                imagen.setTags( ad.getTags( ) );
                
                imagenesEncontrados.add( imagen );
            }
        }
        setChanged();
        notifyObservers(imagenesEncontrados);
    }
    
    //------------------------------------------------------------------------------------------------------------------------------
    // Metodos Privados
    //------------------------------------------------------------------------------------------------------------------------------
    
    private static PipeID crearPipeID(PeerGroupID pgID) 
    {
        PipeID socketID = null;    
        try 
        {
            socketID = (PipeID) IDFactory.newPipeID(pgID);
        } 
        catch (Exception ex) 
        {

        }
        return socketID;
    }

    private PipeAdvertisement crearPipeAdvertisement(ID pipeId) 
    {
        PipeAdvertisement advertisement = (PipeAdvertisement) AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());
        advertisement.setPipeID(pipeId);
        advertisement.setType(PipeService.UnicastType);
        advertisement.setName("Pipe distribuidos");
        return advertisement;
    }
}
