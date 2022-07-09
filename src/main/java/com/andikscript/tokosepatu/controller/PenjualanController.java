package com.andikscript.tokosepatu.controller;

import com.andikscript.tokosepatu.model.Brand;
import com.andikscript.tokosepatu.model.Penjualan;
import com.andikscript.tokosepatu.repository.PenjualanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class PenjualanController {

    @Autowired
    private PenjualanRepository penjualanRepository;

    @GetMapping(value = "/sells", produces = "application/json")
    public List<Penjualan> getAllPenjualan() {
        return penjualanRepository.findAll();
    }

    @PostMapping(value = "/sell", consumes = "application/json", produces = "application/json")
    public Penjualan addPenjualan(@RequestBody Penjualan penjualan) {
        return penjualanRepository.save(penjualan);
    }

    @PutMapping(value = "/sell/{id}", consumes = "application/json")
    public ResponseEntity<Object> putPenjualan(@PathVariable(value = "id") Integer id, @RequestBody Penjualan penjualan) {
        Optional<Penjualan> getPenjualan = penjualanRepository.findById(id);

        if (!getPenjualan.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        penjualan.setId(id);
        penjualanRepository.save(penjualan);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
