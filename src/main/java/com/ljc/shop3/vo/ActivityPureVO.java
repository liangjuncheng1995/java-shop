package com.ljc.shop3.vo;

import com.ljc.shop3.model.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor

public class ActivityPureVO {

    @Id
    private Long id;
    private String title;
    private String entranceImg; //活动入口的图片
    private Boolean online;
    private String remark;
    private Date startTime;
    private Date endTime;

    public ActivityPureVO(Activity activity) {
        BeanUtils.copyProperties(activity, this);
    }

    public ActivityPureVO(Object object) {
        BeanUtils.copyProperties(object, this);
    }
}
