package com.HMPackage.baseResponse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    int pages;
    T response;
}
