package org.newops.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "security")
public class Security {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column (name = "symbol")
    private String Symbol;

    @Column (name = "issuedescription")
    public String IssueDescription;

    @Column (name = "nameofcompany")
    public String NameOfCompany;

    @Column (name = "series")
    public String Series;

    @Column(name = "dateoflisting")
    public Date ListingDate;

    @Column(name = "paidupvalue")
    private Double PaidUpValue;

    @Column(name = "marketlot")
    private Integer MarketLot;

    @Column(name = "isin")
    public String ISIN;

    @Column(name= "facevalue")
    private Double FaceValue;

    @Column(name = "iprate")
    private Double IPRate;

    @Column(name = "allotmentdate")
    public Date AllotmentDate;

    @Column(name = "redemptiondate")
    public Date RedemptionDate;

    @Column(name = "redemptionamount")
    private Integer RedemptionAmount;

    @Column(name = "conversiondate")
    private Date ConversionDate;

    @Column(name = "conversionamount")
    private Integer ConversionAmount;

    @Column(name = "interestpaymentdate")
    public Date InterestPaymentDate;

    // Equity

    public Security(String Symbol, String NameOfCompany,String Series, Double FaceValue, Double PaidUpValue, Integer MarketLot, Double IPRate, Date ListingDate, Date AllotmentDate, Date RedemptionDate, Integer RedemptionAmount, Date ConversionDate, Integer ConversionAmount, Date InterestPaymentDate, String ISIN )
    {
       this.Symbol = Symbol;
       this.NameOfCompany = NameOfCompany;
       this.Series = Series;
       this.FaceValue = FaceValue;
       this.PaidUpValue = PaidUpValue;
       this.MarketLot = MarketLot;
       this.IPRate = IPRate;
       this.ListingDate = ListingDate;
       this.AllotmentDate = AllotmentDate;
       this.RedemptionDate = RedemptionDate;
       this.RedemptionAmount = RedemptionAmount;
       this.ConversionDate = ConversionDate;
       this.ConversionAmount = ConversionAmount;
       this.InterestPaymentDate = InterestPaymentDate;
       this.ISIN = ISIN;

    }

    // Debt

    public Security(String Symbol, String NameOfCompany,String Series,Date ListingDate, Double PaidUpValue, Integer MarketLot,  String ISIN,Double FaceValue )
    {
        this.Symbol = Symbol;
        this.NameOfCompany = NameOfCompany;
        this.Series = Series;
        this.ListingDate = ListingDate;
        this.PaidUpValue = PaidUpValue;
        this.MarketLot = MarketLot;
        this.ISIN = ISIN;
        this.FaceValue = FaceValue;

    }

    public Security(){

    }



}


