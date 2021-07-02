package pe.com.hiper.bmatic.perfilagendamientows.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hiper.bmatic.perfilagendamientows.domain.authorization.model.Authorization;
import pe.com.hiper.bmatic.perfilagendamientows.domain.authorization.repository.AuthorizationRepository;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthorizationRepository repository;

    @Override
    public Authorization getAuthorization(String module, String process, String user) {
        return this.repository.getAuthorization(module, process, user);
    }

}