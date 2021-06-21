package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.agency;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class AgencyJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public AgencyJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Agency> getBranchListByUser(String userId, String branchId) {
        Object[] params;
        PreparedStatement consult = null;
        String query = "SELECT cagencia, cagnombre FROM TMAGENCIA a"
                + " inner join tausuagencia u on a.cagencia = u.cuaagencia"
                + " where u.cuausuario = ? and a.cagencia not in"
                + " (SELECT NCODAGENCIA FROM TMPERFILAGENDAMIENTO";

        if (!branchId.equalsIgnoreCase("0")) {
            query = query + " WHERE NCODAGENCIA != ?";
        }
        query = query + ") ORDER BY a.CAGNOMBRE";

        if (!branchId.equalsIgnoreCase("0")) {
            params = new Object[]{userId, branchId};
        } else {
            params = new Object[]{userId};
        }

        return jdbcTemplate.query(query, params,
                (rs, rowNum) -> {
                    System.out.println("GAAAAAAAAA" + rs.getString(1));
                    return Agency.builder()
                            .codAgency(rs.getString(1))
                            .agName(rs.getString(2))
                            .build();
                });
    }
}
