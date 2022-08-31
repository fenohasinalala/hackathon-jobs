package com.hackathon.jobs.model.validation;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.model.JobOffer;
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
public class JobOfferValidator implements Consumer<JobOffer> {
    private final Validator validator;

    public void accept(List<JobOffer> jobOffers) {
        jobOffers.forEach(this::accept);
    }

    @Override public void accept(JobOffer jobOffer) {
        Set<ConstraintViolation<JobOffer>> violations = validator.validate(jobOffer);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
