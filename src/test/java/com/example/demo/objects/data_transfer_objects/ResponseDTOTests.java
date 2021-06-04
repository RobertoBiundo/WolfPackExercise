package com.example.demo.objects.data_transfer_objects;

import com.example.demo.objects.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseDTOTests {

    @Test
    void instantiateMessageEntity(){
        boolean status = true;
        String message = "responce message";

        ResponseDTO DTOentity = new ResponseDTO(status, message);

        assertEquals(status, DTOentity.getStatus());
        assertEquals(message, DTOentity.getMessage());
    }

    @Test
    void instantiateObjectEntity(){
        boolean status = true;
        Employee entity = new Employee(1, true, "Karel Jansen",1, Date.valueOf("1991-11-03"),"Software engineer");

        ResponseDTO DTOentity = new ResponseDTO(status, entity);

        assertEquals(status, DTOentity.getStatus());
        assertNotNull(DTOentity.getBody());
    }

    @Test
    void instantiateEmptyEntity(){

        ResponseDTO DTOentity = new ResponseDTO();

        assertFalse(DTOentity.getStatus());
        assertNull(DTOentity.getMessage());
        assertNull(DTOentity.getBody());
    }

    @Test
    void fillEmptyEntity(){
        boolean status = true;
        Employee entity = new Employee(1, true, "Karel Jansen",1, Date.valueOf("1991-11-03"),"Software engineer");
        String message = "responce message";

        ResponseDTO DTOentity = new ResponseDTO();

        DTOentity.setStatus(status);
        DTOentity.setBody(entity);
        DTOentity.setMessage(message);

        assertNotNull(DTOentity.getBody());
        assertTrue(DTOentity.getStatus());
        assertEquals(message, DTOentity.getMessage());
    }
}
