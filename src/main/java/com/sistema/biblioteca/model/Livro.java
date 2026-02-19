package com.sistema.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "livro")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_livro")
    private UUID id;

    @Column(name = "titulo")
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaLivro categoria;

    @Column(name = "qtd_disponivel")
    private Integer qtdDisponivel;

    @Column(name = "qtd_emprestado")
    private Integer qtdEmprestado;

}
