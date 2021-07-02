package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pe.com.hiper.bmatic.perfilagendamientows.application.security.SecurityService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.authorization.model.Authorization;
import pe.com.hiper.hcenter.datossesion.entidad.UsuarioRest;
import javax.servlet.http.HttpSession;

@RestController
public class SecurityApiController implements SecurityApi{

    @Autowired
    HttpSession httpSession;

    @Autowired
    SecurityService securityService;

    public ResponseEntity<Authorization> getAuthorization(String module, String submodule) {
        UsuarioRest user = (UsuarioRest) httpSession.getAttribute("usuarioActualControl");
        Authorization authorization = securityService.getAuthorization(module, submodule, user.getCodigo());
        return ResponseEntity.ok(mapAuthorization(authorization));
    }

    private Authorization mapAuthorization(final Authorization authorization) {
        return new Authorization(authorization);
    }
}
