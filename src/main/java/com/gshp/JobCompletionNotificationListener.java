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

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOG.info("Comienzo del job: " + new Date());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        
    }
}
