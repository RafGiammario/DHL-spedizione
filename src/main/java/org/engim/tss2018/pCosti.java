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
import org.engim.tss2018.db.CostoMezzoTrasporto;



public class pCosti extends PaginaConMenu{
  public pCosti(){

    List<IColumn<CostoMezzoTrasporto, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<CostoMezzoTrasporto, String> id =
      new PropertyColumn<>(Model.of("ID"), 
                            "id");
    colonne.add(id);
    
     PropertyColumn<CostoMezzoTrasporto, String> nome_mezzo =
      new PropertyColumn<>(Model.of("Nome mezzo"), 
                            "nomeMezzo");
    colonne.add(nome_mezzo);
    
    PropertyColumn<CostoMezzoTrasporto, String> peso_massimo =
      new PropertyColumn<>(Model.of("Peso massimo"), 
                            "pesoMassimo");
    colonne.add(peso_massimo);
    
    PropertyColumn<CostoMezzoTrasporto, String> costo =
      new PropertyColumn<>(Model.of("Costo"), 
                            "costo");
    colonne.add(costo);
    
    AbstractColumn<CostoMezzoTrasporto, String> azioni =
      new AbstractColumn<CostoMezzoTrasporto, String>(
        Model.of("Azioni"))
      {
        @Override
        public void populateItem(
          Item<ICellPopulator<CostoMezzoTrasporto>> item, 
          String wicketid, IModel<CostoMezzoTrasporto> imodel)
        {
          item.add(new AzioniPanel(wicketid, 
            imodel.getObject()));          
        }
      };
    colonne.add(azioni);
    
       
    SPDataProvider<CostoMezzoTrasporto> dp = 
            new SPDataProvider<>(CostoMezzoTrasporto.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("elencocosti",
                           colonne,
                           dp,
                           5);
    add(tab);
    
    add(new FormAddCosto("addcosto"));
  }
  
}
