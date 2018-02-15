package com.batch.job.processor;

import com.batch.job.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItem implements ItemProcessor<Person, Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonItem.class);

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        LOGGER.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
