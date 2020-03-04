package com.joywayi.process;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author feng
 */
@Data
@MappedSuperclass
public  abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
