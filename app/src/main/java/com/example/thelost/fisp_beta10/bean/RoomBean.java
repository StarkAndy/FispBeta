package com.example.thelost.fisp_beta10.bean;

/**
 * Created by Personal Pc on 10/23/2017.
 */

public class RoomBean {


    //Store information short infomation ie.Two Room in Chakrapath
    private String shortInformation;
    //Store location ie.Karnataka ,Bangalore
    private String location;
    //Store description about room,
    private String description;
    //Store if negotation can be done.ie. Yes
    private String negotiation;
    //String if cost can be manimized,ie.Yes
    private String cost;
    //String if room can be available .ie.2
    private String availableRoom;
    //String store the roomtype ie. Single,Kithen
    private String roomType;
    //String Store the facility available,Fan,Ac
    private String facility;
    //String store the looking for information,Looking for
    private String lookingFor;
    //Stpre the first image,for information,
    private byte[] columnImageFirst;
    //Store the second image about the room.
    private byte[] imageSecond;
    //Store the third image of the room.
    private byte[] imageThird;
    //Store the fourth image of the room.
    private byte[] imageFourtth;

/*
Constructor help to assign the value to variables
 */
    public RoomBean(String shortInformation,
                    String location,
                    String description,
                    String negotiation,
                    String cost,
                    String availableRoom,
                    String roomType,
                    String facility,
                    String lookingFor,
                    byte[] columnImageFirst,
                    byte[] imageSecond,
                    byte[] imageThird,
                    byte[] imageFourtth) {
        this.shortInformation = shortInformation;
        this.location = location;
        this.description = description;
        this.negotiation = negotiation;
        this.cost = cost;
        this.availableRoom = availableRoom;
        this.roomType = roomType;
        this.facility = facility;
        this.lookingFor = lookingFor;
        this.columnImageFirst = columnImageFirst;
        this.imageSecond = imageSecond;
        this.imageThird = imageThird;
        this.imageFourtth = imageFourtth;
    }


    public String getShortInformation() {
        return shortInformation;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public String getCost() {
        return cost;
    }

    public String getAvailableRoom() {
        return availableRoom;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getFacility() {
        return facility;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public byte[] getColumnImageFirst() {
        return columnImageFirst;
    }

    public byte[] getImageSecond() {
        return imageSecond;
    }

    public byte[] getImageThird() {
        return imageThird;
    }

    public byte[] getImageFourtth() {
        return imageFourtth;
    }
}