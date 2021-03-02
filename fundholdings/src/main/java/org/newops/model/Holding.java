package org.newops.model;

import javax.persistence.*;
import java.util.Date;;

@Entity
@Table(name = "holding")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "fundcode")
    private String fundcode;

    @Column(name = "fundname")
    private String fundname;

    @Column(name = "instrumentname")
    private String instrumentname;

    @Column(name = "isin")
    private String isin;

    @Column(name = "industryrating")
    private String industryrating;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "marketvalue")
    private Double marketvalue;

    @Column(name = "netassetspercentage")
    private Double netassetspercentage;

    @Column(name = "yield")
    private Double yield;

    @Column(name = "asofdate")
    private Date asofdate;

    public Holding() {

    }

    public Holding(String fundcode, String fundname, String instrumentname, String isin,String industryrating, Double quantity,Double marketvalue,Double netassetspercentage, Double yield, Date asofdate) {
        this.fundcode = fundcode;
        this.fundname = fundname;
        this.instrumentname = instrumentname;
        this.isin = isin;
        this.industryrating = industryrating;
        this.quantity = quantity;
        this.marketvalue = marketvalue;
        this.netassetspercentage = netassetspercentage;
        this.yield = yield;
        this.asofdate = asofdate;
    }

    public String getFundCode() { return fundcode;   }

    public void setFundCode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getFundName() { return fundname;   }

    public void setFundName(String fundname) {
        this.fundname = fundname;
    }

    public String getInstrumentname() {
        return instrumentname;
    }

    public void setInstrumentname(String instrumentname) {
        this.instrumentname = instrumentname;
    }

    public String getISIN() {
        return isin;
    }

    public void setISIN(String isin) {
        this.isin = isin;
    }

    public String getIndustryrating() {
        return industryrating;
    }

    public void setIndustryrating(String industryrating) {
        this.industryrating = industryrating;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getMarketvalue() {
        return marketvalue;
    }

    public void setMarketvalue(Double marketvalue) {
        this.marketvalue = marketvalue;
    }

    public Double getNetAssetsPercentage() {
        return netassetspercentage;
    }

    public void setNetAssetsPercentage(Double  netassetspercentage) {
        this.netassetspercentage = netassetspercentage;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public Date getAsOfDate() {
        return asofdate;
    }

    public void setAsOfDate(Date asofdate) {
        this.asofdate = asofdate;
    }

    @Override
    public String toString() {
        return "Holding [Fund Name=" + fundname + ", Instrument Name=" + instrumentname + ", Quantity=" + quantity + ", Marketvalue=" + marketvalue + ", Yield=" + yield + "]";
    }
}


