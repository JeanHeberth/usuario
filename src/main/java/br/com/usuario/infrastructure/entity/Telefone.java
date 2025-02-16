package br.com.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "telefone")
@Entity
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "ddd", length = 3)
    private String ddd;

}
