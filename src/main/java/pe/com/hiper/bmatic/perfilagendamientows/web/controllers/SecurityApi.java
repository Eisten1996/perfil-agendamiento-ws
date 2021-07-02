package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.hiper.bmatic.perfilagendamientows.domain.authorization.model.Authorization;

@RequestMapping("/v1/security")
public interface SecurityApi {
    @GetMapping(value = "/authorization")
    public ResponseEntity<Authorization> getAuthorization(@RequestParam(value = "module") String module,
            @RequestParam(value = "submodule") String submodule);
}
