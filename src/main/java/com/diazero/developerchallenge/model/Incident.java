package com.diazero.developerchallenge.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_INCIDENTS")
public class Incident implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idIncident;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "description")
    private String description;
    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updateAt")
    private LocalDateTime updateAt;
    @Column(name = "closeAt")
    private LocalDateTime closeAt;
}