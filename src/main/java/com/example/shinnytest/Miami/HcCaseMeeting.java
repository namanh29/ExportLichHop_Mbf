package com.example.shinnytest.Miami;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HcCaseMeeting {
    private String caseMasterId;
    private String flowInstanceId;
    private String caseType;
    private String caseTitle;
    private String requestUserId;
    private String requestUserFullname;
    private String requestDeptName;
    private String requestRoleId;
    private String requestDeptId;
    private String status;
    private Long isDelete;
    private Date startTime;
    private Date endTime;
    private List<String> userDeptRoleIDList;
    private String userDeptRoleId;
    private String hcCasePhonghopId;
    private String userId;
    private String meetingMemberFullname;
    private String roomId;
    private String services;
    private String roomName;
    private String roomDesc;
    private String criticalLevel;

    public HcCaseMeeting() {
    }

    public HcCaseMeeting(String caseMasterId,
                         String flowInstanceId,
                         String caseType,
                         String caseTitle,
                         String requestUserId,
                         String requestUserFullname,
                         String requestDeptName,
                         String requestRoleId,
                         String requestDeptId,
                         String status,
                         Long isDelete,
                         Date startTime,
                         Date endTime,
                         List<String> userDeptRoleIDList,
                         String roomName,
                         String hcCasePhonghopId,
                         String services,
                         String criticalLevel) {
        this.caseMasterId = caseMasterId;
        this.flowInstanceId = flowInstanceId;
        this.caseType = caseType;
        this.caseTitle = caseTitle;
        this.requestUserId = requestUserId;
        this.requestUserFullname = requestUserFullname;
        this.requestDeptName = requestDeptName;
        this.requestRoleId = requestRoleId;
        this.requestDeptId = requestDeptId;
        this.status = status;
        this.isDelete = isDelete;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userDeptRoleIDList = userDeptRoleIDList;
        this.roomName = roomName;
        this.hcCasePhonghopId = hcCasePhonghopId;
        this.services = services;
        this.criticalLevel = criticalLevel;
    }
}

