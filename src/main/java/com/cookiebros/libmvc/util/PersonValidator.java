package com.cookiebros.libmvc.util;

import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        //FIO
        int fioMin = 5;
        int fioMax = 60;
        String fioPattern = "[А-Я][а-я]{2,20} [А-Я][а-я]{2,20} [А-Я][а-я]{0,20}";

        if (person.getFio().length() < fioMin || person.getFio().length() > fioMax) {
            errors.rejectValue("fio", "", "FIO should be between 2 and 30 characters");
        } else if (!Pattern.compile(fioPattern).matcher(person.getFio()).matches()) {
            errors.rejectValue("fio", "", "Invalid characters in FIO");
        }

        //YearOfBirth
        if (person.getYearOfBirth() < 1900 || person.getYearOfBirth() > 2022)
            errors.rejectValue("yearOfBirth", "", "yearOfBirth should be greater then 1900");


        //проверка на уникальность FIO в БД
        Optional<Person> foundPerson = peopleService.findByFio(person.getFio());

        if (foundPerson.isPresent() && foundPerson.get().getId() != person.getId()) {
            errors.rejectValue("fio", "", "This FIO is already taken");
        }
    }
}