package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.schedule;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model.Schedule;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ScheduleJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveSchedules(List<Schedule> scheduleList) {
        StringBuilder queryInsert = new StringBuilder();
        queryInsert.append("INSERT INTO TMHORARIO(CHORAINICIO, CHORAFIN, NDIA, NCODPERFILAGENDAMIENTO, CCODVENTANILLA" +
                ", FECMODIFICACION, NFECHA, CCITAADICIONAL, BOOKINGTYPE, COUNTERID )  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        this.jdbcTemplate.batchUpdate(queryInsert.toString(),
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, scheduleList.get(i).getStart());
                        ps.setString(2, scheduleList.get(i).getEnd());
                        ps.setInt(3, scheduleList.get(i).getDay());
                        ps.setInt(4, scheduleList.get(i).getSchedulingId());
                        ps.setString(5, scheduleList.get(i).getCounterId());
                        ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                        ps.setString(7, scheduleList.get(i).getDate());
                        ps.setInt(8, scheduleList.get(i).getAddDating());
                        ps.setString(9, scheduleList.get(i).getBookingType());
                        ps.setString(10, scheduleList.get(i).getCounterTypeId());
                    }

                    public int getBatchSize() {
                        return scheduleList.size();
                    }
                });
    }

    public void deleteSchedulesById(Integer schedulingId) {
        String query = "DELETE FROM TMHORARIO WHERE NCODPERFILAGENDAMIENTO = ? ";
        jdbcTemplate.update(query, schedulingId);
    }

    public List<Schedule> getSchedulesById(Integer schedulingId) {
        String query = "SELECT NCODHORARIO, CHORAINICIO, CHORAFIN, NDIA, NCODPERFILAGENDAMIENTO, " +
                "CCODVENTANILLA, NFECHA, CCITAADICIONAL, BOOKINGTYPE, COUNTERID FROM TMHORARIO " +
                "WHERE NCODPERFILAGENDAMIENTO = ? ";
        return jdbcTemplate.query(query, (rs, rowNum) ->
        Schedule.builder()
                .id(rs.getInt("NCODHORARIO"))
                .start(rs.getString("NFECHA") + " " + rs.getString("CHORAINICIO"))
                .end(rs.getString("NFECHA") + " " + rs.getString("CHORAFIN"))
                .day(rs.getInt("NDIA"))
                .schedulingId(rs.getInt("NCODPERFILAGENDAMIENTO"))
                .counterId(rs.getString("CCODVENTANILLA"))
                .date(rs.getString("NFECHA"))
                .addDating(rs.getInt("CCITAADICIONAL"))
                .bookingType(rs.getString("BOOKINGTYPE"))
                .counterTypeId(rs.getString("COUNTERID"))
                .build(), new Object[]{schedulingId}
                
        );
    }
}
