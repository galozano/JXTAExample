package uniandes.kahuuimagenes.mundo;

import java.util.Observer;

public class KahuuJXTA
{
    private AdministradorJXTA admin;
    
    public KahuuJXTA(String nombre) throws Exception
    {
        admin = new AdministradorJXTA( nombre );
    }
    
    public void terminarJXTA()
    {
        admin.deneterJXTA();
    }

    public void addObserver(Observer ob)
    {
        admin.addObserver(ob);
    }          

    public void buscarImagenes(String imagenes)
    {
        admin.buscarImagenes( imagenes );
    }

}
