package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.scheduling;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.util.List;

@Component
public class SchedulingJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public SchedulingJdbcClient(JdbcTemplate jdbcTemplate) {
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

    public int saveScheduling(Scheduling scheduling) {
        StringBuilder query = new StringBuilder();
        int schedulingId = 0;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (scheduling.getId() == 0) {
            query.append("INSERT INTO TMPERFILAGENDAMIENTO (NCODAGENCIA, NNRODIASMINAGEND, NNRODIASMAXAGEND, NMINUTOSTOLERANCIA, CTICKETBASETIEMPO, "
                    + " BMULTIPLE, BCONFIRMAREMAIL, NTIEMPOCONFIREMAIL, CUNIDTIEMPOCONFEMAIL, FFECCREACION)"
                    + "VALUES(?, ? , ? , ? , ? , ?, ?, ?, ?, ?) ");
        } else {
            query.append("UPDATE TMPERFILAGENDAMIENTO " + "SET NCODAGENCIA = ?, "
                    + "NNRODIASMINAGEND = ?, NNRODIASMAXAGEND = ?, NMINUTOSTOLERANCIA = ?, CTICKETBASETIEMPO = ?, "
                    + "BMULTIPLE = ?, BCONFIRMAREMAIL = ?, NTIEMPOCONFIREMAIL = ?, CUNIDTIEMPOCONFEMAIL = ? "
                    + "WHERE NCODPERFILAGENDAMIENTO = ? ");
        }
        jdbcTemplate.update(query.toString(), scheduling.getBranchId(), scheduling.getMinDays(),
                scheduling.getMaxDays(), scheduling.getToleranceTime(), scheduling.getServices(),
                scheduling.getMultipleBookings(), scheduling.getConfirmEmail(), scheduling.getConfirmTime(),
                scheduling.getUnidConfirmTime(), scheduling.getId() == 0 ? new java.sql.Timestamp(System.currentTimeMillis())
                        : scheduling.getId());
        if (scheduling.getId() == 0) {
            schedulingId = keyHolder.getKey().intValue();
        } else {
            schedulingId = scheduling.getId();
        }
        return schedulingId;
    }

//    public boolean existsScheduling(String branchId) throws Exception {
//        boolean exists = {false};
//
//        String query = "SELECT COUNT(1) FROM TMPERFILAGENDAMIENTO WHERE NCODAGENCIA = ?";
//        jdbcTemplate.query(query, new Object[]{branchId},
//                (ResultSetExtractor<Boolean>) (rs) ->
//                        exists[0] = rs.getInt(1) == 1
//        );
//
//        return exists[0];
//    }

}
