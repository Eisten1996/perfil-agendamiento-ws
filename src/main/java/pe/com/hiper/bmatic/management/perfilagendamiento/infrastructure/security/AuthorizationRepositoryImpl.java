package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.security;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.model.Authorization;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.authorization.repository.AuthorizationRepository;

@Component
public class AuthorizationRepositoryImpl implements AuthorizationRepository {

        @Autowired
        private JdbcTemplate jdbcTempleSecurity;

        @Autowired
        private AuthorizationRowMapper authorizationRowMapper;

        @Override
        public Authorization getAuthorization(String module, String process, String user) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT CPRPROCESO,").append("CPRMODULO,").append("DPRCREACION,").append("DPRMODIFICACION,")
                                .append("DPRELIMINACION ").append("FROM TAPERFILROL PR ")
                                .append("INNER JOIN TAUSUARIOROL UR ON UR.CURROL = PR.CPRROL ")
                                .append("INNER JOIN TMUSUARIO U ON U.CUSUARIO = UR.CURUSUARIO ")
                                .append("WHERE PR.CPRMODULO =?  AND PR.CPRPROCESO = ? AND U.CUSUARIO=?");

                List<Map<String, Object>> rows = jdbcTempleSecurity.queryForList(sql.toString(),
                                new Object[] { module, process, user });
                return rows.isEmpty() ? new Authorization("", false, false, false)
                                : authorizationRowMapper.mapRowAuthorization(rows.get(0));
        }

}
