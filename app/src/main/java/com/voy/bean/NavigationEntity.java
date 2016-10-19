package com.voy.bean;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public class NavigationEntity extends BaseEntity {

    private int iconResId;

    public NavigationEntity(String id, String name, int iconResId) {
        super(id, name);
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
