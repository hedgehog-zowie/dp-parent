package com.iuni.dp.admin.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class Status {

    /**
     * 退货单状态
     */
    public enum BackStatus {
        application("application", "申请中"),
        audit("audit", "已审核待确认退款"),
        cancelled("cancelled", "已取消"),
        unusual("unusual", "收包异常"),
        receivedPacket("receivedPacket", "已收包待审核"),
        completed("completed", "已退款"),
        auditNoPass("auditNoPass", "审核不通过"),;
        private String code;
        private String name;

        BackStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static List<BackStatus> getAllStatus() {
            List<BackStatus> statusList = new ArrayList<BackStatus>();
            statusList.add(BackStatus.application);
            statusList.add(BackStatus.audit);
            statusList.add(BackStatus.cancelled);
            statusList.add(BackStatus.unusual);
            statusList.add(BackStatus.receivedPacket);
            statusList.add(BackStatus.completed);
            statusList.add(BackStatus.auditNoPass);
            return statusList;
        }
    }

    /**
     * 换货单状态
     */
    public enum ExchangeStatus {
        EX_INFO_STATUS_QX("cancle", "已取消"),
        EX_INFO_STATUS_QXDFH("cancleWaitDelivery", "取消待发货"),
        EX_INFO_STATUS_DSB("waitReceivePackage", "待收包"),
        EX_INFO_STATUS_SBYC("receivePackageUnusual", "换货收包异常"),
        EX_INFO_STATUS_DCJ("waitInitialCheck", "待售后初检"),
        EX_INFO_STATUS_DSHSH("waitGassAudit", "待售后审核"),
        EX_INFO_STATUS_YFH("shipped", "已发货"),
        EX_INFO_STATUS_YQS("signed", "已签收"),
        EX_INFO_STATUS_YWC("completed", "已完成"),
        EX_INFO_STATUS_DKFSH("waitCustomerAudit", "待客服审核"),
        EX_INFO_STATUS_DFH("waitDelivery", "待发货"),
        EX_INFO_STATUS_ZBX("cancleToRepairs", "转报修"),
        EX_INFO_STATUS_ZTH("cancleToReturn", "转退货"),
        EX_INFO_STATUS_QT("cancleToOther", "作废"),;

        private String code;
        private String name;

        ExchangeStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static List<ExchangeStatus> getAllStatus() {
            List<ExchangeStatus> statusList = new ArrayList<ExchangeStatus>();
            statusList.add(EX_INFO_STATUS_QX);
            statusList.add(EX_INFO_STATUS_QXDFH);
            statusList.add(EX_INFO_STATUS_DSB);
            statusList.add(EX_INFO_STATUS_SBYC);
            statusList.add(EX_INFO_STATUS_DCJ);
            statusList.add(EX_INFO_STATUS_DSHSH);
            statusList.add(EX_INFO_STATUS_YFH);
            statusList.add(EX_INFO_STATUS_YQS);
            statusList.add(EX_INFO_STATUS_YWC);
            statusList.add(EX_INFO_STATUS_DKFSH);
            statusList.add(EX_INFO_STATUS_DFH);
            statusList.add(EX_INFO_STATUS_ZBX);
            statusList.add(EX_INFO_STATUS_ZTH);
            statusList.add(EX_INFO_STATUS_QT);
            return statusList;
        }
    }

    /**
     * 维修单状态
     */
    public enum RepairStatus {
        REP_INFO_STATUS_QX("0", "已取消"),
        REP_INFO_STATUS_DSB("1", "待收包"),
        REP_INFO_STATUS_SBYC("4", "报修收包异常"),
        REP_INFO_STATUS_DSHCJ("3", "待售后初检"),
        REP_INFO_STATUS_DKFSH("14", "待客服审核"),
        REP_INFO_STATUS_DSHWX("5", "待售后维修"),
        REP_INFO_STATUS_DFK("15", "待付款"),
        REP_INFO_STATUS_DFH("2", "待发货"),
        REP_INFO_STATUS_YFH("6", "已发货"),
        REP_INFO_STATUS_YQS("8", "已签收"),
        REP_INFO_STATUS_YWC("10", "已完成"),
        REP_INFO_STATUS_ZTH("16", "转退货"),
        REP_INFO_STATUS_ZHH("17", "转换货"),
        REP_INFO_STATUS_QT("18", "作废"),
        REP_INFO_STATUS_QXDFH("19", "取消待发货"),;
        private String code;
        private String name;

        RepairStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public static List<RepairStatus> getAllStatus() {
            List<RepairStatus> statusList = new ArrayList<RepairStatus>();
            statusList.add(REP_INFO_STATUS_QX);
            statusList.add(REP_INFO_STATUS_DSB);
            statusList.add(REP_INFO_STATUS_SBYC);
            statusList.add(REP_INFO_STATUS_DSHCJ);
            statusList.add(REP_INFO_STATUS_DKFSH);
            statusList.add(REP_INFO_STATUS_DSHWX);
            statusList.add(REP_INFO_STATUS_DFK);
            statusList.add(REP_INFO_STATUS_DFH);
            statusList.add(REP_INFO_STATUS_YFH);
            statusList.add(REP_INFO_STATUS_YQS);
            statusList.add(REP_INFO_STATUS_YWC);
            statusList.add(REP_INFO_STATUS_ZTH);
            statusList.add(REP_INFO_STATUS_ZHH);
            statusList.add(REP_INFO_STATUS_QT);
            statusList.add(REP_INFO_STATUS_QXDFH);
            return statusList;
        }
    }

}
