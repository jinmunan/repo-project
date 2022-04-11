package com.cj.crm.query;

import com.cj.crm.common.base.BaseQuery;

/**
 * Created by Jinmunan
 * 2022/4/11
 * 11:15
 */
public class RoleQuery extends BaseQuery {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
