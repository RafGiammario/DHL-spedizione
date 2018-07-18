package org.engim.tss2018;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.engim.tss2018.db.Articolo;

public class pArticolo extends PaginaConMenu {
  public pArticolo(){
    
    NavMenu.getArticolo().add(AttributeModifier.append("class", "active"));

    List<IColumn<Articolo, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<Articolo, String> id =
      new PropertyColumn<>(Model.of("#"), 
                            "id");
    colonne.add(id);
    
     PropertyColumn<Articolo, String> cod =
      new PropertyColumn<>(Model.of("Codice"), 
                            "codice");
    colonne.add(cod);
    
    PropertyColumn<Articolo, String> desc =
      new PropertyColumn<>(Model.of("Descrizione"), 
                            "descrizione");
    colonne.add(desc);
    
    PropertyColumn<Articolo, String> peso =
      new PropertyColumn<>(Model.of("Peso (kg)"), 
                            "peso");
    colonne.add(peso);
    
    AbstractColumn<Articolo, String> azioni =
      new AbstractColumn<Articolo, String>(
        Model.of("Azioni"))
      {
        @Override
        public void populateItem(
          Item<ICellPopulator<Articolo>> item, 
          String wicketid, IModel<Articolo> imodel)
        {
          item.add(new AzioniPanel(wicketid, 
            imodel.getObject()));          
        }
      };
    colonne.add(azioni);
       
    SPDataProvider<Articolo> dp = 
            new SPDataProvider<>(Articolo.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("elencomerci",
                           colonne,
                           dp,
                           10);
    add(tab);
    
//    add(new FormAddArticolo("addmerce"));
  }
}
