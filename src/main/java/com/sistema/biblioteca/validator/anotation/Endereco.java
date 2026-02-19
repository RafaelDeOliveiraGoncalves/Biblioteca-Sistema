package com.sistema.biblioteca.validator.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // Diz que só pode usar em atributos
@Retention(RetentionPolicy.RUNTIME) // Diz que o Spring deve ler isso com o sistema rodando
@Constraint(validatedBy = EnderecoValido.class) // Aqui é onde liga a etiqueta ao seu código de lógica
public @interface Endereco {
    String mensagem() default "Endereço inválido. Deve conter nome e número.";

    //Esses dois campos abaixo são obrigatórios pelo Bean Validation (padrão do Java)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
