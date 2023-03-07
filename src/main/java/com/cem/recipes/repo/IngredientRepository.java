package com.cem.recipes.repo;

import com.cem.recipes.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepository  extends JpaRepository<Ingredient, Long>{

}