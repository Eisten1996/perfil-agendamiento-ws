package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.scheduling;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.util.List;

@Component
public class SchedulingClient {

    private final JdbcTemplate jdbcTemplate;

    public SchedulingClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Scheduling> listSchedulings() {
        String sql = "SELECT PA.NCODPERFILAGENDAMIENTO, PA.NNRODIASMAXAGEND, PA.NNRODIASMINAGEND, AG.CAGENCIA, AG.CAGNOMBRE"
                + " FROM TMPERFILAGENDAMIENTO PA INNER JOIN TMAGENCIA AG ON AG.CAGENCIA = PA.NCODAGENCIA"
                + " ORDER BY AG.CAGNOMBRE";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> Scheduling.builder()
                        .branchName(rs.getString("CAGNOMBRE"))
                        .id(rs.getInt("NCODPERFILAGENDAMIENTO"))
                        .branchId(rs.getString("CAGENCIA"))
                        .maxDays(rs.getInt("NNRODIASMAXAGEND"))
                        .minDays(rs.getInt("NNRODIASMINAGEND"))
                        .build()
        );
    }
}
