package com.sistema.biblioteca.service;

import com.sistema.biblioteca.exceptions.CopiaNaoDisponivelException;
import com.sistema.biblioteca.exceptions.NenhumaCopiaEmprestadaException;
import com.sistema.biblioteca.model.Livro;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    public void empresta(Livro livro){
        if(copiaDisponivel(livro)){
            int qtdDisponivel = livro.getQtdDisponivel();
            int qtdEmprestado = livro.getQtdEmprestado();
            livro.setQtdDisponivel(qtdDisponivel - 1);
            livro.setQtdEmprestado(qtdEmprestado + 1);
        }
        else{
            throw new CopiaNaoDisponivelException();
        }
    }

    public void devolve(Livro livro){
        if (nenhumaCopiaEmprestada(livro)){
            throw new NenhumaCopiaEmprestadaException();
        }
        int qtdEmprestado = livro.getQtdEmprestado();
        int qtdDisponivel = livro.getQtdDisponivel();
        livro.setQtdEmprestado(qtdEmprestado - 1);
        livro.setQtdDisponivel(qtdDisponivel + 1);
    }

    private boolean copiaDisponivel(Livro livro){
        return livro.getQtdDisponivel() > 0;
    }

    private boolean nenhumaCopiaEmprestada(Livro livro){
        return livro.getQtdEmprestado() == 0;
    }

}
