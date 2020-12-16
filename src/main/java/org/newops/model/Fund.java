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
    private String AMC;

    @Id
    @Column(name = "amficode")
    private Integer AMFICode;

    @Column(name = "schemename")
    private String SchemeName;

    @Column(name = "schemetype")
    private String SchemeType;

    @Column(name = "schemecategory")
    private String SchemeCategory;

    @Column(name= "schemenavname")
    private String SchemeNAVName;

    @Column(name = "schememinamount")
    private String SchemeMinAmount;

    @Column(name = "launchdate")
    private Date LaunchDate;

    @Column(name = "closuredate")
    private Date ClosureDate;

    @Column(name = "isins")
    private String ISIN;

    @Column(name = "amccode")
    private String AMCCode;

    public Fund(String AMC,Integer AMFICode,String SchemeName,String SchemeType,String SchemeCategory,String SchemeNAVName,String SchemeMinAmount,Date LaunchDate, Date ClosureDate, String ISIN, String AMCCode)
    {
        this.AMC = AMC;
        this.AMFICode = AMFICode;
        this.SchemeName = SchemeName;
        this.SchemeType = SchemeType;
        this.SchemeCategory = SchemeCategory;
        this.SchemeNAVName = SchemeNAVName;
        this.SchemeMinAmount = SchemeMinAmount;
        this.LaunchDate = LaunchDate;
        this.ClosureDate = ClosureDate;
        this.ISIN = ISIN;
        this.AMCCode = AMCCode;

    }

    public Fund(){

    }



}


