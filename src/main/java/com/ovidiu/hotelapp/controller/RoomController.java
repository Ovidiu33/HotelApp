package com.ovidiu.hotelapp.controller;
import com.ovidiu.hotelapp.model.Room;
import com.ovidiu.hotelapp.response.RoomResponse;
import com.ovidiu.hotelapp.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
//CORS PROBLEM FIX:
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class RoomController {

    private final IRoomService roomService;

    @PostMapping("/add/new-room")

    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {
        Room savedRoom=roomService.addNewRoom(photo,roomType,roomPrice);
        RoomResponse response = new RoomResponse(savedRoom.getId() , savedRoom.getRoomType() ,
                savedRoom.getRoomPrice());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/room/types")
    public List<String> getRoomTypes(){
        return  roomService.getAllRoomTypes();
    }
}
