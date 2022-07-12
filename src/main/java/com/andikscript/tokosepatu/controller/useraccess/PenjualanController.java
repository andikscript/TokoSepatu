package com.andikscript.tokosepatu.controller.useraccess;

import com.andikscript.tokosepatu.exception.ResourceNotFoundException;
import com.andikscript.tokosepatu.model.Penjualan;
import com.andikscript.tokosepatu.repository.PenjualanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/sell-management")
public class PenjualanController {

    @Autowired
    private PenjualanRepository penjualanRepository;

    @GetMapping(value = "/sells", produces = "application/json")
    public List<Penjualan> getAllPenjualan() {
        return penjualanRepository.findAll();
    }

    @GetMapping(value = "/sell/{id}", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public Penjualan getPenjualan(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return penjualanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    @PostMapping(value = "/sell", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public Penjualan addPenjualan(@RequestBody Penjualan penjualan) {
        return penjualanRepository.save(penjualan);
    }

    @PutMapping(value = "/sell/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> putPenjualan(@PathVariable(value = "id") Integer id, @RequestBody Penjualan penjualan) {
        Optional<Penjualan> getPenjualan = penjualanRepository.findById(id);

        if (!getPenjualan.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        penjualan.setId(id);
        penjualanRepository.save(penjualan);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/sell/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deletePenjualan(@PathVariable(value = "id") Integer id) {
        Optional<Penjualan> getPenjualan = penjualanRepository.findById(id);

        if (!getPenjualan.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        penjualanRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
