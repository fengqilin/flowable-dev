package com.joywayi.process;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 审批流程
 */
@Entity
@Table(name = "JH_Approve_Node")
@Data
@EqualsAndHashCode(callSuper=true)
public class ApproveNode extends BaseEntity {


    private String name;
    private String value;
    @Enumerated(EnumType.STRING)
    private ValueTypeEnum valueType;


}
