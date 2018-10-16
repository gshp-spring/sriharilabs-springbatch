package com.gshp;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOG.info("Comienzo del job: " + new Date());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("JOB FINALIZADO. Verificando resultados en la BD");
            final String orden = "SELECT nombre, apellido FROM personas LIMIT 2 OFFSET 2";
            List<Persona> resultados = jdbcTemplate.query(
                    orden,
                    (rs, fila) -> new Persona(rs.getString(1), rs.getString(2))
            );
            resultados.forEach(persona
                    -> LOG.info("Encontrada <" + persona + "> en la BD.")
            );
        }
    }
}
