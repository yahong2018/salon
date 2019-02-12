package com.zhxh.core.data.DataCode;

import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("cCodeCDAO")
public class CCodeDAO extends BaseDAOWithEntity<CCode> {
    public List<CCode> getCodeByType(int type) {

        return null;
    }
}
