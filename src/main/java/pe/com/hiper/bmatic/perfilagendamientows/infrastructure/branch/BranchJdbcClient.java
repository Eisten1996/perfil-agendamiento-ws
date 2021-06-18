package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.branch;

import org.springframework.jdbc.core.JdbcTemplate;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Branch;

import java.util.List;

/**
 * @author Dipper
 * @project perfil-agendamiento-ws
 * @created 18/06/2021 - 12:09
 */
public class BranchJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public BranchJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Branch> listBranches() {
        String sql = "SELECT PA.NCODPERFILAGENDAMIENTO, PA.NNRODIASMAXAGEND, PA.NNRODIASMINAGEND, AG.CAGENCIA, AG.CAGNOMBRE"
                + " FROM TMPERFILAGENDAMIENTO PA INNER JOIN TMAGENCIA AG ON AG.CAGENCIA = PA.NCODAGENCIA"
                + " ORDER BY AG.CAGNOMBRE";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> Branch.builder()
                        .nameBranch(rs.getString("CAGNOMBRE"))
                        .codProfileScheduler(rs.getString("NCODPERFILAGENDAMIENTO"))
                        .codBranch(rs.getString("CAGENCIA"))
                        .maxDayScheduler(rs.getInt("NNRODIASMAXAGEND"))
                        .minDayScheduler(rs.getInt("NNRODIASMINAGEND"))
                        .build()
        );
    }
}
