package com.example.ap2pt1.services;

import com.example.ap2pt1.models.Car;
import com.example.ap2pt1.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> mostrarTodosCarros() {
        return carRepository.findAll();
    }

    public Optional<Car> mostraCarroId(int id) {
        return carRepository.findById(id);
    }

    public Car criarCarro(Car carro) {
        return carRepository.save(carro);
    }

    public void deletarCarroId(int id) {
        carRepository.deleteById(id);
    }

    public Car alterarCarro(Car carRequest, int id) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setMarca(carRequest.getMarca());
                    car.setModelo(carRequest.getModelo());
                    car.setAno(carRequest.getAno());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }
}
