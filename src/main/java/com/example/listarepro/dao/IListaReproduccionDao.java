package com.example.listarepro.dao;

import com.example.listarepro.entity.Cancion;
import com.example.listarepro.entity.ListaReproduccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IListaReproduccionDao  extends CrudRepository<ListaReproduccion,Long> {

    @Query("SELECT l FROM ListaReproduccion l WHERE l.name = :name")
    Optional<ListaReproduccion> findByName(@Param("name") String name);
}
