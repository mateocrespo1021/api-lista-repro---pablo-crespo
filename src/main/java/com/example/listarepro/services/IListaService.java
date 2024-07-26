package com.example.listarepro.services;

import com.example.listarepro.entity.ListaReproduccion;

import java.util.List;
import java.util.Optional;

public interface IListaService {
    List<ListaReproduccion> findAll();
    ListaReproduccion save(ListaReproduccion lista);
    ListaReproduccion findById(Long id);
    void delete(Long id);
    Optional<ListaReproduccion> findByName(String name);
    void updateList(Long id, ListaReproduccion lista);

}
