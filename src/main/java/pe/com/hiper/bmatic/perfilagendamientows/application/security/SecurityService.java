package pe.com.hiper.bmatic.perfilagendamientows.application.security;

import pe.com.hiper.bmatic.perfilagendamientows.domain.authorization.model.Authorization;

public interface SecurityService {
    Authorization getAuthorization(String module, String process, String user);
}
