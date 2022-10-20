package com.zgshen.code.permission;

/**
 * 位运算
 * 权限管理
 */
public class Permission {

    private final static int BASE   = 1; //基数
    private final static int ADD    = 1;//新增操作权限
    private final static int UPDATE = 2;//更新操作权限
    private final static int DELETE = 3;//删除操作权限

    /**
     * 加权限
     * @param per 原权限
     * @param opr 操作
     * @return
     */
    public static int addPermission(int per, int opr) {
        int p = BASE << opr;
        per |= p;
        return per;
    }

    /**
     * 查权限
     * @param per
     * @param opr
     * @return
     */
    public static boolean hasPermission(int per, int opr) {
        int p = BASE << opr;
        return (per & p) == p;
    }

    /**
     * 删除权限
     * @param per
     * @param opr
     * @return
     */
    public static int delPermission(int per, int opr) {
        int p = BASE << opr;
        p = ~p;
        return per & p;
    }


    public static void main(String[] args) {
        int perssion = 0;
        perssion = addPermission(perssion, ADD);
        System.out.println(hasPermission(perssion, DELETE));
        System.out.println(hasPermission(perssion, ADD));

        perssion = addPermission(perssion, DELETE);

        System.out.println(hasPermission(perssion, ADD));
        System.out.println(hasPermission(perssion, UPDATE));
        System.out.println(hasPermission(perssion, DELETE));
    }

}
