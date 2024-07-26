package com.example.listarepro.dao;

import com.example.listarepro.entity.Cancion;
import org.springframework.data.repository.CrudRepository;

public interface ICancionDao extends CrudRepository<Cancion,Long> {
}
