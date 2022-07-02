package com.andikscript.tokosepatu.controller;

import com.andikscript.tokosepatu.exception.ResourceNotFoundException;
import com.andikscript.tokosepatu.model.Sepatu;
import com.andikscript.tokosepatu.repository.SepatuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class SepatuController {
    @Autowired
    private SepatuRepository sepatuRepository;

    // produces digunakan untuk mengirim balik tipe dokumen ke client
    @GetMapping(value = "/sepatu", produces = "application/json")
    public List<Sepatu> getAll() {
        return sepatuRepository.findAll();
    }

    @GetMapping(value = "/sepatu/{id}", produces = "application/json")
    public Optional<Sepatu> getSepatu(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return sepatuRepository.findById(id);
    }

    // consumes digunakan untuk menentukan tipe apa yang di izinkan untuk dikirim
    // ke server
    @PostMapping(value = "/sepatu",
            consumes = "application/json",
            produces = "application/json")
    public Sepatu addSepatu(@RequestBody Sepatu sepatu) {
        return sepatuRepository.save(sepatu);
    }

    // url api dengan request header harus disertai header X-COM-PERSIST = true
    // dan X-COM-LOCATION bersifat opsional
    @PostMapping(value = "/sepatuheader",
            consumes = "application/json",
            produces = "application/json")
    public Sepatu addSepatuWithHeader(@RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                                      @RequestHeader(name = "X-COM-LOCATION", required = false) String headerLocation,
                                      @RequestBody Sepatu sepatu) {
        return sepatuRepository.save(sepatu);
    }
}
