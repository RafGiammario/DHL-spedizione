package org.engim.tss2018;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.engim.tss2018.dao.GenericoDAO;
import org.engim.tss2018.db.CostoMezzoTrasporto;

public class FormAddCosto extends Form<CostoMezzoTrasporto>
{
  private CostoMezzoTrasporto c;
  
  public FormAddCosto(String id)
  {
    super(id);
    add(new FeedbackPanel("feedback"));
    c = new CostoMezzoTrasporto();
    
    setDefaultModel(
      new CompoundPropertyModel<CostoMezzoTrasporto>(c));
    
    TextField nomeMezzo = new TextField("nomeMezzo");
    nomeMezzo.add(StringValidator.minimumLength(1)).setRequired(true);
    add(nomeMezzo);
    
    TextField<Float> pesoMassimo = new TextField("pesoMassimo");
    pesoMassimo.setRequired(true);
    add(pesoMassimo);
    
    TextField<Float> costo = new TextField("costo");
    costo.setRequired(true);
    add(costo);
    
  }

  @Override
  protected void onBeforeRender() {
    c = new CostoMezzoTrasporto();
    
    setDefaultModel(
      new CompoundPropertyModel<CostoMezzoTrasporto>(c));
    
    super.onBeforeRender();
  }

  @Override
  protected void onSubmit()
  {
    GenericoDAO.add(c);
    c.setId(null);
  }
   
}
