package com.batch.job.job;

import com.batch.job.model.Person;
import com.batch.job.processor.PersonItem;
import com.batch.job.reader.PeopleReader;
import com.batch.job.writer.PersonWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableBatchProcessing
//@Configuration
public class ImportPeople {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PersonWriter personWriter;

    @Bean
    public PersonItem processor() {
        return new PersonItem();
    }

    @Bean
    public Job importUserJob() throws Exception {
        return jobBuilderFactory.get("importUserJob")
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(new PeopleReader().read())
                .processor(processor())
                .writer(personWriter)
                .build();
    }
}
