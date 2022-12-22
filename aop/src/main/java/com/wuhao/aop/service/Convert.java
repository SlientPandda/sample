package com.wuhao.aop.service;

import com.wuhao.aop.dto.OperateLogDto;

/**
 *
 */
public interface Convert<PARAM>{
    OperateLogDto convert(PARAM param);
}
