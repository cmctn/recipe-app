package com.cem.recipes.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME", nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID")
    protected Recipe recipe;
}