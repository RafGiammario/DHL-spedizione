package org.engim.tss2018.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.engim.tss2018.PM;
import org.engim.tss2018.db.TariffaCorriere;
import org.engim.tss2018.db.Voce;
import org.engim.tss2018.db.Ordine;

public class MerceSpedizioneDAO
{
 
 public static float pesoTotale (Ordine o) {
   Float result = new Float("0.0f");
   EntityManager db = PM.db();
   try
    {
      Ordine reloaded = db.find(Ordine.class, o.getId());
      Collection<Voce> voci = reloaded.getVoceCollection();
      
      for (Voce v : voci) {
         int quntita = v.getQuantita();
         float pesomerce = v.getIdArticolo().getPeso();
         result += pesomerce*quntita;
      }
      
      return result;
    }
    finally
    {
      db.close();
    }
 }

    public static TariffaCorriere costoMinimo(Ordine o) {
      
        List<TariffaCorriere> cmt;
        EntityManager db = PM.db();
        Float pesoMerce = pesoTotale(o);
        
        try
         { 
           Float pm;
           
           cmt = db.createNamedQuery("TariffaCorriere.findAllOrder", TariffaCorriere.class).getResultList();
           for (int i = 0; i < cmt.size(); i++) {
               TariffaCorriere tc = cmt.get(i);              
               pm = tc.getPesoMassimo();              

               if (pesoMerce < pm) {
                   return tc;                    
               }
           }             
           return null;
         }
         finally
         {
           db.close();
         }        
    } 
}
