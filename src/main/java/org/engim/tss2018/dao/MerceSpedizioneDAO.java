package org.engim.tss2018.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import org.engim.tss2018.PM;
import org.engim.tss2018.db.CostoMezzoTrasporto;
import org.engim.tss2018.db.MerceSpedizione;
import org.engim.tss2018.db.Spedizione;

public class MerceSpedizioneDAO
{
 
 public static float pesoTotale (Spedizione s) {
   Float result = new Float("0.0f");
   EntityManager db = PM.db();
   try
    {
      Spedizione reloaded = db.find(Spedizione.class, s.getId());
      Collection<MerceSpedizione> mercispedite = reloaded.getMerceSpedizioneCollection();
      
      for (MerceSpedizione mercespedita : mercispedite) {
         int quntita = mercespedita.getQuantita();
         float pesomerce = mercespedita.getIdMerce().getPeso();
         result += pesomerce*quntita;
      }
      
      return result;
    }
    finally
    {
      db.close();
    }
 }

    public static String costoMinimo(Spedizione s) {
      
        List<CostoMezzoTrasporto> cmt;
        EntityManager db = PM.db();
        Float pesoMerce = pesoTotale(s);
        
        try
         { 
           String nm;
           Float pm;
           Float cm;
           
           cmt = db.createQuery("SELECT c FROM CostoMezzoTrasporto c ORDER BY c.pesoMassimo ASC", CostoMezzoTrasporto.class).getResultList();
           for (int i = 0; i < cmt.size(); i++) {
               CostoMezzoTrasporto mdt = cmt.get(i);
               nm = mdt.getNomeMezzo();
               pm = mdt.getPesoMassimo();
               cm = mdt.getCosto();

               if (pesoMerce < pm) {
                   return "€" + String.format("%.2f", cm) + "(" + nm + ")";                     
               }
           }
             
           return "Non è possibile spedire tutto insieme";
         }
         finally
         {
           db.close();
         }        
    }
 
}
