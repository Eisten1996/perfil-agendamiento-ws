package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.counter;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.Counter;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.TypeCounter;

import java.util.List;

@Component
public class CounterJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public CounterJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TypeCounter> getTypeCounterList(String branchId) {

        String query = "SELECT TA.CTVENTANILLA, TV.DTVNOMBRE, TA.BTKTIPORESERVA  FROM TAVENTRESERVA TA "
                + "INNER JOIN TMTVENTANILLA TV ON TV.CTVENTANILLA = TA.CTVENTANILLA where  TA.CAGENCIA = ?"
                + " UNION SELECT  TV.CTVENTANILLA, TV.DTVNOMBRE, NULL FROM   TMTVENTANILLA TV "
                + " LEFT JOIN TMVENTANILLA VEN ON VEN.CVTVENTANILLA = TV.CTVENTANILLA "
                + " WHERE TV.CTVENTANILLA NOT IN ( SELECT  TA.CTVENTANILLA "
                + " FROM TAVENTRESERVA TA WHERE TA.CAGENCIA = ?) " + " AND  VEN.CVAGENCIA = ?";

        return jdbcTemplate.query(query, new Object[] { branchId, branchId, branchId }, (rs, rowNum) -> TypeCounter
                .builder().id(rs.getString(1)).name(rs.getString(2)).bookingType(rs.getString(3)).build());
    }

    public List<Counter> getCounterList(String idTypeCounter, String branchId) {

        String query = "SELECT CVENTANILLA, DVNOMBRE FROM TMVENTANILLA WHERE CVTVENTANILLA = ? AND CVAGENCIA = ?";

        return jdbcTemplate.query(query, new Object[] { idTypeCounter, branchId },
                (rs, rowNum) -> Counter.builder().id(rs.getString(1)).name(rs.getString(2)).build());
    }

}
