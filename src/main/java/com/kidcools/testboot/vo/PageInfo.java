package com.kidcools.testboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> implements Serializable {
    private Integer totalCount;
    private Integer pageSize;
    private Integer curPageNum;
    private List<T> pagedata;
}
