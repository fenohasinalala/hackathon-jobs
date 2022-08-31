package com.hackathon.jobs.model.validation;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.model.Application;
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
public class ApplicationValidator implements Consumer<Application> {
    private final Validator validator;

    public void accept (List<Application> applications){
        applications.forEach(this::accept);
    }

    @Override
    public void accept(Application application) {
        Set<ConstraintViolation<Application>> violations = validator.validate(application);
        if(!(violations.isEmpty())){
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
