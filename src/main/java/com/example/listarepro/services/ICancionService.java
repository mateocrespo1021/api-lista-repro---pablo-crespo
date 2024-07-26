package com.example.listarepro.services;

import com.example.listarepro.entity.Cancion;

import java.util.List;

public interface ICancionService {
    public List<Cancion> findAll();
    public Cancion save(Cancion cancion);
    public Cancion findById(Long id);
    public void delete(Long id);
}
