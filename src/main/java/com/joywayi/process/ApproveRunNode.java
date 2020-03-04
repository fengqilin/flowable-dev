package com.joywayi.process;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JH_Approve_RUN_Node")
@Data
@EqualsAndHashCode(callSuper=true)
public class ApproveRunNode extends ApproveNode {

    private Boolean executed;
}
