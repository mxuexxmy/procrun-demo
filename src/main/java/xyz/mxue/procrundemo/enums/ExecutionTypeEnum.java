package xyz.mxue.procrundemo.enums;

/**
 * 物理指标计算分组ID枚举
 *
 * @author mxuexxmy
 */
public enum ExecutionTypeEnum {

    /**
     * 运行
     */
    START("start", "运行"),

    /**
     * 停止
     */
    STOP("stop", "停止");

    final String code;

    final String name;


    ExecutionTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
