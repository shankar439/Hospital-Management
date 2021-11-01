package com.HMPackage.baseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    @Builder.Default
    private int StatusCode=200;
    @Builder.Default
    private String StatusMessage="Status Success";
    private T Data;
    

}
