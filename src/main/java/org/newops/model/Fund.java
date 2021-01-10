package org.newops.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "fund")
public class Fund {
    @Column (name = "amc")
    public String amc;

    @Id
    @Column(name = "amficode")
    public Integer amfiCode;

    @Column(name = "schemename")
    public String schemeName;

    @Column(name = "schemetype")
    public String schemeType;

    @Column(name = "schemecategory")
    public String schemeCategory;

    @Column(name= "schemenavname")
    public String SchemeNAVName;

    @Column(name = "schememinamount")
    public String schemeMinAmount;

    @Column(name = "launchdate")
    public Date launchDate;

    @Column(name = "closuredate")
    public Date closureDate;

    @Column(name = "isins")
    public String isin;

    @Column(name = "amccode")
    public String amcCode;

    public Fund(String AMC,Integer AMFICode,String SchemeName,String SchemeType,String SchemeCategory,String SchemeNAVName,String SchemeMinAmount,Date LaunchDate, Date ClosureDate, String ISIN, String AMCCode)
    {
        this.amc = AMC;
        this.amfiCode = AMFICode;
        this.schemeName = SchemeName;
        this.schemeType = SchemeType;
        this.schemeCategory = SchemeCategory;
        this.SchemeNAVName = SchemeNAVName;
        this.schemeMinAmount = SchemeMinAmount;
        this.launchDate = LaunchDate;
        this.closureDate = ClosureDate;
        this.isin = ISIN;
        this.amcCode = AMCCode;

    }

    public Fund(){

    }



}


