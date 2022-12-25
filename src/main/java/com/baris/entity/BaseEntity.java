package com.baris.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    private Date creationDate;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
