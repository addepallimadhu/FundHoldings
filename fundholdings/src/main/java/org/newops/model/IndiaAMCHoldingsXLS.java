package org.newops.model;

import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.Column;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@JsonComponent
public class IndiaAMCHoldingsXLS {
//1,1,null,1,2,4,5,6,7,8,1, new String[] {"TREPS","Net Current Assets","Reverse Repo"},1,"Total Net Assets");
    private Integer fundNameRowIndex = 1 ;
    private Integer fundNameColumnIndex =1  ;
    private Date asOfDate;

    private Integer instrumentNameColumnIndex = 1;
    private Integer isinColumnIndex = 2 ;
    private Integer couponColumnIndex ;
    private Integer industryRatingColumnIndex = 4;
    private Integer quantityColumnIndex = 5;
    private Integer marketValueColumnIndex = 6;
    private Integer netAssetsPercentageColumnIndex = 7;

    private Integer yieldColumnIndex = 8;

    private Integer boldExcludeColumnIndex = 1;
    private String[]  boldExcludeException =  {"TREPS","Net Current Assets","Reverse Repo"};
    private Integer endOfDataColumnIndex = 1;
    private String endOfDataColumnString = "Total Net Assets";

    private Integer asOfDateRowIndex = 2;
    private Integer asOfDateColumnIndex = 1;
    private String asOfDatePattern = "MMM dd,yyyy";
    private String asOfDatePrefix = "Portfolio as on ";
    private String[] ignoreSheets = {"Index"};
    private Integer subHeadingColumnIndex = 1;
  //  private List<String> includeWhatSoEver;

}
