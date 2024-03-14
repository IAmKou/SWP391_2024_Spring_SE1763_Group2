/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author luong
 */
public class Report {

    private int report_id;
    private int fid;
    private int uid;
    private LocalDateTime report_time;
    private int pid;
    private String fcontent;

    public Report(int report_id, int fid, int uid, LocalDateTime report_time, int pid, String fcontent) {
        this.report_id = report_id;
        this.fid = fid;
        this.uid = uid;
        this.report_time = report_time;
        this.pid = pid;
        this.fcontent = fcontent;
    }

    public Report() {
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getFid() {
        return fid;
    }

    public int getReport_id() {
        return report_id;
    }

    public LocalDateTime getReport_time() {
        return report_time;
    }

    public int getUid() {
        return uid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public void setReport_time(LocalDateTime report_time) {
        this.report_time = report_time;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
