package com.example.listarepro.controllers;

import com.example.listarepro.entity.Cancion;
import com.example.listarepro.services.ICancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private ICancionService cancionService;

    @PostMapping("/crear")
    public ResponseEntity<?> createCancion(@RequestBody Cancion cancion) {
        if (cancion.getTitle() == null || cancion.getTitle().isEmpty()) {
            return new ResponseEntity<>("El título de la canción no es válido", HttpStatus.BAD_REQUEST);
        }
        Cancion savedCancion = cancionService.save(cancion);
        return new ResponseEntity<>(savedCancion, HttpStatus.CREATED);
    }

    @GetMapping("/todas")
    public List<Cancion> getAllCanciones() {
        return cancionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCancionById(@PathVariable Long id) {
        Cancion cancion = cancionService.findById(id);
        if (cancion == null) {
            return new ResponseEntity<>("Canción no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cancion, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateCancion(@PathVariable Long id, @RequestBody Cancion cancion) {
        Cancion existingCancion = cancionService.findById(id);
        if (existingCancion == null) {
            return new ResponseEntity<>("Canción no encontrada", HttpStatus.NOT_FOUND);
        }
        // Aquí puedes actualizar los atributos de la canción si es necesario.
        existingCancion.setTitle(cancion.getTitle());
        existingCancion.setArtist(cancion.getArtist());
        existingCancion.setAlbum(cancion.getAlbum());
        existingCancion.setYear(cancion.getYear());
        cancionService.save(existingCancion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCancion(@PathVariable Long id) {
        Cancion cancion = cancionService.findById(id);
        if (cancion == null) {
            return new ResponseEntity<>("Canción no encontrada", HttpStatus.NOT_FOUND);
        }
        cancionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
