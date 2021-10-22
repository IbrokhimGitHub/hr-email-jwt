package uz.pdp.hremailjwt.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turnstile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  boolean status;

    @CreatedBy
    private UUID createdBy;
    @NotNull
    private Timestamp enterDateTime;   // ishga kirgan vaqti

    @NotNull
    private Timestamp exitDateTime; //ishdan ketgan vaqti

    //rahbar vaqt orasini tanlashi uchun vaqtning boshi
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;








}
