package com.hackathon.jobs.model.validation;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.model.Domain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DomainValidator implements Consumer<Domain> {
    private final Validator validator;

    public void accept(List<Domain> domains) {
        domains.forEach(this::accept);
    }

    @Override public void accept(Domain domain) {
        Set<ConstraintViolation<Domain>> violations = validator.validate(domain);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}