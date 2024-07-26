package com.example.listarepro.services;

import com.example.listarepro.dao.IListaReproduccionDao;
import com.example.listarepro.entity.ListaReproduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaReproduccionServiceImpl implements IListaService{

    @Autowired
    private IListaReproduccionDao listaReproduccionDao;

    @Override
    public List<ListaReproduccion> findAll() {
        return (List<ListaReproduccion>) listaReproduccionDao.findAll();
    }

    @Override
    public ListaReproduccion save(ListaReproduccion lista) {
        return listaReproduccionDao.save(lista);
    }

    @Override
    public ListaReproduccion findById(Long id) {
        Optional<ListaReproduccion> result = listaReproduccionDao.findById(id);
        return result.orElse(null);
    }

    @Override
    public void delete(Long id) {
        listaReproduccionDao.deleteById(id);
    }

    @Override
    public Optional<ListaReproduccion> findByName(String name) {
        return listaReproduccionDao.findByName(name);
    }

    @Override
    public void updateList(Long id, ListaReproduccion lista) {
        Optional<ListaReproduccion> existingList = listaReproduccionDao.findById(id);
        if (existingList.isPresent()) {
            ListaReproduccion listaToUpdate = existingList.get();
            listaToUpdate.setDescription(lista.getDescription());
            listaToUpdate.setCanciones(lista.getCanciones());
            listaReproduccionDao.save(listaToUpdate);
        }
    }
}
