package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.counter;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;

import java.util.List;

@Component
public class CounterJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public CounterJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Counter> getCounterList(String branchId) {

        String query = "SELECT TA.CTVENTANILLA, TA.CAGENCIA, TA.BTKTIPORESERVA FROM TAVENTRESERVA TA " +
                " UNION SELECT  TV.CTVENTANILLA, VEN.CVAGENCIA, NULL FROM   TMTVENTANILLA TV " +
                " CROSS JOIN TMVENTANILLA VEN ON VEN.CVTVENTANILLA = TV.CTVENTANILLA " +
                " WHERE TV.CTVENTANILLA NOT IN ( SELECT  TA.CTVENTANILLA " +
                " FROM TAVENTRESERVA TA WHERE TA.CAGENCIA = ?) " +
                " AND  VEN.CVAGENCIA = ?";

        return jdbcTemplate.query(query, new Object[]{branchId, branchId},
                (rs, rowNum) ->
                        Counter.builder()
                                .id(rs.getString(1))
                                .name(rs.getString(2))
                                .bookingType(rs.getString(3))
                                .build()
        );
    }
}
