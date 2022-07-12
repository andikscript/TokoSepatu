package com.andikscript.tokosepatu.controller.publicaccess;

import com.andikscript.tokosepatu.exception.ResourceNotFoundException;
import com.andikscript.tokosepatu.model.Sepatu;
import com.andikscript.tokosepatu.patch.Patch;
import com.andikscript.tokosepatu.repository.SepatuRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class SepatuController {
    @Autowired
    private SepatuRepository sepatuRepository;

    // produces digunakan untuk mengirim balik tipe dokumen ke client
    @GetMapping(value = "/shoeses", produces = "application/json")
    public List<Sepatu> getAll() {
        return sepatuRepository.findAll();
    }

    @GetMapping(value = "/shoes/{id}", produces = "application/json")
    public Sepatu getSepatu(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return sepatuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    // consumes digunakan untuk menentukan tipe apa yang di izinkan untuk dikirim
    // ke server
    @PostMapping(value = "/shoes",
            consumes = "application/json",
            produces = "application/json")
    public Sepatu addSepatu(@RequestBody Sepatu sepatu) {
        return sepatuRepository.save(sepatu);
    }

    // url api dengan request header harus disertai header X-COM-PERSIST = true
    // dan X-COM-LOCATION bersifat opsional
    @PostMapping(value = "/shoesheader",
            consumes = "application/json",
            produces = "application/json")
    public Sepatu addSepatuWithHeader(@RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                                      @RequestHeader(name = "X-COM-LOCATION", required = false) String headerLocation,
                                      @RequestBody Sepatu sepatu) {
        return sepatuRepository.save(sepatu);
    }

    // metode put adalah mengganti nilai seluruh baris dengan nilai yang baru alhasil memerlukan waktu yang agak lama
    @PutMapping(value = "/shoes/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> putSepatu(@PathVariable(value = "id") Integer id, @RequestBody Sepatu sepatu) {
        Optional<Sepatu> getSepatu =  sepatuRepository.findById(id);
        if (!getSepatu.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sepatu.setId(id);
        sepatuRepository.save(sepatu);
        return ResponseEntity.noContent().build();
    }

    // metode patch adalah mengganti nilai kolom bagian tertentu saja dari baris alhasil memerlukan waktu yang cepat
    @PatchMapping(value = "/shoes/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Sepatu> patchSepatu(@PathVariable(value = "id") Integer id,
                                             @RequestBody JsonPatch patch) throws ResourceNotFoundException {
        try {
            Sepatu sepatu = sepatuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
            Sepatu sepatuPatch = Patch.applyToSepatu(patch, sepatu);
            sepatuRepository.save(sepatuPatch);
            return ResponseEntity.ok(sepatuPatch);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/shoes/{id}")
    public ResponseEntity deleteSepatu(@PathVariable(value = "id") Integer id) {
        Optional<Sepatu> getSepatu = sepatuRepository.findById(id);

        if (!getSepatu.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        sepatuRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
