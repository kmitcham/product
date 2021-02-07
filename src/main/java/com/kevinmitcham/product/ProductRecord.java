package com.kevinmitcham.product;

import java.time.Instant;

public class ProductRecord {

    public final String id;
    public String description;
    public String regularDisplayPrice;
    public Double regularCalculatorPrice;
    public String saleDisplayPrice;
    public Double saleCaclulatorPrice;
    public String unitOfMeasure; // TODO: switch this to enum of "Each|Pound"
    public String productSize;
    public Double taxRate; // 0 or 7.775%  TODO: confirm how we want to store tax value?  String, 7.775, or 0.07775
    public Instant lastUpdated;

    public ProductRecord(String id){
        this.id = id;
        lastUpdated = Instant.now();
    }

    public String getId() {
        return this.id;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegularDisplayPrice() {
        return this.regularDisplayPrice;
    }

    public void setRegularDisplayPrice(String regularDisplayPrice) {
        this.regularDisplayPrice = regularDisplayPrice;
    }

    public Double getRegularCalculatorPrice() {
        return this.regularCalculatorPrice;
    }

    public void setRegularCalculatorPrice(Double regularCalculatorPrice) {
        this.regularCalculatorPrice = regularCalculatorPrice;
    }

    public String getSaleDisplayPrice() {
        return this.saleDisplayPrice;
    }

    public void setSaleDisplayPrice(String saleDisplayPrice) {
        this.saleDisplayPrice = saleDisplayPrice;
    }

    public Double getSaleCaclulatorPrice() {
        return this.saleCaclulatorPrice;
    }

    public void setSaleCaclulatorPrice(Double saleCaclulatorPrice) {
        this.saleCaclulatorPrice = saleCaclulatorPrice;
    }

    public String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getProductSize() {
        return this.productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Double getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public Instant getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", regularDisplayPrice='" + getRegularDisplayPrice() + "'" +
            ", regularCalculatorPrice='" + getRegularCalculatorPrice() + "'" +
            ", saleDisplayPrice='" + getSaleDisplayPrice() + "'" +
            ", saleCaclulatorPrice='" + getSaleCaclulatorPrice() + "'" +
            ", unitOfMeasure='" + getUnitOfMeasure() + "'" +
            ", productSize='" + getProductSize() + "'" +
            ", taxRate='" + getTaxRate() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            "}";
    }
    
}
