package com.andikscript.tokosepatu.patch;

import com.andikscript.tokosepatu.model.Sepatu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public class SepatuPatch {

    // method yang digunakan untuk menambal / patch terhadap suatu object yang akan di ubah di @PatchMapping
    public static Sepatu applyToSepatu(
            JsonPatch patch, Sepatu targetSepatu
    ) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetSepatu, JsonNode.class));
        return objectMapper.treeToValue(patched, Sepatu.class);
    }
}
