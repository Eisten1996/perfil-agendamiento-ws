package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.asesor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model.Asesor;

import java.util.List;

@Component
public class AsesorJdbcClient {
    private final JdbcTemplate jdbcTemplate;

    public AsesorJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Asesor> getAsesorList(String idTypeCounter, String branchId) {

        String query = "SELECT DISTINCT U.CUSUARIO, CONCAT(U.DUCUENTA, ' - ', U.DUNOMBRE, ' ', U.DUAPELLIDO) AS NOMBRES "
                + "FROM TAUSUAGENCIA A " + "INNER JOIN DBBMATICSEGURIDAD.TMUSUARIO U ON U.CUSUARIO = A.CUAUSUARIO "
                + "INNER JOIN TAUSUPERFIL P ON P.CUAUSUARIO = U.CUSUARIO "
                + "INNER JOIN TATTICKPERFIL TA ON TA.CTTPPERFIL = P.CVPERFIL "
                + "INNER JOIN TMTTICKET TT ON TT.CTTICKET = TA.CTTPTICKET "
                + "WHERE A.CUAAGENCIA = ? AND U.BUNIVELACCESO = ? AND TT.CTTTVENTANILLA = ? ORDER BY NOMBRES";

        return jdbcTemplate.query(query, new Object[] { branchId, "L", idTypeCounter },
                (rs, rowNum) -> Asesor.builder().id(rs.getString(1)).name(rs.getString(2)).build());
    }
}
