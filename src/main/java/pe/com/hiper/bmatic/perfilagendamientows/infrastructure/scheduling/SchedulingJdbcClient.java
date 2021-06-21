package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.scheduling;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public int saveScheduling(Connection connection, Scheduling scheduling) throws Exception {
        PreparedStatement consult = null;
        int schedulingId = 0;
        try {
            String query = scheduling.getId() == 0
                    ? "INSERT INTO TMPERFILAGENDAMIENTO (NCODAGENCIA, NNRODIASMINAGEND, NNRODIASMAXAGEND, NMINUTOSTOLERANCIA, CTICKETBASETIEMPO, "
                    + " BMULTIPLE, BCONFIRMAREMAIL, NTIEMPOCONFIREMAIL, CUNIDTIEMPOCONFEMAIL, FFECCREACION)"
                    + "VALUES(?, ? , ? , ? , ? , ?, ?, ?, ?, ?) "

                    : "UPDATE TMPERFILAGENDAMIENTO " + "SET NCODAGENCIA = ?, "
                    + "NNRODIASMINAGEND = ?, NNRODIASMAXAGEND = ?, NMINUTOSTOLERANCIA = ?, CTICKETBASETIEMPO = ?, "
                    + "BMULTIPLE = ?, BCONFIRMAREMAIL = ?, NTIEMPOCONFIREMAIL = ?, CUNIDTIEMPOCONFEMAIL = ? "
                    + "WHERE NCODPERFILAGENDAMIENTO = ? ";

            consult = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            consult.setString(1, scheduling.getBranchId());
            consult.setInt(2, scheduling.getMinDays());
            consult.setInt(3, scheduling.getMaxDays());
            consult.setInt(4, scheduling.getToleranceTime());
            consult.setString(5, scheduling.getServices());
            consult.setInt(6, scheduling.getMultipleBookings());
            consult.setInt(7, scheduling.getConfirmEmail());
            consult.setObject(8, scheduling.getConfirmTime());
            consult.setString(9, scheduling.getUnidConfirmTime());

            if (scheduling.getId() == 0) {
                consult.setTimestamp(10, new java.sql.Timestamp(System.currentTimeMillis()));
            } else {
                consult.setInt(10, scheduling.getId());
            }

            consult.executeUpdate();

            if (scheduling.getId() == 0) {
                ResultSet rs = consult.getGeneratedKeys();
                if (rs.next()) {
                    schedulingId = rs.getInt(1);
                }
            } else {
                schedulingId = scheduling.getId();
            }
        } catch (Exception e) {
            System.out.println("saveScheduling: " + e.getMessage());
            throw e;
        } finally {
            consult.close();
        }
        return schedulingId;
    }


}
