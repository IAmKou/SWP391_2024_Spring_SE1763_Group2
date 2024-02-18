package model;

import java.util.Date;

public class Request {

    private int requestId;
    private House house_id;
    private User seller_id;
    private User customer_id;
    private Purpose purpose_id;
    private Status request_status;
    private Date date_time;

    public Request() {
    }

    public Request(int requestId, House house_id, User seller_id, User customer_id, Purpose purpose_id, Status request_status, Date date_time) {
        this.requestId = requestId;
        this.house_id = house_id;
        this.seller_id = seller_id;
        this.customer_id = customer_id;
        this.purpose_id = purpose_id;
        this.request_status = request_status;
        this.date_time = date_time;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public House getHouse_id() {
        return house_id;
    }

    public void setHouse_id(House house_id) {
        this.house_id = house_id;
    }

    public User getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(User seller_id) {
        this.seller_id = seller_id;
    }

    public User getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(User customer_id) {
        this.customer_id = customer_id;
    }

    public Purpose getPurpose_id() {
        return purpose_id;
    }

    public void setPurpose_id(Purpose purpose_id) {
        this.purpose_id = purpose_id;
    }

    public Status getRequest_status() {
        return request_status;
    }

    public void setRequest_status(Status request_status) {
        this.request_status = request_status;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    
    @Override
    public String toString() {
        return "Request{" + "requestId=" + requestId + ", house_id=" + house_id + ", seller_id=" + seller_id + ", customer_id=" + customer_id + ", purpose_id=" + purpose_id + ", request_status=" + request_status + ", date_time=" + date_time + '}';
    }
    

}
