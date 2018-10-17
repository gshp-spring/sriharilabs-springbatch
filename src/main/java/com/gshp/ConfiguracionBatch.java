/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gshp;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author usuario
 */
@Configuration
@EnableBatchProcessing
public class ConfiguracionBatch {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

   
    @Bean
    public FlatFileItemReader<Persona> reader() {
        FlatFileItemReader<Persona> lector = new FlatFileItemReader<>();
        lector.setResource(new ClassPathResource("personas.csv"));
        lector.setLineMapper(new DefaultLineMapper<Persona>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{"nombre", "apellido"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Persona>() {
                    {
                        setTargetType(Persona.class);
                    }
                });
            }
        });
        return lector;
    }

    @Bean
    public PersonaItemProcessor processor() {
        return new PersonaItemProcessor();
    }

    @Bean
    public CustomWriter writer() {
        
        return new CustomWriter();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("transformacionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("paso1")
                .<Persona, Persona>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
