package com.ovidiu.hotelapp.service;

import com.ovidiu.hotelapp.model.Room;
import com.ovidiu.hotelapp.repository.RoomRepository;
import com.ovidiu.hotelapp.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service

public class RoomService implements IRoomService{

    private final RoomRepository roomRepository;
    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if (!file.isEmpty()){
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            // The data of an SQL BLOB value can be materialized on the client
            // as an array of bytes (using the method Blob.getBytes)
            //SerialBlob methods make it possible to make a copy of a SerialBlob object as an array of bytes
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }
}
