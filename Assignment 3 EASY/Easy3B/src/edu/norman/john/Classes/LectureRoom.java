package edu.norman.john.Classes;

/**
 * Class representing a lecture room
 */
public class LectureRoom {
    private final int roomCapacity; // 1 - 2^32 - 1
    String roomName;

    /**
     * Initializes capactiy and room name
     *
     * @param roomCapacity max num of people
     * @param roomName name of room
     */
    public LectureRoom(int roomCapacity, String roomName){
        this.roomCapacity = roomCapacity;
        this.roomName = roomName;
    }
}
