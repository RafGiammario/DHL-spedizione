package org.engim.tss2018;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.engim.tss2018.db.Merce;

public class pMerce extends PaginaConMenu {
  public pMerce(){

    List<IColumn<Merce, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<Merce, String> id =
      new PropertyColumn<>(Model.of("ID"), 
                            "id");
    colonne.add(id);
    
     PropertyColumn<Merce, String> cod =
      new PropertyColumn<>(Model.of("Codice"), 
                            "codice");
    colonne.add(cod);
    
    PropertyColumn<Merce, String> desc =
      new PropertyColumn<>(Model.of("Descrizione"), 
                            "descrizione");
    colonne.add(desc);
    
    PropertyColumn<Merce, String> peso =
      new PropertyColumn<>(Model.of("Peso"), 
                            "peso");
    colonne.add(peso);
    
    AbstractColumn<Merce, String> azioni =
      new AbstractColumn<Merce, String>(
        Model.of("Azioni"))
      {
        @Override
        public void populateItem(
          Item<ICellPopulator<Merce>> item, 
          String wicketid, IModel<Merce> imodel)
        {
          item.add(new AzioniPanel(wicketid, 
            imodel.getObject()));          
        }
      };
    colonne.add(azioni);
       
    SPDataProvider<Merce> dp = 
            new SPDataProvider<>(Merce.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("elencomerci",
                           colonne,
                           dp,
                           5);
    add(tab);
    
    add(new FormAddMerce("addmerce"));
  }
}
