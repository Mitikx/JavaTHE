package com.boutique.thes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String nom;

    @NotBlank(message = "Le type de thé est obligatoire")
    @Size(max = 50, message = "Le type de thé ne doit pas dépasser 50 caractères")
    @Column(nullable = false, length = 50)
    private String typeThe;

    @NotBlank(message = "L'origine est obligatoire")
    @Size(max = 100, message = "L'origine ne doit pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String origine;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "5.00", message = "Le prix minimum est de 5,00€")
    @DecimalMax(value = "100.00", message = "Le prix maximum est de 100,00€")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @NotNull(message = "La quantité en stock est obligatoire")
    @Min(value = 0, message = "La quantité en stock ne peut pas être négative")
    @Column(nullable = false)
    private Integer quantiteStock;

    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
    @Column(length = 500)
    private String description;

    @NotNull(message = "La date de réception est obligatoire")
    @PastOrPresent(message = "La date de réception ne peut pas être dans le futur")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dateReception;
}