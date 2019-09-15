package vvk.catalog.image.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "vvk.catalog.image.repositories")
@PropertySource(value = "classpath:application.properties")
public class DbConfig {


    private Environment env;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:your.db");
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "vvk.catalog.image" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        if (env.getProperty("hibernate.dialect") != null) {
            hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        }
        if (env.getProperty("hibernate.show_sql") != null) {
            hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        }
        return hibernateProperties;
    }

}
