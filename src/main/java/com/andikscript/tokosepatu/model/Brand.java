package com.andikscript.tokosepatu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @Column(name = "id_brand", nullable = false, length = 3)
    private String id;

    @Column(name = "nama_brand", nullable = false, length = 1024)
    private String namaBrand;

    @Column(name = "kategori", nullable = false, length = 1024)
    private String kategori;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaBrand() {
        return namaBrand;
    }

    public void setNamaBrand(String namaBrand) {
        this.namaBrand = namaBrand;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

}