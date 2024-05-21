package org.raven.logger.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

/**
 * @author by yanfeng
 * date 2021/9/14 18:52
 */
@Deprecated
@Getter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class Trace {

    private String remote;

    private String endpoint;

    private Date startTime;

    private Date endTime;

    private Long timeLength;

    private Boolean success;

    private String code;

    private Map<String, Object> ext;

}
