package com.cj.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 9:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String userIdStr;
    private String username;
    private String trueName;
}
