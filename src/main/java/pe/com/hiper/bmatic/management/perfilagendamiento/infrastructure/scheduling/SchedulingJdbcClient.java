package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.scheduling;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model.TypeScheduling;

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

    public List<Scheduling> listSchedulings(String userId) {
        String sql = "SELECT PA.NCODPERFILAGENDAMIENTO, PA.NNRODIASMAXAGEND, PA.NNRODIASMINAGEND, AG.CAGENCIA, AG.CAGNOMBRE"
                + " FROM TMPERFILAGENDAMIENTO PA INNER JOIN TMAGENCIA AG ON AG.CAGENCIA = PA.NCODAGENCIA"
                + " INNER JOIN tausuagencia U ON U.CUAAGENCIA = AG.CAGENCIA WHERE U.CUAUSUARIO = '" + userId +"' ORDER BY AG.CAGNOMBRE";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> Scheduling.builder().branchName(rs.getString("CAGNOMBRE"))
                        .id(rs.getInt("NCODPERFILAGENDAMIENTO")).branchId(rs.getString("CAGENCIA"))
                        .maxDays(rs.getInt("NNRODIASMAXAGEND")).minDays(rs.getInt("NNRODIASMINAGEND")).build());
    }

    public Scheduling getScheduling(String schedulingId) {
        StringBuilder query = new StringBuilder();
        query.append(
                "SELECT PA.NCODPERFILAGENDAMIENTO, AG.CAGNOMBRE, PA.NCODAGENCIA, PA.NNRODIASMINAGEND, PA.NNRODIASMAXAGEND, PA.NMINUTOSTOLERANCIA, PA.CTICKETBASETIEMPO,"
                        + "PA.BMULTIPLE, PA.BCONFIRMAREMAIL, PA.NTIEMPOCONFIREMAIL, PA.CUNIDTIEMPOCONFEMAIL, AG.CAGTIPOATENCION "
                        + "FROM TMPERFILAGENDAMIENTO PA INNER JOIN TMAGENCIA AG ON AG.CAGENCIA = PA.NCODAGENCIA "
                        + "WHERE PA.NCODPERFILAGENDAMIENTO = ?");
        return jdbcTemplate.queryForObject(query.toString(), new Object[] { schedulingId },
                (rs, rowNum) -> Scheduling.builder().id(rs.getInt("NCODPERFILAGENDAMIENTO"))
                        .branchId(rs.getString("NCODAGENCIA")).branchName(rs.getString("CAGNOMBRE"))
                        .minDays(rs.getInt("NNRODIASMINAGEND")).maxDays(rs.getInt("NNRODIASMAXAGEND"))
                        .toleranceTime(rs.getInt("NMINUTOSTOLERANCIA")).services(rs.getString("CTICKETBASETIEMPO"))
                        .multipleBookings(rs.getInt("BMULTIPLE")).confirmEmail(rs.getInt("BCONFIRMAREMAIL"))
                        .confirmTime(rs.getInt("NTIEMPOCONFIREMAIL"))
                        .unidConfirmTime(rs.getString("CUNIDTIEMPOCONFEMAIL"))
                        .typeAttention(rs.getString("CAGTIPOATENCION")).build());

    }

    public int saveScheduling(Scheduling scheduling) {
        StringBuilder query = new StringBuilder();
        int schedulingId = 0;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (scheduling.getId() == 0) {
            query.append(
                    "INSERT INTO TMPERFILAGENDAMIENTO (NCODAGENCIA, NNRODIASMINAGEND, NNRODIASMAXAGEND, NMINUTOSTOLERANCIA, CTICKETBASETIEMPO, "
                            + " BMULTIPLE, BCONFIRMAREMAIL, NTIEMPOCONFIREMAIL, CUNIDTIEMPOCONFEMAIL, FFECCREACION)"
                            + "VALUES(?, ? , ? , ? , ? , ?, ?, ?, ?, ?) ");
        } else {
            query.append("UPDATE TMPERFILAGENDAMIENTO " + "SET NCODAGENCIA = ?, "
                    + "NNRODIASMINAGEND = ?, NNRODIASMAXAGEND = ?, NMINUTOSTOLERANCIA = ?, CTICKETBASETIEMPO = ?, "
                    + "BMULTIPLE = ?, BCONFIRMAREMAIL = ?, NTIEMPOCONFIREMAIL = ?, CUNIDTIEMPOCONFEMAIL = ? "
                    + "WHERE NCODPERFILAGENDAMIENTO = ? ");
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query.toString(), new String[] { "id_usuario" });
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

    public int[] saveTypeScheduling(List<TypeScheduling> typeSchedulingList, String branchId) {
        StringBuilder queryInsert = new StringBuilder();

        this.deleteCounterBookings(branchId);
        queryInsert.append("INSERT INTO TAVENTRESERVA (NCODPERFILAGENDAMIENTO, CAGENCIA, CTVENTANILLA, BTKTIPORESERVA) "
                + "VALUES(?,?,?,?); ");

        return this.jdbcTemplate.batchUpdate(queryInsert.toString(), new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, typeSchedulingList.get(i).getSchedulingId());
                ps.setString(2, typeSchedulingList.get(i).getBranchId());
                ps.setString(3, typeSchedulingList.get(i).getCounterId());
                ps.setString(4, typeSchedulingList.get(i).getTypeBooking());
            }

            public int getBatchSize() {
                return typeSchedulingList.size();
            }

        });
    }

    public boolean existsScheduling(String branchId) {
        boolean exists;

        String query = "SELECT COUNT(1) FROM TMPERFILAGENDAMIENTO WHERE NCODAGENCIA = '" + branchId + "'";
        exists = jdbcTemplate.queryForObject(query, Integer.class) == 1;

        return exists;
    }

    public void deleteSchedulingById(Integer schedulingId) {
        String query = "DELETE FROM TMPERFILAGENDAMIENTO WHERE NCODPERFILAGENDAMIENTO = ? ";
        jdbcTemplate.update(query, schedulingId);
    }

    public void deleteCounterBookings(String branchId) {
        String query = "DELETE FROM TAVENTRESERVA WHERE CAGENCIA = ?; ";
        jdbcTemplate.update(query, branchId);
    }
}
