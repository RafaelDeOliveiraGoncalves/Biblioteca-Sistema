package com.sistema.biblioteca.validator;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class ValidaEndereco {

    private static final Pattern ENDERECO_PATTERN = Pattern.compile(
            "^[\\p{L}0-9\\.,\\-\\s]+\\d{1,5}.*$"
    );

    public static boolean validaEndereco(String endereco){
        if(enderecoVazio(endereco)){
            return false;
        }
        return ENDERECO_PATTERN.matcher(endereco).matches();
    }

    private static boolean enderecoVazio(String endereco){
        if(endereco.isBlank()){
            return true;
        }
        return false;
    }

}
