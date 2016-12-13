/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uniandes.kahuImagenes.advertisements;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Enumeration;
import net.jxta.document.Advertisement;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.Document;
import net.jxta.document.Element;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.StructuredDocumentUtils;
import net.jxta.document.StructuredTextDocument;
import net.jxta.document.TextElement;
import net.jxta.protocol.PipeAdvertisement;

/**
 *
 * @author alvar-go@uniandes.edu.co
 */
public class MyAdvertisementRecurso extends AdvertisementRecurso{

    private final static String MIME_TYPE= "text/xml";

    public static final String NOMBRE_FOTO = "nombreFoto";

    public static final String TAG = "tag";

    /**
     * Elementos que se usan para indexar el advertisement
     */
    private final static String[] fields = {NOMBRE_FOTO};

    
    public MyAdvertisementRecurso(InputStream  stream) throws IOException
    {
        super();

        StructuredTextDocument document= (StructuredTextDocument) StructuredDocumentFactory.newStructuredDocument( new MimeMediaType(MIME_TYPE), stream);
        readAdvertisement(document);
    }

    public MyAdvertisementRecurso(Element document)
    {
        super();
        readAdvertisement((TextElement)document);
    }

    public Document getDocument(MimeMediaType asMimeType) throws IllegalArgumentException
    {
        if((null!= getPipeAdv()))
        {
            StructuredDocument document= (StructuredDocument) StructuredDocumentFactory.newStructuredDocument(asMimeType, getAdvertisementType());
            Element element;
            
            System.out.println("getDocument:" + getNombreFoto( ));
            
            element= document.createElement(NOMBRE_FOTO, getNombreFoto( ));
            document.appendChild(element);

            element= document.createElement(TAG, getTags( ));
            document.appendChild(element);

            PipeAdvertisement pipe = getPipeAdv();
            
            if(pipe!=null)
            {
                StructuredTextDocument advDoc = (StructuredTextDocument) pipe.getDocument(asMimeType);
                StructuredDocumentUtils.copyElements(document, document, advDoc);
            }
            return document;
        }
        else
        {
            throw new IllegalArgumentException("Faltan datos para hacer el advert");
        }
    }

    public void readAdvertisement(TextElement document)throws IllegalArgumentException
    {
        if(document.getName().equals(getAdvertisementType()))
        {
            Enumeration elements= document.getChildren();

            while(elements.hasMoreElements())
            {
                TextElement element= (TextElement) elements.nextElement();

                if(element.getName().equals(PipeAdvertisement.getAdvertisementType()))
                {
                    PipeAdvertisement pipe = (PipeAdvertisement) AdvertisementFactory.newAdvertisement(element);
                    setPipeAdv(pipe);
                    continue;
                }
                System.out.println("READ ADV");
                
                if(element.getName().equals(NOMBRE_FOTO))
                {
                    System.out.println("READ ADV:" + element.getTextValue( ));
                    setNombreFoto( element.getTextValue() );
                    continue;
                }
                
                if(element.getName().equals(TAG))
                {
                    setTags( element.getTextValue( ) );
                    continue;
                }
            }
        }
        else{
            throw new IllegalArgumentException("No Corresponde con el tipo de advertisement esperado");
        }
    }

    public String toString()
    {
        try
        {
            StringWriter out = new StringWriter();
            StructuredTextDocument doc = (StructuredTextDocument) getDocument(new MimeMediaType(MIME_TYPE));
            doc.sendToWriter(out);
            return out.toString();
        } 
        catch (IOException ex) 
        {
            System.out.println("====== ERROR ======");
            System.out.println(ex.getMessage());
            return "";
        }
    }
    public MyAdvertisementRecurso() 
    {
       super();
    }

    @Override
    public String[] getIndexFields() 
    {
        return fields;
    }

    public static class Instantiator implements AdvertisementFactory.Instantiator
    {

        public String getAdvertisementType() 
        {
           return AdvertisementRecurso.getAdvertisementType();
        }

        public Advertisement newInstance() 
        {
            return new MyAdvertisementRecurso();
        }

        public Advertisement newInstance(Element root) 
        {
            return new MyAdvertisementRecurso(root);
        }

    };
}
