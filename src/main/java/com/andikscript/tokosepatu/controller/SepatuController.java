package com.andikscript.tokosepatu.controller;

import com.andikscript.tokosepatu.exception.ResourceNotFoundException;
import com.andikscript.tokosepatu.model.Sepatu;
import com.andikscript.tokosepatu.repository.SepatuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SepatuController {
    @Autowired
    private SepatuRepository sepatuRepository;

    @GetMapping("/sepatu")
    public List<Sepatu> getAll() {
        return sepatuRepository.findAll();
    }

    @GetMapping("/sepatu/{id}")
    public Optional<Sepatu> getSepatu(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return sepatuRepository.findById(id);
    }

    @PostMapping("/sepatu")
    public Sepatu addSepatu(@RequestBody Sepatu sepatu) {
        return sepatuRepository.save(sepatu);
    }
}
