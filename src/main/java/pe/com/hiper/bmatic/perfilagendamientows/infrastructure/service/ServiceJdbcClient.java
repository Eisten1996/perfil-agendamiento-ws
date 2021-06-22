package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;

import java.util.List;

@Component
public class ServiceJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public ServiceJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Service> getServicesByBranch(String branchId) {
        String query = "SELECT DISTINCT TB.cTTBase, TB.dTTBNombre FROM TAUSUPERFIL X"
                + " INNER JOIN tattickperfil TA ON TA.CTTPPERFIL = X.CVPERFIL"
                + " INNER JOIN TMTTICKET TT ON TT.CTTICKET = TA.CTTPTICKET"
                + " INNER JOIN tmtticketbase TB ON TB.CTTBASE = TT.CTTTBASE"
                + " INNER JOIN TAUSUAGENCIA AG ON AG.CUAUSUARIO = X.CUAUSUARIO"
                + " WHERE AG.CUAAGENCIA = ?"
                + " ORDER BY TB.dTTBNombre";

        return jdbcTemplate.query(query, new Object[]{branchId},
                (rs, rowNum) ->
                        Service.builder()
                                .id(rs.getString(1))
                                .name(rs.getString(2))
                                .build()
        );
    }
}
