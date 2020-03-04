package com.joywayi.process;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 审查要素表
 * @author fengqilin
 *
 */
@Entity
@Table(name = "JH_Audit_Element")
@Data
@EqualsAndHashCode(callSuper=true)
public class AuditElement extends BaseEntity {


    private String processInstanceId;
    private String elementKey;
    private String elementName;


}
