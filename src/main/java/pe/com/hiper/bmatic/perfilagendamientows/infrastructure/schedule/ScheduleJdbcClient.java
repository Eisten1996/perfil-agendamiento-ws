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

    public int[] saveSchedules(List<Schedule> scheduleList) {
        StringBuilder queryInsert = new StringBuilder();
        queryInsert.append("INSERT INTO TMHORARIO(CHORAINICIO, CHORAFIN, NDIA, NCODPERFILAGENDAMIENTO, CCODVENTANILLA" +
                ", FECMODIFICACION, NFECHA, CCITAADICIONAL, BOOKINGTYPE, COUNTERID )  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        return this.jdbcTemplate.batchUpdate(queryInsert.toString(),
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, scheduleList.get(i).getStartHour());
                        ps.setString(2, scheduleList.get(i).getEndHour());
                        ps.setInt(3, scheduleList.get(i).getDay());
                        ps.setString(4, scheduleList.get(i).getSchedulingId());
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
}
