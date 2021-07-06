package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.security;

import java.util.Map;

import org.springframework.stereotype.Component;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.model.Authorization;

@Component
public class AuthorizationRowMapper {

    public Authorization mapRowAuthorization(Map<String, Object> rows) {
        Authorization authorization;
        String module = rows.get("CPRPROCESO").toString();
        boolean delete = rows.get("DPRELIMINACION") == null ? false : rows.get("DPRELIMINACION").toString().equals("1");
        boolean update = rows.get("DPRMODIFICACION") == null ? false
                : rows.get("DPRMODIFICACION").toString().equals("1");
        boolean insert = rows.get("DPRCREACION") == null ? false : rows.get("DPRCREACION").toString().equals("1");
        authorization = new Authorization(module, delete, update, insert);
        return authorization;
    }
}
