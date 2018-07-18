package org.engim.tss2018;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.engim.tss2018.dao.MerceSpedizioneDAO;
import org.engim.tss2018.db.Ordine;
import org.engim.tss2018.db.TariffaCorriere;

public class pOrdine extends PaginaConMenu{
  public pOrdine(){
    
    NavMenu.getOrdine().add(AttributeModifier.append("class", "active"));

    List<IColumn<Ordine, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<Ordine, String> id =
      new PropertyColumn<>(Model.of("#"), 
                            "id");
    colonne.add(id);
    
     PropertyColumn<Ordine, String> num =
      new PropertyColumn<>(Model.of("Numero"), 
                            "numero");
    colonne.add(num);
    
    PropertyColumn<Ordine, String> data =
      new PropertyColumn<>(Model.of("Data"), 
                            "data");
    colonne.add(data);
    
//    AbstractColumn<Ordine, String> pesoTot = 
//           new AbstractColumn<Ordine, String>(Model.of("Peso Totale")) //essendo una classe astratta
//           {
//              @Override
//              public void populateItem(Item<ICellPopulator<Ordine>> item, 
//                      String wicketid, IModel<Ordine> imodel)
//              {
//                String pesotot = String.format("%.2f", MerceSpedizioneDAO.pesoTotale(imodel.getObject()));
//                   
//                Label pt = new Label(wicketid, "" + pesotot);
//                item.add(pt);
//              }
//           };
//    colonne.add(pesoTot);
    
    AbstractColumn<Ordine, String> costoMin = 
           new AbstractColumn<Ordine, String>(Model.of("Tariffa migliore")) //essendo una classe astratta
           {
              @Override
              public void populateItem(Item<ICellPopulator<Ordine>> item, 
                      String wicketid, IModel<Ordine> rowModel)
              {
                TariffaCorriere cmt = MerceSpedizioneDAO.costoMinimo( rowModel.getObject());
                String costomin = new String();
                if (cmt == null) {
                  costomin = "Inspedibile";
                } else {
                  costomin = "€" + cmt.getCosto() + " (" + cmt.getNomeCorriere() + " " + cmt.getNomeTariffa() + ")";
                }                   
                Label cm = new Label(wicketid, costomin);
                item.add(cm);
              }
           };
    colonne.add(costoMin);
    
    AbstractColumn<Ordine, String> azioni =
      new AbstractColumn<Ordine, String>(
        Model.of("Azioni"))
      {
        @Override
        public void populateItem(
          Item<ICellPopulator<Ordine>> item, 
          String wicketid, IModel<Ordine> imodel)
        {
          item.add(new AzioniPanel(wicketid, 
            imodel.getObject()));          
        }
      };
    colonne.add(azioni);
    
    SPDataProvider<Ordine> dp = 
            new SPDataProvider<>(Ordine.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("elencospedizioni",
                           colonne,
                           dp,
                           10);
    add(tab);
    
//    add(new FormAddOrdine("addspedizione"));
//    add(new FeedbackPanel("feedback"));
  }
}
