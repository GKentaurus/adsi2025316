/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.adsi2025316;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_usuarioid")
    private Integer usuUsuarioid;
    @Size(max = 45)
    @Column(name = "usu_tipodocumento")
    private String usuTipodocumento;
    @Size(max = 45)
    @Column(name = "usu_documento")
    private String usuDocumento;
    @Size(max = 45)
    @Column(name = "usu_nombre")
    private String usuNombre;
    @Size(max = 45)
    @Column(name = "usu_apellido")
    private String usuApellido;
    @Size(max = 45)
    @Column(name = "usu_correo")
    private String usuCorreo;
    @Column(name = "usu_movil")
    private Integer usuMovil;
    @Size(max = 45)
    @Column(name = "usu_clave")
    private String usuClave;
    @Column(name = "usu_estado")
    private Short usuEstado;
    @JoinTable(name = "tbl_usuario_has_tbl_rol", joinColumns = {
        @JoinColumn(name = "usu_usuario_id", referencedColumnName = "usu_usuarioid")}, inverseJoinColumns = {
        @JoinColumn(name = "rol_rol_id", referencedColumnName = "ro_rolid")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Rol> rolCollection;

    public Usuario() {
    }

    public Usuario(Integer usuUsuarioid) {
        this.usuUsuarioid = usuUsuarioid;
    }

    public Integer getUsuUsuarioid() {
        return usuUsuarioid;
    }

    public void setUsuUsuarioid(Integer usuUsuarioid) {
        this.usuUsuarioid = usuUsuarioid;
    }

    public String getUsuTipodocumento() {
        return usuTipodocumento;
    }

    public void setUsuTipodocumento(String usuTipodocumento) {
        this.usuTipodocumento = usuTipodocumento;
    }

    public String getUsuDocumento() {
        return usuDocumento;
    }

    public void setUsuDocumento(String usuDocumento) {
        this.usuDocumento = usuDocumento;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuApellido() {
        return usuApellido;
    }

    public void setUsuApellido(String usuApellido) {
        this.usuApellido = usuApellido;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public Integer getUsuMovil() {
        return usuMovil;
    }

    public void setUsuMovil(Integer usuMovil) {
        this.usuMovil = usuMovil;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public Short getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(Short usuEstado) {
        this.usuEstado = usuEstado;
    }

    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuUsuarioid != null ? usuUsuarioid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuUsuarioid == null && other.usuUsuarioid != null) || (this.usuUsuarioid != null && !this.usuUsuarioid.equals(other.usuUsuarioid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.adsi2025316.Usuario[ usuUsuarioid=" + usuUsuarioid + " ]";
    }
    
}
