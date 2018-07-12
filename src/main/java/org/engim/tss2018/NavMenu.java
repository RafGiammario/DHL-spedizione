package org.engim.tss2018;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class NavMenu extends Panel
{
  public NavMenu(String id)
  {
    super(id);
    
    BookmarkablePageLink home = 
      new BookmarkablePageLink("home", 
        HomePage.class);
    add(home);
    
    BookmarkablePageLink merce = 
      new BookmarkablePageLink("merce", 
        pMerce.class);
    add(merce);

    BookmarkablePageLink costi = 
      new BookmarkablePageLink("costi", 
        pCosti.class);
    add(costi);
    
     BookmarkablePageLink spedizioni = 
      new BookmarkablePageLink("spedizioni", 
        pSpedizioni.class);
    add(spedizioni);
    
    
    //Aggiungere una classe di stile ad un oggetto
    //person.add(new AttributeModifier("class, "min-class.css));
    //add(persone);
  }
  
}
