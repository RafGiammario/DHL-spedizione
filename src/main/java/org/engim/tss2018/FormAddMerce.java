package org.engim.tss2018;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.engim.tss2018.dao.GenericoDAO;
import org.engim.tss2018.db.Merce;

public class FormAddMerce extends Form<Merce>
{
  private Merce m;
  
  public FormAddMerce(String id)
  {
    super(id);
    add(new FeedbackPanel("feedback"));
        
    TextField codice = new TextField("codice");
    codice.add(StringValidator.minimumLength(1)).setRequired(true);
    add(codice);
    
    TextField descrizione = new TextField("descrizione");
    descrizione.add(StringValidator.minimumLength(1)).setRequired(true);
    add(descrizione);
    
    TextField<Float> peso = new TextField("peso");
    peso.setRequired(true);
    add(peso);
    
  }

  @Override
  protected void onBeforeRender() {
    m = new Merce();
    
    setDefaultModel(
      new CompoundPropertyModel<Merce>(m));
   
    super.onBeforeRender();
  }

  @Override
  protected void onSubmit()
  {
    GenericoDAO.add(m);
    m.setId(null);
  }
   
}
