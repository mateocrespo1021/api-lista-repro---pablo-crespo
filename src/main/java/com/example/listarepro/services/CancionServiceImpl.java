package com.example.listarepro.services;

import com.example.listarepro.dao.ICancionDao;
import com.example.listarepro.entity.Cancion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionServiceImpl implements ICancionService {

    @Autowired
    private ICancionDao cancionDao;

    @Override
    public List<Cancion> findAll() {
        // Convert Iterable to List
        return (List<Cancion>) cancionDao.findAll();
    }

    @Override
    public Cancion save(Cancion cancion) {
        return cancionDao.save(cancion);
    }

    @Override
    public Cancion findById(Long id) {
        Optional<Cancion> result = cancionDao.findById(id);
        return result.orElse(null);
    }

    @Override
    public void delete(Long id) {
        cancionDao.deleteById(id);
    }
}
