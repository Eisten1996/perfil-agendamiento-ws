package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.branch;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class BranchJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public BranchJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Branch> getBranchListByUser(String userId, String branchId) {
        Object[] params;
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
                (rs, rowNum) ->
                        Branch.builder()
                                .codBranch(rs.getString(1))
                                .agName(rs.getString(2))
                                .build()
        );
    }
}
