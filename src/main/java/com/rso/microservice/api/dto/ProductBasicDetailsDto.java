package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductBasicDetailsDto {

    @JsonProperty("id")
    @NotNull(message = "is required")
    private Long id;

    @JsonProperty("name")
    @NotBlank(message = "is required")
    private String name;

    @JsonProperty("brand")
    @NotBlank(message = "is required")
    private String brand;

    @JsonProperty("product_type")
    @NotBlank(message = "is required")
    private String productType;

    @JsonProperty("concentration")
    @NotNull(message = "is required")
    private Double concentration;

    @JsonProperty("concentration_unit")
    @NotBlank(message = "is required")
    private String concentrationUnit;

    @JsonProperty("image")
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getConcentration() {
        return concentration;
    }

    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public String getConcentrationUnit() {
        return concentrationUnit;
    }

    public void setConcentrationUnit(String concentrationUnit) {
        this.concentrationUnit = concentrationUnit;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
