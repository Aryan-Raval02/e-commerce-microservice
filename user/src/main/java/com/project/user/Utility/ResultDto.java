package com.project.user.Utility;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"total", "list"})
public class ResultDto<T> {
    private Integer total;
    private List<T> list;
}
