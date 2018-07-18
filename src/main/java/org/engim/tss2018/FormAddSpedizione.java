//package org.engim.tss2018;
//
//import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
//import org.apache.wicket.markup.html.form.Form;
//import org.apache.wicket.markup.html.form.TextField;
//import org.apache.wicket.markup.html.panel.FeedbackPanel;
//import org.apache.wicket.model.CompoundPropertyModel;
//import org.apache.wicket.validation.validator.StringValidator;
//import org.engim.tss2018.dao.GenericoDAO;
//import org.engim.tss2018.db.Spedizione;
//
//public class FormAddSpedizione extends Form<Spedizione>
//{
//  private Spedizione s;
//  
//  public FormAddSpedizione(String id)
//  {
//    super(id);
//       
//    TextField<Integer> numero = new TextField("numero");
//    numero.setRequired(true);
//    add(numero);
//    
//    TextField data = new TextField("data");
//    data.add(StringValidator.lengthBetween(3, 5));
//    data.setRequired(true);
//    add(data);   
//  }
//
//  @Override
//  protected void onBeforeRender() {
//    s = new Spedizione();
//    
//    setDefaultModel(
//      new CompoundPropertyModel<Spedizione>(s));
//    
//    super.onBeforeRender();
//  }
//
//  @Override
//  protected void onSubmit()
//  {    
//    GenericoDAO.add(s);
//    s.setId(null);
//  }
//   
//}
