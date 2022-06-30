package com.schedule.application.payload;

import com.schedule.domain.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserPayload {

    private String name;

    private String address;

    public static GetUserPayload of(User user) {
        return GetUserPayload.builder()
                             .name(user.getName())
                             .address(user.getAddress())
                             .build();
    }
}
