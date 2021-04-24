/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.adsi2025316;

import edu.sena.adsi2025316.Rol;
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
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "edu.sena_adsi2025316_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
 
    
    @Override
    public List<Rol> usuarioNoRoles( int id_usuario){
        try {
         String consulta = "SELECT * FROM tbl_rol WHERE tbl_rol.ro_rolid NOT IN ("+
                 " SELECT tbl_rol.ro_rolid  FROM tbl_rol RIGHT JOIN tbl_usuario_has_tbl_rol"+
                 " ON tbl_rol.ro_rolid = tbl_usuario_has_tbl_rol.rol_rol_id"+
                 " WHERE tbl_usuario_has_tbl_rol.usu_usuario_id = "+id_usuario+")";
            Query qt = em.createNativeQuery(consulta, Rol.class);
            return qt.getResultList();
        } catch (Exception e) {
           return null;
        }
    }
    
    
    @Override
    public boolean addRol(int id_usuario, int id_rol){
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_usuario_has_tbl_rol (usu_usuario_id,rol_rol_id) VALUES (?,?)");
            q.setParameter(1, id_usuario);
            q.setParameter(2, id_rol);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }
    
    
    @Override
     public boolean removerRol(int id_usuario, int id_rol){
        try {
            Query q = em.createNativeQuery("DELETE FROM tbl_usuario_has_tbl_rol WHERE (usu_usuario_id = ?) AND (rol_rol_id = ?)");
            q.setParameter(1, id_usuario);
            q.setParameter(2, id_rol);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }
    
    
}
