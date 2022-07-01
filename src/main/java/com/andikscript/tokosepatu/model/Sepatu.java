package com.andikscript.tokosepatu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "sepatu")
public class Sepatu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nama_produk", nullable = false, length = 1024)
    private String namaProduk;

    @Column(name = "ukuran", nullable = false)
    private Integer ukuran;

    @Column(name = "warna", nullable = false)
    private String warna;

    @Column(name = "stok", nullable = false)
    private Integer stok;

    @Column(name = "harga", nullable = false)
    private Integer harga;

    // anotasi @JsonIgnoreProperties digunakan untuk membuat agar tabel relasi di API
    // dapat memunculkan seluruh tabel induknya
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brand")
    private Brand idBrand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Integer getUkuran() {
        return ukuran;
    }

    public void setUkuran(Integer ukuran) {
        this.ukuran = ukuran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Brand getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Brand idBrand) {
        this.idBrand = idBrand;
    }

}