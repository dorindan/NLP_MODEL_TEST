package smart.smartbook.model;

import lombok.Data;

import java.io.InputStream;

@Data
public class ExecutionStatus {

    private String bookName;

    private String algorithm;

    private String model;

    private Long executionTime;
}
