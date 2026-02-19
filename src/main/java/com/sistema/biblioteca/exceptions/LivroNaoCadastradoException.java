package com.sistema.biblioteca.exceptions;

public class LivroNaoCadastradoException extends RuntimeException{

    public LivroNaoCadastradoException(){
        super("Livro não cadastrado no sistema");
    }

    public LivroNaoCadastradoException(int codigo){
        super("Livro com código: " + codigo + " não cadastrado");
    }

}
