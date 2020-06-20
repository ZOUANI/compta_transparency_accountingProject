package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Role;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.RoleVo;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter<Role, RoleVo> {
    @Override
    public Role toItem(RoleVo vo) {
        if (vo == null) {
            return null;
        } else {
            Role item = new Role();

            if (StringUtil.isNotEmpty(vo.getAuthority())) {
                item.setAuthority(vo.getAuthority());
            }


            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }


            return item;
        }
    }

    @Override
    public RoleVo toVo(Role item) {
        if (item == null) {
            return null;
        } else {
            RoleVo vo = new RoleVo();

            if (StringUtil.isNotEmpty(item.getAuthority())) {
                vo.setAuthority(item.getAuthority());
            }


            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }


            return vo;
        }
    }
}
