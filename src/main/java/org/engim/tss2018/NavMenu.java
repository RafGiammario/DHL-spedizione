package org.engim.tss2018;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class NavMenu extends Panel
{
  private static BookmarkablePageLink home;
  private static BookmarkablePageLink articolo;
  private static BookmarkablePageLink tariffa;
  private static BookmarkablePageLink ordine;
  
  public NavMenu(String id)
  {
    super(id);
    
    home = 
      new BookmarkablePageLink("home", 
        HomePage.class);
    add(home);
    
    
    articolo = 
      new BookmarkablePageLink("articolo", 
        pArticolo.class);
    add(articolo);

    tariffa = 
      new BookmarkablePageLink("tariffa", 
        pTariffa.class);
    add(tariffa);
    
    ordine = 
      new BookmarkablePageLink("ordine", 
        pOrdine.class);
    add(ordine);
    
    
    //Aggiungere una classe di stile ad un oggetto
    //person.add(new AttributeModifier("class, "min-class.css));
    //add(persone);
  }

  public static BookmarkablePageLink getHome() {
    return home;
  }
  
  public static BookmarkablePageLink getArticolo() {
    return articolo;
  }

  public static BookmarkablePageLink getTariffa() {
    return tariffa;
  }

  public static BookmarkablePageLink getOrdine() {
    return ordine;
  }
}
