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
import org.engim.tss2018.db.TariffaCorriere;



public class pTariffa extends PaginaConMenu{
  public pTariffa(){
    
    NavMenu.getTariffa().add(AttributeModifier.append("class", "active"));

    List<IColumn<TariffaCorriere, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<TariffaCorriere, String> id =
      new PropertyColumn<>(Model.of("#"), 
                            "id");
    colonne.add(id);
    
    PropertyColumn<TariffaCorriere, String> nome_corriere =
      new PropertyColumn<>(Model.of("Nome corriere"), 
                            "nomeCorriere");
    colonne.add(nome_corriere);
    
    PropertyColumn<TariffaCorriere, String> nome_tariffa =
      new PropertyColumn<>(Model.of("Nome tariffa"), 
                            "nomeTariffa");
    colonne.add(nome_tariffa);
    
    PropertyColumn<TariffaCorriere, String> peso_massimo =
      new PropertyColumn<>(Model.of("Peso massimo"), 
                            "pesoMassimo");
    colonne.add(peso_massimo);
    
    PropertyColumn<TariffaCorriere, String> costo =
      new PropertyColumn<>(Model.of("Costo"), 
                            "costo");
    colonne.add(costo);
    
    AbstractColumn<TariffaCorriere, String> azioni =
      new AbstractColumn<TariffaCorriere, String>(
        Model.of("Azioni"))
      {
        @Override
        public void populateItem(
          Item<ICellPopulator<TariffaCorriere>> item, 
          String wicketid, IModel<TariffaCorriere> imodel)
        {
          item.add(new AzioniPanel(wicketid, 
            imodel.getObject()));          
        }
      };
    colonne.add(azioni);
    
       
    SPDataProvider<TariffaCorriere> dp = 
            new SPDataProvider<>(TariffaCorriere.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("elencocosti",
                           colonne,
                           dp,
                           10);
    add(tab);
    
//    add(new FormAddCosto("addcosto"));
  }
  
}
