package smart.smartbook.model;

import lombok.Data;

import java.io.InputStream;
import java.util.List;

@Data
public class ExecutionStatus {

    private String bookName;

    private String algorithm;

    private String model;

    private Long modelReadExecutionTime1;
    private Long modelReadExecutionTime2;
    private Long modelReadExecutionTime3;
    private Long modelReadExecutionTime4;
    private Long modelReadExecutionTime5;
    private Long modelReadExecutionTime6;
    private Long modelReadExecutionTime7;
    private Long modelReadExecutionTime8;
    private Long modelReadExecutionTime9;
    private Long modelReadExecutionTime10;

    private Double modelReadExecutionTimeAverage;

    private Long modelprocessExecutionTime1;
    private Long modelprocessExecutionTime2;
    private Long modelprocessExecutionTime3;
    private Long modelprocessExecutionTime4;
    private Long modelprocessExecutionTime5;
    private Long modelprocessExecutionTime6;
    private Long modelprocessExecutionTime7;
    private Long modelprocessExecutionTime8;
    private Long modelprocessExecutionTime9;
    private Long modelprocessExecutionTime10;

    private Double modelprocessExecutionTimeAverage;

    private List<String> results;

}
