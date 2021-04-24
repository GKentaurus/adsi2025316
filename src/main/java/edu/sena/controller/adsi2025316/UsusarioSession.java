/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.adsi2025316;

import edu.sena.adsi2025316.Rol;
import edu.sena.adsi2025316.Usuario;
import edu.sena.facade.adsi2025316.RolFacadeLocal;
import edu.sena.facade.adsi2025316.UsuarioFacadeLocal;
import edu.sena.utilidades.adsi2025316.Email;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Josarta
 */
@Named(value = "ususarioSession")
@SessionScoped
public class UsusarioSession implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    RolFacadeLocal rolFacadeLocal;

    private Usuario usuNuevo = new Usuario();
    private Usuario usuLog = new Usuario();
    private Usuario usuEditar = new Usuario();

    private String mesError = "";
    private String correoIn = "";
    private String claveIn = "";

    /**
     * Creates a new instance of UsusarioSession
     */
    public UsusarioSession() {
    }

    public void crearUsuario() {
        mesError = "";
        try {
            usuNuevo.setUsuEstado(Short.parseShort("1"));
            usuarioFacadeLocal.create(usuNuevo);
            mesError = "m2";
        } catch (Exception e) {
            mesError = "m1";
        }
        usuNuevo = new Usuario();
    }

    public void actualizarMisDatos() {
        mesError = "";
        try {
            usuarioFacadeLocal.edit(usuLog);
            mesError = "m2";
        } catch (Exception e) {
            mesError = "m1";
        }
    }

    public void actualizarDatosUsuario() {
        mesError = "";
        try {
            usuarioFacadeLocal.edit(usuEditar);
            mesError = "m2";
        } catch (Exception e) {
            mesError = "m1";
        }
    }

    public List<Usuario> todosUsuarios() {
        return usuarioFacadeLocal.findAll();
    }

    public void validarUsuario() {
        try {
            usuLog = usuarioFacadeLocal.validarUsuario(correoIn, claveIn);
            if (usuLog != null) {
                if (usuLog.getUsuEstado().toString().equalsIgnoreCase("1")) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("usuario/index.xhtml");
                } else {
                    mesError = "mAdmin";
                }

            } else {
                mesError = "mBad";
            }
        } catch (Exception e) {
            mesError = "mError";
        }
    }

    public void editarUsuario(Usuario usuEditar) {
        this.usuEditar = usuEditar;
    }

    public List<Rol> usuarioNoRoles() {
        try {
            return rolFacadeLocal.usuarioNoRoles(usuEditar.getUsuUsuarioid());
        } catch (Exception e) {
            return null;
        }
    }

    public void removerRol(int id_rol) {
        try {
            boolean salida = rolFacadeLocal.removerRol(usuEditar.getUsuUsuarioid(), id_rol);
        } catch (Exception e) {
            System.out.println("edu.sena.controller.adsi2025316.UsusarioSession.removerRol() " + e.getMessage());
        }
        usuEditar = usuarioFacadeLocal.leerUsuario(usuEditar.getUsuUsuarioid());

    }

    public void adicionarRol(int id_rol) {
        try {
            boolean salida = rolFacadeLocal.addRol(usuEditar.getUsuUsuarioid(), id_rol);
        } catch (Exception e) {
            System.out.println("edu.sena.controller.adsi2025316.UsusarioSession.removerAdicionarRol()" + e.getMessage());
        }
        usuEditar = usuarioFacadeLocal.leerUsuario(usuEditar.getUsuUsuarioid());
    }

    public void cambiarEstado(Usuario usuEstado) {

        if (usuEstado.getUsuEstado() == 1) {
            usuEstado.setUsuEstado(Short.parseShort("0"));
        } else {
            usuEstado.setUsuEstado(Short.parseShort("1"));
        }
        usuarioFacadeLocal.edit(usuEstado);
    }

    public void recordarClave() {
        Usuario usuClave = usuarioFacadeLocal.recordarClave(correoIn);
        if (usuClave != null && !usuClave.getUsuNombre().isEmpty()) {
            Email.recuperarClaves(correoIn, usuClave.getUsuClave());
            mesError = "ok";
        } else {
            mesError = "bad";
        }

    }

    public Usuario getUsuNuevo() {
        return usuNuevo;
    }

    public void setUsuNuevo(Usuario usuNuevo) {
        this.usuNuevo = usuNuevo;
    }

    public String getMesError() {
        return mesError;
    }

    public void setMesError(String mesError) {
        this.mesError = mesError;

    }

    public String getClaveIn() {
        return claveIn;
    }

    public void setClaveIn(String claveIn) {
        this.claveIn = claveIn;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public Usuario getUsuLog() {
        return usuLog;
    }

    public void setUsuLog(Usuario usuLog) {
        this.usuLog = usuLog;
    }

    public Usuario getUsuEditar() {
        return usuEditar;
    }

    public void setUsuEditar(Usuario usuEditar) {
        this.usuEditar = usuEditar;
    }

}
