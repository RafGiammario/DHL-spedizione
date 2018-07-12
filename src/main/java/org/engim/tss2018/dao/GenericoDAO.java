package org.engim.tss2018.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.engim.tss2018.PM;
import org.engim.tss2018.db.ChiavePrimaria;

public class GenericoDAO
{
 
 public static void add(ChiavePrimaria p){
    
    EntityManager em = PM.db();
    EntityTransaction et = em.getTransaction();
    try {
      et.begin();
      em.persist(p);
      et.commit();
    } 
    finally {
      if (et.isActive())
        et.rollback();
      em.close();
    }
  }
  
  public static void update(ChiavePrimaria p){
    
    EntityManager em = PM.db();
    EntityTransaction et = em.getTransaction();
    try {
      et.begin();
      em.merge(p);
      et.commit();
    } 
    finally {
      if (et.isActive()) 
        et.rollback();
      em.close();
    }
  }  
  
   public static void delete(ChiavePrimaria p){
    
    EntityManager em = PM.db();
    EntityTransaction et = em.getTransaction();
    
    try {
      p = em.find(p.getClass(), p.getId());
      et.begin();
      em.remove(p);
      et.commit();
    } 
    finally {
      if (et.isActive()) et.rollback();
      em.close();
    }
  }
}
