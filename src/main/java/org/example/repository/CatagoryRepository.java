package org.example.repository;

import org.example.entities.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatagoryRepository extends JpaRepository<Catagory, Integer> {

    @Override
    Optional<Catagory> findById(Integer integer);
}
