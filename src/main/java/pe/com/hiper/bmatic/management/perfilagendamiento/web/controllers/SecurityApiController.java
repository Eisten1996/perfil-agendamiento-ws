package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pe.com.hiper.bmatic.management.perfilagendamiento.application.security.SecurityService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.model.Authorization;
import pe.com.hiper.hcenter.datossesion.entidad.UsuarioRest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/v1/security")
public class SecurityApiController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    SecurityService securityService;

    @GetMapping(value = "/authorization")
    public ResponseEntity<Authorization> getAuthorization(@RequestParam(value = "module") String module,
            @RequestParam(value = "submodule") String submodule) {

        UsuarioRest user = (UsuarioRest) httpSession.getAttribute("usuarioActualControl");
        Authorization authorization = securityService.getAuthorization(module, submodule, user.getCodigo());
        return ResponseEntity.ok(mapAuthorization(authorization));
    }

    private Authorization mapAuthorization(final Authorization authorization) {
        return new Authorization(authorization);
    }
}
