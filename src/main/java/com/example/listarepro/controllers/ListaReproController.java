package com.example.listarepro.controllers;

import com.example.listarepro.entity.ListaReproduccion;
import com.example.listarepro.services.IListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListaReproController {

    @Autowired
    private IListaService listaService;

    @PostMapping("/crear")
    public ResponseEntity<?> createList(@RequestBody ListaReproduccion lista) {
        if (lista.getName() == null || lista.getName().isEmpty()) {
            return new ResponseEntity<>("El nombre de la lista no es válido", HttpStatus.BAD_REQUEST);
        }
        ListaReproduccion savedList = listaService.save(lista);
        return new ResponseEntity<>(savedList, HttpStatus.CREATED);
    }

    @GetMapping("/lists")
    public List<ListaReproduccion> getAllLists() {
        return listaService.findAll();
    }

    @GetMapping("/lists/{name}")
    public ResponseEntity<?> getListByName(@PathVariable String name) {
        Optional<ListaReproduccion> lista = listaService.findByName(name);
        if (!lista.isPresent()) {
            return new ResponseEntity<>("Lista no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lista.get(), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateList(@PathVariable Long id, @RequestBody ListaReproduccion lista) {
        ListaReproduccion existingList = listaService.findById(id);
        if (existingList == null) {
            return new ResponseEntity<>("Lista no encontrada", HttpStatus.NOT_FOUND);
        }
        if (!existingList.getName().equals(lista.getName())) {
            return new ResponseEntity<>("No se puede modificar el nombre de la lista", HttpStatus.CONFLICT);
        }
        listaService.updateList(id, lista);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/lists/{name}")
    public ResponseEntity<?> deleteList(@PathVariable String name) {
        Optional<ListaReproduccion> lista = listaService.findByName(name);
        if (!lista.isPresent()) {
            return new ResponseEntity<>("Lista no encontrada", HttpStatus.NOT_FOUND);
        }
        listaService.delete(lista.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
