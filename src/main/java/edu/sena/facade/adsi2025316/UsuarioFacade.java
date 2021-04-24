/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.adsi2025316;

import edu.sena.adsi2025316.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Josarta
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "edu.sena_adsi2025316_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    public Usuario validarUsuario(String correoIn, String claveIn){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.usuClave = :p1 AND u.usuCorreo = :p2");
            qt.setParameter("p1", claveIn);
            qt.setParameter("p2", correoIn);
            List<Usuario> listaUsuarios = qt.getResultList();
            
            if(listaUsuarios.isEmpty()){
                return null;
            }else{
                return  listaUsuarios.get(0);
            }            
        } catch (Exception e) {
            return null;
        }
    }
    
    
    @Override
     public Usuario leerUsuario(int id_ususario){
         try {
             em.getEntityManagerFactory().getCache().evictAll();
             Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuUsuarioid = :id_ususario");
             q.setParameter("id_ususario",  id_ususario);
             return  (Usuario) q.getSingleResult();
         } catch (Exception e) {
         return null;
         }         
     }
     
     @Override
     public Usuario recordarClave(String correoIn){
         try {
           Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuCorreo = :correoIn");
           q.setParameter("correoIn",  correoIn);
           return  (Usuario) q.getSingleResult();
         } catch (Exception e) {
             return null;
         }         
     
     }
     
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
    
}
