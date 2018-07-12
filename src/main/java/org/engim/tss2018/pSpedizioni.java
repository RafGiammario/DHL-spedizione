package org.engim.tss2018;

import java.util.LinkedList;
import java.util.List;
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
import org.engim.tss2018.db.Spedizione;

public class pSpedizioni extends PaginaConMenu{
  public pSpedizioni(){

    List<IColumn<Spedizione, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<Spedizione, String> id =
      new PropertyColumn<>(Model.of("ID"), 
                            "id");
    colonne.add(id);
    
     PropertyColumn<Spedizione, String> num =
      new PropertyColumn<>(Model.of("Numero"), 
                            "numero");
    colonne.add(num);
    
    PropertyColumn<Spedizione, String> data =
      new PropertyColumn<>(Model.of("Data"), 
                            "data");
    colonne.add(data);
    
    AbstractColumn<Spedizione, String> pesoTot = 
           new AbstractColumn<Spedizione, String>(Model.of("Peso Totale")) //essendo una classe astratta
           {
              @Override
              public void populateItem(Item<ICellPopulator<Spedizione>> item, 
                      String wicketid, IModel<Spedizione> imodel)
              {
                String pesotot = String.format("%.2f", MerceSpedizioneDAO.pesoTotale(imodel.getObject()));
                   
                Label pt = new Label(wicketid, "" + pesotot);
                item.add(pt);
              }
           };
    colonne.add(pesoTot);
    
    AbstractColumn<Spedizione, String> costoMin = 
           new AbstractColumn<Spedizione, String>(Model.of("Costo più basso")) //essendo una classe astratta
           {
              @Override
              public void populateItem(Item<ICellPopulator<Spedizione>> item, 
                      String wicketid, IModel<Spedizione> imodel)
              {
                String costomin = MerceSpedizioneDAO.costoMinimo(imodel.getObject());
                   
                Label cm = new Label(wicketid, "" + costomin);
                item.add(cm);
              }
           };
    colonne.add(costoMin);
    
    AbstractColumn<Spedizione, String> azioni =
      new AbstractColumn<Spedizione, String>(
        Model.of("Azioni"))
      {
        @Override
        public void populateItem(
          Item<ICellPopulator<Spedizione>> item, 
          String wicketid, IModel<Spedizione> imodel)
        {
          item.add(new AzioniPanel(wicketid, 
            imodel.getObject()));          
        }
      };
    colonne.add(azioni);
    
    SPDataProvider<Spedizione> dp = 
            new SPDataProvider<>(Spedizione.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("elencospedizioni",
                           colonne,
                           dp,
                           5);
    add(tab);
    
    add(new FormAddSpedizione("addspedizione"));
    add(new FeedbackPanel("feedback"));
  }
}
