package pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.repository;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.model.Authorization;

public interface AuthorizationRepository {

    Authorization getAuthorization(String module, String process, String user);
}
