package unc.ueJava.TPFinal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableTransactionManagement // active le management des transaction
@ComponentScan(basePackages = { "nc.univ.springdata.Services" })
@EnableJpaRepositories(basePackages = { "unc.ueJava.TPFinal.DAO" }) // active les repositories
public class Config {
    /**
     * Retourne le bean correspondant a la source
     * de données cablé sur la base H2
     *
     * pour un serveur h2 :
     * dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
     *
     * pour un fichier local :
     * dataSource.setUrl("jdbc:h2:C:/Users/matma/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {

        // Declaration du dataSource avec le driver et le login/password
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        return dataSource;
    }

    /**
     * Retourne la fabrique d'entityManager
     * 
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        // Creation de la factory
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        // Configuration du datasource
        em.setDataSource(dataSource());

        // Configuration des modeles a analyser
        em.setPackagesToScan(new String[] { "unc.ueJava.TPFinal.Model" });

        // Configuration du JPA implementation (Hibernate)
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Ajout des propriété du JPA Impl (config hibernate)
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaProperties(properties);

        return em;
    }

    /**
     * Retourne le bean correspondant au manager
     * des transaction
     * 
     * @param emf
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public ClassLoaderTemplateResolver elevesTemplateResolver() {
        ClassLoaderTemplateResolver elevesTemplateResolver = new ClassLoaderTemplateResolver();
        elevesTemplateResolver.setPrefix("templates/eleves/");
        elevesTemplateResolver.setSuffix(".html");
        elevesTemplateResolver.setTemplateMode(TemplateMode.HTML);
        elevesTemplateResolver.setCharacterEncoding("UTF-8");
        elevesTemplateResolver.setCheckExistence(true);
        return elevesTemplateResolver;
    }

    @Bean
    public ClassLoaderTemplateResolver coursTemplateResolver() {
        ClassLoaderTemplateResolver coursTemplateResolver = new ClassLoaderTemplateResolver();
        coursTemplateResolver.setPrefix("templates/cours/");
        coursTemplateResolver.setSuffix(".html");
        coursTemplateResolver.setTemplateMode(TemplateMode.HTML);
        coursTemplateResolver.setCharacterEncoding("UTF-8");
        coursTemplateResolver.setCheckExistence(true);
        return coursTemplateResolver;
    }

    @Bean
    public ClassLoaderTemplateResolver niveauTemplateResolver() {
        ClassLoaderTemplateResolver niveauTemplateResolver = new ClassLoaderTemplateResolver();
        niveauTemplateResolver.setPrefix("templates/niveau/");
        niveauTemplateResolver.setSuffix(".html");
        niveauTemplateResolver.setTemplateMode(TemplateMode.HTML);
        niveauTemplateResolver.setCharacterEncoding("UTF-8");
        niveauTemplateResolver.setCheckExistence(true);
        return niveauTemplateResolver;
    }

    @Bean
    public ClassLoaderTemplateResolver salleTemplateResolver() {
        ClassLoaderTemplateResolver salleTemplateResolver = new ClassLoaderTemplateResolver();
        salleTemplateResolver.setPrefix("templates/salle/");
        salleTemplateResolver.setSuffix(".html");
        salleTemplateResolver.setTemplateMode(TemplateMode.HTML);
        salleTemplateResolver.setCharacterEncoding("UTF-8");
        salleTemplateResolver.setCheckExistence(true);
        return salleTemplateResolver;
    }
}
