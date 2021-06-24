package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;

import java.util.List;

@Component
public class ServiceJdbcClient {

    private final JdbcTemplate jdbcTemplate;

    public ServiceJdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Service> getServicesByBranch(String branchId) {
        String query = "SELECT TB.CTTBASE, TB.DTTBNOMBRE FROM TMTTICKETBASE TB "
                + " INNER JOIN TMAGENCIA AG ON AG.CAGGAGENCIA = TB.CTTGAGENCIA "
                + " WHERE AG.CAGENCIA = ? "
                + " ORDER BY TB.DTTBNOMBRE ";

        return jdbcTemplate.query(query, new Object[]{branchId},
                (rs, rowNum) ->
                        Service.builder()
                                .id(rs.getString(1))
                                .name(rs.getString(2))
                                .build()
        );
    }
}
