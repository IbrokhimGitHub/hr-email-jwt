package uz.pdp.hremailjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hremailjwt.component.MostUsed;
import uz.pdp.hremailjwt.entity.Turnstile;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.repository.TurnstileRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class TurnstileService {

    @Autowired
    TurnstileRepository turnstileRepository;

    @Autowired
    MostUsed mostUsed;

    public ApiResponse comeToWork(){
        Turnstile turnstile=new Turnstile();

        turnstile.setCreatedBy(mostUsed.getCurrentEmployee().getUuid());
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        turnstile.setEnterDateTime(timestamp);
        turnstile.setStatus(true);
      return new ApiResponse("Entry time saved",true);
    }
    public ApiResponse outgoingFromWork(){
        Turnstile turnstile=new Turnstile();

        turnstile.setCreatedBy(mostUsed.getCurrentEmployee().getUuid());
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        turnstile.setExitDateTime(timestamp);
        turnstile.setStatus(false);
        return new ApiResponse("exit time saved",true);
    }
}
