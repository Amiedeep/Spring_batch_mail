package com.batch.job.writer;

import com.batch.job.model.Person;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class PersonWriter implements ItemWriter<Person> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void write(List<? extends Person> list) throws Exception {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setSql("INSERT INTO people(first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setDataSource(dataSource);
        writer.afterPropertiesSet();

        writer.write(list);
    }
}
