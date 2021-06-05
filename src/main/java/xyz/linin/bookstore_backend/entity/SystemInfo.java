package xyz.linin.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.linin.bookstore_backend.constants.SystemInfoKey;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "系统信息")
public class SystemInfo {
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy", Locale.ENGLISH);
    @Id
    @Enumerated(EnumType.STRING)
    private SystemInfoKey infoKey;

    @Column(nullable = false)
    // value是SQL关键字
    private String infoValue;

    @JsonIgnore
    public List<Date> getDate() throws ParseException {
        String[] dateStrList = infoValue.split(",");
        List<Date> result = new ArrayList<>();
        Date startDate = dateFormat.parse(dateStrList[0]);
        Date endDate = dateFormat.parse(dateStrList[1]);
        result.add(startDate);
        result.add(endDate);
        return result;
    }
}
