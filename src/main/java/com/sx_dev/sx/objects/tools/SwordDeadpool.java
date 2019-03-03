package com.sx_dev.sx.objects.tools;

import com.sx_dev.sx.tabs.CustomItemGroup;

import static com.sx_dev.sx.init.ItemInit.TOOL_DEADPOOL;

public class SwordDeadpool extends ToolSword {
    public SwordDeadpool(String name) {
        super(name, TOOL_DEADPOOL, CustomItemGroup.Groups.SUPERHEROES_X_TAB_MARVEL);
    }
}
