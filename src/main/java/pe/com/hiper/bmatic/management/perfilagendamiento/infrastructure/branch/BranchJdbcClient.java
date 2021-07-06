package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.branch;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;

import java.util.List;

@Component
public class BranchJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public BranchJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Branch> getBranchListByUser(String userId) {
        String query = "SELECT cagencia, cagnombre FROM TMAGENCIA a"
                + " inner join tausuagencia u on a.cagencia = u.cuaagencia"
                + " where u.cuausuario = ? and a.cagencia not in"
                + " (SELECT NCODAGENCIA FROM TMPERFILAGENDAMIENTO) ORDER BY a.CAGNOMBRE";

        return jdbcTemplate.query(query, new Object[] { userId },
                (rs, rowNum) -> Branch.builder().codBranch(rs.getString(1)).agName(rs.getString(2)).build());
    }
}
