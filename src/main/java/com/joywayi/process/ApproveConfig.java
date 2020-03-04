package com.joywayi.process;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author feng
 */

@Entity
@Table(name = "JH_Approve_Config")
@Data
@EqualsAndHashCode(callSuper = true)
public class ApproveConfig extends BaseEntity {

    private String name;
    /**
     * 流程defineKey
     */
    private String processKey;
    /**
     * 任务节点key
     */
    private String taskKey;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<ApproveCondition> conditions;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<ApproveNode> approveNodes;
}
