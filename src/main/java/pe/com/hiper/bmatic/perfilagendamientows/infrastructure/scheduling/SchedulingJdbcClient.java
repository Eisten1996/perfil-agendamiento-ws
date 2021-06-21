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

    public int saveScheduling(Connection conexion, Scheduling scheduling) throws Exception {
        PreparedStatement consulta = null;
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

            consulta = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            consulta.setString(1, scheduling.getBranchId());
            consulta.setInt(2, scheduling.getMinDays());
            consulta.setInt(3, scheduling.getMaxDays());
            consulta.setInt(4, scheduling.getToleranceTime());
            consulta.setString(5, scheduling.getServices());
            consulta.setInt(6, scheduling.getMultipleBookings());
            consulta.setInt(7, scheduling.getConfirmEmail());
            consulta.setObject(8, scheduling.getConfirmTime());
            consulta.setString(9, scheduling.getUnidConfirmTime());

            if (scheduling.getId() == 0) {
                consulta.setTimestamp(10, new java.sql.Timestamp(System.currentTimeMillis()));
            } else {
                consulta.setInt(10, scheduling.getId());
            }

            consulta.executeUpdate();

            if (scheduling.getId() == 0) {
                ResultSet rs = consulta.getGeneratedKeys();
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
            consulta.close();
        }
        return schedulingId;
    }
}
