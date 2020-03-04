package com.joywayi.process;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 条件
 * @author feng
 */
@Entity
@Table(name = "JH_Approve_Condition")
@Data
@EqualsAndHashCode(callSuper=true)
public class ApproveCondition extends BaseEntity {


    private String key;
    private String value;
    @Enumerated(EnumType.STRING)
    private OperatorEnum operator;
}
