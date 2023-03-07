package com.cem.recipes.repo;

import com.cem.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, QuerydslPredicateExecutor<Recipe> {

    Optional<Recipe> findByName(String name);

}