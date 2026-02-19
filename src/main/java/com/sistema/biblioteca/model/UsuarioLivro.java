package com.sistema.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario_livro")
@IdClass(UsuarioLivro.UsuarioLivroId.class)
@Getter
@Setter
@NoArgsConstructor
public class UsuarioLivro implements Serializable {

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class UsuarioLivroId implements Serializable{
        private UUID usuario;
        private UUID livro;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
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
