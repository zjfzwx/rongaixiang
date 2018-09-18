package com.dingshen.rongaixiang.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Job {
    private Integer id;

    private String jobName;

    private String jobDescription;

    private String jobNature;

    private String experience;

    private String additional;

    private String salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}