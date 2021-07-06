package pe.com.hiper.bmatic.management.perfilagendamiento.application.security;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.model.Authorization;

public interface SecurityService {
    Authorization getAuthorization(String module, String process, String user);
}
