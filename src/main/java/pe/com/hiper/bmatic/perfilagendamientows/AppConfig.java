package pe.com.hiper.bmatic.perfilagendamientows;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pe.com.hiper.seguridad.acceso.ControlAccesoFilter;

import java.util.List;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import javax.servlet.Filter;
import javax.sql.DataSource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public Filter filterAccessControl() {
        return new ControlAccesoFilter();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        return new InternalResourceViewResolver();
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public JndiPropertyHolder primary() {
        return new JndiPropertyHolder();
    }

    @Bean
    @Primary
    @Qualifier("centralDataSource")
    public DataSource primaryDataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(primary().getJndiName());
        return dataSource;
    }

    @Bean("jdbcTemplate")
    JdbcTemplate JdbcTemplate(//@Qualifier("centralDataSource")
    		DataSource centralDataSource) {
        return new JdbcTemplate(centralDataSource);
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public JndiPropertyHolder secondary() {
        return new JndiPropertyHolder();
    }


    @Bean
    @Qualifier("securityDataSource")
    public DataSource secondaryDataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(secondary().getJndiName());
        return dataSource;
    }

    @Bean("jdbcTempleSecurity")
    JdbcTemplate securityJdbcTemplate(@Qualifier("securityDataSource") DataSource securityDataSource) {
        return new JdbcTemplate(securityDataSource);
    }
    
    private static class JndiPropertyHolder {
        private String jndiName;

        public String getJndiName() {
            return jndiName;
        }
        
        public void setJndiName(String jndiName) {
            this.jndiName = jndiName;
        }
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE).modules(new JodaModule());

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter(
                builder.build());
        ObjectMapper objectMapper = jsonMessageConverter.getObjectMapper();

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        converters.add(jsonMessageConverter);


    }
}
