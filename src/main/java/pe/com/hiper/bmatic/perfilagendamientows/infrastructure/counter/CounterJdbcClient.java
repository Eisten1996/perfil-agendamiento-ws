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

    public List<Counter> getCounterList() {
        String query = "SELECT CTVENTANILLA, DTVNOMBRE FROM TMTVENTANILLA ";

        return jdbcTemplate.query(query,
                (rs, rowNum) ->
                        Counter.builder()
                                .id(rs.getString(1))
                                .name(rs.getString(2))
                                .build()
        );
    }
}
