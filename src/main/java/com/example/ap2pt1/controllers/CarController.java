package com.example.ap2pt1.controllers;

import com.example.ap2pt1.models.Car;
import com.example.ap2pt1.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carros")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService){
        this.carService=carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getCar(@PathVariable int id) {
        return ResponseEntity.ok(this.carService.mostraCarroId(id));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.ok(carService.mostrarTodosCarros());
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        Car criarCarro = carService.criarCarro(newCar);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .path("/{id}")
                .buildAndExpand(criarCarro.getId())
                .toUri();
        return ResponseEntity.created(uri).body(this.carService.criarCarro(newCar));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        this.carService.deletarCarroId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity <Car> updateCar (@RequestBody Car userRequest, @PathVariable int id){
//        Car updatedCar = carService.alterarCarro(userRequest, id);
        return ResponseEntity.ok(this.carService.alterarCarro(userRequest, id));
    }

}


