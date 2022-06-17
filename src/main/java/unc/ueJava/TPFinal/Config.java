package unc.ueJava.TPFinal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Optional;
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

import unc.ueJava.TPFinal.DAO.CoursRepository;
import unc.ueJava.TPFinal.DAO.EleveRepository;
import unc.ueJava.TPFinal.DAO.NiveauRepository;
import unc.ueJava.TPFinal.DAO.SalleRepository;
import unc.ueJava.TPFinal.Model.Cours;
import unc.ueJava.TPFinal.Model.Eleve;
import unc.ueJava.TPFinal.Model.Salle;
import unc.ueJava.TPFinal.Model.Niveau.CodeEnum;
import unc.ueJava.TPFinal.Model.Niveau.LibelleEnum;
import unc.ueJava.TPFinal.Model.Niveau.Niveau;

@Configuration
@EnableTransactionManagement // active le management des transaction
@ComponentScan(basePackages = { "unc.ueJava.TPFinal.Services" })
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

    @Bean
    public CommandLineRunner loadData(NiveauRepository niveauRepository, SalleRepository salleRepository, CoursRepository coursRepository, EleveRepository eleveRepository) {
        return (args) -> {

            Arrays.asList(CodeEnum.values()).forEach(code -> {
                Arrays.asList(LibelleEnum.values())
                        .forEach(libelle -> niveauRepository.save(new Niveau(code, libelle)));
            });

            Optional<Niveau> l1Info = niveauRepository.findById(1);
            Optional<Niveau> l2Info = niveauRepository.findById(5);
            Optional<Niveau> l3Info = niveauRepository.findById(9);
            Optional<Niveau> l2Math = niveauRepository.findById(6);

            Salle f1 = new Salle("F1", "Salle informatique 1", 20);
            Salle f2 = new Salle("F2", "Salle informatique 2", 30);
            Salle f3 = new Salle("F3", "Salle de cours 1", 30);
            Salle f4 = new Salle("F4", "Salle de cours 2", 40);

            salleRepository.save(f1);
            salleRepository.save(f2);
            salleRepository.save(f3);
            salleRepository.save(f4);

            Cours decidabilite = new Cours("Décidabilité", LocalDateTime.of(2022, Month.AUGUST, 15, 10, 00), LocalDateTime.of(2022, Month.AUGUST, 15, 12, 00), l3Info.get(), f3);
            Cours java = new Cours("Java", LocalDateTime.of(2022, Month.AUGUST, 15, 16, 30), LocalDateTime.of(2022, Month.AUGUST, 15, 18, 30), l2Info.get(), f2);
            Cours algoProg = new Cours("Algorithme et programmation", LocalDateTime.of(2022, Month.AUGUST, 17, 7, 45), LocalDateTime.of(2022, Month.AUGUST, 17, 9, 45), l1Info.get(), f2);
            Cours algebre = new Cours("Algèbre linéaire", LocalDateTime.of(2022, Month.AUGUST, 18, 13, 30), LocalDateTime.of(2022, Month.AUGUST, 18, 15, 30), l2Math.get(), f4);

            coursRepository.save(decidabilite);
            coursRepository.save(java);
            coursRepository.save(algoProg);
            coursRepository.save(algebre);

            Eleve jean = new Eleve("Martin", "Jean", 21, "8 rue Miyamura", l2Math.get());
            Eleve eren = new Eleve("Jaeger", "Eren", 18, "4 rue Clémenceau", l1Info.get());
            Eleve charles = new Eleve("Oliveira", "Charles", 22, "20 rue Martin", l3Info.get());
            Eleve lily = new Eleve("Kim", "Lily", 21, "2 avenue de France", l2Info.get());

            eleveRepository.save(jean);
            eleveRepository.save(eren);
            eleveRepository.save(charles);
            eleveRepository.save(lily);
        };
    }

}
