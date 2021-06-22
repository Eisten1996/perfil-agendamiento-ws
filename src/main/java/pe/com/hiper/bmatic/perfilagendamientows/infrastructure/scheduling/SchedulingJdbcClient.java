package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.scheduling;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query.toString(), new String[]{"id_usuario"});
            ps.setString(1, scheduling.getBranchId());
            ps.setInt(2, scheduling.getMinDays());
            ps.setInt(3, scheduling.getMaxDays());
            ps.setInt(4, scheduling.getToleranceTime());
            ps.setString(5, scheduling.getServices());
            ps.setInt(6, scheduling.getMultipleBookings());
            ps.setInt(7, scheduling.getConfirmEmail());
            ps.setInt(8, scheduling.getConfirmTime());
            ps.setString(9, scheduling.getUnidConfirmTime());
            if (scheduling.getId() == 0) {
                ps.setTimestamp(10, new java.sql.Timestamp(System.currentTimeMillis()));
            } else {
                ps.setInt(10, scheduling.getId());
            }
            return ps;
        }, keyHolder);
        if (scheduling.getId() == 0) {
            schedulingId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        } else {
            schedulingId = scheduling.getId();
        }
        return schedulingId;
    }

    public boolean existsScheduling(String branchId) throws Exception {
        boolean exists = false;

        String query = "SELECT COUNT(1) FROM TMPERFILAGENDAMIENTO WHERE NCODAGENCIA = ?";
        exists = jdbcTemplate.queryForObject(query, new Object[]{branchId}, Integer.class) == 1;

        return exists;
    }

}
