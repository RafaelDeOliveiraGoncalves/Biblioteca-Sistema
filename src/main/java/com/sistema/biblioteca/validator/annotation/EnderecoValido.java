package com.sistema.biblioteca.validator.annotation;

import com.sistema.biblioteca.validator.ValidaEndereco;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnderecoValido implements ConstraintValidator<Endereco, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value.isBlank()){
            return true;
        }
        return ValidaEndereco.validaEndereco(value);
    }
}
