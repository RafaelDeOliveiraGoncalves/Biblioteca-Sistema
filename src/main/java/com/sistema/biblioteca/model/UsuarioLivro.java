package com.sistema.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "usuario_livro")
@IdClass(UsuarioLivro.UsuarioLivroId.class)
@Getter
@Setter
@NoArgsConstructor
public class UsuarioLivro implements Serializable {

    public UsuarioLivro(Usuario usuario, Livro livro, LocalDate dataEmprestimo){
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class UsuarioLivroId implements Serializable{
        private String usuario;
        private Integer livro;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "cpf")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

}
