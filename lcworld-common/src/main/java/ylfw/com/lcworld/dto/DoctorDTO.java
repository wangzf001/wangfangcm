package com.lcworld.dto;

public class DoctorDTO {
    private Integer doctorid;
    private Integer consultcount;
    private Integer totalcount;
    private Integer remaincount;
    
    private String hospitalname;
    private String positionname;
    private String photo;
    private String realname;
    private String skilledinfo;
    private Integer available;
    private Double score;
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public Integer getDoctorid() {
        return doctorid;
    }
    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }
    public Integer getConsultcount() {
        return consultcount;
    }
    public void setConsultcount(Integer consultcount) {
        this.consultcount = consultcount;
    }
    public Integer getTotalcount() {
        return totalcount;
    }
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }
    public Integer getRemaincount() {
        return remaincount;
    }
    public void setRemaincount(Integer remaincount) {
        this.remaincount = remaincount;
    }
    public String getHospitalname() {
        return hospitalname;
    }
    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }
    public String getPositionname() {
        return positionname;
    }
    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getSkilledinfo() {
        return skilledinfo;
    }
    public void setSkilledinfo(String skilledinfo) {
        this.skilledinfo = skilledinfo;
    }
    public Integer getAvailable() {
        return available;
    }
    public void setAvailable(Integer available) {
        this.available = available;
    }
    
    
}
