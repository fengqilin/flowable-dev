package com.joywayi.process;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 执行的流程配置
 * 在条件确定了一条执行流程后，把对应的节点复制到这个表中
 * 这就是说当修改审批后，对已经确定审批流程的任务是不生效的
 */
@Entity
@Table(name = "JH_Approve_Run_Config")
@Data
@EqualsAndHashCode(callSuper=true)
public class ApproveRunConfig extends BaseEntity {
    //流程实例ID
    private String processInstanceId;
    //流程唯一ID
    private String processDefineId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ApproveRunNode> approveRunNodes;
}
