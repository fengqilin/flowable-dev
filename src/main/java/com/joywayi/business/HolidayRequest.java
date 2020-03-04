package com.joywayi.business;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author feng
 */
@Entity
@Table(name = "JH_Biz_HolidayRequest")
public class HolidayRequest extends BaseEntity {

    private String days;
    private String reason;
    private String requestUserId;

}
