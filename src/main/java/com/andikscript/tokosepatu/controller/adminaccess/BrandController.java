package com.andikscript.tokosepatu.controller.adminaccess;

import com.andikscript.tokosepatu.exception.ResourceNotFoundException;
import com.andikscript.tokosepatu.model.Brand;
import com.andikscript.tokosepatu.patch.Patch;
import com.andikscript.tokosepatu.repository.BrandRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping(value = "/brands", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Brand> getBrandAll() {
        return brandRepository.findAll();
    }

    @GetMapping(value = "/brand/{id}", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public Brand getBrand(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    @PostMapping(value = "/brand", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public Brand addBrand(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    @PutMapping(value = "/brand/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> putSiswa(@PathVariable(value = "id") String id, @RequestBody Brand brand) {
        Optional<Brand> getBrand = brandRepository.findById(id);

        if (!getBrand.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        brand.setId(id);
        brandRepository.save(brand);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/brand/{id}", consumes = "application/json-patch+json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Brand> patchBrand(@PathVariable(value = "id") String id,
                                            @RequestBody JsonPatch patch) {
        try {
            Brand brand = brandRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
            Brand brandPatch = Patch.applyToBrand(patch, brand);
            brandRepository.save(brandPatch);
            return ResponseEntity.ok(brandPatch);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/brand/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteBrand(@PathVariable(value = "id") String id) {
        Optional<Brand> getBrand = brandRepository.findById(id);

        if (!getBrand.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        brandRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
