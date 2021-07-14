package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.shared.TypeSchedule;

import java.util.List;

@Component
public class ServiceJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public ServiceJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Service> getServicesByBranch(String branchId) {
        String query = "SELECT TB.CTTBASE, TB.DTTBNOMBRE FROM TMTTICKETBASE TB "
                + " INNER JOIN TMAGENCIA AG ON AG.CAGGAGENCIA = TB.CTTGAGENCIA " + " WHERE AG.CAGENCIA = ? "
                + " ORDER BY TB.DTTBNOMBRE ";

        return jdbcTemplate.query(query, new Object[] { branchId },
                (rs, rowNum) -> Service.builder().id(rs.getString(1)).name(rs.getString(2)).build());
    }

    public List<Service> getServicesByType(TypeSchedule type, String typeId) {
        String tableType = type == TypeSchedule.COUNTER ? "TMVENTANILLA" : "TAUSUPERFIL";
        String query = "SELECT DISTINCT TB.cTTBase, TB.dTTBNombre FROM " + tableType + " X"
                + " INNER JOIN tattickperfil TA ON TA.CTTPPERFIL = X.CVPERFIL"
                + " INNER JOIN TMTTICKET TT ON TT.CTTICKET = TA.CTTPTICKET"
                + " INNER JOIN tmtticketbase TB ON TB.CTTBASE = TT.CTTTBASE";

        if (type == TypeSchedule.ASESOR) {
            query = query + " INNER JOIN TAUSUAGENCIA AG ON AG.CUAUSUARIO = X.CUAUSUARIO";
        }

        query = query + (type == TypeSchedule.ASESOR ? " WHERE AG.CUAUSUARIO = ?" : " WHERE X.CVENTANILLA = ?");

        return jdbcTemplate.query(query, new Object[] { typeId },
                (rs, rowNum) -> Service.builder().id(rs.getString(1)).name(rs.getString(2)).build());
    }
}
